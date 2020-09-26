package co.edu.petlovers.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "detalle_orden_de_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleOrdenDePago.findAll", query = "SELECT d FROM DetalleOrdenDePago d"),
    @NamedQuery(name = "DetalleOrdenDePago.findByIddetalleorden", query = "SELECT d FROM DetalleOrdenDePago d WHERE d.iddetalleorden = :iddetalleorden"),
    @NamedQuery(name = "DetalleOrdenDePago.findByDescripcion", query = "SELECT d FROM DetalleOrdenDePago d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "DetalleOrdenDePago.findByCantidad", query = "SELECT d FROM DetalleOrdenDePago d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DetalleOrdenDePago.findByValorUnitario", query = "SELECT d FROM DetalleOrdenDePago d WHERE d.valorUnitario = :valorUnitario"),
    @NamedQuery(name = "DetalleOrdenDePago.findByIva", query = "SELECT d FROM DetalleOrdenDePago d WHERE d.iva = :iva"),
    @NamedQuery(name = "DetalleOrdenDePago.findByPorcentajeDescuento", query = "SELECT d FROM DetalleOrdenDePago d WHERE d.porcentajeDescuento = :porcentajeDescuento"),
    @NamedQuery(name = "DetalleOrdenDePago.findByValorDescuento", query = "SELECT d FROM DetalleOrdenDePago d WHERE d.valorDescuento = :valorDescuento"),
    @NamedQuery(name = "DetalleOrdenDePago.findByValorTotal", query = "SELECT d FROM DetalleOrdenDePago d WHERE d.valorTotal = :valorTotal")})
public class DetalleOrdenDePago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_detalle_orden")
    private Integer iddetalleorden;
    @Size(max = 50)
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "Cantidad")
    private Integer cantidad;
    @Column(name = "Valor_Unitario")
    private Integer valorUnitario;
    @Column(name = "Iva")
    private Integer iva;
    @Column(name = "Porcentaje_Descuento")
    private Integer porcentajeDescuento;
    @Column(name = "Valor_Descuento")
    private Integer valorDescuento;
    @Column(name = "Valor_Total")
    private Integer valorTotal;
    @JoinColumn(name = "Id_Producto", referencedColumnName = "Id_Producto")
    @ManyToOne
    private Productos idProducto;
    @JoinColumn(name = "Id_Servicio", referencedColumnName = "Id_Servicio")
    @ManyToOne
    private Servicios idServicio;
    @JoinColumn(name = "Numero_Orden", referencedColumnName = "Numero_Orden")
    @ManyToOne
    private OrdenDePago numeroOrden;

    public DetalleOrdenDePago() {
    }

    public DetalleOrdenDePago(Integer iddetalleorden) {
        this.iddetalleorden = iddetalleorden;
    }

    public Integer getIddetalleorden() {
        return iddetalleorden;
    }

    public void setIddetalleorden(Integer iddetalleorden) {
        this.iddetalleorden = iddetalleorden;
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

    public Integer getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Integer valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getIva() {
        return iva;
    }

    public void setIva(Integer iva) {
        this.iva = iva;
    }

    public Integer getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(Integer porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public Integer getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(Integer valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public Integer getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Integer valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    public Servicios getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Servicios idServicio) {
        this.idServicio = idServicio;
    }

    public OrdenDePago getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(OrdenDePago numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalleorden != null ? iddetalleorden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleOrdenDePago)) {
            return false;
        }
        DetalleOrdenDePago other = (DetalleOrdenDePago) object;
        if ((this.iddetalleorden == null && other.iddetalleorden != null) || (this.iddetalleorden != null && !this.iddetalleorden.equals(other.iddetalleorden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.petlovers.model.DetalleOrdenDePago[ iddetalleorden=" + iddetalleorden + " ]";
    }
    
}
