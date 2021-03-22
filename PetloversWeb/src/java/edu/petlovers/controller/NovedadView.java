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
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author HP
 */
@ManagedBean
@Named(value = "novedadView")
@ViewScoped
public class NovedadView implements Serializable{

    @EJB
    NovedadesFacadeLocal novedadesFacadeLocal;
    @EJB
    CitasFacadeLocal citasFacadeLocal;
    
    private Novedades objNovedad = new Novedades();
    private Citas citaSeleccionada = new Citas();
    
    private ArrayList<Novedades> listaNovedad = new ArrayList<>();
    private ArrayList<Citas> listaIdCitas = new ArrayList<>();

    private int idCita;
    
    private int idNovedad;
    private ArrayList<Novedades> unicaNovedad = new ArrayList<>();
    
    public NovedadView() {
    }

    @PostConstruct
    public void postNovedad() {
        listaNovedad.addAll(novedadesFacadeLocal.findAll());
        listaIdCitas.addAll(citasFacadeLocal.findAll());
    }
    
    public void cargaCita() {
        citaSeleccionada = citasFacadeLocal.find(idCita);
    }

    public String fechaCita() {
        try {
            return citaSeleccionada.getFechaCita();
        } catch (Exception e) {
            return " ";
        }
    }
    
    public String tipoServicio() {
        try {
            return citaSeleccionada.getIdServicio().getNombreServicio();
        } catch (Exception e) {
            return " ";
        }
    }

    public String nombreMascota() {
        try {
            return citaSeleccionada.getIdMascota().getNombresMascota();
        } catch (Exception e) {
            return " ";
        }
    }

    public String edadMascota() {
        try {
            return citaSeleccionada.getIdMascota().getEdad();
        } catch (Exception e) {
            return " ";
        }
    }
    
    public String sexoMascota() {
        try {
            return citaSeleccionada.getIdMascota().getSexo();
        } catch (Exception e) {
            return " ";
        }
    }
    
    public String tipoMascota() {
        try {
            return citaSeleccionada.getIdMascota().getIdTipoMascota().getTipoMascota();
        } catch (Exception e) {
            return " ";
        }
    }
    
    public String razaMascota() {
        try {
            return citaSeleccionada.getIdMascota().getIdRazaMascota().getRazaMascota();
        } catch (Exception e) {
            return " ";
        }
    }
    
    public String nombrePropietario() {
        try {
            return citaSeleccionada.getIdMascota().getIdCliente().getIdUsuario().getNombres() + " " + citaSeleccionada.getIdMascota().getIdCliente().getIdUsuario().getApellidos();
        } catch (Exception e) {
            return " ";
        }
    }
    
    public void crearNovedad() {
        String mensaje = "";
        
        try {
            objNovedad.setIdCitas(getCitaSeleccionada());
            novedadesFacadeLocal.create(objNovedad);
            listaNovedad.add(objNovedad);
            mensaje = "swal('La novedad' , ' Se ha registrado exitosamente ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('La novedad' , ' No ha sido registrada ', 'error')";
        }
        citaSeleccionada = new Citas();
        objNovedad = new Novedades();
        PrimeFaces.current().executeScript(mensaje);
    }
    
    public void cargarNovedad(Novedades objCargar) {
        this.objNovedad = objCargar;
    }
    
    public void actualizarNovedad() {
        String mensaje = "";

        try {
//            objNovedad.setIdCitas(objNovedad.getIdCitas());
            novedadesFacadeLocal.edit(objNovedad);
            listaNovedad.clear();
            listaNovedad.addAll(novedadesFacadeLocal.findAll());
            mensaje = "swal('La novedad' , ' se ha modificado exitosamente ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('La novedad' , ' No ha sido modificada ', 'error')";
        }
        citaSeleccionada = new Citas();
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
    
    public void buscaNovedad() {
        Novedades nove = new Novedades();
        String mensaje = "";
        try {
            nove = novedadesFacadeLocal.buscarNovedad(idNovedad);
            if (nove.getIdCitas() == null) {
                mensaje = "swal('La novedad' , ' No se encuentra registrada ', 'error')";
            } else {
                unicaNovedad.add(nove);
                mensaje = "swal('Novedad' , ' encontrada exitosamente ', 'success')";
            }
        } catch (Exception e) {
            mensaje = "swal('La novedad' , ' No se encuentra registrada ', 'error')";
        }
        PrimeFaces.current().executeScript(mensaje);
    }

    public void vaciarBusqueda() {
        unicaNovedad = new ArrayList<>();
    }
    
    public int getIdNovedad() {
        return idNovedad;
    }

    public void setIdNovedad(int idNovedad) {
        this.idNovedad = idNovedad;
    }

    public Novedades getObjNovedad() {
        return objNovedad;
    }

    public void setObjNovedad(Novedades objNovedad) {
        this.objNovedad = objNovedad;
    }

    public Citas getCitaSeleccionada() {
        return citaSeleccionada;
    }

    public void setCitaSeleccionada(Citas citaSeleccionada) {
        this.citaSeleccionada = citaSeleccionada;
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

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public ArrayList<Novedades> getUnicaNovedad() {
        return unicaNovedad;
    }

    public void setUnicaNovedad(ArrayList<Novedades> unicaNovedad) {
        this.unicaNovedad = unicaNovedad;
    }
    
}
