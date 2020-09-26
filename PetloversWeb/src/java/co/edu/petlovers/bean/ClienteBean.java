package co.edu.petlovers.bean;

import co.edu.petlovers.dao.ClientesDAO;
import co.edu.petlovers.model.Clientes;
//import javax.annotation.ManagedBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "clienteBean")
@RequestScoped
public class ClienteBean {

    //Se crea variable para poder optener sus atributos 
    private Clientes cliente;

    public ClienteBean() {
        cliente = new Clientes();
    }

    // invocamos los metodos del patron de acceso DAO
    // insercion a la base de datos 
    public void create() throws Exception {
        try {
            ClientesDAO dao = new ClientesDAO();
            dao.create(cliente);
        } catch (Exception e) {
            throw e;
        }
    }

    public Clientes getClientes() {
        return cliente;
    }

    public void setClientes(Clientes cliente) {
        this.cliente = cliente;
    }
}
