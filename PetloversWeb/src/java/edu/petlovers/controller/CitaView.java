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
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.PrimeFaces;

/**
 *
 * @author HP
 */
@ManagedBean
@ViewScoped
@Named(value = "citaView")
public class CitaView implements Serializable{
    
    @EJB
    CitasFacadeLocal citasFacadeLocal;
    @EJB
    MascotasFacadeLocal mascotasFacadeLocal;
    @EJB
    ServiciosFacadeLocal serviciosFacadeLocal;
    
//    @Inject
//    UsuarioSession usuarioSession;
    
    private Citas objCita = new Citas();
    private Mascotas mascotaSeleccionada = new Mascotas();
    
    private ArrayList<Citas> listaCitas = new ArrayList<>();
    private ArrayList<Mascotas> listaIdMascotas = new ArrayList<>();
    private ArrayList<Servicios> listaIdServicio = new ArrayList<>();

    private int idMascota;
    private int idCita;
    private ArrayList<Citas> unicaCita = new ArrayList<>();
    
    public CitaView() {
    }
        
    @PostConstruct
    public void postCita() {
        listaCitas.addAll(citasFacadeLocal.findAll());
        listaIdMascotas.addAll(mascotasFacadeLocal.findAll());
        listaIdServicio.addAll(serviciosFacadeLocal.findAll());
        objCita.setIdMascota(new Mascotas());
        objCita.setIdServicio(new Servicios());
    }
    
    public void cargaMascota() {
        mascotaSeleccionada = mascotasFacadeLocal.find(idMascota);
    }
    
    public String nombrePropietario() {
        try {
            return mascotaSeleccionada.getIdCliente().getIdUsuario().getNombres() + " " + mascotaSeleccionada.getIdCliente().getIdUsuario().getApellidos();
        } catch (Exception e) {
            return " ";
        }
    }
    
    public void crearCita() {
        String mensaje = "";
        try {
            objCita.setIdMascota(getMascotaSeleccionada());
            int cantidadCitas = citasFacadeLocal.cantidadCitas(objCita.getIdMascota().getIdMascota(), objCita.getFechaCita());
            if (cantidadCitas < 3) {
//                objCita.setIdMascota(mascotasFacadeLocal.find(objCita.getIdMascota().getIdMascota()));
                objCita.setIdServicio(serviciosFacadeLocal.find(objCita.getIdServicio().getIdServicio()));
                citasFacadeLocal.create(objCita);
                mensaje = "swal('La cita' , ' Se ha registrado exitosamente ', 'success')";
            } else {
                mensaje = "swal('Excede numero de citas' , ' por el dia " + objCita.getFechaCita() + " ', 'error')";
            }
        } catch (Exception e) {
            mensaje = "swal('La cita' , ' No ha sido registrada ', 'error')";
        }
        mascotaSeleccionada = new Mascotas();
        objCita = new Citas();
        PrimeFaces.current().executeScript(mensaje);
    }

    public void cargarCita(Citas objCargar) {
        this.objCita = objCargar;
//        usuarioSession.setObjCit(objCita);
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
        mascotaSeleccionada = new Mascotas();
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

    
    public void descargaReporte(String nombreReporte, String nombreUsuario)  {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("RutaLogo", context.getRealPath("/assets/img/logo/fondo1.jpg"));
            parametro.put("UsuarioReporte", nombreUsuario);//usuarioSession.getUsuLogin().getNombres() + " " + usuarioSession.getUsuLogin().getApellidos());
            parametro.put("RutaImagenFondo", context.getRealPath("/assets/img/logo/logoPetlovers2.png"));
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/petlovers", "petloversuser", "1234");
            System.out.println("Catalogo : " + conec.getCatalog());
            
            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/petlovers/reports/"+nombreReporte+".jasper"));
             
            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);
            
            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Listado Citas.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();
           
        } catch (JRException e) {
            System.out.println("edu.petlovers.controller.CitaView.descargaReporte() " + e.getMessage());
        } catch(IOException i){
            System.out.println("edu.petlovers.controller.CitaView.descargaReporte() " + i.getMessage());
        } catch (SQLException q){
            System.out.println("edu.petlovers.controller.CitaView.descargaReporte() " + q.getMessage());
        }

    }

    
    
    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public Mascotas getMascotaSeleccionada() {
        return mascotaSeleccionada;
    }

    public void setMascotaSeleccionada(Mascotas mascotaSeleccionada) {
        this.mascotaSeleccionada = mascotaSeleccionada;
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
