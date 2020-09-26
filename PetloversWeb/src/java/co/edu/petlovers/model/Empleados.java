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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "empleados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleados.findAll", query = "SELECT e FROM Empleados e"),
    @NamedQuery(name = "Empleados.findByIdEmpleado", query = "SELECT e FROM Empleados e WHERE e.idEmpleado = :idEmpleado")})
public class Empleados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Empleado")
    private Integer idEmpleado;
    @OneToMany(mappedBy = "idEmpleado")
    private List<OrdenDePago> ordenDePagoList;
    @OneToMany(mappedBy = "idEmpleado")
    private List<Cronograma> cronogramaList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "Id_Usuario")
    @ManyToOne
    private Usuarios idUsuario;
    @JoinColumn(name = "Id_Tipo_Empleado", referencedColumnName = "Id_Tipo_Empleado")
    @ManyToOne
    private TipoEmpleados idTipoEmpleado;
    @OneToMany(mappedBy = "idEmpleado")
    private List<Servicios> serviciosList;

    public Empleados() {
    }

    public Empleados(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @XmlTransient
    public List<OrdenDePago> getOrdenDePagoList() {
        return ordenDePagoList;
    }

    public void setOrdenDePagoList(List<OrdenDePago> ordenDePagoList) {
        this.ordenDePagoList = ordenDePagoList;
    }

    @XmlTransient
    public List<Cronograma> getCronogramaList() {
        return cronogramaList;
    }

    public void setCronogramaList(List<Cronograma> cronogramaList) {
        this.cronogramaList = cronogramaList;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TipoEmpleados getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(TipoEmpleados idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

    @XmlTransient
    public List<Servicios> getServiciosList() {
        return serviciosList;
    }

    public void setServiciosList(List<Servicios> serviciosList) {
        this.serviciosList = serviciosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleados)) {
            return false;
        }
        Empleados other = (Empleados) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.petlovers.model.Empleados[ idEmpleado=" + idEmpleado + " ]";
    }
    
}
