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
    private boolean nuevoActualizar;
    private Integer idToUpdate;

    /**
     * Creates a new instance of InventarioRequest
     */
    public InventarioRequest() {
    }
    
    @PostConstruct
    public void postInventario() {
        listaInventario.addAll(inventarioFacadeLocal.findAll());
        objInventario.setIdAdmin(new Administradores());
        objInventario.setIdEntradaProductos(new EntradaProductos());
        objInventario.setIdSalidaProductos(new SalidaProductos());
    }
    
    public void registrarInventario() {
        String mensajeSw;
        
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
        this.nuevoActualizar = true;
    }
    
    public void removerInventario(Inventario objInventario) {
        inventarioFacadeLocal.remove(objInventario);
        listaInventario.remove(objInventario);
    }
    
    public void actualizarInventario() {
        inventarioFacadeLocal.edit(objInventario);
        this.nuevoActualizar = true;
        listaInventario.clear();
        listaInventario.addAll(inventarioFacadeLocal.findAll());
    }
    
    public ArrayList<Inventario> getListaInventario() {
        return listaInventario;
    }
    
    public void setListaInventario(ArrayList<Inventario> listaInventario) {
        this.listaInventario = listaInventario;
    }
    
    public boolean isNuevoActualizar() {
        return nuevoActualizar;
    }
    
    public void setNuevoActualizar(boolean nuevoActualizar) {
        this.nuevoActualizar = nuevoActualizar;
    }
    
    public Inventario getObjInventario() {
        return objInventario;
    }
    
    public void setObjInventario(Inventario objInventario) {
        this.objInventario = objInventario;
    }
    
}
