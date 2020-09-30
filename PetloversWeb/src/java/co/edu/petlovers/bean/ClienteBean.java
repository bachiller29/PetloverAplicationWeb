package co.edu.petlovers.bean;

import co.edu.petlovers.dao.ClientesDAO;
import co.edu.petlovers.model.Clientes;
import co.edu.petlovers.model.Productos;
import co.edu.petlovers.model.Proveedores;
import co.edu.petlovers.model.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
//import javax.annotation.ManagedBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "clienteBean")
@SessionScoped
public class ClienteBean {

    //Se crea variable para poder optener sus atributos 
    private Clientes cliente;
    private Proveedores proveedor;
    private Productos producto;
    private Usuarios usuario;
    private List<Clientes> listCliente;
    private List<Proveedores> listProveedor;
    private List<Productos> listProducto;
    private List<Usuarios> listUsuario;

    public ClienteBean() {
        cliente = new Clientes();
        proveedor = new Proveedores();
        producto = new Productos();
        usuario = new Usuarios();
        listCliente = new ArrayList<Clientes>();
        listProveedor = new ArrayList<Proveedores>();
        listProducto = new ArrayList<Productos>();
        listUsuario = new ArrayList<Usuarios>();
    }

    // invocamos los metodos del patron de acceso DAO
    // insercion a la base de datos 
    public String createCliente() throws Exception {
        try {
            ClientesDAO dao = new ClientesDAO();
            dao.createCliente(cliente);
            cliente = new Clientes();
            return "client-list";
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

    public void listCliente() throws Exception {
        try {
            ClientesDAO dao = new ClientesDAO();
            listCliente = dao.listCliente();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listProveedor() throws Exception {
        try {
            ClientesDAO dao = new ClientesDAO();
            listProveedor = dao.listProveedor();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listProducto() throws Exception {
        try {
            ClientesDAO dao = new ClientesDAO();
            listProducto = dao.listProducto();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listUsuario() throws Exception {
        try {
            ClientesDAO dao = new ClientesDAO();
            listUsuario = dao.listUsuario();
        } catch (Exception e) {
            throw e;
        }
    }

    public String tomarCliente(Clientes objCliente) throws Exception {
        try {
            cliente = new Clientes();
            cliente = objCliente;
            return "client-update";
        } catch (Exception e) {
            throw e;
        }
    }

    public String byProveedor(Proveedores objProveedor) throws Exception {
        try {
            proveedor = new Proveedores();
            proveedor = objProveedor;
            return "item-update";
        } catch (Exception e) {
            throw e;
        }
    }

    public String byProducto(Productos objProducto) throws Exception {
        try {
            producto = new Productos();
            producto = objProducto;
            return "reservation-update";
        } catch (Exception e) {
            throw e;
        }
    }

    public String byUsuario(Usuarios objUsuario) throws Exception {
        try {
            usuario = new Usuarios();
            usuario = objUsuario;
            return "user-update";
        } catch (Exception e) {
            throw e;
        }
    }

    public String updateCliente() throws Exception {
        ClientesDAO dao = new ClientesDAO();
        dao.updateCliente(cliente);
        cliente = new Clientes();
        return "client-list";
    }

    public String updateProveedor() throws Exception {
        ClientesDAO dao = new ClientesDAO();
        dao.updateProveedor(proveedor);
        proveedor = new Proveedores();
        return "item-list";
    }

    public String updateProducto() throws Exception {
        ClientesDAO dao = new ClientesDAO();
        dao.updateProducto(producto);
        producto = new Productos();
        return "reservation-list";
    }

    public String updateUsuario() throws Exception {
        ClientesDAO dao = new ClientesDAO();
        dao.updateUsuario(usuario);
        usuario = new Usuarios();
        return "user-list";
    }

    public void deleteCliente(Clientes cliente) throws Exception {
        ClientesDAO dao = new ClientesDAO();
        dao.deleteCliente(cliente);
        listCliente = dao.listCliente();
    }

    public void deleteProvedor(Proveedores proveedor) throws Exception {
        ClientesDAO dao = new ClientesDAO();
        dao.deleteProveedor(proveedor);
        listProveedor = dao.listProveedor();
    }

    public void deleteProducto(Productos producto) throws Exception {
        ClientesDAO dao = new ClientesDAO();
        if (!dao.validateDetalleOrdenPagoByIdProduct(producto.getIdProducto())) {
            dao.deleteProducto(producto);
            listProducto = dao.listProducto();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Producto eliminado exitosamente.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El producto no puede ser eliminado porque tiene una orden de pago.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void deleteUsuario(Usuarios usuario) throws Exception {
        ClientesDAO dao = new ClientesDAO();
        if (!dao.validateTipoRolByIdTipoRol(usuario.getIdUsuario())) {
            dao.deleteUsuario(usuario);
            listUsuario = dao.listUsuario();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Producto eliminado exitosamente.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El producto no puede ser eliminado por que tiene rol asignado");
            FacesContext.getCurrentInstance().addMessage(null, message);
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

    public List<Clientes> getListClientes() {
        return listCliente;
    }

    public void setListClientes(List<Clientes> listCliente) {
        this.listCliente = listCliente;
    }

    public List<Proveedores> getListProveedores() {
        return listProveedor;
    }

    public void setListProveedores(List<Proveedores> listProveedor) {
        this.listProveedor = listProveedor;
    }

    public List<Productos> getListProductos() {
        return listProducto;
    }

    public void setListProductos(List<Productos> listProducto) {
        this.listProducto = listProducto;
    }

    public List<Usuarios> getListUsuarios() {
        return listUsuario;
    }

    public void setListUsuarios(List<Usuarios> listUsuario) {
        this.listUsuario = listUsuario;
    }
}
