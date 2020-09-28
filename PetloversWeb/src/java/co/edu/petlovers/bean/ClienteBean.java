package co.edu.petlovers.bean;

import co.edu.petlovers.dao.ClientesDAO;
import co.edu.petlovers.model.Clientes;
import co.edu.petlovers.model.Productos;
import co.edu.petlovers.model.Proveedores;
import co.edu.petlovers.model.Usuarios;
//import javax.annotation.ManagedBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "clienteBean")
@RequestScoped
public class ClienteBean {

    //Se crea variable para poder optener sus atributos 
    private Clientes cliente;
    private Proveedores proveedor;
    private Productos producto;
    private Usuarios usuario;

    public ClienteBean() {
        cliente = new Clientes();
        proveedor = new Proveedores();
        producto = new Productos();
        usuario = new Usuarios();
    }

    // invocamos los metodos del patron de acceso DAO
    // insercion a la base de datos 
    public void createCliente() throws Exception {
        try {
            ClientesDAO dao = new ClientesDAO();
            dao.createCliente(cliente);
        } catch (Exception e) {
            throw e;
        }
    }

    public void createProveedor() throws Exception {
        try {
            ClientesDAO dao = new ClientesDAO();
            dao.createProveedor(proveedor);
        } catch (Exception e) {
            throw e;
        }
    }

    public void createProducto() throws Exception {
        try {
            ClientesDAO dao = new ClientesDAO();
            dao.createProductos(producto);
        } catch (Exception e) {
            throw e;
        }
    }

    public void createUsuario() throws Exception {
        try {
            ClientesDAO dao = new ClientesDAO();
            dao.createUsuario(usuario);
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

    public Proveedores getProveedores() {
        return proveedor;
    }

    public void setProveedores(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    public Productos getProductos() {
        return producto;
    }

    public void setProductos(Productos producto) {
        this.producto = producto;
    }

    public Usuarios getUsuarios() {
        return usuario;
    }

    public void setUsuarios(Usuarios usuario) {
        this.usuario = usuario;
    }
}
