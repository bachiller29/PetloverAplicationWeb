package co.edu.petlovers.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "pet_matchs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PetMatchs.findAll", query = "SELECT p FROM PetMatchs p"),
    @NamedQuery(name = "PetMatchs.findByIdPetMatch", query = "SELECT p FROM PetMatchs p WHERE p.idPetMatch = :idPetMatch"),
    @NamedQuery(name = "PetMatchs.findByFechaMatch", query = "SELECT p FROM PetMatchs p WHERE p.fechaMatch = :fechaMatch"),
    @NamedQuery(name = "PetMatchs.findByObservaciones", query = "SELECT p FROM PetMatchs p WHERE p.observaciones = :observaciones"),
    @NamedQuery(name = "PetMatchs.findByEstadoMatch", query = "SELECT p FROM PetMatchs p WHERE p.estadoMatch = :estadoMatch")})
public class PetMatchs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Pet_Match")
    private Integer idPetMatch;
    @Column(name = "Fecha_Match")
    @Temporal(TemporalType.DATE)
    private Date fechaMatch;
    @Size(max = 100)
    @Column(name = "Observaciones")
    private String observaciones;
    @Size(max = 10)
    @Column(name = "Estado_Match")
    private String estadoMatch;
    @JoinColumn(name = "Id_Mascota", referencedColumnName = "Id_Mascota")
    @ManyToOne
    private Mascotas idMascota;
    @JoinColumn(name = "Id_Cliente", referencedColumnName = "Id_Cliente")
    @ManyToOne
    private Clientes idCliente;
    @JoinColumn(name = "Nit_Criadero", referencedColumnName = "Nit_Criadero")
    @ManyToOne
    private Criadero nitCriadero;
    @JoinColumn(name = "Id_Veterinaria", referencedColumnName = "Id_Veterinaria")
    @ManyToOne
    private Veterinarias idVeterinaria;

    public PetMatchs() {
    }

    public PetMatchs(Integer idPetMatch) {
        this.idPetMatch = idPetMatch;
    }

    public Integer getIdPetMatch() {
        return idPetMatch;
    }

    public void setIdPetMatch(Integer idPetMatch) {
        this.idPetMatch = idPetMatch;
    }

    public Date getFechaMatch() {
        return fechaMatch;
    }

    public void setFechaMatch(Date fechaMatch) {
        this.fechaMatch = fechaMatch;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstadoMatch() {
        return estadoMatch;
    }

    public void setEstadoMatch(String estadoMatch) {
        this.estadoMatch = estadoMatch;
    }

    public Mascotas getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Mascotas idMascota) {
        this.idMascota = idMascota;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    public Criadero getNitCriadero() {
        return nitCriadero;
    }

    public void setNitCriadero(Criadero nitCriadero) {
        this.nitCriadero = nitCriadero;
    }

    public Veterinarias getIdVeterinaria() {
        return idVeterinaria;
    }

    public void setIdVeterinaria(Veterinarias idVeterinaria) {
        this.idVeterinaria = idVeterinaria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPetMatch != null ? idPetMatch.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PetMatchs)) {
            return false;
        }
        PetMatchs other = (PetMatchs) object;
        if ((this.idPetMatch == null && other.idPetMatch != null) || (this.idPetMatch != null && !this.idPetMatch.equals(other.idPetMatch))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.petlovers.model.PetMatchs[ idPetMatch=" + idPetMatch + " ]";
    }
    
}
