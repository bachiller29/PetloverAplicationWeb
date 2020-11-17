/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.entity.Proveedores;
import edu.petlovers.local.ProveedoresFacadeLocal;
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
@Named(value = "productosRequest")
@RequestScoped
public class ProveedorRequest implements Serializable {

    @EJB
    ProveedoresFacadeLocal proveedorFacadeLocal;

    private Proveedores objProveedores = new Proveedores();
    ArrayList<Proveedores> listaProveedores = new ArrayList();
    boolean nuevoActualizar;

    /**
     * Creates a new instance of ProveedorRequest
     */
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
            mensajeSw = "swal('Producto creado' , 'con exito' , 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('El producto' , 'no fue registrado' , 'error');";
        }

        objProveedores = new Proveedores();
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void removerProveedor(Proveedores objProveedor) {
        proveedorFacadeLocal.remove(objProveedor);
        listaProveedores.remove(objProveedor);
    }
    
    public void cargarProveedor(Proveedores objProveedor){
        this.objProveedores = objProveedor;
        this.nuevoActualizar = true;
    }
    
    public void actualizarProveedor(){
        proveedorFacadeLocal.edit(objProveedores);
        this.nuevoActualizar = false;
        listaProveedores.clear();
        listaProveedores.addAll(proveedorFacadeLocal.findAll());
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

    public boolean isNuevoActualizar() {
        return nuevoActualizar;
    }

    public void setNuevoActualizar(boolean nuevoActualizar) {
        this.nuevoActualizar = nuevoActualizar;
    }

}
