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
@Table(name = "pet_match")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PetMatch.findAll", query = "SELECT p FROM PetMatch p"),
    @NamedQuery(name = "PetMatch.findByIdPetMatch", query = "SELECT p FROM PetMatch p WHERE p.idPetMatch = :idPetMatch"),
    @NamedQuery(name = "PetMatch.findByFechaMatch", query = "SELECT p FROM PetMatch p WHERE p.fechaMatch = :fechaMatch"),
    @NamedQuery(name = "PetMatch.findByEstadoMatch", query = "SELECT p FROM PetMatch p WHERE p.estadoMatch = :estadoMatch"),
    @NamedQuery(name = "PetMatch.findByIdCliente", query = "SELECT p FROM PetMatch p WHERE p.idCliente = :idCliente"),
    @NamedQuery(name = "PetMatch.findByIdMascota", query = "SELECT p FROM PetMatch p WHERE p.idMascota = :idMascota"),
    @NamedQuery(name = "PetMatch.findByNombresMascota", query = "SELECT p FROM PetMatch p WHERE p.nombresMascota = :nombresMascota"),
    @NamedQuery(name = "PetMatch.findBySexo", query = "SELECT p FROM PetMatch p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "PetMatch.findByEdad", query = "SELECT p FROM PetMatch p WHERE p.edad = :edad"),
    @NamedQuery(name = "PetMatch.findByTipoMascota", query = "SELECT p FROM PetMatch p WHERE p.tipoMascota = :tipoMascota"),
    @NamedQuery(name = "PetMatch.findByRazaMascota", query = "SELECT p FROM PetMatch p WHERE p.razaMascota = :razaMascota"),
    @NamedQuery(name = "PetMatch.findByNombreVeterinaria", query = "SELECT p FROM PetMatch p WHERE p.nombreVeterinaria = :nombreVeterinaria"),
    @NamedQuery(name = "PetMatch.findByDireccionVeterinaria", query = "SELECT p FROM PetMatch p WHERE p.direccionVeterinaria = :direccionVeterinaria"),
    @NamedQuery(name = "PetMatch.findByTelefonoVeterinaria", query = "SELECT p FROM PetMatch p WHERE p.telefonoVeterinaria = :telefonoVeterinaria")})
public class PetMatch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Pet_Match")
    private int idPetMatch;
    @Column(name = "Fecha_Match")
    @Temporal(TemporalType.DATE)
    private Date fechaMatch;
    @Size(max = 10)
    @Column(name = "Estado_Match")
    private String estadoMatch;
    @Column(name = "Id_Cliente")
    private Integer idCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Mascota")
    private int idMascota;
    @Size(max = 15)
    @Column(name = "Nombres_Mascota")
    private String nombresMascota;
    @Size(max = 10)
    @Column(name = "Sexo")
    private String sexo;
    @Size(max = 25)
    @Column(name = "Edad")
    private String edad;
    @Size(max = 10)
    @Column(name = "Tipo_Mascota")
    private String tipoMascota;
    @Size(max = 10)
    @Column(name = "Raza_Mascota")
    private String razaMascota;
    @Size(max = 20)
    @Column(name = "Nombre_Veterinaria")
    private String nombreVeterinaria;
    @Size(max = 30)
    @Column(name = "Direccion_Veterinaria")
    private String direccionVeterinaria;
    @Column(name = "Telefono_Veterinaria")
    private Integer telefonoVeterinaria;

    public PetMatch() {
    }

    public int getIdPetMatch() {
        return idPetMatch;
    }

    public void setIdPetMatch(int idPetMatch) {
        this.idPetMatch = idPetMatch;
    }

    public Date getFechaMatch() {
        return fechaMatch;
    }

    public void setFechaMatch(Date fechaMatch) {
        this.fechaMatch = fechaMatch;
    }

    public String getEstadoMatch() {
        return estadoMatch;
    }

    public void setEstadoMatch(String estadoMatch) {
        this.estadoMatch = estadoMatch;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombresMascota() {
        return nombresMascota;
    }

    public void setNombresMascota(String nombresMascota) {
        this.nombresMascota = nombresMascota;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(String tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public String getRazaMascota() {
        return razaMascota;
    }

    public void setRazaMascota(String razaMascota) {
        this.razaMascota = razaMascota;
    }

    public String getNombreVeterinaria() {
        return nombreVeterinaria;
    }

    public void setNombreVeterinaria(String nombreVeterinaria) {
        this.nombreVeterinaria = nombreVeterinaria;
    }

    public String getDireccionVeterinaria() {
        return direccionVeterinaria;
    }

    public void setDireccionVeterinaria(String direccionVeterinaria) {
        this.direccionVeterinaria = direccionVeterinaria;
    }

    public Integer getTelefonoVeterinaria() {
        return telefonoVeterinaria;
    }

    public void setTelefonoVeterinaria(Integer telefonoVeterinaria) {
        this.telefonoVeterinaria = telefonoVeterinaria;
    }
    
}
