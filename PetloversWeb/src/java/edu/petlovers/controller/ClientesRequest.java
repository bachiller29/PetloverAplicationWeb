/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.entity.Clientes;
import edu.petlovers.local.ClientesFacadeLocal;
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
@Named(value = "clientesRequest")
@RequestScoped
public class ClientesRequest implements Serializable {

    @EJB
    ClientesFacadeLocal clientesFacadeLocal;

    private Clientes objClientes = new Clientes();
    private ArrayList<Clientes> listaClientes = new ArrayList();
    private int idCliente;

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
        PrimeFaces.current().executeInitScript(mensajeSw);
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
                listaClientes.add(busCliente);
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

}
