package co.edu.petlovers.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "produc_client")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProducClient.findAll", query = "SELECT p FROM ProducClient p"),
    @NamedQuery(name = "ProducClient.findByIdCliente", query = "SELECT p FROM ProducClient p WHERE p.idCliente = :idCliente"),
    @NamedQuery(name = "ProducClient.findByNombres", query = "SELECT p FROM ProducClient p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "ProducClient.findByApellidos", query = "SELECT p FROM ProducClient p WHERE p.apellidos = :apellidos"),
    @NamedQuery(name = "ProducClient.findByBarrio", query = "SELECT p FROM ProducClient p WHERE p.barrio = :barrio"),
    @NamedQuery(name = "ProducClient.findByNumeroOrden", query = "SELECT p FROM ProducClient p WHERE p.numeroOrden = :numeroOrden"),
    @NamedQuery(name = "ProducClient.findByFechaOrden", query = "SELECT p FROM ProducClient p WHERE p.fechaOrden = :fechaOrden"),
    @NamedQuery(name = "ProducClient.findByIdProducto", query = "SELECT p FROM ProducClient p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "ProducClient.findByNombreProducto", query = "SELECT p FROM ProducClient p WHERE p.nombreProducto = :nombreProducto"),
    @NamedQuery(name = "ProducClient.findByDescripcion", query = "SELECT p FROM ProducClient p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "ProducClient.findByCantidad", query = "SELECT p FROM ProducClient p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "ProducClient.findByValorTotal", query = "SELECT p FROM ProducClient p WHERE p.valorTotal = :valorTotal")})
public class ProducClient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Cliente")
    private int idCliente;
    @Size(max = 25)
    @Column(name = "Nombres")
    private String nombres;
    @Size(max = 25)
    @Column(name = "Apellidos")
    private String apellidos;
    @Size(max = 15)
    @Column(name = "Barrio")
    private String barrio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Numero_Orden")
    private int numeroOrden;
    @Column(name = "Fecha_Orden")
    @Temporal(TemporalType.DATE)
    private Date fechaOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Producto")
    private int idProducto;
    @Size(max = 20)
    @Column(name = "Nombre_Producto")
    private String nombreProducto;
    @Size(max = 50)
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "Cantidad")
    private Integer cantidad;
    @Column(name = "Valor_Total")
    private Integer valorTotal;

    public ProducClient() {
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Integer valorTotal) {
        this.valorTotal = valorTotal;
    }
    
}
