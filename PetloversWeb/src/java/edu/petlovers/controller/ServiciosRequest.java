/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.entity.Criadero;
import edu.petlovers.entity.Empleados;
import edu.petlovers.entity.Servicios;
import edu.petlovers.entity.TipoServicios;
import edu.petlovers.local.CriaderoFacadeLocal;
import edu.petlovers.local.EmpleadosFacadeLocal;
import edu.petlovers.local.ServiciosFacadeLocal;
import edu.petlovers.local.TipoServiciosFacadeLocal;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.PrimeFaces;

/**
 *
 * @author HP
 */
@ManagedBean
@RequestScoped
public class ServiciosRequest {

    @EJB
    ServiciosFacadeLocal serviciosFacadeLocal;
    @EJB
    TipoServiciosFacadeLocal tipoServiciosFacadeLocal;
    @EJB
    EmpleadosFacadeLocal empleadosFacadeLocal;
    @EJB
    CriaderoFacadeLocal criaderoFacadeLocal;
    
    private Servicios objServicios = new Servicios();
    
    private ArrayList<Servicios> listaServicios = new ArrayList<>();
    private ArrayList<TipoServicios> listaTipoServicio = new ArrayList<>();
    private ArrayList<Empleados> listaEmpleados = new ArrayList<>();
    private ArrayList<Criadero> listaCriaderos = new ArrayList<>();
    
    public ServiciosRequest() {
    }
    
    @PostConstruct
    public void postServicio() {
        listaServicios.addAll(serviciosFacadeLocal.findAll());
        listaTipoServicio.addAll(tipoServiciosFacadeLocal.findAll());
        listaEmpleados.addAll(empleadosFacadeLocal.findAll());
        listaCriaderos.addAll(criaderoFacadeLocal.findAll());
        objServicios.setIdTipoServicio(new TipoServicios());
        objServicios.setIdEmpleado(new Empleados());
        objServicios.setNitCriadero(new Criadero());
    }

    public void crearServicio(){
        String mensaje = "";

        try {
            objServicios.setIdTipoServicio(tipoServiciosFacadeLocal.find(objServicios.getIdTipoServicio().getIdTipoServicio()));
            objServicios.setIdEmpleado(empleadosFacadeLocal.find(objServicios.getIdEmpleado().getIdEmpleado()));
            objServicios.setNitCriadero(criaderoFacadeLocal.find(objServicios.getNitCriadero().getNitCriadero()));
            serviciosFacadeLocal.create(objServicios);
            mensaje = "swal('El servicio' , ' Se ha registrado exitosamente ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('El servicio' , ' No ha sido registrado ', 'error')";
        }
        objServicios = new Servicios();
        PrimeFaces.current().executeScript(mensaje);
    }
    
    public void cargarServicio(Servicios objCargar){
        this.objServicios = objCargar;
    }
    
    public void actualizarServicio() {
        String mensaje = "";
        
        try {
            serviciosFacadeLocal.edit(objServicios);
            listaServicios.clear();
            listaServicios.addAll(serviciosFacadeLocal.findAll());
            mensaje = "swal('El servicio' , ' se ha modificado exitosamente ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('El servicio' , ' No ha sido modificado ', 'error')";
        }
        objServicios = new Servicios();
        PrimeFaces.current().executeScript(mensaje);
    }
    
    public void eliminarServicio(Servicios objEliminar) {
        String mensaje = "";
        try {
            serviciosFacadeLocal.remove(objEliminar);
            listaServicios.remove(objEliminar);
            mensaje = "swal('Servicio' , ' Eliminado ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('El servicio' , ' No ha sido eliminado ', 'error')";
        }
        PrimeFaces.current().executeScript(mensaje);
    }
    
    public Servicios getObjServicios() {
        return objServicios;
    }

    public void setObjServicios(Servicios objServicios) {
        this.objServicios = objServicios;
    }

    public ArrayList<Servicios> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(ArrayList<Servicios> listaServicios) {
        this.listaServicios = listaServicios;
    }
    
    public ArrayList<TipoServicios> getListaTipoServicio() {
        return listaTipoServicio;
    }

    public void setListaTipoServicio(ArrayList<TipoServicios> listaTipoServicio) {
        this.listaTipoServicio = listaTipoServicio;
    }

    public ArrayList<Empleados> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(ArrayList<Empleados> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public ArrayList<Criadero> getListaCriaderos() {
        return listaCriaderos;
    }

    public void setListaCriaderos(ArrayList<Criadero> listaCriaderos) {
        this.listaCriaderos = listaCriaderos;
    }

}
