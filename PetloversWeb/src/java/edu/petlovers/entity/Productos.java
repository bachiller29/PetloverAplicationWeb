/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "productos")
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p")})
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Producto")
    private Integer idProducto;
    @Size(max = 20)
    @Column(name = "Nombre_Producto")
    private String nombreProducto;
    @Size(max = 30)
    @Column(name = "Descripcion")
    private String descripcion;
    @Size(max = 10)
    @Column(name = "Tamano_Producto")
    private String tamanoProducto;
    @Size(max = 10)
    @Column(name = "Color_Producto")
    private String colorProducto;
    @Size(max = 10)
    @Column(name = "Sabor_Producto")
    private String saborProducto;
    @Column(name = "Precio_Producto")
    private Integer precioProducto;
    @Column(name = "Codigo_Barras_Producto")
    private Integer codigoBarrasProducto;
    @JoinColumn(name = "Id_Tipo_Producto", referencedColumnName = "Id_Tipo_Producto")
    @ManyToOne(fetch = FetchType.EAGER)
    private TipoProductos idTipoProducto;
    @JoinColumn(name = "Nit_Proveedor", referencedColumnName = "Nit_Proveedor")
    @ManyToOne(fetch = FetchType.EAGER)
    private Proveedores nitProveedor;
    @JoinColumn(name = "Id_Inventario", referencedColumnName = "Id_Inventario")
    @ManyToOne(fetch = FetchType.EAGER)
    private Inventario idInventario;
    @JoinColumn(name = "Id_Marca_Producto", referencedColumnName = "Id_Marca_Producto")
    @ManyToOne(fetch = FetchType.EAGER)
    private MarcaProductos idMarcaProducto;

    public Productos() {
    }

    public Productos(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTamanoProducto() {
        return tamanoProducto;
    }

    public void setTamanoProducto(String tamanoProducto) {
        this.tamanoProducto = tamanoProducto;
    }

    public String getColorProducto() {
        return colorProducto;
    }

    public void setColorProducto(String colorProducto) {
        this.colorProducto = colorProducto;
    }

    public String getSaborProducto() {
        return saborProducto;
    }

    public void setSaborProducto(String saborProducto) {
        this.saborProducto = saborProducto;
    }

    public Integer getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Integer precioProducto) {
        this.precioProducto = precioProducto;
    }

    public Integer getCodigoBarrasProducto() {
        return codigoBarrasProducto;
    }

    public void setCodigoBarrasProducto(Integer codigoBarrasProducto) {
        this.codigoBarrasProducto = codigoBarrasProducto;
    }

    public TipoProductos getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(TipoProductos idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public Proveedores getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(Proveedores nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public Inventario getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Inventario idInventario) {
        this.idInventario = idInventario;
    }

    public MarcaProductos getIdMarcaProducto() {
        return idMarcaProducto;
    }

    public void setIdMarcaProducto(MarcaProductos idMarcaProducto) {
        this.idMarcaProducto = idMarcaProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.petlovers.entity.Productos[ idProducto=" + idProducto + " ]";
    }
    
}
