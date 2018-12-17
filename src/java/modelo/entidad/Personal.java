/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author PeruServiFast
 */
@Entity
@Table(name = "PERSONAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personal.findAll", query = "SELECT p FROM Personal p")
    , @NamedQuery(name = "Personal.findByCodPer", query = "SELECT p FROM Personal p WHERE p.codPer = :codPer")
    , @NamedQuery(name = "Personal.findByNomPer", query = "SELECT p FROM Personal p WHERE p.nomPer = :nomPer")
    , @NamedQuery(name = "Personal.findByApePer", query = "SELECT p FROM Personal p WHERE p.apePer = :apePer")
    , @NamedQuery(name = "Personal.findByDniPer", query = "SELECT p FROM Personal p WHERE p.dniPer = :dniPer")
    , @NamedQuery(name = "Personal.findBySexPer", query = "SELECT p FROM Personal p WHERE p.sexPer = :sexPer")
    , @NamedQuery(name = "Personal.findByEstPer", query = "SELECT p FROM Personal p WHERE p.estPer = :estPer")
    , @NamedQuery(name = "Personal.findByUserPer", query = "SELECT p FROM Personal p WHERE p.userPer = :userPer")
    , @NamedQuery(name = "Personal.findByPassPer", query = "SELECT p FROM Personal p WHERE p.passPer = :passPer")})
public class Personal implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_PER")
    private BigDecimal codPer;
    @Size(max = 50)
    @Column(name = "NOM_PER")
    private String nomPer;
    @Size(max = 50)
    @Column(name = "APE_PER")
    private String apePer;
    @Size(max = 8)
    @Column(name = "DNI_PER")
    private String dniPer;
    @Size(max = 2)
    @Column(name = "SEX_PER")
    private String sexPer;
    @Size(max = 2)
    @Column(name = "EST_PER")
    private String estPer;
    @Size(max = 50)
    @Column(name = "USER_PER")
    private String userPer;
    @Size(max = 50)
    @Column(name = "PASS_PER")
    private String passPer;
    @JoinColumn(name = "MODALIDAD_COD_MOD", referencedColumnName = "COD_MOD")
    @ManyToOne(optional = false)
    private Modalidad modalidadCodMod;
    @JoinColumn(name = "ZONA_COD_ZONA", referencedColumnName = "COD_ZONA")
    @ManyToOne(optional = false)
    private Zona zonaCodZona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personalCodPer")
    private List<Ventas> ventasList;

    public Personal() {
    }

    public Personal(BigDecimal codPer) {
        this.codPer = codPer;
    }

    public BigDecimal getCodPer() {
        return codPer;
    }

    public void setCodPer(BigDecimal codPer) {
        this.codPer = codPer;
    }

    public String getNomPer() {
        return nomPer;
    }

    public void setNomPer(String nomPer) {
        this.nomPer = nomPer;
    }

    public String getApePer() {
        return apePer;
    }

    public void setApePer(String apePer) {
        this.apePer = apePer;
    }

    public String getDniPer() {
        return dniPer;
    }

    public void setDniPer(String dniPer) {
        this.dniPer = dniPer;
    }

    public String getSexPer() {
        return sexPer;
    }

    public void setSexPer(String sexPer) {
        this.sexPer = sexPer;
    }

    public String getEstPer() {
        return estPer;
    }

    public void setEstPer(String estPer) {
        this.estPer = estPer;
    }

    public String getUserPer() {
        return userPer;
    }

    public void setUserPer(String userPer) {
        this.userPer = userPer;
    }

    public String getPassPer() {
        return passPer;
    }

    public void setPassPer(String passPer) {
        this.passPer = passPer;
    }

    public Modalidad getModalidadCodMod() {
        return modalidadCodMod;
    }

    public void setModalidadCodMod(Modalidad modalidadCodMod) {
        this.modalidadCodMod = modalidadCodMod;
    }

    public Zona getZonaCodZona() {
        return zonaCodZona;
    }

    public void setZonaCodZona(Zona zonaCodZona) {
        this.zonaCodZona = zonaCodZona;
    }

    @XmlTransient
    public List<Ventas> getVentasList() {
        return ventasList;
    }

    public void setVentasList(List<Ventas> ventasList) {
        this.ventasList = ventasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPer != null ? codPer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personal)) {
            return false;
        }
        Personal other = (Personal) object;
        if ((this.codPer == null && other.codPer != null) || (this.codPer != null && !this.codPer.equals(other.codPer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidad.Personal[ codPer=" + codPer + " ]";
    }
    
}
