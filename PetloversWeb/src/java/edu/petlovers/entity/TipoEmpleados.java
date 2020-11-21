/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "tipo_empleados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEmpleados.findAll", query = "SELECT t FROM TipoEmpleados t")})
public class TipoEmpleados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Tipo_Empleado")
    private Integer idTipoEmpleado;
    @Size(max = 60)
    @Column(name = "Tipo_empleado")
    private String tipoempleado;
    @OneToMany(mappedBy = "idTipoEmpleado", fetch = FetchType.LAZY)
    private Collection<Empleados> empleadosCollection;

    public TipoEmpleados() {
    }

    public TipoEmpleados(Integer idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

    public Integer getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(Integer idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

    public String getTipoempleado() {
        return tipoempleado;
    }

    public void setTipoempleado(String tipoempleado) {
        this.tipoempleado = tipoempleado;
    }

    @XmlTransient
    public Collection<Empleados> getEmpleadosCollection() {
        return empleadosCollection;
    }

    public void setEmpleadosCollection(Collection<Empleados> empleadosCollection) {
        this.empleadosCollection = empleadosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoEmpleado != null ? idTipoEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEmpleados)) {
            return false;
        }
        TipoEmpleados other = (TipoEmpleados) object;
        if ((this.idTipoEmpleado == null && other.idTipoEmpleado != null) || (this.idTipoEmpleado != null && !this.idTipoEmpleado.equals(other.idTipoEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.petlovers.entity.TipoEmpleados[ idTipoEmpleado=" + idTipoEmpleado + " ]";
    }
    
}
