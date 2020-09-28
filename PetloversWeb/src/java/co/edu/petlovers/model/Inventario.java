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
@Table(name = "inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventario.findAll", query = "SELECT i FROM Inventario i"),
    @NamedQuery(name = "Inventario.findByIdInventario", query = "SELECT i FROM Inventario i WHERE i.idInventario = :idInventario"),
    @NamedQuery(name = "Inventario.findByCantidadProductos", query = "SELECT i FROM Inventario i WHERE i.cantidadProductos = :cantidadProductos")})
public class Inventario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Inventario")
    private Integer idInventario;
    @Column(name = "Cantidad_Productos")
    private Integer cantidadProductos;
    @JoinColumn(name = "Id_Admin", referencedColumnName = "Id_Admin")
    @ManyToOne
    private Administradores idAdmin;
    @JoinColumn(name = "Id_Salida_Productos", referencedColumnName = "Id_Salida_Productos")
    @ManyToOne
    private SalidaProductos idSalidaProductos;
    @JoinColumn(name = "Id_Entrada_Productos", referencedColumnName = "Id_Entrada_Productos")
    @ManyToOne
    private EntradaProductos idEntradaProductos;
    @OneToMany(mappedBy = "idInventario")
    private List<Productos> productosList;

    public Inventario() {
    }

    public Inventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public Integer getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public Integer getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(Integer cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public Administradores getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Administradores idAdmin) {
        this.idAdmin = idAdmin;
    }

    public SalidaProductos getIdSalidaProductos() {
        return idSalidaProductos;
    }

    public void setIdSalidaProductos(SalidaProductos idSalidaProductos) {
        this.idSalidaProductos = idSalidaProductos;
    }

    public EntradaProductos getIdEntradaProductos() {
        return idEntradaProductos;
    }

    public void setIdEntradaProductos(EntradaProductos idEntradaProductos) {
        this.idEntradaProductos = idEntradaProductos;
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
        hash += (idInventario != null ? idInventario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventario)) {
            return false;
        }
        Inventario other = (Inventario) object;
        if ((this.idInventario == null && other.idInventario != null) || (this.idInventario != null && !this.idInventario.equals(other.idInventario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.petlovers.model.Inventario[ idInventario=" + idInventario + " ]";
    }
    
}