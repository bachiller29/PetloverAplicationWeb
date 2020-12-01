/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.entity.Inventario;
import edu.petlovers.entity.MarcaProductos;
import edu.petlovers.entity.Productos;
import edu.petlovers.entity.Proveedores;
import edu.petlovers.entity.TipoProductos;
import edu.petlovers.local.InventarioFacadeLocal;
import edu.petlovers.local.MarcaProductosFacadeLocal;
import edu.petlovers.local.ProductosFacadeLocal;
import edu.petlovers.local.ProveedoresFacadeLocal;
import edu.petlovers.local.TipoProductosFacadeLocal;
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
public class ProductosRequest implements Serializable {

    @EJB
    ProductosFacadeLocal productosFacadeLocal;

    @EJB
    ProveedoresFacadeLocal proveedoresFacadeLocal;

    @EJB
    TipoProductosFacadeLocal tipoProductosFacadeLocal;

    @EJB
    InventarioFacadeLocal inventarioFacadeLocal;

    @EJB
    MarcaProductosFacadeLocal marcaProductosFacadeLocal;

    private Productos objProductos = new Productos();

    private Integer idToUpdate;
    ArrayList<Productos> listaProductos = new ArrayList();

    /**
     * Creates a new instance of ProductosRequest
     */
    public ProductosRequest() {
    }

    @PostConstruct
    public void postProductos() {
        listaProductos.addAll(productosFacadeLocal.findAll());
        objProductos.setNitProveedor(new Proveedores());
        objProductos.setIdTipoProducto(new TipoProductos(1));
        objProductos.setIdInventario(new Inventario(1));
        objProductos.setIdMarcaProducto(new MarcaProductos(1));
    }

    public void registrarProducto() {
        String mensajeSw = "";

        try {
            objProductos.setNitProveedor(proveedoresFacadeLocal.find(objProductos.getNitProveedor().getNitProveedor()));
            objProductos.setIdTipoProducto(tipoProductosFacadeLocal.find(objProductos.getIdTipoProducto().getIdTipoProducto()));
            objProductos.setIdInventario(inventarioFacadeLocal.find(1));
            objProductos.setIdMarcaProducto(marcaProductosFacadeLocal.find(1));

            productosFacadeLocal.create(objProductos);
            listaProductos.add(objProductos);
            mensajeSw = "swal('Producto creado' , 'con exito' , 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('El producto' , 'no fue registrado' , 'error');";
        }
        objProductos = new Productos();
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void cargarFormulario(Productos objCargar) {
        this.objProductos = objCargar;
        this.idToUpdate = objCargar.getIdProducto();
    }

    public void removerProducto(Productos objProductos) {
        productosFacadeLocal.remove(objProductos);
        listaProductos.remove(objProductos);
    }

    public void actualizarProducto() {

//        objProductos.setIdProducto(this.idToUpdate);
        productosFacadeLocal.edit(objProductos);
        listaProductos.clear();
        listaProductos.addAll(productosFacadeLocal.findAll());
    }

    public Productos getObjProductos() {
        return objProductos;
    }

    public void setObjProductos(Productos objProductos) {
        this.objProductos = objProductos;
    }

    public ArrayList<Productos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Productos> listaProductos) {
        this.listaProductos = listaProductos;
    }

}
