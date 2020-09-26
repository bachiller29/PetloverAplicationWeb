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
@Table(name = "tipo_productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoProductos.findAll", query = "SELECT t FROM TipoProductos t"),
    @NamedQuery(name = "TipoProductos.findByIdTipoProducto", query = "SELECT t FROM TipoProductos t WHERE t.idTipoProducto = :idTipoProducto"),
    @NamedQuery(name = "TipoProductos.findByTipoProducto", query = "SELECT t FROM TipoProductos t WHERE t.tipoProducto = :tipoProducto")})
public class TipoProductos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Tipo_Producto")
    private Integer idTipoProducto;
    @Size(max = 15)
    @Column(name = "Tipo_Producto")
    private String tipoProducto;
    @OneToMany(mappedBy = "idTipoProducto")
    private List<Productos> productosList;

    public TipoProductos() {
    }

    public TipoProductos(Integer idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public Integer getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(Integer idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    @XmlTransient
    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoProducto != null ? idTipoProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoProductos)) {
            return false;
        }
        TipoProductos other = (TipoProductos) object;
        if ((this.idTipoProducto == null && other.idTipoProducto != null) || (this.idTipoProducto != null && !this.idTipoProducto.equals(other.idTipoProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.petlovers.model.TipoProductos[ idTipoProducto=" + idTipoProducto + " ]";
    }
    
}
