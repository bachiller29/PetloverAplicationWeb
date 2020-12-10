/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.entity.Usuarios;
import edu.petlovers.local.UsuariosFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;

/**
 *
 * @author wsbachiller
 */
@ManagedBean
@SessionScoped
@Named(value = "usuarioSession")
public class UsuarioSession implements Serializable {

    @EJB
    UsuariosFacadeLocal usuariosFacadeLocal;

    private Usuarios usuLogin = new Usuarios();

    private ArrayList<Usuarios> usuariologeado = new ArrayList<>();
    
    private String emailI = "";
    private String contrasenaIn = "";

    public UsuarioSession() {
    }

    public void inicioSecion() {
        String mensaje = "";

        try {
            usuLogin = usuariosFacadeLocal.loginUsuario(emailI, contrasenaIn);
            if (usuLogin.getIdUsuario() == null) {
                mensaje = "swal('El usuario' , 'no se encuentra registrado' , 'error');";
            } else {
                usuLogin.setUltimoIngreso(new Date());
                if (usuLogin.getIdTipoRol().getIdTipoRol() == 1 || usuLogin.getIdTipoRol().getIdTipoRol() == 2) {
                    usuariologeado.add(usuLogin);
                    FacesContext fc = FacesContext.getCurrentInstance();
                    fc.getExternalContext().redirect("../DocAdmin/home.xhtml");
                } else {
                    usuLogin.setUltimoIngreso(new Date());
                    if (usuLogin.getIdTipoRol().getIdTipoRol() == 3) {
                        usuariologeado.add(usuLogin);
                        FacesContext fc = FacesContext.getCurrentInstance();
                        fc.getExternalContext().redirect("../DocPersonalDeTienda/home.xhtml");
                    } else {
                        usuLogin.setUltimoIngreso(new Date());
                        usuariologeado.add(usuLogin);
                        FacesContext fc = FacesContext.getCurrentInstance();
                        fc.getExternalContext().redirect("../DocCliente/home.xhtml");
                    }
                }
            }
        } catch (Exception e) {
            mensaje = "swal('El usuario' , 'no se encuentra registrado' , 'error');";
        }
        PrimeFaces.current().executeScript(mensaje);
    }

    
    public void cerrarSesion() {
        usuLogin = null;
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            ((HttpSession) ext.getSession(false)).invalidate();
            fc.getExternalContext().redirect("../index.xhtml");
        } catch (Exception e) {
            System.out.println("Error cerrando sesion UsuarioSession:cerrarSesion " + e.getMessage());
        }
    }

    
    public void actualizarMisDatos() {
        String mensaje = "";
        try {
            usuariosFacadeLocal.edit(usuLogin);
            mensaje = "swal('Se actualizaron' , ' Sus datos exitosamente ', 'success')";
        } catch (Exception e) {
            mensaje = "swal('No se puede' , ' Actualizar mis datos ', 'error')";
        }
        PrimeFaces.current().executeScript(mensaje);
    }

    
    public String getEmailI() {
        return emailI;
    }

    public void setEmailI(String emailI) {
        this.emailI = emailI;
    }

    public String getContrasenaIn() {
        return contrasenaIn;
    }

    public void setContrasenaIn(String contrasenaIn) {
        this.contrasenaIn = contrasenaIn;
    }

    public Usuarios getUsuLogin() {
        return usuLogin;
    }

    public void setUsuLogin(Usuarios usuLogin) {
        this.usuLogin = usuLogin;
    }

    public ArrayList<Usuarios> getUsuariologeado() {
        return usuariologeado;
    }

    public void setUsuariologeado(ArrayList<Usuarios> usuariologeado) {
        this.usuariologeado = usuariologeado;
    }

}
