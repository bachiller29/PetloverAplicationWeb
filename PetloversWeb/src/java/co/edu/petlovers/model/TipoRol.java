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
@Table(name = "tipo_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoRol.findAll", query = "SELECT t FROM TipoRol t"),
    @NamedQuery(name = "TipoRol.findByIdTipoRol", query = "SELECT t FROM TipoRol t WHERE t.idTipoRol = :idTipoRol"),
    @NamedQuery(name = "TipoRol.findByNombreRol", query = "SELECT t FROM TipoRol t WHERE t.nombreRol = :nombreRol")})
public class TipoRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Tipo_Rol")
    private Integer idTipoRol;
    @Size(max = 20)
    @Column(name = "Nombre_Rol")
    private String nombreRol;
    @OneToMany(mappedBy = "idTipoRol")
    private List<Usuarios> usuariosList;

    public TipoRol() {
    }

    public TipoRol(Integer idTipoRol) {
        this.idTipoRol = idTipoRol;
    }

    public Integer getIdTipoRol() {
        return idTipoRol;
    }

    public void setIdTipoRol(Integer idTipoRol) {
        this.idTipoRol = idTipoRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoRol != null ? idTipoRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRol)) {
            return false;
        }
        TipoRol other = (TipoRol) object;
        if ((this.idTipoRol == null && other.idTipoRol != null) || (this.idTipoRol != null && !this.idTipoRol.equals(other.idTipoRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.petlovers.model.TipoRol[ idTipoRol=" + idTipoRol + " ]";
    }
    
}
