/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "servicios")
@NamedQueries({
    @NamedQuery(name = "Servicios.findAll", query = "SELECT s FROM Servicios s")})
public class Servicios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Servicio")
    private Integer idServicio;
    @Size(max = 30)
    @Column(name = "Nombre_Servicio")
    private String nombreServicio;
    @Size(max = 280)
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "Valor_Servicio")
    private Integer valorServicio;
    @OneToMany(mappedBy = "idServicio", fetch = FetchType.EAGER)
    private Collection<Citas> citasCollection;
    @JoinColumn(name = "Nit_Criadero", referencedColumnName = "Nit_Criadero")
    @ManyToOne(fetch = FetchType.EAGER)
    private Criadero nitCriadero;
    @JoinColumn(name = "Id_Tipo_Servicio", referencedColumnName = "Id_Tipo_Servicio")
    @ManyToOne(fetch = FetchType.EAGER)
    private TipoServicios idTipoServicio;
    @JoinColumn(name = "Id_Empleado", referencedColumnName = "Id_Empleado")
    @ManyToOne(fetch = FetchType.EAGER)
    private Empleados idEmpleado;

    public Servicios() {
    }

    public Servicios(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getValorServicio() {
        return valorServicio;
    }

    public void setValorServicio(Integer valorServicio) {
        this.valorServicio = valorServicio;
    }

    public Collection<Citas> getCitasCollection() {
        return citasCollection;
    }

    public void setCitasCollection(Collection<Citas> citasCollection) {
        this.citasCollection = citasCollection;
    }

    public Criadero getNitCriadero() {
        return nitCriadero;
    }

    public void setNitCriadero(Criadero nitCriadero) {
        this.nitCriadero = nitCriadero;
    }

    public TipoServicios getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(TipoServicios idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public Empleados getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleados idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServicio != null ? idServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicios)) {
            return false;
        }
        Servicios other = (Servicios) object;
        if ((this.idServicio == null && other.idServicio != null) || (this.idServicio != null && !this.idServicio.equals(other.idServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.petlovers.entity.Servicios[ idServicio=" + idServicio + " ]";
    }
    
}
