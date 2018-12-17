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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "ZONA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zona.findAll", query = "SELECT z FROM Zona z")
    , @NamedQuery(name = "Zona.findByCodZona", query = "SELECT z FROM Zona z WHERE z.codZona = :codZona")
    , @NamedQuery(name = "Zona.findByNomZona", query = "SELECT z FROM Zona z WHERE z.nomZona = :nomZona")
    , @NamedQuery(name = "Zona.findByEstZona", query = "SELECT z FROM Zona z WHERE z.estZona = :estZona")
    , @NamedQuery(name = "Zona.findByCantMesaZona", query = "SELECT z FROM Zona z WHERE z.cantMesaZona = :cantMesaZona")})
public class Zona implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_ZONA")
    //    GENERAR CODIGO AUTOINCREMENTADO
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ZONA_SEQ")
    @SequenceGenerator(sequenceName = "zona_seq", allocationSize = 1, name = "ZONA_SEQ")
    private BigDecimal codZona;
    @Size(max = 50)
    @Column(name = "NOM_ZONA")
    private String nomZona;
    @Size(max = 2)
    @Column(name = "EST_ZONA")
    private String estZona = "A";
    @Size(max = 10)
    @Column(name = "CANT_MESA_ZONA")
    private String cantMesaZona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zonaCodZona")
    private List<Personal> personalList;

    public Zona() {
    }

    public Zona(BigDecimal codZona) {
        this.codZona = codZona;
    }

    public BigDecimal getCodZona() {
        return codZona;
    }

    public void setCodZona(BigDecimal codZona) {
        this.codZona = codZona;
    }

    public String getNomZona() {
        return nomZona;
    }

    public void setNomZona(String nomZona) {
        this.nomZona = nomZona;
    }

    public String getEstZona() {
        return estZona;
    }

    public void setEstZona(String estZona) {
        this.estZona = estZona;
    }

    public String getCantMesaZona() {
        return cantMesaZona;
    }

    public void setCantMesaZona(String cantMesaZona) {
        this.cantMesaZona = cantMesaZona;
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
        hash += (codZona != null ? codZona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zona)) {
            return false;
        }
        Zona other = (Zona) object;
        if ((this.codZona == null && other.codZona != null) || (this.codZona != null && !this.codZona.equals(other.codZona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidad.Zona[ codZona=" + codZona + " ]";
    }

}
