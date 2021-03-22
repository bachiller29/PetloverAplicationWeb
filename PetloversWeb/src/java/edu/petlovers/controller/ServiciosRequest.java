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
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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
            hsr.addHeader("Content-disposition", "attachment; filename=Listado Servicios.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();
           
        } catch (JRException e) {
            System.out.println("edu.petlovers.controller.ServiciosRequest.descargaReporte() " + e.getMessage());
        } catch(IOException i){
            System.out.println("edu.petlovers.controller.ServiciosRequest.descargaReporte() " + i.getMessage());
        } catch (SQLException q){
            System.out.println("edu.petlovers.controller.ServiciosRequest.descargaReporte() " + q.getMessage());
        }

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
