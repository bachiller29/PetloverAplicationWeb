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
@Table(name = "mascotas")
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
    @Size(max = 25)
    @Column(name = "Edad")
    private String edad;
    @Size(max = 15)
    @Column(name = "Fecha_De_Nacimiento")
    private String fechaDeNacimiento;
    @Size(max = 25)
    @Column(name = "Color")
    private String color;
    @Size(max = 5)
    @Column(name = "Esterilizado")
    private String esterilizado;
    @OneToMany(mappedBy = "idMascota", fetch = FetchType.EAGER)
    private Collection<Citas> citasCollection;
    @JoinColumn(name = "Id_Tipo_Mascota", referencedColumnName = "Id_Tipo_Mascota")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TipoMascotas idTipoMascota;
    @JoinColumn(name = "Id_Raza_Mascota", referencedColumnName = "Id_Raza_Mascota")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private RazaMascotas idRazaMascota;
    @JoinColumn(name = "Id_Cliente", referencedColumnName = "Id_Cliente")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Clientes idCliente;
    @OneToMany(mappedBy = "idMascota", fetch = FetchType.EAGER)
    private Collection<HistoriaClinica> historiaClinicaCollection;

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

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
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

    public Collection<Citas> getCitasCollection() {
        return citasCollection;
    }

    public void setCitasCollection(Collection<Citas> citasCollection) {
        this.citasCollection = citasCollection;
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

    public Collection<HistoriaClinica> getHistoriaClinicaCollection() {
        return historiaClinicaCollection;
    }

    public void setHistoriaClinicaCollection(Collection<HistoriaClinica> historiaClinicaCollection) {
        this.historiaClinicaCollection = historiaClinicaCollection;
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
