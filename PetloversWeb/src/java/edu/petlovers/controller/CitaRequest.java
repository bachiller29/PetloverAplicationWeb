/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.entity.Citas;
import edu.petlovers.entity.Mascotas;
import edu.petlovers.entity.Servicios;
import edu.petlovers.local.CitasFacadeLocal;
import edu.petlovers.local.MascotasFacadeLocal;
import edu.petlovers.local.ServiciosFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author HP
 */
@ManagedBean
@RequestScoped
public class CitaRequest implements Serializable{

    @EJB
    CitasFacadeLocal citasFacadeLocal;
    @EJB
    MascotasFacadeLocal mascotasFacadeLocal;
    @EJB
    ServiciosFacadeLocal serviciosFacadeLocal;
    
    private Citas objCita = new Citas();
    
    private ArrayList<Citas> listaCitas = new ArrayList<>();
    private ArrayList<Mascotas> listaIdMascotas = new ArrayList<>();
    private ArrayList<Servicios> listaIdServicio = new ArrayList<>();

    
    public CitaRequest() {
    }
    
    @PostConstruct
    public void postCita() {
        listaCitas.addAll(citasFacadeLocal.findAll());
        listaIdMascotas.addAll(mascotasFacadeLocal.findAll());
        listaIdServicio.addAll(serviciosFacadeLocal.findAll());
        objCita.setIdMascota(new Mascotas());
        objCita.setIdServicio(new Servicios());
    }
    
    public void cargarCita(Citas objCargar) {
        this.objCita = objCargar;
//        usuarioSession.setObjCit(objCita);
    }

    public void actualizarCita() {
        String mensaje = "";
        try {
            objCita.setIdMascota(mascotasFacadeLocal.find(objCita.getIdMascota().getIdMascota()));
            objCita.setIdServicio(serviciosFacadeLocal.find(objCita.getIdServicio().getIdServicio()));
            citasFacadeLocal.edit(objCita);
            listaCitas.clear();
            listaCitas.addAll(citasFacadeLocal.findAll());
            mensaje = "swal('La cita' , ' Se ha modificado exitosamente ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('La cita' , ' No ha sido modificada ', 'error')";
        }
        objCita = new Citas();
        PrimeFaces.current().executeScript(mensaje);
    }

    public void eliminarCita(Citas objEliminar) {
        String mensaje = "";
        try {
            citasFacadeLocal.remove(objEliminar);
            listaCitas.remove(objEliminar);
            mensaje = "swal('Cita' , ' Eliminada ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('La cita' , ' No ha sido eliminada ', 'error')";
        }
        PrimeFaces.current().executeScript(mensaje);
    }

    
    public Citas getObjCita() {
        return objCita;
    }

    public void setObjCita(Citas objCita) {
        this.objCita = objCita;
    }

    public ArrayList<Citas> getListaCitas() {
        return listaCitas;
    }

    public void setListaCitas(ArrayList<Citas> listaCitas) {
        this.listaCitas = listaCitas;
    }

    public ArrayList<Mascotas> getListaIdMascotas() {
        return listaIdMascotas;
    }

    public void setListaIdMascotas(ArrayList<Mascotas> listaIdMascotas) {
        this.listaIdMascotas = listaIdMascotas;
    }
    
    public ArrayList<Servicios> getListaIdServicio() {
        return listaIdServicio;
    }

    public void setListaIdServicio(ArrayList<Servicios> listaIdServicio) {
        this.listaIdServicio = listaIdServicio;
    }

}
