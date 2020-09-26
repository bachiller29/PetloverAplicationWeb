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
@Table(name = "raza_mascotas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RazaMascotas.findAll", query = "SELECT r FROM RazaMascotas r"),
    @NamedQuery(name = "RazaMascotas.findByIdRazaMascota", query = "SELECT r FROM RazaMascotas r WHERE r.idRazaMascota = :idRazaMascota"),
    @NamedQuery(name = "RazaMascotas.findByRazaMascota", query = "SELECT r FROM RazaMascotas r WHERE r.razaMascota = :razaMascota")})
public class RazaMascotas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Raza_Mascota")
    private Integer idRazaMascota;
    @Size(max = 10)
    @Column(name = "Raza_Mascota")
    private String razaMascota;
    @OneToMany(mappedBy = "idRazaMascota")
    private List<Mascotas> mascotasList;

    public RazaMascotas() {
    }

    public RazaMascotas(Integer idRazaMascota) {
        this.idRazaMascota = idRazaMascota;
    }

    public Integer getIdRazaMascota() {
        return idRazaMascota;
    }

    public void setIdRazaMascota(Integer idRazaMascota) {
        this.idRazaMascota = idRazaMascota;
    }

    public String getRazaMascota() {
        return razaMascota;
    }

    public void setRazaMascota(String razaMascota) {
        this.razaMascota = razaMascota;
    }

    @XmlTransient
    public List<Mascotas> getMascotasList() {
        return mascotasList;
    }

    public void setMascotasList(List<Mascotas> mascotasList) {
        this.mascotasList = mascotasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRazaMascota != null ? idRazaMascota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RazaMascotas)) {
            return false;
        }
        RazaMascotas other = (RazaMascotas) object;
        if ((this.idRazaMascota == null && other.idRazaMascota != null) || (this.idRazaMascota != null && !this.idRazaMascota.equals(other.idRazaMascota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.petlovers.model.RazaMascotas[ idRazaMascota=" + idRazaMascota + " ]";
    }
    
}
