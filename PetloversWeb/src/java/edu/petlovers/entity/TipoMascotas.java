/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "tipo_mascotas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMascotas.findAll", query = "SELECT t FROM TipoMascotas t")})
public class TipoMascotas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Tipo_Mascota")
    private Integer idTipoMascota;
    @Size(max = 10)
    @Column(name = "Tipo_Mascota")
    private String tipoMascota;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoMascota", fetch = FetchType.LAZY)
    private Collection<Mascotas> mascotasCollection;

    public TipoMascotas() {
    }

    public TipoMascotas(Integer idTipoMascota) {
        this.idTipoMascota = idTipoMascota;
    }

    public Integer getIdTipoMascota() {
        return idTipoMascota;
    }

    public void setIdTipoMascota(Integer idTipoMascota) {
        this.idTipoMascota = idTipoMascota;
    }

    public String getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(String tipoMascota) {
        this.tipoMascota = tipoMascota;
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
        hash += (idTipoMascota != null ? idTipoMascota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMascotas)) {
            return false;
        }
        TipoMascotas other = (TipoMascotas) object;
        if ((this.idTipoMascota == null && other.idTipoMascota != null) || (this.idTipoMascota != null && !this.idTipoMascota.equals(other.idTipoMascota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.petlovers.entity.TipoMascotas[ idTipoMascota=" + idTipoMascota + " ]";
    }
    
}
