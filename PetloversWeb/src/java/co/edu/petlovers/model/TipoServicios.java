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
@Table(name = "tipo_servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoServicios.findAll", query = "SELECT t FROM TipoServicios t"),
    @NamedQuery(name = "TipoServicios.findByIdTipoServicio", query = "SELECT t FROM TipoServicios t WHERE t.idTipoServicio = :idTipoServicio"),
    @NamedQuery(name = "TipoServicios.findByNombreServicio", query = "SELECT t FROM TipoServicios t WHERE t.nombreServicio = :nombreServicio"),
    @NamedQuery(name = "TipoServicios.findByValorServicio", query = "SELECT t FROM TipoServicios t WHERE t.valorServicio = :valorServicio")})
public class TipoServicios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Tipo_Servicio")
    private Integer idTipoServicio;
    @Size(max = 20)
    @Column(name = "Nombre_Servicio")
    private String nombreServicio;
    @Column(name = "Valor_Servicio")
    private Integer valorServicio;
    @OneToMany(mappedBy = "idTipoServicio")
    private List<Servicios> serviciosList;

    public TipoServicios() {
    }

    public TipoServicios(Integer idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public Integer getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(Integer idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
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
        hash += (idTipoServicio != null ? idTipoServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoServicios)) {
            return false;
        }
        TipoServicios other = (TipoServicios) object;
        if ((this.idTipoServicio == null && other.idTipoServicio != null) || (this.idTipoServicio != null && !this.idTipoServicio.equals(other.idTipoServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.petlovers.model.TipoServicios[ idTipoServicio=" + idTipoServicio + " ]";
    }
    
}
