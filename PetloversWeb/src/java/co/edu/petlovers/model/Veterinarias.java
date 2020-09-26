package co.edu.petlovers.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "veterinarias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Veterinarias.findAll", query = "SELECT v FROM Veterinarias v"),
    @NamedQuery(name = "Veterinarias.findByIdVeterinaria", query = "SELECT v FROM Veterinarias v WHERE v.idVeterinaria = :idVeterinaria"),
    @NamedQuery(name = "Veterinarias.findByNombreVeterinaria", query = "SELECT v FROM Veterinarias v WHERE v.nombreVeterinaria = :nombreVeterinaria"),
    @NamedQuery(name = "Veterinarias.findByDireccionVeterinaria", query = "SELECT v FROM Veterinarias v WHERE v.direccionVeterinaria = :direccionVeterinaria"),
    @NamedQuery(name = "Veterinarias.findByTelefonoVeterinaria", query = "SELECT v FROM Veterinarias v WHERE v.telefonoVeterinaria = :telefonoVeterinaria"),
    @NamedQuery(name = "Veterinarias.findByEmailVeterinaria", query = "SELECT v FROM Veterinarias v WHERE v.emailVeterinaria = :emailVeterinaria")})
public class Veterinarias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Veterinaria")
    private Integer idVeterinaria;
    @Size(max = 20)
    @Column(name = "Nombre_Veterinaria")
    private String nombreVeterinaria;
    @Size(max = 30)
    @Column(name = "Direccion_Veterinaria")
    private String direccionVeterinaria;
    @Column(name = "Telefono_Veterinaria")
    private Integer telefonoVeterinaria;
    @Size(max = 20)
    @Column(name = "Email_Veterinaria")
    private String emailVeterinaria;
    @OneToMany(mappedBy = "idVeterinaria")
    private List<PetMatchs> petMatchsList;

    public Veterinarias() {
    }

    public Veterinarias(Integer idVeterinaria) {
        this.idVeterinaria = idVeterinaria;
    }

    public Integer getIdVeterinaria() {
        return idVeterinaria;
    }

    public void setIdVeterinaria(Integer idVeterinaria) {
        this.idVeterinaria = idVeterinaria;
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

    public String getEmailVeterinaria() {
        return emailVeterinaria;
    }

    public void setEmailVeterinaria(String emailVeterinaria) {
        this.emailVeterinaria = emailVeterinaria;
    }

    @XmlTransient
    public List<PetMatchs> getPetMatchsList() {
        return petMatchsList;
    }

    public void setPetMatchsList(List<PetMatchs> petMatchsList) {
        this.petMatchsList = petMatchsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVeterinaria != null ? idVeterinaria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Veterinarias)) {
            return false;
        }
        Veterinarias other = (Veterinarias) object;
        if ((this.idVeterinaria == null && other.idVeterinaria != null) || (this.idVeterinaria != null && !this.idVeterinaria.equals(other.idVeterinaria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.petlovers.model.Veterinarias[ idVeterinaria=" + idVeterinaria + " ]";
    }
    
}
