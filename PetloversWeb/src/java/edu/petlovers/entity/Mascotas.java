/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wsbachiller
 */
@Entity
@Table(name = "mascotas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mascotas.findAll", query = "SELECT m FROM Mascotas m")})
public class Mascotas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Mascota")
    private Integer idMascota;
    @Size(max = 10)
    @Column(name = "Sexo")
    private String sexo;
    @Size(max = 15)
    @Column(name = "Nombres_Mascota")
    private String nombresMascota;
    @Column(name = "Fecha_De_Nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaDeNacimiento;
    @Size(max = 25)
    @Column(name = "Edad")
    private String edad;
    @Size(max = 25)
    @Column(name = "Color")
    private String color;
    @Size(max = 5)
    @Column(name = "Esterilizado")
    private String esterilizado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMascota", fetch = FetchType.LAZY)
    private Collection<HistoriaClinica> historiaClinicaCollection;
    @JoinColumn(name = "Id_Tipo_Mascota", referencedColumnName = "Id_Tipo_Mascota")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoMascotas idTipoMascota;
    @JoinColumn(name = "Id_Raza_Mascota", referencedColumnName = "Id_Raza_Mascota")
    @ManyToOne(fetch = FetchType.LAZY)
    private RazaMascotas idRazaMascota;
    @JoinColumn(name = "Id_Cliente", referencedColumnName = "Id_Cliente")
    @ManyToOne(fetch = FetchType.LAZY)
    private Clientes idCliente;

    public Mascotas() {
    }

    public Mascotas(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNombresMascota() {
        return nombresMascota;
    }

    public void setNombresMascota(String nombresMascota) {
        this.nombresMascota = nombresMascota;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEsterilizado() {
        return esterilizado;
    }

    public void setEsterilizado(String esterilizado) {
        this.esterilizado = esterilizado;
    }

    @XmlTransient
    public Collection<HistoriaClinica> getHistoriaClinicaCollection() {
        return historiaClinicaCollection;
    }

    public void setHistoriaClinicaCollection(Collection<HistoriaClinica> historiaClinicaCollection) {
        this.historiaClinicaCollection = historiaClinicaCollection;
    }

    public TipoMascotas getIdTipoMascota() {
        return idTipoMascota;
    }

    public void setIdTipoMascota(TipoMascotas idTipoMascota) {
        this.idTipoMascota = idTipoMascota;
    }

    public RazaMascotas getIdRazaMascota() {
        return idRazaMascota;
    }

    public void setIdRazaMascota(RazaMascotas idRazaMascota) {
        this.idRazaMascota = idRazaMascota;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMascota != null ? idMascota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mascotas)) {
            return false;
        }
        Mascotas other = (Mascotas) object;
        if ((this.idMascota == null && other.idMascota != null) || (this.idMascota != null && !this.idMascota.equals(other.idMascota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.petlovers.entity.Mascotas[ idMascota=" + idMascota + " ]";
    }
    
}
