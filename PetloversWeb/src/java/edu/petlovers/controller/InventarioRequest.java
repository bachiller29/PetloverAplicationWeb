/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.entity.Administradores;
import edu.petlovers.entity.EntradaProductos;
import edu.petlovers.entity.Inventario;
import edu.petlovers.entity.SalidaProductos;
import edu.petlovers.local.AdministradoresFacadeLocal;
import edu.petlovers.local.EntradaProductosFacadeLocal;
import edu.petlovers.local.InventarioFacadeLocal;
import edu.petlovers.local.SalidaProductosFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
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
    SalidaProductosFacadeLocal salidaProductosFacadeLocal;

    @EJB
    EntradaProductosFacadeLocal entradaProductosFacadeLocal;

    private Inventario objInventario = new Inventario();
    
    private ArrayList<Inventario> listaInventario = new ArrayList();
    private ArrayList<Administradores> listaAdministradores = new ArrayList<>();
    private ArrayList<EntradaProductos> listaEntradaP = new ArrayList<>();
    private ArrayList<SalidaProductos> listaSalidaP = new ArrayList<>();

    private Integer idToUpdate;
    
    private int idInventario;
    private ArrayList<Inventario> unicoInventario = new ArrayList<>();

    public InventarioRequest() {
    }

    @PostConstruct
    public void postInventario() {
        listaInventario.addAll(inventarioFacadeLocal.findAll());
        listaAdministradores.addAll(administradoresFacadeLocal.findAll());
        listaEntradaP.addAll(entradaProductosFacadeLocal.findAll());
        listaSalidaP.addAll(salidaProductosFacadeLocal.findAll());
        objInventario.setIdAdmin(new Administradores());
        objInventario.setIdEntradaProductos(new EntradaProductos());
        objInventario.setIdSalidaProductos(new SalidaProductos());
    }

    public void registrarInventario() {
        String mensajeSw = "";

        try {
            objInventario.setIdAdmin(administradoresFacadeLocal.find(objInventario.getIdAdmin().getIdAdmin()));
            objInventario.setIdEntradaProductos(entradaProductosFacadeLocal.find(objInventario.getIdEntradaProductos().getIdEntradaProductos()));
            objInventario.setIdSalidaProductos(salidaProductosFacadeLocal.find(objInventario.getIdSalidaProductos().getIdSalidaProductos()));

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

    public ArrayList<EntradaProductos> getListaEntradaP() {
        return listaEntradaP;
    }

    public void setListaEntradaP(ArrayList<EntradaProductos> listaEntradaP) {
        this.listaEntradaP = listaEntradaP;
    }

    public ArrayList<SalidaProductos> getListaSalidaP() {
        return listaSalidaP;
    }

    public void setListaSalidaP(ArrayList<SalidaProductos> listaSalidaP) {
        this.listaSalidaP = listaSalidaP;
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

}
