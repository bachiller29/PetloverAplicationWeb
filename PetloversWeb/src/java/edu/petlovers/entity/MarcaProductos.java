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
 * @author HP
 */
@Entity
@Table(name = "marca_productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MarcaProductos.findAll", query = "SELECT m FROM MarcaProductos m")})
public class MarcaProductos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Marca_Producto")
    private Integer idMarcaProducto;
    @Size(max = 15)
    @Column(name = "Marca_Producto")
    private String marcaProducto;
    @OneToMany(mappedBy = "idMarcaProducto", fetch = FetchType.LAZY)
    private Collection<Productos> productosCollection;

    public MarcaProductos() {
    }

    public MarcaProductos(Integer idMarcaProducto) {
        this.idMarcaProducto = idMarcaProducto;
    }

    public Integer getIdMarcaProducto() {
        return idMarcaProducto;
    }

    public void setIdMarcaProducto(Integer idMarcaProducto) {
        this.idMarcaProducto = idMarcaProducto;
    }

    public String getMarcaProducto() {
        return marcaProducto;
    }

    public void setMarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
    }

    @XmlTransient
    public Collection<Productos> getProductosCollection() {
        return productosCollection;
    }

    public void setProductosCollection(Collection<Productos> productosCollection) {
        this.productosCollection = productosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMarcaProducto != null ? idMarcaProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MarcaProductos)) {
            return false;
        }
        MarcaProductos other = (MarcaProductos) object;
        if ((this.idMarcaProducto == null && other.idMarcaProducto != null) || (this.idMarcaProducto != null && !this.idMarcaProducto.equals(other.idMarcaProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.petlovers.entity.MarcaProductos[ idMarcaProducto=" + idMarcaProducto + " ]";
    }
    
}
