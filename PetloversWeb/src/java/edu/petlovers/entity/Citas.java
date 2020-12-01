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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "citas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Citas.findAll", query = "SELECT c FROM Citas c")})
public class Citas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Citas")
    private Integer idCitas;
    @Size(max = 15)
    @Column(name = "Fecha_Cita")
    private String fechaCita;
    @Size(max = 15)
    @Column(name = "Hora_Cita")
    private String horaCita;
    @Size(max = 20)
    @Column(name = "Estado_Cita")
    private String estadoCita;
    @JoinColumn(name = "Id_Mascota", referencedColumnName = "Id_Mascota")
    @ManyToOne(fetch = FetchType.LAZY)
    private Mascotas idMascota;
    @JoinColumn(name = "Id_Servicio", referencedColumnName = "Id_Servicio")
    @ManyToOne(fetch = FetchType.LAZY)
    private Servicios idServicio;
    @JoinColumn(name = "Id_Cliente", referencedColumnName = "Id_Cliente")
    @ManyToOne(fetch = FetchType.LAZY)
    private Clientes idCliente;
    @OneToMany(mappedBy = "idCitas", fetch = FetchType.LAZY)
    private Collection<Cronograma> cronogramaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCitas", fetch = FetchType.LAZY)
    private Collection<HistoriaClinica> historiaClinicaCollection;

    public Citas() {
    }

    public Citas(Integer idCitas) {
        this.idCitas = idCitas;
    }

    public Integer getIdCitas() {
        return idCitas;
    }

    public void setIdCitas(Integer idCitas) {
        this.idCitas = idCitas;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public String getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(String estadoCita) {
        this.estadoCita = estadoCita;
    }

    public Mascotas getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Mascotas idMascota) {
        this.idMascota = idMascota;
    }

    public Servicios getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Servicios idServicio) {
        this.idServicio = idServicio;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    @XmlTransient
    public Collection<Cronograma> getCronogramaCollection() {
        return cronogramaCollection;
    }

    public void setCronogramaCollection(Collection<Cronograma> cronogramaCollection) {
        this.cronogramaCollection = cronogramaCollection;
    }

    @XmlTransient
    public Collection<HistoriaClinica> getHistoriaClinicaCollection() {
        return historiaClinicaCollection;
    }

    public void setHistoriaClinicaCollection(Collection<HistoriaClinica> historiaClinicaCollection) {
        this.historiaClinicaCollection = historiaClinicaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCitas != null ? idCitas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citas)) {
            return false;
        }
        Citas other = (Citas) object;
        if ((this.idCitas == null && other.idCitas != null) || (this.idCitas != null && !this.idCitas.equals(other.idCitas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.petlovers.entity.Citas[ idCitas=" + idCitas + " ]";
    }
    
}
