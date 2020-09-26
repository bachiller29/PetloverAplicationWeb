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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "salida_productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalidaProductos.findAll", query = "SELECT s FROM SalidaProductos s"),
    @NamedQuery(name = "SalidaProductos.findByIdSalidaProductos", query = "SELECT s FROM SalidaProductos s WHERE s.idSalidaProductos = :idSalidaProductos"),
    @NamedQuery(name = "SalidaProductos.findByCantidadSalida", query = "SELECT s FROM SalidaProductos s WHERE s.cantidadSalida = :cantidadSalida"),
    @NamedQuery(name = "SalidaProductos.findByFechaSalida", query = "SELECT s FROM SalidaProductos s WHERE s.fechaSalida = :fechaSalida")})
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
    @OneToMany(mappedBy = "idSalidaProductos")
    private List<Inventario> inventarioList;

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
    public List<Inventario> getInventarioList() {
        return inventarioList;
    }

    public void setInventarioList(List<Inventario> inventarioList) {
        this.inventarioList = inventarioList;
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
        return "co.edu.petlovers.model.SalidaProductos[ idSalidaProductos=" + idSalidaProductos + " ]";
    }
    
}
