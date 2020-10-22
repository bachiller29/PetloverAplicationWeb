/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.petlovers.bean;

import co.edu.petlovers.dao.ClientesDAO;
import co.edu.petlovers.model.Usuarios;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author GAMER
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable{

    private String user;
    private String password;

    public UserBean() {
    }

    public void login() throws Exception {
        ClientesDAO cDAO = new ClientesDAO();
        List<Usuarios> list = cDAO.validateCredentials(user, password);
        if (list.size() > 0) {
            String page = "";
            switch (list.get(0).getIdTipoRol().getIdTipoRol()) {
                case 1:
                    page = "../DocAdmin/home.xhtml";
                    break;
                case 2:
                    page = "../DocAdminAuxiliar/home.xhtml";
                    break;
                case 3:
                    page = "../DocPersonalDeTienda/home.xhtml";
                    break;
                case 4:
                    page = "../DocVentas/index.xhtml";
                    break;

            }
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            FacesContext.getCurrentInstance().responseComplete();;
            response.sendRedirect(page);
//            return "null";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña invalidos.");
            FacesContext.getCurrentInstance().addMessage(null, message);
//            return "";
        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
