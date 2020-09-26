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
@Table(name = "cita_emple")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CitaEmple.findAll", query = "SELECT c FROM CitaEmple c"),
    @NamedQuery(name = "CitaEmple.findByIdCitas", query = "SELECT c FROM CitaEmple c WHERE c.idCitas = :idCitas"),
    @NamedQuery(name = "CitaEmple.findByIdCliente", query = "SELECT c FROM CitaEmple c WHERE c.idCliente = :idCliente"),
    @NamedQuery(name = "CitaEmple.findByFechaCita", query = "SELECT c FROM CitaEmple c WHERE c.fechaCita = :fechaCita"),
    @NamedQuery(name = "CitaEmple.findByHoraCita", query = "SELECT c FROM CitaEmple c WHERE c.horaCita = :horaCita"),
    @NamedQuery(name = "CitaEmple.findByEstadoCita", query = "SELECT c FROM CitaEmple c WHERE c.estadoCita = :estadoCita"),
    @NamedQuery(name = "CitaEmple.findByIdServicio", query = "SELECT c FROM CitaEmple c WHERE c.idServicio = :idServicio"),
    @NamedQuery(name = "CitaEmple.findByNombreServicio", query = "SELECT c FROM CitaEmple c WHERE c.nombreServicio = :nombreServicio"),
    @NamedQuery(name = "CitaEmple.findByValorServicio", query = "SELECT c FROM CitaEmple c WHERE c.valorServicio = :valorServicio"),
    @NamedQuery(name = "CitaEmple.findByIdEmpleado", query = "SELECT c FROM CitaEmple c WHERE c.idEmpleado = :idEmpleado"),
    @NamedQuery(name = "CitaEmple.findByNombres", query = "SELECT c FROM CitaEmple c WHERE c.nombres = :nombres"),
    @NamedQuery(name = "CitaEmple.findByApellidos", query = "SELECT c FROM CitaEmple c WHERE c.apellidos = :apellidos"),
    @NamedQuery(name = "CitaEmple.findByTipoEmpleado", query = "SELECT c FROM CitaEmple c WHERE c.tipoEmpleado = :tipoEmpleado")})
public class CitaEmple implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Citas")
    private int idCitas;
    @Column(name = "Id_Cliente")
    private Integer idCliente;
    @Column(name = "Fecha_Cita")
    @Temporal(TemporalType.DATE)
    private Date fechaCita;
    @Column(name = "Hora_Cita")
    @Temporal(TemporalType.TIME)
    private Date horaCita;
    @Size(max = 20)
    @Column(name = "Estado_Cita")
    private String estadoCita;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Servicio")
    private int idServicio;
    @Size(max = 20)
    @Column(name = "Nombre_Servicio")
    private String nombreServicio;
    @Column(name = "Valor_Servicio")
    private Integer valorServicio;
    @Column(name = "Id_Empleado")
    private Integer idEmpleado;
    @Size(max = 25)
    @Column(name = "Nombres")
    private String nombres;
    @Size(max = 25)
    @Column(name = "Apellidos")
    private String apellidos;
    @Size(max = 60)
    @Column(name = "tipo_empleado")
    private String tipoEmpleado;

    public CitaEmple() {
    }

    public int getIdCitas() {
        return idCitas;
    }

    public void setIdCitas(int idCitas) {
        this.idCitas = idCitas;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
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

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public Integer getValorServicio() {
        return valorServicio;
    }

    public void setValorServicio(Integer valorServicio) {
        this.valorServicio = valorServicio;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }
    
}
