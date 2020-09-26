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
@Table(name = "proveedores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedores.findAll", query = "SELECT p FROM Proveedores p"),
    @NamedQuery(name = "Proveedores.findByNitProveedor", query = "SELECT p FROM Proveedores p WHERE p.nitProveedor = :nitProveedor"),
    @NamedQuery(name = "Proveedores.findByNombresProveedor", query = "SELECT p FROM Proveedores p WHERE p.nombresProveedor = :nombresProveedor"),
    @NamedQuery(name = "Proveedores.findByDireccionProveedor", query = "SELECT p FROM Proveedores p WHERE p.direccionProveedor = :direccionProveedor"),
    @NamedQuery(name = "Proveedores.findByCiudadProveedor", query = "SELECT p FROM Proveedores p WHERE p.ciudadProveedor = :ciudadProveedor"),
    @NamedQuery(name = "Proveedores.findByEmailProveedor", query = "SELECT p FROM Proveedores p WHERE p.emailProveedor = :emailProveedor"),
    @NamedQuery(name = "Proveedores.findByTelefonoProveedor", query = "SELECT p FROM Proveedores p WHERE p.telefonoProveedor = :telefonoProveedor")})
public class Proveedores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Nit_Proveedor")
    private Integer nitProveedor;
    @Size(max = 20)
    @Column(name = "Nombres_Proveedor")
    private String nombresProveedor;
    @Size(max = 40)
    @Column(name = "Direccion_Proveedor")
    private String direccionProveedor;
    @Size(max = 15)
    @Column(name = "Ciudad_Proveedor")
    private String ciudadProveedor;
    @Size(max = 40)
    @Column(name = "Email_Proveedor")
    private String emailProveedor;
    @Column(name = "Telefono_Proveedor")
    private Integer telefonoProveedor;
    @OneToMany(mappedBy = "nitProveedor")
    private List<Productos> productosList;

    public Proveedores() {
    }

    public Proveedores(Integer nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public Integer getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(Integer nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public String getNombresProveedor() {
        return nombresProveedor;
    }

    public void setNombresProveedor(String nombresProveedor) {
        this.nombresProveedor = nombresProveedor;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

    public String getCiudadProveedor() {
        return ciudadProveedor;
    }

    public void setCiudadProveedor(String ciudadProveedor) {
        this.ciudadProveedor = ciudadProveedor;
    }

    public String getEmailProveedor() {
        return emailProveedor;
    }

    public void setEmailProveedor(String emailProveedor) {
        this.emailProveedor = emailProveedor;
    }

    public Integer getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(Integer telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
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
        hash += (nitProveedor != null ? nitProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedores)) {
            return false;
        }
        Proveedores other = (Proveedores) object;
        if ((this.nitProveedor == null && other.nitProveedor != null) || (this.nitProveedor != null && !this.nitProveedor.equals(other.nitProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.petlovers.model.Proveedores[ nitProveedor=" + nitProveedor + " ]";
    }
    
}
