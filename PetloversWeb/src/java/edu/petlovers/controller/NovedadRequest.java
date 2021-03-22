/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.entity.Citas;
import edu.petlovers.entity.Novedades;
import edu.petlovers.local.CitasFacadeLocal;
import edu.petlovers.local.NovedadesFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.PrimeFaces;

/**
 *
 * @author HP
 */
@ManagedBean
@Named(value = "novedadRequest")
@RequestScoped
public class NovedadRequest implements Serializable{

    @EJB
    NovedadesFacadeLocal novedadesFacadeLocal;
    @EJB
    CitasFacadeLocal citasFacadeLocal;
    
    private Novedades objNovedad = new Novedades();
    
    private ArrayList<Novedades> listaNovedad = new ArrayList<>();
    private ArrayList<Citas> listaIdCitas = new ArrayList<>();
    
    public NovedadRequest() {
    }
    
    @PostConstruct
    public void postNovedad() {
        listaNovedad.addAll(novedadesFacadeLocal.findAll());
        listaIdCitas.addAll(citasFacadeLocal.findAll());
        objNovedad.setIdCitas(new Citas());
    }
    
    public void cargarNovedad(Novedades objCargar) {
        this.objNovedad = objCargar;
    }
    
    public void actualizarNovedad() {
        String mensaje = "";

        try {
            objNovedad.setIdCitas(citasFacadeLocal.find(objNovedad.getIdCitas().getIdCitas()));
            novedadesFacadeLocal.edit(objNovedad);
            listaNovedad.clear();
            listaNovedad.addAll(novedadesFacadeLocal.findAll());
            mensaje = "swal('La novedad' , ' se ha modificado exitosamente ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('La novedad' , ' No ha sido modificada ', 'error')";
        }
        objNovedad = new Novedades();
        PrimeFaces.current().executeScript(mensaje);
    }
    
    public void eliminarNovedad(Novedades objEliminar) {
        String mensaje = "";
        try {
            novedadesFacadeLocal.remove(objEliminar);
            listaNovedad.remove(objEliminar);
            mensaje = "swal('Novedad' , ' Eliminada ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('La Novedad' , ' No ha sido eliminada ', 'error')";
        }
        PrimeFaces.current().executeScript(mensaje);
    }
    
    public Novedades getObjNovedad() {
        return objNovedad;
    }

    public void setObjNovedad(Novedades objNovedad) {
        this.objNovedad = objNovedad;
    }
    
    public ArrayList<Novedades> getListaNovedad() {
        return listaNovedad;
    }

    public void setListaNovedad(ArrayList<Novedades> listaNovedad) {
        this.listaNovedad = listaNovedad;
    }

    public ArrayList<Citas> getListaIdCitas() {
        return listaIdCitas;
    }

    public void setListaIdCitas(ArrayList<Citas> listaIdCitas) {
        this.listaIdCitas = listaIdCitas;
    }
    
}
