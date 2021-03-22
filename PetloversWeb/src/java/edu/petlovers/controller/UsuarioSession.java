/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.controller;

import edu.petlovers.dto.CitasDTO;
import edu.petlovers.dto.MascotasDTO;
import edu.petlovers.entity.Citas;
import edu.petlovers.entity.Clientes;
import edu.petlovers.entity.Mascotas;
import edu.petlovers.entity.Usuarios;
import edu.petlovers.local.CitasFacadeLocal;
import edu.petlovers.local.ClientesFacadeLocal;
import edu.petlovers.local.MascotasFacadeLocal;
import edu.petlovers.local.UsuariosFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    @EJB
    MascotasFacadeLocal mascotasFacadeLocal;
    @EJB
    CitasFacadeLocal citasFacadeLocal;
    @EJB
    ClientesFacadeLocal clientesFacadeLocal;
    
    private Usuarios usuLogin = new Usuarios();

    private ArrayList<Usuarios> usuariologeado = new ArrayList<>();
    
    private Clientes objCostumer = new Clientes();
    private Clientes objCostum = new Clientes();
 
    private List<Mascotas> listMascotas = new ArrayList<>();
    private List<MascotasDTO> listaMascotasDTO = new ArrayList<>();
    
    private List<Citas> listCitas = new ArrayList<>();
    private List<CitasDTO> listaCitasDTO = new ArrayList<>();
    
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
                        fc.getExternalContext().redirect("../DocAssesorComercial/home.xhtml");
                    } else {
                        usuLogin.setUltimoIngreso(new Date());
                        if (usuLogin.getIdTipoRol().getIdTipoRol() == 4 || usuLogin.getIdTipoRol().getIdTipoRol() == 5 || usuLogin.getIdTipoRol().getIdTipoRol() == 6) {
                            usuariologeado.add(usuLogin);
                            FacesContext fc = FacesContext.getCurrentInstance();
                            fc.getExternalContext().redirect("../DocPersonalDeTienda/home.xhtml");
                        } else {
                            usuLogin.setUltimoIngreso(new Date());
                            usuariologeado.add(usuLogin);
                            objCostumer = clientesFacadeLocal.datosPorUsuario(usuLogin.getIdUsuario());
                            listMascotas = mascotasFacadeLocal.mascotasPorUsuario(usuLogin.getIdUsuario());
                            listaMascotasDTO = this.consultarInfoMascota(listMascotas);
                            listCitas = citasFacadeLocal.citasPorUsuario(usuLogin.getIdUsuario());
                            listaCitasDTO = this.consultarInfoCita(listCitas);
                            FacesContext fc = FacesContext.getCurrentInstance();
                            fc.getExternalContext().redirect("../DocCliente/home.xhtml");
                        }
                    }
                }
            }
        } catch (Exception e) {
            mensaje = "swal('El usuario' , 'no se encuentra registrado' , 'error');";
        }
        PrimeFaces.current().executeScript(mensaje);
    }
    
    private List<MascotasDTO> consultarInfoMascota(List<Mascotas> list){
        List<MascotasDTO> listaMascotasDTO = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            MascotasDTO objMascotasDTO = new MascotasDTO();
            
            objMascotasDTO.setIdMascota(list.get(i).getIdMascota());
            objMascotasDTO.setIdCliente(list.get(i).getIdCliente().getIdCliente());
            objMascotasDTO.setTipoMascota(list.get(i).getIdTipoMascota().getTipoMascota());
            objMascotasDTO.setRazaMascota(list.get(i).getIdRazaMascota().getRazaMascota());
            objMascotasDTO.setSexo(list.get(i).getSexo());
            objMascotasDTO.setNombre(list.get(i).getNombresMascota());
            objMascotasDTO.setEdad(list.get(i).getEdad());
            objMascotasDTO.setFechaNacimiento(list.get(i).getFechaDeNacimiento());
            objMascotasDTO.setColor(list.get(i).getColor());
            objMascotasDTO.setEsterilizado(list.get(i).getEsterilizado());
            listaMascotasDTO.add(objMascotasDTO);
        }
        return listaMascotasDTO;
    }

    private List<CitasDTO> consultarInfoCita(List<Citas> list){
        List<CitasDTO> listaCitasDTO = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CitasDTO objCitasDTO = new CitasDTO();
            
            objCitasDTO.setIdCita(list.get(i).getIdCitas());
            objCitasDTO.setIdMascota(list.get(i).getIdMascota().getNombresMascota());
            objCitasDTO.setIdServicio(list.get(i).getIdServicio().getNombreServicio());
            objCitasDTO.setFechaCita(list.get(i).getFechaCita());
            objCitasDTO.setHoraCita(list.get(i).getHoraCita());
            objCitasDTO.setEstadoCita(list.get(i).getEstadoCita());
            listaCitasDTO.add(objCitasDTO);
        }
        return listaCitasDTO;
    }
    
    public void verificarSesion() throws IOException{
        if(usuLogin.getIdUsuario()==null){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().redirect("../DocLogin/login.xhtml");
        }
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

    public void cargarDatosCliente() {
        this.objCostum = objCostumer;
    }
    
    public void actualizarDatosCliente() {
        String mensajeSw = "";

        try {
            clientesFacadeLocal.edit(objCostum);
            mensajeSw = "swal('Sus datos' , ' se han modificado exitosamente ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('Los datos' , ' No han sido modificados ', 'error')";
        }
        PrimeFaces.current().executeScript(mensajeSw);
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

    public List<MascotasDTO> getListaMascotasDTO() {
        return listaMascotasDTO;
    }

    public void setListaMascotasDTO(List<MascotasDTO> listaMascotasDTO) {
        this.listaMascotasDTO = listaMascotasDTO;
    }

    public List<CitasDTO> getListaCitasDTO() {
        return listaCitasDTO;
    }

    public void setListaCitasDTO(List<CitasDTO> listaCitasDTO) {
        this.listaCitasDTO = listaCitasDTO;
    }

    public Clientes getObjCostum() {
        return objCostum;
    }

    public void setObjCostum(Clientes objCostum) {
        this.objCostum = objCostum;
    }

}
