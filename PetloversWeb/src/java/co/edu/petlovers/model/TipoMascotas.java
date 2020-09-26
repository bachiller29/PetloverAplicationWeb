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
@Table(name = "tipo_mascotas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMascotas.findAll", query = "SELECT t FROM TipoMascotas t"),
    @NamedQuery(name = "TipoMascotas.findByIdTipoMascota", query = "SELECT t FROM TipoMascotas t WHERE t.idTipoMascota = :idTipoMascota"),
    @NamedQuery(name = "TipoMascotas.findByTipoMascota", query = "SELECT t FROM TipoMascotas t WHERE t.tipoMascota = :tipoMascota")})
public class TipoMascotas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Tipo_Mascota")
    private Integer idTipoMascota;
    @Size(max = 10)
    @Column(name = "Tipo_Mascota")
    private String tipoMascota;
    @OneToMany(mappedBy = "idTipoMascota")
    private List<Mascotas> mascotasList;

    public TipoMascotas() {
    }

    public TipoMascotas(Integer idTipoMascota) {
        this.idTipoMascota = idTipoMascota;
    }

    public Integer getIdTipoMascota() {
        return idTipoMascota;
    }

    public void setIdTipoMascota(Integer idTipoMascota) {
        this.idTipoMascota = idTipoMascota;
    }

    public String getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(String tipoMascota) {
        this.tipoMascota = tipoMascota;
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
        hash += (idTipoMascota != null ? idTipoMascota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMascotas)) {
            return false;
        }
        TipoMascotas other = (TipoMascotas) object;
        if ((this.idTipoMascota == null && other.idTipoMascota != null) || (this.idTipoMascota != null && !this.idTipoMascota.equals(other.idTipoMascota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.petlovers.model.TipoMascotas[ idTipoMascota=" + idTipoMascota + " ]";
    }
    
}
