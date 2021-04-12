/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.entity.Administradores;
import edu.petlovers.entity.Inventario;
import edu.petlovers.entity.Productos;
import edu.petlovers.local.AdministradoresFacadeLocal;
import edu.petlovers.local.InventarioFacadeLocal;
import edu.petlovers.local.ProductosFacadeLocal;

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
@Named(value = "inventarioRequest")
@RequestScoped
public class InventarioRequest implements Serializable {

    @EJB
    InventarioFacadeLocal inventarioFacadeLocal;

    @EJB
    AdministradoresFacadeLocal administradoresFacadeLocal;

    @EJB
    ProductosFacadeLocal productosFacadeLocal;

    private Inventario objInventario = new Inventario();
    
    private ArrayList<Inventario> listaInventario = new ArrayList();
    private ArrayList<Administradores> listaAdministradores = new ArrayList<>();
    private ArrayList<Productos> listaProductos = new ArrayList<>();

    private Integer idToUpdate;
    
    private int idInventario;
    private ArrayList<Inventario> unicoInventario = new ArrayList<>();

    public InventarioRequest() {
    }

    @PostConstruct
    public void postInventario() {
        listaInventario.addAll(inventarioFacadeLocal.findAll());
        listaAdministradores.addAll(administradoresFacadeLocal.findAll());       
        listaProductos.addAll(productosFacadeLocal.findAll());
        objInventario.setIdAdmin(new Administradores());    
        objInventario.setIdProducto(new Productos());
    }

    public void registrarInventario() {
        String mensajeSw = "";

        try {
            objInventario.setIdAdmin(administradoresFacadeLocal.find(objInventario.getIdAdmin().getIdAdmin()));
objInventario.setIdProducto(productosFacadeLocal.find(objInventario.getIdProducto().getIdProducto()));
            inventarioFacadeLocal.create(objInventario);
            listaInventario.add(objInventario);
            mensajeSw = "swal('Inventario creado' , 'con exito' , 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('El producto' , 'no fue registrado' , 'error');";
        }
        objInventario = new Inventario();
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void cargaInventario(Inventario objCargar) {
        this.objInventario = objCargar;
        this.idToUpdate = objCargar.getIdInventario();
    }

    public void removerInventario(Inventario objInventario) {
        String mensajeSw = "";

        try {
            inventarioFacadeLocal.remove(objInventario);
            listaInventario.remove(objInventario);
            mensajeSw = "swal('Inventario' , ' Eliminado ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('El inventario' , ' No ha sido eliminado ', 'error')";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void actualizarInventario() {
        String mensajeSw = "";

        try {
            inventarioFacadeLocal.edit(objInventario);
            listaInventario.clear();
            listaInventario.addAll(inventarioFacadeLocal.findAll());
            mensajeSw = "swal('El inventario' , ' se ha modificado exitosamente ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('El inventario' , ' No ha sido modificado ', 'error')";
        }
        objInventario = new Inventario();
        PrimeFaces.current().executeScript(mensajeSw);
    }
    
    public void buscaInventario() {
        Inventario busInvent = new Inventario();
        String mensajeSw = "";
        try {
            busInvent = inventarioFacadeLocal.buscarInventario(idInventario);
            if(busInvent.getIdInventario() == null) {
                mensajeSw = "swal('El inventario' , ' No se encuentra registrado ', 'error')";
            } else {
                unicoInventario.add(busInvent);
                mensajeSw = "swal('Inventario' , ' Encontrado exitosamente ', 'success')";
            }
        } catch (Exception e) {
            mensajeSw = "swal('El inventario' , ' No se encuentra registrado ', 'error')";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }
    
    public void vaciarBusqueda() {
        unicoInventario = new ArrayList<>();
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
            hsr.addHeader("Content-disposition", "attachment; filename=Reporte Inventario.pdf");
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

    public ArrayList<Inventario> getListaInventario() {
        return listaInventario;
    }
    
    public void setListaInventario(ArrayList<Inventario> listaInventario) {
        this.listaInventario = listaInventario;
    }

    public Inventario getObjInventario() {
        return objInventario;
    }

    public void setObjInventario(Inventario objInventario) {
        this.objInventario = objInventario;
    }

    public ArrayList<Administradores> getListaAdministradores() {
        return listaAdministradores;
    }

    public void setListaAdministradores(ArrayList<Administradores> listaAdministradores) {
        this.listaAdministradores = listaAdministradores;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public ArrayList<Inventario> getUnicoInventario() {
        return unicoInventario;
    }

    public void setUnicoInventario(ArrayList<Inventario> unicoInventario) {
        this.unicoInventario = unicoInventario;
    }

    public ArrayList<Productos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Productos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    
}
