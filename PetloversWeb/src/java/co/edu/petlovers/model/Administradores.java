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
@Table(name = "administradores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administradores.findAll", query = "SELECT a FROM Administradores a"),
    @NamedQuery(name = "Administradores.findByIdAdmin", query = "SELECT a FROM Administradores a WHERE a.idAdmin = :idAdmin")})
public class Administradores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Admin")
    private Integer idAdmin;
    @OneToMany(mappedBy = "idAdmin")
    private List<Criadero> criaderoList;
    @OneToMany(mappedBy = "idAdmin")
    private List<Inventario> inventarioList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "Id_Usuario")
    @ManyToOne
    private Usuarios idUsuario;

    public Administradores() {
    }

    public Administradores(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    @XmlTransient
    public List<Criadero> getCriaderoList() {
        return criaderoList;
    }

    public void setCriaderoList(List<Criadero> criaderoList) {
        this.criaderoList = criaderoList;
    }

    @XmlTransient
    public List<Inventario> getInventarioList() {
        return inventarioList;
    }

    public void setInventarioList(List<Inventario> inventarioList) {
        this.inventarioList = inventarioList;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdmin != null ? idAdmin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administradores)) {
            return false;
        }
        Administradores other = (Administradores) object;
        if ((this.idAdmin == null && other.idAdmin != null) || (this.idAdmin != null && !this.idAdmin.equals(other.idAdmin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.petlovers.model.Administradores[ idAdmin=" + idAdmin + " ]";
    }
    
}
