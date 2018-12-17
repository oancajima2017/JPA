package controlador;

import modelo.entidad.Personal;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import java.io.IOException;
import modelo.dao.PersonalFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("personalController")
@SessionScoped
public class PersonalController implements Serializable {

    @EJB
    private modelo.dao.PersonalFacade ejbFacade;
    private List<Personal> items = null;
    private Personal selected = new Personal();
    private String user, pass;

    public String iniciarSesion() throws Exception {
        try {
            FacesContext contex = FacesContext.getCurrentInstance();
            Personal u = ejbFacade.validarUsuario(selected);
            if (u != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", u);
                contex.getExternalContext().redirect("faces/vista/ventas/List.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("usuario o contraseña incorrecta"));
            }
        } catch (IOException e) {
            throw e;
        }
        return null;
    }

    public void cerrarSesion() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        FacesContext contex = FacesContext.getCurrentInstance();
        contex.getExternalContext().redirect("/BarSys");
    }

    public static boolean _isLogin() {
        Personal us = (Personal) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return us != null;
    }

    public void validarSesion() throws Exception {
        if (!_isLogin()) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/BarSys");
        }
    }

    public void validarLogin() throws Exception {
        if (_isLogin()) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/BarSys/faces/vista/ventas/List.xhtml");
        }
    }

    public PersonalController() {
    }

    public Personal getSelected() {
        return selected;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setSelected(Personal selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PersonalFacade getFacade() {
        return ejbFacade;
    }

    public Personal prepareCreate() {
        selected = new Personal();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Vista").getString("PersonalCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Vista").getString("PersonalUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Vista").getString("PersonalDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Personal> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Vista").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Vista").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Personal getPersonal(java.math.BigDecimal id) {
        return getFacade().find(id);
    }

    public List<Personal> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Personal> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Personal.class)
    public static class PersonalControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonalController controller = (PersonalController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personalController");
            return controller.getPersonal(getKey(value));
        }

        java.math.BigDecimal getKey(String value) {
            java.math.BigDecimal key;
            key = new java.math.BigDecimal(value);
            return key;
        }

        String getStringKey(java.math.BigDecimal value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Personal) {
                Personal o = (Personal) object;
                return getStringKey(o.getCodPer());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Personal.class.getName()});
                return null;
            }
        }

    }

}
