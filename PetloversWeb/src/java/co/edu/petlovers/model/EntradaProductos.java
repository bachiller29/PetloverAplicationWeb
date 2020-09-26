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
@Table(name = "entrada_productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntradaProductos.findAll", query = "SELECT e FROM EntradaProductos e"),
    @NamedQuery(name = "EntradaProductos.findByIdEntradaProductos", query = "SELECT e FROM EntradaProductos e WHERE e.idEntradaProductos = :idEntradaProductos"),
    @NamedQuery(name = "EntradaProductos.findByCantidadEntrada", query = "SELECT e FROM EntradaProductos e WHERE e.cantidadEntrada = :cantidadEntrada"),
    @NamedQuery(name = "EntradaProductos.findByFechaEntrada", query = "SELECT e FROM EntradaProductos e WHERE e.fechaEntrada = :fechaEntrada")})
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
    @OneToMany(mappedBy = "idEntradaProductos")
    private List<Inventario> inventarioList;

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
    public List<Inventario> getInventarioList() {
        return inventarioList;
    }

    public void setInventarioList(List<Inventario> inventarioList) {
        this.inventarioList = inventarioList;
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
        return "co.edu.petlovers.model.EntradaProductos[ idEntradaProductos=" + idEntradaProductos + " ]";
    }
    
}
