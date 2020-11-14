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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author wsbachiller
 */
@Entity
@Table(name = "criadero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Criadero.findAll", query = "SELECT c FROM Criadero c")})
public class Criadero implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Nit_Criadero")
    private Integer nitCriadero;
    @Size(max = 20)
    @Column(name = "Nombre_Criadero")
    private String nombreCriadero;
    @Size(max = 20)
    @Column(name = "Sede_Criadero")
    private String sedeCriadero;
    @JoinColumn(name = "Id_Admin", referencedColumnName = "Id_Admin")
    @ManyToOne(fetch = FetchType.LAZY)
    private Administradores idAdmin;
    @OneToMany(mappedBy = "nitCriadero", fetch = FetchType.LAZY)
    private Collection<Servicios> serviciosCollection;

    public Criadero() {
    }

    public Criadero(Integer nitCriadero) {
        this.nitCriadero = nitCriadero;
    }

    public Integer getNitCriadero() {
        return nitCriadero;
    }

    public void setNitCriadero(Integer nitCriadero) {
        this.nitCriadero = nitCriadero;
    }

    public String getNombreCriadero() {
        return nombreCriadero;
    }

    public void setNombreCriadero(String nombreCriadero) {
        this.nombreCriadero = nombreCriadero;
    }

    public String getSedeCriadero() {
        return sedeCriadero;
    }

    public void setSedeCriadero(String sedeCriadero) {
        this.sedeCriadero = sedeCriadero;
    }

    public Administradores getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Administradores idAdmin) {
        this.idAdmin = idAdmin;
    }

    @XmlTransient
    public Collection<Servicios> getServiciosCollection() {
        return serviciosCollection;
    }

    public void setServiciosCollection(Collection<Servicios> serviciosCollection) {
        this.serviciosCollection = serviciosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nitCriadero != null ? nitCriadero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Criadero)) {
            return false;
        }
        Criadero other = (Criadero) object;
        if ((this.nitCriadero == null && other.nitCriadero != null) || (this.nitCriadero != null && !this.nitCriadero.equals(other.nitCriadero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.petlovers.entity.Criadero[ nitCriadero=" + nitCriadero + " ]";
    }
    
}
