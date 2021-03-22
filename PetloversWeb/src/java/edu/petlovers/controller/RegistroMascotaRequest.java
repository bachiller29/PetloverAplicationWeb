/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.entity.Clientes;
import edu.petlovers.entity.Mascotas;
import edu.petlovers.entity.RazaMascotas;
import edu.petlovers.entity.TipoMascotas;
import edu.petlovers.local.ClientesFacadeLocal;
import edu.petlovers.local.MascotasFacadeLocal;
import edu.petlovers.local.RazaMascotasFacadeLocal;
import edu.petlovers.local.TipoMascotasFacadeLocal;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.jfree.chart.block.Arrangement;
import org.primefaces.PrimeFaces;

/**
 *
 * @author HP
 */
@ManagedBean
@RequestScoped
public class RegistroMascotaRequest {

    @EJB
    MascotasFacadeLocal mascotasFacadeLocal;
    @EJB
    ClientesFacadeLocal clientesFacadeLocal;
    @EJB
    TipoMascotasFacadeLocal tipoMascotasFacadeLocal;
    @EJB
    RazaMascotasFacadeLocal razaMascotasFacadeLocal;

    private Mascotas objMascotas = new Mascotas();

    private ArrayList<Mascotas> listaMascotas = new ArrayList<>();
    private ArrayList<Clientes> listaClientes = new ArrayList<>();
    private ArrayList<TipoMascotas> listaTipoMascota = new ArrayList<>();
    private ArrayList<RazaMascotas> listaRazaMascota = new ArrayList<>();

    public RegistroMascotaRequest() {
    }

    @PostConstruct
    public void postMascota() {
        listaMascotas.addAll(mascotasFacadeLocal.findAll());
        listaClientes.addAll(clientesFacadeLocal.findAll());
        listaTipoMascota.addAll(tipoMascotasFacadeLocal.findAll());
        listaRazaMascota.addAll(razaMascotasFacadeLocal.findAll());
        objMascotas.setIdCliente(new Clientes());
        objMascotas.setIdTipoMascota(new TipoMascotas());
        objMascotas.setIdRazaMascota(new RazaMascotas());
    }

    public void crearMascota() {
        String mensaje = "";

        try {
            int cantidadMascotas = mascotasFacadeLocal.cantidadMascotas(objMascotas.getIdCliente().getIdCliente());
            if (cantidadMascotas < 3) {
                objMascotas.setIdCliente(clientesFacadeLocal.find(objMascotas.getIdCliente().getIdCliente()));
                objMascotas.setIdTipoMascota(tipoMascotasFacadeLocal.find(objMascotas.getIdTipoMascota().getIdTipoMascota()));
                objMascotas.setIdRazaMascota(razaMascotasFacadeLocal.find(objMascotas.getIdRazaMascota().getIdRazaMascota()));
                mascotasFacadeLocal.create(objMascotas);
                listaMascotas.add(objMascotas);
                mensaje = "swal('La mascota' , ' Se ha registrado exitosamente ', 'success')";
            } else {
                mensaje = "swal('Excede número de mascotas' , ' Registradas por cliente ', 'error')";
            }
        } catch (Exception e) {
            mensaje = "swal('La mascota' , ' No ha sido registrada ', 'error')";
        }
        objMascotas = new Mascotas();
        PrimeFaces.current().executeScript(mensaje);
    }

    public void cargarMascota(Mascotas objCargar) {
        this.objMascotas = objCargar;
    }

    public void actualizarMascota() {
        String mensaje = "";

        try {
            mascotasFacadeLocal.edit(objMascotas);
            listaMascotas.clear();
            listaMascotas.addAll(mascotasFacadeLocal.findAll());
            mensaje = "swal('La mascota' , ' Se ha modificado exitosamente ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('La mascota' , ' No ha sido modificada ', 'error')";
        }
        objMascotas = new Mascotas();
        PrimeFaces.current().executeScript(mensaje);
    }
    
    public void eliminarMascota(Mascotas objEliminar) {
        String mensaje = "";
        try {
            mascotasFacadeLocal.remove(objEliminar);
            listaMascotas.remove(objEliminar);
            mensaje = "swal('Mascota' , ' Eliminada ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('La mascota' , ' No ha sido eliminada ', 'error')";
        }
        PrimeFaces.current().executeScript(mensaje);
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
            hsr.addHeader("Content-disposition", "attachment; filename=Listado Mascotas.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();
           
        } catch (JRException e) {
            System.out.println("edu.petlovers.controller.RegistroMascotaRequest.descargaReporte() " + e.getMessage());
        } catch(IOException i){
            System.out.println("edu.petlovers.controller.RegistroMascotaRequest.descargaReporte() " + i.getMessage());
        } catch (SQLException q){
            System.out.println("edu.petlovers.controller.RegistroMascotaRequest.descargaReporte() " + q.getMessage());
        }

    }
    
    public Mascotas getObjMascotas() {
        return objMascotas;
    }

    public void setObjMascotas(Mascotas objMascotas) {
        this.objMascotas = objMascotas;
    }

    public ArrayList<Mascotas> getListaMascotas() {
        return listaMascotas;
    }

    public void setListaMascotas(ArrayList<Mascotas> listaMascotas) {
        this.listaMascotas = listaMascotas;
    }

    public ArrayList<Clientes> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Clientes> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ArrayList<TipoMascotas> getListaTipoMascota() {
        return listaTipoMascota;
    }

    public void setListaTipoMascota(ArrayList<TipoMascotas> listaTipoMascota) {
        this.listaTipoMascota = listaTipoMascota;
    }

    public ArrayList<RazaMascotas> getListaRazaMascota() {
        return listaRazaMascota;
    }

    public void setListaRazaMascota(ArrayList<RazaMascotas> listaRazaMascota) {
        this.listaRazaMascota = listaRazaMascota;
    }

}
