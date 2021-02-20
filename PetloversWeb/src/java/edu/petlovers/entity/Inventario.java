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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "inventario")
@NamedQueries({
    @NamedQuery(name = "Inventario.findAll", query = "SELECT i FROM Inventario i")})
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Inventario")
    private Integer idInventario;
    @Column(name = "Cantidad_Productos")
    private Integer cantidadProductos;
    @JoinColumn(name = "Id_Admin", referencedColumnName = "Id_Admin")
    @ManyToOne(fetch = FetchType.EAGER)
    private Administradores idAdmin;
    @JoinColumn(name = "Id_Salida_Productos", referencedColumnName = "Id_Salida_Productos")
    @ManyToOne(fetch = FetchType.EAGER)
    private SalidaProductos idSalidaProductos;
    @JoinColumn(name = "Id_Entrada_Productos", referencedColumnName = "Id_Entrada_Productos")
    @ManyToOne(fetch = FetchType.EAGER)
    private EntradaProductos idEntradaProductos;
    @OneToMany(mappedBy = "idInventario", fetch = FetchType.EAGER)
    private Collection<Productos> productosCollection;

    public Inventario() {
    }

    public Inventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public Integer getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public Integer getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(Integer cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public Administradores getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Administradores idAdmin) {
        this.idAdmin = idAdmin;
    }

    public SalidaProductos getIdSalidaProductos() {
        return idSalidaProductos;
    }

    public void setIdSalidaProductos(SalidaProductos idSalidaProductos) {
        this.idSalidaProductos = idSalidaProductos;
    }

    public EntradaProductos getIdEntradaProductos() {
        return idEntradaProductos;
    }

    public void setIdEntradaProductos(EntradaProductos idEntradaProductos) {
        this.idEntradaProductos = idEntradaProductos;
    }

    public Collection<Productos> getProductosCollection() {
        return productosCollection;
    }

    public void setProductosCollection(Collection<Productos> productosCollection) {
        this.productosCollection = productosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInventario != null ? idInventario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventario)) {
            return false;
        }
        Inventario other = (Inventario) object;
        if ((this.idInventario == null && other.idInventario != null) || (this.idInventario != null && !this.idInventario.equals(other.idInventario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.petlovers.entity.Inventario[ idInventario=" + idInventario + " ]";
    }
    
}
