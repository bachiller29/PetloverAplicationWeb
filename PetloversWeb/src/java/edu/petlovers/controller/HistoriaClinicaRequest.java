/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.entity.Citas;
import edu.petlovers.entity.Clientes;
import edu.petlovers.entity.HistoriaClinica;
import edu.petlovers.entity.Mascotas;
import edu.petlovers.local.CitasFacadeLocal;
import edu.petlovers.local.ClientesFacadeLocal;
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
public class HistoriaClinicaRequest implements Serializable{

    @EJB
    HistoriaClinicaFacadeLocal historiaClinicaFacadeLocal;
    @EJB
    CitasFacadeLocal citasFacadeLocal;
    @EJB
    MascotasFacadeLocal mascotasFacadeLocal;
    @EJB
    ClientesFacadeLocal clientesFacadeLocal;
    
    private HistoriaClinica objHistoriaC = new HistoriaClinica();
    
    private ArrayList<HistoriaClinica> listaHistoriaC = new ArrayList<>();
    private ArrayList<Citas> listaIdCitas = new ArrayList<>();
    private ArrayList<Mascotas> listaIdMascotas = new ArrayList<>();
    private ArrayList<Clientes> listaIdClientes = new ArrayList<>();

    
    public HistoriaClinicaRequest() {
    }

    @PostConstruct
    public void postHistoriaC() {
        listaHistoriaC.addAll(historiaClinicaFacadeLocal.findAll());
        listaIdCitas.addAll(citasFacadeLocal.findAll());
        listaIdMascotas.addAll(mascotasFacadeLocal.findAll());
        listaIdClientes.addAll(clientesFacadeLocal.findAll());
        objHistoriaC.setIdCitas(new Citas());
        objHistoriaC.setIdMascota(new Mascotas());
        objHistoriaC.setIdCliente(new Clientes());
    }

    public void cargarHistoriaC(HistoriaClinica objCargar) {
        this.objHistoriaC = objCargar;
    }

    public void actualizarHistoriaC() {
        String mensaje = "";

        try {
            objHistoriaC.setIdCitas(citasFacadeLocal.find(objHistoriaC.getIdCitas().getIdCitas()));
            objHistoriaC.setIdMascota(mascotasFacadeLocal.find(objHistoriaC.getIdMascota().getIdMascota()));
            objHistoriaC.setIdCliente(clientesFacadeLocal.find(objHistoriaC.getIdCliente().getIdCliente()));
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

    public ArrayList<Citas> getListaIdCitas() {
        return listaIdCitas;
    }

    public void setListaIdCitas(ArrayList<Citas> listaIdCitas) {
        this.listaIdCitas = listaIdCitas;
    }

    public ArrayList<Mascotas> getListaIdMascotas() {
        return listaIdMascotas;
    }

    public void setListaIdMascotas(ArrayList<Mascotas> listaIdMascotas) {
        this.listaIdMascotas = listaIdMascotas;
    }

    public ArrayList<Clientes> getListaIdClientes() {
        return listaIdClientes;
    }

    public void setListaIdClientes(ArrayList<Clientes> listaIdClientes) {
        this.listaIdClientes = listaIdClientes;
    }
    
}
