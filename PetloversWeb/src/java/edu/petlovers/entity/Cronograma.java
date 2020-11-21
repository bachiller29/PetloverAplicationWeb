/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "cronograma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cronograma.findAll", query = "SELECT c FROM Cronograma c")})
public class Cronograma implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Cronograma")
    private Integer idCronograma;
    @Size(max = 20)
    @Column(name = "Jornada")
    private String jornada;
    @Size(max = 10)
    @Column(name = "Fecha")
    private String fecha;
    @Size(max = 10)
    @Column(name = "Hora")
    private String hora;
    @JoinColumn(name = "Id_Empleado", referencedColumnName = "Id_Empleado")
    @ManyToOne(fetch = FetchType.LAZY)
    private Empleados idEmpleado;
    @JoinColumn(name = "Id_Citas", referencedColumnName = "Id_Citas")
    @ManyToOne(fetch = FetchType.LAZY)
    private Citas idCitas;

    public Cronograma() {
    }

    public Cronograma(Integer idCronograma) {
        this.idCronograma = idCronograma;
    }

    public Integer getIdCronograma() {
        return idCronograma;
    }

    public void setIdCronograma(Integer idCronograma) {
        this.idCronograma = idCronograma;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Empleados getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleados idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Citas getIdCitas() {
        return idCitas;
    }

    public void setIdCitas(Citas idCitas) {
        this.idCitas = idCitas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCronograma != null ? idCronograma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cronograma)) {
            return false;
        }
        Cronograma other = (Cronograma) object;
        if ((this.idCronograma == null && other.idCronograma != null) || (this.idCronograma != null && !this.idCronograma.equals(other.idCronograma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.petlovers.entity.Cronograma[ idCronograma=" + idCronograma + " ]";
    }
    
}
