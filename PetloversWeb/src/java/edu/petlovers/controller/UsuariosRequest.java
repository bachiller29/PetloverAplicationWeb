/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.entity.TipoRol;
import edu.petlovers.entity.Usuarios;
import edu.petlovers.local.TipoRolFacadeLocal;
import edu.petlovers.local.UsuariosFacadeLocal;
import edu.petlovers.utilities.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author HP
 */
@ManagedBean
@RequestScoped
public class UsuariosRequest implements Serializable {

    @EJB
    UsuariosFacadeLocal usuariosFacadeLocal;
    @EJB
    TipoRolFacadeLocal tipoRolFacadeLocal;

    private Usuarios objUsuario = new Usuarios();

    private ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
    private ArrayList<TipoRol> listaRoles = new ArrayList<>();

    private int idUsuario;
    private ArrayList<Usuarios> unicoUsuario = new ArrayList<>();

    public UsuariosRequest() {
    }

    @PostConstruct
    public void postUsuario() {
        listaUsuarios.addAll(usuariosFacadeLocal.findAll());
        listaRoles.addAll(tipoRolFacadeLocal.findAll());
        objUsuario.setIdTipoRol(new TipoRol());
    }

    public void crearUsuario() {
        String mensaje = "";

        try {
            objUsuario.setIdTipoRol(tipoRolFacadeLocal.find(objUsuario.getIdTipoRol().getIdTipoRol()));
            objUsuario.setFechaRegistro(new Date());
            usuariosFacadeLocal.create(objUsuario);
            mensaje = "swal('El usuario' , ' Se ha registrado exitosamente ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('El usuario' , ' No ha sido registrado ', 'error')";
        }
        objUsuario = new Usuarios();
        PrimeFaces.current().executeScript(mensaje);
    }

    public void cargarUsuario(Usuarios objCargar) {
        this.objUsuario = objCargar;
    }

    public void actualizarUsuario() {
        String mensaje = "";

        try {
            usuariosFacadeLocal.edit(objUsuario);
            listaUsuarios.clear();
            listaUsuarios.addAll(usuariosFacadeLocal.findAll());
            mensaje = "swal('El usuario' , ' se ha modificado exitosamente ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('El usuario' , ' No ha sido modificado ', 'error')";
        }
        objUsuario = new Usuarios();
        PrimeFaces.current().executeScript(mensaje);
    }

    public void eliminarUsuario(Usuarios objEliminar) {
        String mensaje = "";
        try {
            usuariosFacadeLocal.remove(objEliminar);
            listaUsuarios.remove(objEliminar);
            mensaje = "swal('Usuario' , ' Eliminado ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('El usuario' , ' No ha sido eliminado ', 'error')";
        }
        PrimeFaces.current().executeScript(mensaje);
    }

    public void buscaUsuario() {
        Usuarios busUsua = new Usuarios();
        String mensaje = "";
        try {
            busUsua = usuariosFacadeLocal.buscarUsuario(idUsuario);
            if (busUsua.getIdUsuario() == null) {
                mensaje = "swal('El usuario' , ' No se encuentra registrado ', 'error')";
            } else {
                unicoUsuario.add(busUsua);
                mensaje = "swal('Usuario' , ' Encontrado exitosamente ', 'success')";
            }
        } catch (Exception e) {
            mensaje = "swal('El usuario' , ' No se encuentra registrado ', 'error')";
        }
        PrimeFaces.current().executeScript(mensaje);
    }

    public void vaciarBusqueda() {
        unicoUsuario = new ArrayList<>();
    }

    public void correoMasivo() {
        try {
            int total = listaUsuarios.size();
            int counter = 1;
            for (Usuarios lUsuario : listaUsuarios) {
                Email.sendWelcome(lUsuario.getEmail(),
                        lUsuario.getNombres() + " " + lUsuario.getApellidos(),
                        lUsuario.getEmail(), lUsuario.getContrasena());
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Send " + counter + " of " + total);
                counter++;
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void correoMasivoDos() {
        try {
            for (Usuarios lUsuario : listaUsuarios) {
                Email.send(lUsuario.getEmail(),
                        lUsuario.getNombres() + " " + lUsuario.getApellidos(),
                        lUsuario.getEmail(), lUsuario.getContrasena());
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public Usuarios getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(Usuarios objUsuario) {
        this.objUsuario = objUsuario;
    }

    public ArrayList<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public ArrayList<TipoRol> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(ArrayList<TipoRol> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ArrayList<Usuarios> getUnicoUsuario() {
        return unicoUsuario;
    }

    public void setUnicoUsuario(ArrayList<Usuarios> unicoUsuario) {
        this.unicoUsuario = unicoUsuario;
    }

}
