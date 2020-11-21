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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author HP
 */
@Entity
@Table(name = "tipo_servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoServicios.findAll", query = "SELECT t FROM TipoServicios t")})
public class TipoServicios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Tipo_Servicio")
    private Integer idTipoServicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 280)
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "Valor_Servicio")
    private Integer valorServicio;
    @OneToMany(mappedBy = "idTipoServicio", fetch = FetchType.LAZY)
    private Collection<Servicios> serviciosCollection;

    public TipoServicios() {
    }

    public TipoServicios(Integer idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public TipoServicios(Integer idTipoServicio, String descripcion) {
        this.idTipoServicio = idTipoServicio;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(Integer idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getValorServicio() {
        return valorServicio;
    }

    public void setValorServicio(Integer valorServicio) {
        this.valorServicio = valorServicio;
    }

    @XmlTransient
    public Collection<Servicios> getServiciosCollection() {
        return serviciosCollection;
    }

    public void setServiciosCollection(Collection<Servicios> serviciosCollection) {
        this.serviciosCollection = serviciosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoServicio != null ? idTipoServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoServicios)) {
            return false;
        }
        TipoServicios other = (TipoServicios) object;
        if ((this.idTipoServicio == null && other.idTipoServicio != null) || (this.idTipoServicio != null && !this.idTipoServicio.equals(other.idTipoServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.petlovers.entity.TipoServicios[ idTipoServicio=" + idTipoServicio + " ]";
    }
    
}
