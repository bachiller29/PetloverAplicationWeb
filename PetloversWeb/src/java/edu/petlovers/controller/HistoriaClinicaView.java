/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.entity.Citas;
import edu.petlovers.entity.HistoriaClinica;
import edu.petlovers.local.CitasFacadeLocal;
import edu.petlovers.local.HistoriaClinicaFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author HP
 */
@ManagedBean
@ViewScoped
@Named(value = "historiaClinicaView")
public class HistoriaClinicaView implements Serializable{

    @EJB
    HistoriaClinicaFacadeLocal historiaClinicaFacadeLocal;
    @EJB
    CitasFacadeLocal citasFacadeLocal;
    
    private HistoriaClinica objHistoriaC = new HistoriaClinica();
    private Citas citaSeleccionada = new Citas();
    
    private int idCita;
    
    public HistoriaClinicaView() {
    }

    @PostConstruct
    public void postHistoriaC(){
        objHistoriaC.setIdCitas(new Citas());
    }
    
    public void cargaCita(){
        citaSeleccionada = citasFacadeLocal.find(idCita);
    }
    
    public String nombreMascota(){
        try {
            return citaSeleccionada.getIdMascota().getNombresMascota();
        } catch (Exception e) {
            return " ";
        }
    }
    
    public String nombrePropietario(){
        try {
            return citaSeleccionada.getIdMascota().getIdCliente().getIdUsuario().getNombres() + " " + citaSeleccionada.getIdMascota().getIdCliente().getIdUsuario().getApellidos();
        } catch (Exception e) {
            return " ";
        }
    }
    
    public void crearHistoriaC() {
        String mensaje = "";
        
        try {
            objHistoriaC.setIdCitas(getCitaSeleccionada());
//            objHistoriaC.setIdCitas(citasFacadeLocal.find(objHistoriaC.getIdCitas().getIdCitas()));
            objHistoriaC.setIdMascota(getCitaSeleccionada().getIdMascota());
//            objHistoriaC.setIdMascota(mascotasFacadeLocal.find(objHistoriaC.getIdMascota().getIdMascota()));
            objHistoriaC.setIdCliente(getCitaSeleccionada().getIdMascota().getIdCliente());
//            objHistoriaC.setIdCliente(clientesFacadeLocal.find(objHistoriaC.getIdCliente().getIdCliente()));
            historiaClinicaFacadeLocal.create(objHistoriaC);
            mensaje = "swal('La historia clinica' , ' Se ha registrado exitosamente ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('La historia clinica' , ' No ha sido registrada ', 'error')";
        }
        objHistoriaC = new HistoriaClinica();
        PrimeFaces.current().executeScript(mensaje);
    }
    
    
    
    public Citas getCitaSeleccionada() {
        return citaSeleccionada;
    }

    public void setCitaSeleccionada(Citas citaSeleccionada) {
        this.citaSeleccionada = citaSeleccionada;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public HistoriaClinica getObjHistoriaC() {
        return objHistoriaC;
    }

    public void setObjHistoriaC(HistoriaClinica objHistoriaC) {
        this.objHistoriaC = objHistoriaC;
    }
    
}
