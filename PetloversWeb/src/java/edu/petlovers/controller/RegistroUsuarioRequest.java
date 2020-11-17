/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.entity.Usuarios;
import edu.petlovers.local.UsuariosFacadeLocal;
import edu.petlovers.utilities.Email;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
/*import org.primefaces.PrimeFaces;*/

/**
 *
 * @author wsbachiller
 */
@ManagedBean
@Named(value = "registroUsuarioRequest")
@RequestScoped
public class RegistroUsuarioRequest implements Serializable {
    
    @EJB
    UsuariosFacadeLocal usuariosFacadeLocal;
    
    Usuarios usRed = new Usuarios();
    String correoRecuperar = "";

    /**
     * Creates a new instance of RegistroUsuarioRequest
     */
    public RegistroUsuarioRequest() {
    }
    
    public void crearUsuario() {
        String mensajeSw = "";
        try {
            usuariosFacadeLocal.create(usRed);
            mensajeSw = "swal('Usuario creado' , 'con exito' , 'success')";
            
        } catch (Exception e) {
            mensajeSw = "swal('El usuario' , 'ya se encuentra registrado' , 'error');";
        }
        usRed = new Usuarios();
             PrimeFaces.current().executeScript(mensajeSw);
    }
    
    public void recuperarClave() {
        Usuarios usuRecuperar = new Usuarios();
        String mensajeSw = "";
        try {
            usRed.setFechaRegistro(new Date());
            usuRecuperar = usuariosFacadeLocal.recuperarClave(correoRecuperar);
            if (usuRecuperar.getNombres() == null) {
                mensajeSw = "swal('El correo' , 'no se encuentra registrado' , 'error');";
            } else {
                Email.sendClaves(usuRecuperar.getEmail(),
                        usuRecuperar.getNombres() + " " + usuRecuperar.getApellidos(),
                        usuRecuperar.getEmail(),
                        usuRecuperar.getContrasena());
                mensajeSw = "swal('La clave es invalida' , 'a su correo electronico' , 'success');";
            }
        } catch (Exception e) {
            mensajeSw = "swal('El correo' , 'no se encuentra registrado' , 'error');";
        }
                     PrimeFaces.current().executeScript(mensajeSw);
    }
    
    public Usuarios getUsRed() {
        return usRed;
    }
    
    public void setUsRed(Usuarios usRed) {
        this.usRed = usRed;
    }
    
    public String getCorreoRecuperar() {
        return correoRecuperar;
    }
    
    public void setCorreoRecuperar(String correoRecuperar) {
        this.correoRecuperar = correoRecuperar;
    }
    
}
