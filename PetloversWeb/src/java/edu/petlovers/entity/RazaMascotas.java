/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author wsbachiller
 */
@Entity
@Table(name = "raza_mascotas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RazaMascotas.findAll", query = "SELECT r FROM RazaMascotas r")})
public class RazaMascotas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Raza_Mascota")
    private Integer idRazaMascota;
    @Size(max = 10)
    @Column(name = "Raza_Mascota")
    private String razaMascota;
    @OneToMany(mappedBy = "idRazaMascota", fetch = FetchType.LAZY)
    private Collection<Mascotas> mascotasCollection;

    public RazaMascotas() {
    }

    public RazaMascotas(Integer idRazaMascota) {
        this.idRazaMascota = idRazaMascota;
    }

    public Integer getIdRazaMascota() {
        return idRazaMascota;
    }

    public void setIdRazaMascota(Integer idRazaMascota) {
        this.idRazaMascota = idRazaMascota;
    }

    public String getRazaMascota() {
        return razaMascota;
    }

    public void setRazaMascota(String razaMascota) {
        this.razaMascota = razaMascota;
    }

    @XmlTransient
    public Collection<Mascotas> getMascotasCollection() {
        return mascotasCollection;
    }

    public void setMascotasCollection(Collection<Mascotas> mascotasCollection) {
        this.mascotasCollection = mascotasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRazaMascota != null ? idRazaMascota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RazaMascotas)) {
            return false;
        }
        RazaMascotas other = (RazaMascotas) object;
        if ((this.idRazaMascota == null && other.idRazaMascota != null) || (this.idRazaMascota != null && !this.idRazaMascota.equals(other.idRazaMascota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.petlovers.entity.RazaMascotas[ idRazaMascota=" + idRazaMascota + " ]";
    }
    
}
