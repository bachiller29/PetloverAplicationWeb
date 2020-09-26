package co.edu.petlovers.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "provee_inve")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProveeInve.findAll", query = "SELECT p FROM ProveeInve p"),
    @NamedQuery(name = "ProveeInve.findByIdProducto", query = "SELECT p FROM ProveeInve p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "ProveeInve.findByNombreProducto", query = "SELECT p FROM ProveeInve p WHERE p.nombreProducto = :nombreProducto"),
    @NamedQuery(name = "ProveeInve.findByPrecioProducto", query = "SELECT p FROM ProveeInve p WHERE p.precioProducto = :precioProducto"),
    @NamedQuery(name = "ProveeInve.findByCodigoBarrasProducto", query = "SELECT p FROM ProveeInve p WHERE p.codigoBarrasProducto = :codigoBarrasProducto"),
    @NamedQuery(name = "ProveeInve.findByTipoProducto", query = "SELECT p FROM ProveeInve p WHERE p.tipoProducto = :tipoProducto"),
    @NamedQuery(name = "ProveeInve.findByMarcaProducto", query = "SELECT p FROM ProveeInve p WHERE p.marcaProducto = :marcaProducto"),
    @NamedQuery(name = "ProveeInve.findByNitProveedor", query = "SELECT p FROM ProveeInve p WHERE p.nitProveedor = :nitProveedor"),
    @NamedQuery(name = "ProveeInve.findByNombresProveedor", query = "SELECT p FROM ProveeInve p WHERE p.nombresProveedor = :nombresProveedor"),
    @NamedQuery(name = "ProveeInve.findByTelefonoProveedor", query = "SELECT p FROM ProveeInve p WHERE p.telefonoProveedor = :telefonoProveedor"),
    @NamedQuery(name = "ProveeInve.findByEmailProveedor", query = "SELECT p FROM ProveeInve p WHERE p.emailProveedor = :emailProveedor"),
    @NamedQuery(name = "ProveeInve.findByIdInventario", query = "SELECT p FROM ProveeInve p WHERE p.idInventario = :idInventario")})
public class ProveeInve implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Producto")
    private int idProducto;
    @Size(max = 20)
    @Column(name = "Nombre_Producto")
    private String nombreProducto;
    @Column(name = "Precio_Producto")
    private Integer precioProducto;
    @Column(name = "Codigo_Barras_Producto")
    private Integer codigoBarrasProducto;
    @Size(max = 15)
    @Column(name = "Tipo_Producto")
    private String tipoProducto;
    @Size(max = 15)
    @Column(name = "Marca_Producto")
    private String marcaProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Nit_Proveedor")
    private int nitProveedor;
    @Size(max = 20)
    @Column(name = "Nombres_Proveedor")
    private String nombresProveedor;
    @Column(name = "Telefono_Proveedor")
    private Integer telefonoProveedor;
    @Size(max = 40)
    @Column(name = "Email_Proveedor")
    private String emailProveedor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Inventario")
    private int idInventario;

    public ProveeInve() {
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
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

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getMarcaProducto() {
        return marcaProducto;
    }

    public void setMarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
    }

    public int getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(int nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public String getNombresProveedor() {
        return nombresProveedor;
    }

    public void setNombresProveedor(String nombresProveedor) {
        this.nombresProveedor = nombresProveedor;
    }

    public Integer getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(Integer telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getEmailProveedor() {
        return emailProveedor;
    }

    public void setEmailProveedor(String emailProveedor) {
        this.emailProveedor = emailProveedor;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }
    
}
