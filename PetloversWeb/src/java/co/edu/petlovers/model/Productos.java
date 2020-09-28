package co.edu.petlovers.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p"),
    @NamedQuery(name = "Productos.findByIdProducto", query = "SELECT p FROM Productos p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "Productos.findByNombreProducto", query = "SELECT p FROM Productos p WHERE p.nombreProducto = :nombreProducto"),
    @NamedQuery(name = "Productos.findByDescripcion", query = "SELECT p FROM Productos p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Productos.findByTamanoProducto", query = "SELECT p FROM Productos p WHERE p.tamanoProducto = :tamanoProducto"),
    @NamedQuery(name = "Productos.findByColorProducto", query = "SELECT p FROM Productos p WHERE p.colorProducto = :colorProducto"),
    @NamedQuery(name = "Productos.findBySaborProducto", query = "SELECT p FROM Productos p WHERE p.saborProducto = :saborProducto"),
    @NamedQuery(name = "Productos.findByPrecioProducto", query = "SELECT p FROM Productos p WHERE p.precioProducto = :precioProducto"),
    @NamedQuery(name = "Productos.findByCodigoBarrasProducto", query = "SELECT p FROM Productos p WHERE p.codigoBarrasProducto = :codigoBarrasProducto")})
public class Productos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
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
    @OneToMany(mappedBy = "idProducto")
    private List<DetalleOrdenDePago> detalleOrdenDePagoList;
    @JoinColumn(name = "Id_Tipo_Producto", referencedColumnName = "Id_Tipo_Producto")
    @ManyToOne
    private TipoProductos idTipoProducto;
    @JoinColumn(name = "Nit_Proveedor", referencedColumnName = "Nit_Proveedor")
    @ManyToOne
    private Integer nitProveedor;
    @JoinColumn(name = "Id_Inventario", referencedColumnName = "Id_Inventario")
    @ManyToOne
    private Inventario idInventario;
    @JoinColumn(name = "Id_Marca_Producto", referencedColumnName = "Id_Marca_Producto")
    @ManyToOne
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

    @XmlTransient
    public List<DetalleOrdenDePago> getDetalleOrdenDePagoList() {
        return detalleOrdenDePagoList;
    }

    public void setDetalleOrdenDePagoList(List<DetalleOrdenDePago> detalleOrdenDePagoList) {
        this.detalleOrdenDePagoList = detalleOrdenDePagoList;
    }

    public TipoProductos getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(TipoProductos idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public Integer getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(Integer nitProveedor) {
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
        return "co.edu.petlovers.model.Productos[ idProducto=" + idProducto + " ]";
    }
    
}
