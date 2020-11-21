/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.entity.Citas;
import edu.petlovers.entity.HistoriaClinica;
import edu.petlovers.entity.Mascotas;
import edu.petlovers.local.CitasFacadeLocal;
import edu.petlovers.local.HistoriaClinicaFacadeLocal;
import edu.petlovers.local.MascotasFacadeLocal;
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
public class HistoriasClinicasRequest implements Serializable{
    @EJB
    HistoriaClinicaFacadeLocal historiaClinicaFacadeLocal;
    @EJB
    MascotasFacadeLocal mascotasFacadeLocal;
    @EJB
    CitasFacadeLocal citasFacadeLocal;
    
    private HistoriaClinica objHistoriaC = new HistoriaClinica();
    
    private ArrayList<HistoriaClinica> listaHistoriaC = new ArrayList<>();
    private ArrayList<Mascotas> listaIdMascotas = new ArrayList<>();
    private ArrayList<Citas> listaIdCitas = new ArrayList<>();
    
    private int idHistoria;
    private ArrayList<HistoriaClinica> unicaHistoriaC = new ArrayList<>();
    
    public HistoriasClinicasRequest() {
    }

    @PostConstruct
    public void postHistoriaC(){
        listaHistoriaC.addAll(historiaClinicaFacadeLocal.findAll());
        listaIdMascotas.addAll(mascotasFacadeLocal.findAll());
        listaIdCitas.addAll(citasFacadeLocal.findAll());
        objHistoriaC.setIdMascota(new Mascotas());
        objHistoriaC.setIdCitas(new Citas());
    }
    
    public void crearHistoriaC() {
        String mensaje = "";
        
        try {
            objHistoriaC.setIdMascota(mascotasFacadeLocal.find(objHistoriaC.getIdMascota().getIdMascota()));
            objHistoriaC.setIdCitas(citasFacadeLocal.find(objHistoriaC.getIdCitas().getIdCitas()));
            historiaClinicaFacadeLocal.create(objHistoriaC);
            mensaje = "swal('La historia clinica' , ' Se ha registrado exitosamente ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('La historia clinica' , ' No ha sido registrada ', 'error')";
        }
        objHistoriaC = new HistoriaClinica();
        PrimeFaces.current().executeScript(mensaje);
    }
    
    public void cargarHistoriaC(HistoriaClinica objCargar){
        this.objHistoriaC = objCargar;
    }
    
    public void actualizarHistoriaC() {
        String mensaje = "";
        
        try {
            historiaClinicaFacadeLocal.edit(objHistoriaC);
            listaHistoriaC.clear();
            listaHistoriaC.addAll(historiaClinicaFacadeLocal.findAll());
            mensaje = "swal('La historia clinica' , ' se ha modificado exitosamente ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('La historia clinica' , ' No ha sido modificada ', 'error')";
        }
        objHistoriaC = new HistoriaClinica();
        PrimeFaces.current().executeScript(mensaje);
    }
    
    public void eliminarHistoriaC(HistoriaClinica objEliminar) {
        String mensaje = "";
        try {
            historiaClinicaFacadeLocal.remove(objEliminar);
            listaHistoriaC.remove(objEliminar);
            mensaje = "swal('Historia Clinica' , ' Eliminada ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('La historia clinica' , ' No ha sido eliminada ', 'error')";
        }
        PrimeFaces.current().executeScript(mensaje);
    }
    
    public void buscaHistoriaC() {
        HistoriaClinica histCli = new HistoriaClinica();
        String mensaje = "";
        try {
            histCli = historiaClinicaFacadeLocal.buscarHistoriaClinica(idHistoria);
            if(histCli.getIdCitas() == null) {
                mensaje = "swal('La historia clinica' , ' No se encuentra registrada ', 'error')";
            } else {
                unicaHistoriaC.add(histCli);
                mensaje = "swal('Historia clinica' , ' encontrada exitosamente ', 'success')";
            }
        } catch (Exception e) {
            mensaje = "swal('La historia clinica' , ' No se encuentra registrada ', 'error')";
        }
        PrimeFaces.current().executeScript(mensaje);
    }
    
    public void vaciarBusqueda() {
        unicaHistoriaC = new ArrayList<>();
    }
    
    public HistoriaClinica getObjHistoriaC() {
        return objHistoriaC;
    }

    public void setObjHistoriaC(HistoriaClinica objHistoriaC) {
        this.objHistoriaC = objHistoriaC;
    }

    public ArrayList<HistoriaClinica> getListaHistoriaC() {
        return listaHistoriaC;
    }

    public void setListaHistoriaC(ArrayList<HistoriaClinica> listaHistoriaC) {
        this.listaHistoriaC = listaHistoriaC;
    }

    public ArrayList<Mascotas> getListaIdMascotas() {
        return listaIdMascotas;
    }

    public void setListaIdMascotas(ArrayList<Mascotas> listaIdMascotas) {
        this.listaIdMascotas = listaIdMascotas;
    }

    public ArrayList<Citas> getListaIdCitas() {
        return listaIdCitas;
    }

    public void setListaIdCitas(ArrayList<Citas> listaIdCitas) {
        this.listaIdCitas = listaIdCitas;
    }

    public int getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(int idHistoria) {
        this.idHistoria = idHistoria;
    }

    public ArrayList<HistoriaClinica> getUnicaHistoriaC() {
        return unicaHistoriaC;
    }

    public void setUnicaHistoriaC(ArrayList<HistoriaClinica> unicaHistoriaC) {
        this.unicaHistoriaC = unicaHistoriaC;
    }
    
}
