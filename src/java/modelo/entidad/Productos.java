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
@Table(name = "PRODUCTOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p")
    , @NamedQuery(name = "Productos.findByCodProd", query = "SELECT p FROM Productos p WHERE p.codProd = :codProd")
    , @NamedQuery(name = "Productos.findByNomProd", query = "SELECT p FROM Productos p WHERE p.nomProd = :nomProd")
    , @NamedQuery(name = "Productos.findByPrecProd", query = "SELECT p FROM Productos p WHERE p.precProd = :precProd")
    , @NamedQuery(name = "Productos.findByCatgProd", query = "SELECT p FROM Productos p WHERE p.catgProd = :catgProd")
    , @NamedQuery(name = "Productos.findByEstProd", query = "SELECT p FROM Productos p WHERE p.estProd = :estProd")})
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_PROD")
    private BigDecimal codProd;
    @Size(max = 50)
    @Column(name = "NOM_PROD")
    private String nomProd;
    @Size(max = 50)
    @Column(name = "PREC_PROD")
    private String precProd;
    @Size(max = 50)
    @Column(name = "CATG_PROD")
    private String catgProd;
    @Size(max = 2)
    @Column(name = "EST_PROD")
    private String estProd;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productosCodProd")
    private List<Ventas> ventasList;

    public Productos() {
    }

    public Productos(BigDecimal codProd) {
        this.codProd = codProd;
    }

    public BigDecimal getCodProd() {
        return codProd;
    }

    public void setCodProd(BigDecimal codProd) {
        this.codProd = codProd;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public String getPrecProd() {
        return precProd;
    }

    public void setPrecProd(String precProd) {
        this.precProd = precProd;
    }

    public String getCatgProd() {
        return catgProd;
    }

    public void setCatgProd(String catgProd) {
        this.catgProd = catgProd;
    }

    public String getEstProd() {
        return estProd;
    }

    public void setEstProd(String estProd) {
        this.estProd = estProd;
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
        hash += (codProd != null ? codProd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.codProd == null && other.codProd != null) || (this.codProd != null && !this.codProd.equals(other.codProd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidad.Productos[ codProd=" + codProd + " ]";
    }
    
}
