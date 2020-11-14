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
@Table(name = "entrada_productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntradaProductos.findAll", query = "SELECT e FROM EntradaProductos e")})
public class EntradaProductos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Entrada_Productos")
    private Integer idEntradaProductos;
    @Column(name = "Cantidad_Entrada")
    private Integer cantidadEntrada;
    @Column(name = "Fecha_Entrada")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrada;
    @OneToMany(mappedBy = "idEntradaProductos", fetch = FetchType.LAZY)
    private Collection<Inventario> inventarioCollection;

    public EntradaProductos() {
    }

    public EntradaProductos(Integer idEntradaProductos) {
        this.idEntradaProductos = idEntradaProductos;
    }

    public Integer getIdEntradaProductos() {
        return idEntradaProductos;
    }

    public void setIdEntradaProductos(Integer idEntradaProductos) {
        this.idEntradaProductos = idEntradaProductos;
    }

    public Integer getCantidadEntrada() {
        return cantidadEntrada;
    }

    public void setCantidadEntrada(Integer cantidadEntrada) {
        this.cantidadEntrada = cantidadEntrada;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
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
        hash += (idEntradaProductos != null ? idEntradaProductos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntradaProductos)) {
            return false;
        }
        EntradaProductos other = (EntradaProductos) object;
        if ((this.idEntradaProductos == null && other.idEntradaProductos != null) || (this.idEntradaProductos != null && !this.idEntradaProductos.equals(other.idEntradaProductos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.petlovers.entity.EntradaProductos[ idEntradaProductos=" + idEntradaProductos + " ]";
    }
    
}
