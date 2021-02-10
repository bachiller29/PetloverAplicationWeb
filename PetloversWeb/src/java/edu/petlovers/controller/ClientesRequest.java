/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.entity.Clientes;
import edu.petlovers.local.ClientesFacadeLocal;
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
import javax.faces.bean.RequestScoped;
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
 * @author wsbachiller
 */
@ManagedBean
@Named(value = "clientesRequest")
@RequestScoped
public class ClientesRequest implements Serializable {

    @EJB
    ClientesFacadeLocal clientesFacadeLocal;

    private Clientes objClientes = new Clientes();
    private ArrayList<Clientes> listaClientes = new ArrayList();
    private int idCliente;
    private ArrayList<Clientes> unicoClientes = new ArrayList();
    /**
     * Creates a new instance of ClientesRequest
     */
    public ClientesRequest() {
    }

    @PostConstruct
    public void postCliente() {
        listaClientes.addAll(clientesFacadeLocal.findAll());
    }

    public void registrarCliente() {
        String mensajeSw = "";

        try {
            clientesFacadeLocal.create(objClientes);
            listaClientes.add(objClientes);
            mensajeSw = "swal('Cliente creado' , 'con exito' , 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('El cliente' , 'no fue registrado' , 'error');";
        }
        objClientes = new Clientes();
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void removerCliente(Clientes objCliente) {
        String mensajeSw = "";

        try {
            clientesFacadeLocal.remove(objCliente);
            listaClientes.remove(objCliente);
            mensajeSw = "swal('Cliente' , ' Eliminado ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('El cliente' , 'no ha sido eliminado' , 'error');";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }
    
    public void cargarCliente(Clientes objCliente){
        this.objClientes = objCliente;
         this.idCliente = objClientes.getIdCliente();
    }

    public void actualizarCliente() {
        String mensajeSw = "";

        try {
            clientesFacadeLocal.edit(objClientes);
            listaClientes.clear();
            listaClientes.addAll(clientesFacadeLocal.findAll());
            mensajeSw = "swal('El cliente' , ' se ha modificado exitosamente ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('El cliente' , ' No ha sido modificado ', 'error')";
        }
        objClientes = new Clientes();
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void buscarCliente() {
        Clientes busCliente = new Clientes();
        String mensajeSw = "";
        try {
            busCliente = clientesFacadeLocal.buscarCliente(idCliente);
            if (busCliente.getIdCliente() == null) {
                mensajeSw = "swal('El cliente' , ' no se encuentra registrado ', 'error')";
            } else {
                unicoClientes.add(busCliente);
                mensajeSw = "swal('Cliente' , ' Encontrado exitosamente ', 'success')";
            }
        } catch (Exception e) {
            mensajeSw = "swal('El cliente' , ' No se encuentra registrado ', 'error')";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void vaciarBuqueda() {
        listaClientes = new ArrayList<>();
    }
    
    public void descargaReporte(String nombreReporte, String nombreUsuario) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("RutaLogo", context.getRealPath("/assets/img/logo/fondo1.jpg"));
            parametro.put("UsuarioReporte", nombreUsuario);
            parametro.put("RutaImagenFondo", context.getRealPath("/assets/img/logo/logoPetlovers2.png"));       
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/petlovers", "petloversuser", "1234");
            System.out.println("Catalogo : " + conec.getCatalog());

            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/petlovers/reports/" + nombreReporte + ".jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Reporte Clientes.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (JRException e) {
            System.out.println("edu.petlovers.controller.CitaView.descargaReporte() " + e.getMessage());
        } catch (IOException i) {
            System.out.println("edu.petlovers.controller.CitaView.descargaReporte() " + i.getMessage());
        } catch (SQLException q) {
            System.out.println("edu.petlovers.controller.CitaView.descargaReporte() " + q.getMessage());
        }

    }

    public ArrayList<Clientes> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Clientes> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Clientes getObjClientes() {
        return objClientes;
    }

    public void setObjClientes(Clientes objClientes) {
        this.objClientes = objClientes;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public ArrayList<Clientes> getUnicoClientes() {
        return unicoClientes;
    }

    public void setUnicoClientes(ArrayList<Clientes> unicoClientes) {
        this.unicoClientes = unicoClientes;
    }

}
