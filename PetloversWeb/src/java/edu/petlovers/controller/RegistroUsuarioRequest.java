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
import java.util.Date;
import javax.annotation.PostConstruct;
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
    @EJB
    TipoRolFacadeLocal tipoRolFacadeLocal;
    
    Usuarios usRed = new Usuarios();
    
    private String correoRecuperar = "";

    public RegistroUsuarioRequest() {
    }
    
    @PostConstruct
    public void postUsuario() {
        usRed.setIdTipoRol(new TipoRol(7));
    }
    
    public void crearUsuario() {
        String mensajeSw = "";
        try {
            usRed.setIdTipoRol(tipoRolFacadeLocal.find(7));
            usRed.setFechaRegistro(new Date());
            usuariosFacadeLocal.create(usRed);
            mensajeSw = "swal('Usuario registrado' , ' con exito ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('El usuario' , 'ya se encuentra registrado', 'error');";
        }
        usRed = new Usuarios();
        PrimeFaces.current().executeScript(mensajeSw);
    }
    
    public void recuperarClave() {
        Usuarios usuRecuperar = new Usuarios();
        String mensajeSw = "";
        try {
            usuRecuperar = usuariosFacadeLocal.recuperarClave(correoRecuperar);
            if (usuRecuperar.getNombres() == null) {
                mensajeSw = "swal('El correo' , 'no se encuentra registrado' , 'error');";
            } else {
                Email.sendClaves(usuRecuperar.getEmail(),
                        usuRecuperar.getNombres() + " " + usuRecuperar.getApellidos(),
                        usuRecuperar.getEmail(),
                        usuRecuperar.getContrasena());
                mensajeSw = "swal('La clave fue enviada' , 'a su correo electronico' , 'success');";
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
