package co.edu.petlovers.model;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicios.findAll", query = "SELECT s FROM Servicios s"),
    @NamedQuery(name = "Servicios.findByIdServicio", query = "SELECT s FROM Servicios s WHERE s.idServicio = :idServicio"),
    @NamedQuery(name = "Servicios.findByDescripcion", query = "SELECT s FROM Servicios s WHERE s.descripcion = :descripcion")})
public class Servicios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Servicio")
    private Integer idServicio;
    @Size(max = 280)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idServicio")
    private List<DetalleOrdenDePago> detalleOrdenDePagoList;
    @OneToMany(mappedBy = "idServicio")
    private List<Citas> citasList;
    @JoinColumn(name = "Nit_Criadero", referencedColumnName = "Nit_Criadero")
    @ManyToOne
    private Criadero nitCriadero;
    @JoinColumn(name = "Id_Tipo_Servicio", referencedColumnName = "Id_Tipo_Servicio")
    @ManyToOne
    private TipoServicios idTipoServicio;
    @JoinColumn(name = "Id_Empleado", referencedColumnName = "Id_Empleado")
    @ManyToOne
    private Empleados idEmpleado;

    public Servicios() {
    }

    public Servicios(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<DetalleOrdenDePago> getDetalleOrdenDePagoList() {
        return detalleOrdenDePagoList;
    }

    public void setDetalleOrdenDePagoList(List<DetalleOrdenDePago> detalleOrdenDePagoList) {
        this.detalleOrdenDePagoList = detalleOrdenDePagoList;
    }

    @XmlTransient
    public List<Citas> getCitasList() {
        return citasList;
    }

    public void setCitasList(List<Citas> citasList) {
        this.citasList = citasList;
    }

    public Criadero getNitCriadero() {
        return nitCriadero;
    }

    public void setNitCriadero(Criadero nitCriadero) {
        this.nitCriadero = nitCriadero;
    }

    public TipoServicios getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(TipoServicios idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
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
        hash += (idServicio != null ? idServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicios)) {
            return false;
        }
        Servicios other = (Servicios) object;
        if ((this.idServicio == null && other.idServicio != null) || (this.idServicio != null && !this.idServicio.equals(other.idServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.petlovers.model.Servicios[ idServicio=" + idServicio + " ]";
    }
    
}
