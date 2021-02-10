/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.entity.Proveedores;
import edu.petlovers.local.ProveedoresFacadeLocal;
import edu.petlovers.utilities.Email;
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
@Named(value = "productosRequest")
@RequestScoped
public class ProveedorRequest implements Serializable {

    @EJB
    ProveedoresFacadeLocal proveedorFacadeLocal;

    private Proveedores objProveedores = new Proveedores();

    ArrayList<Proveedores> listaProveedores = new ArrayList();

    private int idProveedor;
    private ArrayList<Proveedores> unicoProveedor = new ArrayList<>();

    public ProveedorRequest() {
    }

    @PostConstruct
    public void postProveedor() {
        listaProveedores.addAll(proveedorFacadeLocal.findAll());
    }

    public void registrarProveedor() {
        String mensajeSw = "";

        try {
            proveedorFacadeLocal.create(objProveedores);
            listaProveedores.add(objProveedores);
            mensajeSw = "swal('Proveedor creado' , 'con exito' , 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('El proveedor' , 'no fue registrado' , 'error');";
        }
        objProveedores = new Proveedores();
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void removerProveedor(Proveedores objProveedor) {
        String mensajeSw = "";

        try {
            proveedorFacadeLocal.remove(objProveedor);
            listaProveedores.remove(objProveedor);
            mensajeSw = "swal('Proveedor' , ' Eliminado ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('El proveedor' , 'no ha sido eliminado' , 'error');";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void cargarProveedor(Proveedores objProveedor) {
        this.objProveedores = objProveedor;
    }

    public void actualizarProveedor() {
        String mensajeSw = "";

        try {
            proveedorFacadeLocal.edit(objProveedores);
            listaProveedores.clear();
            listaProveedores.addAll(proveedorFacadeLocal.findAll());
            mensajeSw = "swal('El proveedor' , ' se ha modificado exitosamente ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('El proveedor' , ' No ha sido modificado ', 'error')";
        }
        objProveedores = new Proveedores();
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void buscaProveedor() {
        Proveedores busProve = new Proveedores();
        String mensajeSw = "";
        try {
            busProve = proveedorFacadeLocal.buscarProveedor(idProveedor);
            if (busProve.getNitProveedor() == null) {
                mensajeSw = "swal('El proveedor' , ' No se encuentra registrado ', 'error')";
            } else {
                unicoProveedor.add(busProve);
                mensajeSw = "swal('Proveedor' , ' Encontrado exitosamente ', 'success')";
            }
        } catch (Exception e) {
            mensajeSw = "swal('El proveedor' , ' No se encuentra registrado ', 'error')";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void vaciarBusqueda() {
        unicoProveedor = new ArrayList<>();
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
            hsr.addHeader("Content-disposition", "attachment; filename=Listado Proveedor.pdf");
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

    public Proveedores getObjProveedores() {
        return objProveedores;
    }

    public void setObjProveedores(Proveedores objProveedores) {
        this.objProveedores = objProveedores;
    }

    public ArrayList<Proveedores> getListaProveedores() {
        return listaProveedores;
    }

    public void setListaProveedores(ArrayList<Proveedores> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public ArrayList<Proveedores> getUnicoProveedor() {
        return unicoProveedor;
    }

    public void setUnicoProveedor(ArrayList<Proveedores> unicoProveedor) {
        this.unicoProveedor = unicoProveedor;
    }

}
