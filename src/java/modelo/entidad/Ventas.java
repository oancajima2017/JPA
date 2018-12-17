/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PeruServiFast
 */
@Entity
@Table(name = "VENTAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ventas.findAll", query = "SELECT v FROM Ventas v")
    , @NamedQuery(name = "Ventas.findByCodVenta", query = "SELECT v FROM Ventas v WHERE v.codVenta = :codVenta")
    , @NamedQuery(name = "Ventas.findByCantVenta", query = "SELECT v FROM Ventas v WHERE v.cantVenta = :cantVenta")
    , @NamedQuery(name = "Ventas.findByEstVenta", query = "SELECT v FROM Ventas v WHERE v.estVenta = :estVenta")})
public class Ventas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_VENTA")
    //    GENERAR CODIGO AUTOINCREMENTADO
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VENTAS_SEQ")
    @SequenceGenerator(sequenceName = "ventas_seq", allocationSize = 1, name = "VENTAS_SEQ")
    private BigDecimal codVenta;
    @Column(name = "CANT_VENTA")
    private BigInteger cantVenta;
    @Size(max = 2)
    @Column(name = "EST_VENTA")
    private String estVenta = "A";
    @JoinColumn(name = "PERSONAL_COD_PER", referencedColumnName = "COD_PER")
    @ManyToOne(optional = false)
    private Personal personalCodPer;
    @JoinColumn(name = "PRODUCTOS_COD_PROD", referencedColumnName = "COD_PROD")
    @ManyToOne(optional = false)
    private Productos productosCodProd;

    public Ventas() {
    }

    public Ventas(BigDecimal codVenta) {
        this.codVenta = codVenta;
    }

    public BigDecimal getCodVenta() {
        return codVenta;
    }

    public void setCodVenta(BigDecimal codVenta) {
        this.codVenta = codVenta;
    }

    public BigInteger getCantVenta() {
        return cantVenta;
    }

    public void setCantVenta(BigInteger cantVenta) {
        this.cantVenta = cantVenta;
    }

    public String getEstVenta() {
        return estVenta;
    }

    public void setEstVenta(String estVenta) {
        this.estVenta = estVenta;
    }

    public Personal getPersonalCodPer() {
        return personalCodPer;
    }

    public void setPersonalCodPer(Personal personalCodPer) {
        this.personalCodPer = personalCodPer;
    }

    public Productos getProductosCodProd() {
        return productosCodProd;
    }

    public void setProductosCodProd(Productos productosCodProd) {
        this.productosCodProd = productosCodProd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codVenta != null ? codVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ventas)) {
            return false;
        }
        Ventas other = (Ventas) object;
        if ((this.codVenta == null && other.codVenta != null) || (this.codVenta != null && !this.codVenta.equals(other.codVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidad.Ventas[ codVenta=" + codVenta + " ]";
    }

}
