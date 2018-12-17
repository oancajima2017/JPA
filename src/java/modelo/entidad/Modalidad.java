/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "MODALIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modalidad.findAll", query = "SELECT m FROM Modalidad m")
    , @NamedQuery(name = "Modalidad.findByCodMod", query = "SELECT m FROM Modalidad m WHERE m.codMod = :codMod")
    , @NamedQuery(name = "Modalidad.findByNomCarMod", query = "SELECT m FROM Modalidad m WHERE m.nomCarMod = :nomCarMod")
    , @NamedQuery(name = "Modalidad.findByMonSuelMod", query = "SELECT m FROM Modalidad m WHERE m.monSuelMod = :monSuelMod")
    , @NamedQuery(name = "Modalidad.findByEstMod", query = "SELECT m FROM Modalidad m WHERE m.estMod = :estMod")})
public class Modalidad implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_MOD")
    private BigDecimal codMod;
    @Size(max = 50)
    @Column(name = "NOM_CAR_MOD")
    private String nomCarMod;
    @Column(name = "MON_SUEL_MOD")
    private BigInteger monSuelMod;
    @Size(max = 2)
    @Column(name = "EST_MOD")
    private String estMod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modalidadCodMod")
    private List<Personal> personalList;

    public Modalidad() {
    }

    public Modalidad(BigDecimal codMod) {
        this.codMod = codMod;
    }

    public BigDecimal getCodMod() {
        return codMod;
    }

    public void setCodMod(BigDecimal codMod) {
        this.codMod = codMod;
    }

    public String getNomCarMod() {
        return nomCarMod;
    }

    public void setNomCarMod(String nomCarMod) {
        this.nomCarMod = nomCarMod;
    }

    public BigInteger getMonSuelMod() {
        return monSuelMod;
    }

    public void setMonSuelMod(BigInteger monSuelMod) {
        this.monSuelMod = monSuelMod;
    }

    public String getEstMod() {
        return estMod;
    }

    public void setEstMod(String estMod) {
        this.estMod = estMod;
    }

    @XmlTransient
    public List<Personal> getPersonalList() {
        return personalList;
    }

    public void setPersonalList(List<Personal> personalList) {
        this.personalList = personalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codMod != null ? codMod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modalidad)) {
            return false;
        }
        Modalidad other = (Modalidad) object;
        if ((this.codMod == null && other.codMod != null) || (this.codMod != null && !this.codMod.equals(other.codMod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidad.Modalidad[ codMod=" + codMod + " ]";
    }
    
}
