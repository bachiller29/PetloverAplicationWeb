/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.entity.Citas;
import edu.petlovers.entity.HistoriaClinica;
import edu.petlovers.local.CitasFacadeLocal;
import edu.petlovers.local.HistoriaClinicaFacadeLocal;
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
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
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
@Named(value = "historiaClinicaView")
public class HistoriaClinicaView implements Serializable {

    @EJB
    HistoriaClinicaFacadeLocal historiaClinicaFacadeLocal;
    @EJB
    CitasFacadeLocal citasFacadeLocal;

    private HistoriaClinica objHistoriaC = new HistoriaClinica();
    private Citas citaSeleccionada = new Citas();

    private ArrayList<HistoriaClinica> listaHistoriaC = new ArrayList<>();
    private ArrayList<Citas> listaIdCitas = new ArrayList<>();

    private int idCita;

    private int idHistoria;
    private ArrayList<HistoriaClinica> unicaHistoriaC = new ArrayList<>();

    public HistoriaClinicaView() {
    }

    @PostConstruct
    public void postHistoriaC() {
        listaHistoriaC.addAll(historiaClinicaFacadeLocal.findAll());
        listaIdCitas.addAll(citasFacadeLocal.findAll());
//        objHistoriaC.setIdCitas(new Citas());
//        objHistoriaC.setIdMascota(new Mascotas());
//        objHistoriaC.setIdCliente(new Clientes());

    }

    public void cargaCita() {
        citaSeleccionada = citasFacadeLocal.find(idCita);
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

    public String nombrePropietario() {
        try {
            return citaSeleccionada.getIdMascota().getIdCliente().getIdUsuario().getNombres() + " " + citaSeleccionada.getIdMascota().getIdCliente().getIdUsuario().getApellidos();
        } catch (Exception e) {
            return " ";
        }
    }

    public void crearHistoriaC() {
        String mensaje = "";

        try {
            objHistoriaC.setIdCitas(getCitaSeleccionada());
//            objHistoriaC.setIdCitas(citasFacadeLocal.find(objHistoriaC.getIdCitas().getIdCitas()));
            objHistoriaC.setIdMascota(getCitaSeleccionada().getIdMascota());
//            objHistoriaC.setIdMascota(mascotasFacadeLocal.find(objHistoriaC.getIdMascota().getIdMascota()));
            objHistoriaC.setIdCliente(getCitaSeleccionada().getIdMascota().getIdCliente());
//            objHistoriaC.setIdCliente(clientesFacadeLocal.find(objHistoriaC.getIdCliente().getIdCliente()));
            int cantidadHistorias = historiaClinicaFacadeLocal.cantidadHistoriasC(objHistoriaC.getIdCitas().getIdCitas(), objHistoriaC.getIdMascota().getIdMascota(), objHistoriaC.getIdCliente().getIdCliente());
            if (cantidadHistorias < 1) {
                historiaClinicaFacadeLocal.create(objHistoriaC);
                listaHistoriaC.add(objHistoriaC);
                mensaje = "swal('La historia clinica' , ' Se ha registrado exitosamente ', 'success')";
            } else {
                mensaje = "swal('Excede la cantidad' , ' de historias clinicas por cita ', 'error')";
            }
        } catch (Exception e) {
            mensaje = "swal('La historia clinica' , ' No ha sido registrada ', 'error')";
        }
        citaSeleccionada = new Citas();
        objHistoriaC = new HistoriaClinica();
        PrimeFaces.current().executeScript(mensaje);
    }

    public void cargarHistoriaC(HistoriaClinica objCargar) {
        this.objHistoriaC = objCargar;
    }

    public void actualizarHistoriaC() {
        String mensaje = "";

        try {
//            objHistoriaC.setIdCitas(objHistoriaC.getIdCitas());
//            objHistoriaC.setIdMascota(objHistoriaC.getIdMascota());
//            objHistoriaC.setIdCliente(objHistoriaC.getIdCliente());
            historiaClinicaFacadeLocal.edit(objHistoriaC);
            listaHistoriaC.clear();
            listaHistoriaC.addAll(historiaClinicaFacadeLocal.findAll());
            mensaje = "swal('La historia clinica' , ' se ha modificado exitosamente ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('La historia clinica' , ' No ha sido modificada ', 'error')";
        }
        citaSeleccionada = new Citas();
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
            if (histCli.getIdCitas() == null) {
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

    public void descargaHistoriaClinica(String idHistoriaClinica)  {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("RutaLogo", context.getRealPath("/assets/img/logo/fondo1.jpg"));
            parametro.put("IdHistoriaClinica", idHistoriaClinica);
            parametro.put("RutaImagenFondo", context.getRealPath("/assets/img/logo/logoPetlovers2.png"));
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/petlovers", "petloversuser", "1234");
            System.out.println("Catalogo : " + conec.getCatalog());
            
            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/petlovers/reports/HistoriaClinica.jasper"));
             
            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);
            
            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Historia Clinica.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();
           
        } catch (JRException e) {
            System.out.println("edu.petlovers.controller.HistoriaClinicaView.descargaHistoriaClinica() " + e.getMessage());
        } catch (IOException i){
            System.out.println("edu.petlovers.controller.HistoriaClinicaView.descargaHistoriaClinica() " + i.getMessage());
        } catch (SQLException q){
            System.out.println("edu.petlovers.controller.HistoriaClinicaView.descargaHistoriaClinica() " + q.getMessage());
        }
    }
    
    
    
    public Citas getCitaSeleccionada() {
        return citaSeleccionada;
    }

    public void setCitaSeleccionada(Citas citaSeleccionada) {
        this.citaSeleccionada = citaSeleccionada;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
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
