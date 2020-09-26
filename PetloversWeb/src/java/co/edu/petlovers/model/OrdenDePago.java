package co.edu.petlovers.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "orden_de_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenDePago.findAll", query = "SELECT o FROM OrdenDePago o"),
    @NamedQuery(name = "OrdenDePago.findByNumeroOrden", query = "SELECT o FROM OrdenDePago o WHERE o.numeroOrden = :numeroOrden"),
    @NamedQuery(name = "OrdenDePago.findByFechaOrden", query = "SELECT o FROM OrdenDePago o WHERE o.fechaOrden = :fechaOrden"),
    @NamedQuery(name = "OrdenDePago.findByHoraOrden", query = "SELECT o FROM OrdenDePago o WHERE o.horaOrden = :horaOrden")})
public class OrdenDePago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Numero_Orden")
    private Integer numeroOrden;
    @Column(name = "Fecha_Orden")
    @Temporal(TemporalType.DATE)
    private Date fechaOrden;
    @Column(name = "Hora_Orden")
    @Temporal(TemporalType.TIME)
    private Date horaOrden;
    @OneToMany(mappedBy = "numeroOrden")
    private List<DetalleOrdenDePago> detalleOrdenDePagoList;
    @JoinColumn(name = "Id_Cliente", referencedColumnName = "Id_Cliente")
    @ManyToOne
    private Clientes idCliente;
    @JoinColumn(name = "Id_Empleado", referencedColumnName = "Id_Empleado")
    @ManyToOne
    private Empleados idEmpleado;

    public OrdenDePago() {
    }

    public OrdenDePago(Integer numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Integer getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(Integer numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public Date getHoraOrden() {
        return horaOrden;
    }

    public void setHoraOrden(Date horaOrden) {
        this.horaOrden = horaOrden;
    }

    @XmlTransient
    public List<DetalleOrdenDePago> getDetalleOrdenDePagoList() {
        return detalleOrdenDePagoList;
    }

    public void setDetalleOrdenDePagoList(List<DetalleOrdenDePago> detalleOrdenDePagoList) {
        this.detalleOrdenDePagoList = detalleOrdenDePagoList;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    public Empleados getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleados idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroOrden != null ? numeroOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenDePago)) {
            return false;
        }
        OrdenDePago other = (OrdenDePago) object;
        if ((this.numeroOrden == null && other.numeroOrden != null) || (this.numeroOrden != null && !this.numeroOrden.equals(other.numeroOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.petlovers.model.OrdenDePago[ numeroOrden=" + numeroOrden + " ]";
    }
    
}
