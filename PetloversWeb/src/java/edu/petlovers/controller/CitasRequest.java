/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.entity.Citas;
import edu.petlovers.entity.Clientes;
import edu.petlovers.entity.Servicios;
import edu.petlovers.local.CitasFacadeLocal;
import edu.petlovers.local.ClientesFacadeLocal;
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
public class CitasRequest implements Serializable {

    @EJB
    CitasFacadeLocal citasFacadeLocal;
    @EJB
    ClientesFacadeLocal clientesFacadeLocal;
    @EJB
    ServiciosFacadeLocal serviciosFacadeLocal;

    private Citas objCita = new Citas();
//    private Clientes objCliente = new Clientes();

    private ArrayList<Citas> listaCitas = new ArrayList<>();
    private ArrayList<Clientes> listaIdClientes = new ArrayList<>();
    private ArrayList<Servicios> listaIdServicio = new ArrayList<>();

//    private Clientes idCliente;
//    private Servicios idServicio;
    private int idCita;
    private ArrayList<Citas> unicaCita = new ArrayList<>();

    public CitasRequest() {
    }

    @PostConstruct
    public void postCita() {
        listaCitas.addAll(citasFacadeLocal.findAll());
        listaIdClientes.addAll(clientesFacadeLocal.findAll());
        listaIdServicio.addAll(serviciosFacadeLocal.findAll());
        objCita.setIdCliente(new Clientes());
        objCita.setIdServicio(new Servicios());
    }

    public void crearCita() {
        String mensaje = "";

        try {
            objCita.setIdCliente(clientesFacadeLocal.find(objCita.getIdCliente().getIdCliente()));
            objCita.setIdServicio(serviciosFacadeLocal.find(objCita.getIdServicio().getIdServicio()));
            citasFacadeLocal.create(objCita);
            mensaje = "swal('La cita' , ' Se ha registrado exitosamente ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('La cita' , ' No ha sido registrada ', 'error')";
        }
        objCita = new Citas();
        PrimeFaces.current().executeScript(mensaje);
    }

    public void cargarCita(Citas objCargar) {
        this.objCita = objCargar;
    }

    public void actualizarCita() {
        String mensaje = "";

        try {
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

    public void buscaCita() {
        Citas busCita = new Citas();
        String mensaje = "";
        try {
            busCita = citasFacadeLocal.buscarCita(idCita);
            if (busCita.getFechaCita() == null) {
                mensaje = "swal('La cita' , ' No se encuentra registrada ', 'error')";
            } else {
                unicaCita.add(busCita);
                mensaje = "swal('Cita' , ' encontrada exitosamente ', 'success')";
            }
        } catch (Exception e) {
            mensaje = "swal('La cita' , ' No se encuentra registrada ', 'error')";
        }
        PrimeFaces.current().executeScript(mensaje);
    }

    public void vaciarBusqueda() {
        unicaCita = new ArrayList<>();
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

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public ArrayList<Citas> getUnicaCita() {
        return unicaCita;
    }

    public void setUnicaCita(ArrayList<Citas> unicaCita) {
        this.unicaCita = unicaCita;
    }

    public ArrayList<Clientes> getListaIdClientes() {
        return listaIdClientes;
    }

    public void setListaIdClientes(ArrayList<Clientes> listaIdClientes) {
        this.listaIdClientes = listaIdClientes;
    }

    public ArrayList<Servicios> getListaIdServicio() {
        return listaIdServicio;
    }

    public void setListaIdServicio(ArrayList<Servicios> listaIdServicio) {
        this.listaIdServicio = listaIdServicio;
    }

    /*
     public Clientes getObjCliente() {
     return objCliente;
     }

     public void setObjCliente(Clientes objCliente) {
     this.objCliente = objCliente;
     }    

     public Clientes getIdCliente() {
     return idCliente;
     }

     public void setIdCliente(Clientes idCliente) {
     this.idCliente = idCliente;
     }
    
     public Servicios getIdServicio() {
     return idServicio;
     }

     public void setIdServicio(Servicios idServicio) {
     this.idServicio = idServicio;
     }

     */
}
