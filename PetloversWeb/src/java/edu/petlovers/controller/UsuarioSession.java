/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.entity.Usuarios;
import edu.petlovers.local.UsuariosFacadeLocal;
import java.io.Serializable;
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

    private String emailI = "";
    private String contrasenaIn = "";

    public UsuarioSession() {
    }

    public void inicioSecion() {
        String mensajeSw = "";

        try {
            usuLogin = usuariosFacadeLocal.loginUsuario(emailI, contrasenaIn);
            if (usuLogin.getIdUsuario() == null) {
                mensajeSw = "swal('El usuario' , 'no se encuentra registrado' , 'error');";
            } else {
                usuLogin.setUltimoIngreso(new Date());
                if (usuLogin.getIdTipoRol().getIdTipoRol() == 1 || usuLogin.getIdTipoRol().getIdTipoRol() == 2) {
                    FacesContext fc = FacesContext.getCurrentInstance();
                    fc.getExternalContext().redirect("../DocAdmin/home.xhtml");
                } else {
                    if (usuLogin.getIdTipoRol().getIdTipoRol() == 3) {
                        FacesContext fc = FacesContext.getCurrentInstance();
                        fc.getExternalContext().redirect("../DocPersonalDeTienda/home.xhtml");
                    } else {
                        FacesContext fc = FacesContext.getCurrentInstance();
                        fc.getExternalContext().redirect("../DocVentas/checkout.xhtml");
                    }
                }
            }
        } catch (Exception e) {
            mensajeSw = "swal('El usuario' , 'no se encuentra registrado' , 'error');";
        }
        PrimeFaces.current().executeScript(mensajeSw);
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

}
