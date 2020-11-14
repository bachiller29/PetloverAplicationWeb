/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wsbachiller
 */
@Entity
@Table(name = "salida_productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalidaProductos.findAll", query = "SELECT s FROM SalidaProductos s")})
public class SalidaProductos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Salida_Productos")
    private Integer idSalidaProductos;
    @Column(name = "Cantidad_Salida")
    private Integer cantidadSalida;
    @Column(name = "Fecha_Salida")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @OneToMany(mappedBy = "idSalidaProductos", fetch = FetchType.LAZY)
    private Collection<Inventario> inventarioCollection;

    public SalidaProductos() {
    }

    public SalidaProductos(Integer idSalidaProductos) {
        this.idSalidaProductos = idSalidaProductos;
    }

    public Integer getIdSalidaProductos() {
        return idSalidaProductos;
    }

    public void setIdSalidaProductos(Integer idSalidaProductos) {
        this.idSalidaProductos = idSalidaProductos;
    }

    public Integer getCantidadSalida() {
        return cantidadSalida;
    }

    public void setCantidadSalida(Integer cantidadSalida) {
        this.cantidadSalida = cantidadSalida;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    @XmlTransient
    public Collection<Inventario> getInventarioCollection() {
        return inventarioCollection;
    }

    public void setInventarioCollection(Collection<Inventario> inventarioCollection) {
        this.inventarioCollection = inventarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSalidaProductos != null ? idSalidaProductos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalidaProductos)) {
            return false;
        }
        SalidaProductos other = (SalidaProductos) object;
        if ((this.idSalidaProductos == null && other.idSalidaProductos != null) || (this.idSalidaProductos != null && !this.idSalidaProductos.equals(other.idSalidaProductos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.petlovers.entity.SalidaProductos[ idSalidaProductos=" + idSalidaProductos + " ]";
    }
    
}
