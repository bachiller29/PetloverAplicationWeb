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
@Table(name = "mascotas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mascotas.findAll", query = "SELECT m FROM Mascotas m"),
    @NamedQuery(name = "Mascotas.findByIdMascota", query = "SELECT m FROM Mascotas m WHERE m.idMascota = :idMascota"),
    @NamedQuery(name = "Mascotas.findBySexo", query = "SELECT m FROM Mascotas m WHERE m.sexo = :sexo"),
    @NamedQuery(name = "Mascotas.findByNombresMascota", query = "SELECT m FROM Mascotas m WHERE m.nombresMascota = :nombresMascota"),
    @NamedQuery(name = "Mascotas.findByFechaDeNacimiento", query = "SELECT m FROM Mascotas m WHERE m.fechaDeNacimiento = :fechaDeNacimiento"),
    @NamedQuery(name = "Mascotas.findByEdad", query = "SELECT m FROM Mascotas m WHERE m.edad = :edad"),
    @NamedQuery(name = "Mascotas.findByColor", query = "SELECT m FROM Mascotas m WHERE m.color = :color"),
    @NamedQuery(name = "Mascotas.findByEsterilizado", query = "SELECT m FROM Mascotas m WHERE m.esterilizado = :esterilizado")})
public class Mascotas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Mascota")
    private Integer idMascota;
    @Size(max = 10)
    @Column(name = "Sexo")
    private String sexo;
    @Size(max = 15)
    @Column(name = "Nombres_Mascota")
    private String nombresMascota;
    @Column(name = "Fecha_De_Nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaDeNacimiento;
    @Size(max = 25)
    @Column(name = "Edad")
    private String edad;
    @Size(max = 25)
    @Column(name = "Color")
    private String color;
    @Size(max = 5)
    @Column(name = "Esterilizado")
    private String esterilizado;
    @OneToMany(mappedBy = "idMascota")
    private List<PetMatchs> petMatchsList;
    @JoinColumn(name = "Id_Tipo_Mascota", referencedColumnName = "Id_Tipo_Mascota")
    @ManyToOne
    private TipoMascotas idTipoMascota;
    @JoinColumn(name = "Id_Raza_Mascota", referencedColumnName = "Id_Raza_Mascota")
    @ManyToOne
    private RazaMascotas idRazaMascota;
    @JoinColumn(name = "Id_Cliente", referencedColumnName = "Id_Cliente")
    @ManyToOne
    private Clientes idCliente;

    public Mascotas() {
    }

    public Mascotas(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNombresMascota() {
        return nombresMascota;
    }

    public void setNombresMascota(String nombresMascota) {
        this.nombresMascota = nombresMascota;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEsterilizado() {
        return esterilizado;
    }

    public void setEsterilizado(String esterilizado) {
        this.esterilizado = esterilizado;
    }

    @XmlTransient
    public List<PetMatchs> getPetMatchsList() {
        return petMatchsList;
    }

    public void setPetMatchsList(List<PetMatchs> petMatchsList) {
        this.petMatchsList = petMatchsList;
    }

    public TipoMascotas getIdTipoMascota() {
        return idTipoMascota;
    }

    public void setIdTipoMascota(TipoMascotas idTipoMascota) {
        this.idTipoMascota = idTipoMascota;
    }

    public RazaMascotas getIdRazaMascota() {
        return idRazaMascota;
    }

    public void setIdRazaMascota(RazaMascotas idRazaMascota) {
        this.idRazaMascota = idRazaMascota;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMascota != null ? idMascota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mascotas)) {
            return false;
        }
        Mascotas other = (Mascotas) object;
        if ((this.idMascota == null && other.idMascota != null) || (this.idMascota != null && !this.idMascota.equals(other.idMascota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.petlovers.model.Mascotas[ idMascota=" + idMascota + " ]";
    }
    
}
