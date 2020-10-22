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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "citas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Citas.findAll", query = "SELECT c FROM Citas c"),
    @NamedQuery(name = "Citas.findByIdCitas", query = "SELECT c FROM Citas c WHERE c.idCitas = :idCitas"),
    @NamedQuery(name = "Citas.findByFechaCita", query = "SELECT c FROM Citas c WHERE c.fechaCita = :fechaCita"),
    @NamedQuery(name = "Citas.findByHoraCita", query = "SELECT c FROM Citas c WHERE c.horaCita = :horaCita"),
    @NamedQuery(name = "Citas.findByEstadoCita", query = "SELECT c FROM Citas c WHERE c.estadoCita = :estadoCita")})
public class Citas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Citas")
    private Integer idCitas;
    @Column(name = "Fecha_Cita")
    @Temporal(TemporalType.DATE)
    private Date fechaCita;
    @Column(name = "Hora_Cita")
    @Temporal(TemporalType.TIME)
    private Date horaCita;
    @Size(max = 20)
    @Column(name = "Estado_Cita")
    private String estadoCita;
    @OneToMany(mappedBy = "idCitas")
    private List<Cronograma> cronogramaList;
    @JoinColumn(name = "Id_Cliente", referencedColumnName = "Id_Cliente")
    @ManyToOne
    private Clientes idCliente;
    @JoinColumn(name = "Id_Servicio", referencedColumnName = "Id_Servicio")
    @ManyToOne
    private Servicios idServicio;

    public Citas() {
    }

    public Citas(Integer idCitas) {
        this.idCitas = idCitas;
    }

    public Integer getIdCitas() {
        return idCitas;
    }

    public void setIdCitas(Integer idCitas) {
        this.idCitas = idCitas;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Date getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(Date horaCita) {
        this.horaCita = horaCita;
    }

    public String getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(String estadoCita) {
        this.estadoCita = estadoCita;
    }

    @XmlTransient
    public List<Cronograma> getCronogramaList() {
        return cronogramaList;
    }

    public void setCronogramaList(List<Cronograma> cronogramaList) {
        this.cronogramaList = cronogramaList;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    public Servicios getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Servicios idServicio) {
        this.idServicio = idServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCitas != null ? idCitas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citas)) {
            return false;
        }
        Citas other = (Citas) object;
        if ((this.idCitas == null && other.idCitas != null) || (this.idCitas != null && !this.idCitas.equals(other.idCitas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.petlovers.model.Citas[ idCitas=" + idCitas + " ]";
    }

}
