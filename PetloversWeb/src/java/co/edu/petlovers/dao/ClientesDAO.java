package co.edu.petlovers.dao;

import co.edu.petlovers.model.Clientes;
import co.edu.petlovers.model.Productos;
import co.edu.petlovers.model.Proveedores;
import co.edu.petlovers.model.Usuarios;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.persistence.Query;

public class ClientesDAO extends Dao {

    public void createCliente(Clientes clientes) throws Exception {
        try {
            this.Connet();
            StringBuilder str = new StringBuilder("insert into clientes (direccion, ciudad, barrio, telefono, email) values (");
            str.append("\"");
            str.append(clientes.getDireccion());
            str.append("\",\"");
            str.append(clientes.getCiudad());
            str.append("\",\"");
            str.append(clientes.getBarrio());
            str.append("\",");
            str.append(clientes.getTelefono());
            str.append(",\"");
            str.append(clientes.getEmail());
            str.append("\")");

            Statement st = this.getConnection().createStatement();
            st.executeUpdate(str.toString());
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
    }

    public void createProveedor(Proveedores proveedor) throws Exception {
        try {
            this.Connet();
            StringBuilder str = new StringBuilder("insert into proveedores (Nit_Proveedor, Nombres_Proveedor, Direccion_Proveedor, Ciudad_Proveedor, Email_Proveedor, Telefono_Proveedor) values (");
            str.append(proveedor.getNitProveedor());
            str.append(",'");
            str.append(proveedor.getNombresProveedor());
            str.append("','");
            str.append(proveedor.getDireccionProveedor());
            str.append("','");
            str.append(proveedor.getCiudadProveedor());
            str.append("','");
            str.append(proveedor.getEmailProveedor());
            str.append("',");
            str.append(proveedor.getTelefonoProveedor());
            str.append(")");

            Statement st = this.getConnection().createStatement();
            st.executeUpdate(str.toString());
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
    }

    public void createProductos(Productos producto) throws Exception {
        try {
            this.Connet();
            StringBuilder str = new StringBuilder("insert into productos (Nombre_Producto, Descripcion, Tamano_Producto, Color_Producto, Sabor_Producto, "
                    + "Precio_Producto, Codigo_Barras_Producto, Nit_Proveedor) values (");
            str.append("\"");
            str.append(producto.getNombreProducto());
            str.append("\",\"");
            str.append(producto.getDescripcion());
            str.append("\",\"");
            str.append(producto.getTamanoProducto());
            str.append("\",\"");
            str.append(producto.getColorProducto());
            str.append("\",\"");
            str.append(producto.getSaborProducto());
            str.append(",'");
            str.append(producto.getPrecioProducto());
            str.append("','");
            str.append(producto.getCodigoBarrasProducto());
            str.append("','");
            str.append(producto.getNitProveedor());
            str.append(")");

            Statement st = this.getConnection().createStatement();
            st.executeUpdate(str.toString());
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
    }

    public void createUsuario(Usuarios usuario) throws Exception {
        try {
            this.Connet();
            StringBuilder str = new StringBuilder("insert into usuario (Nombres, Apellidos, Usuario_login, Contrasena) values (");
            str.append("\"");
            str.append(usuario.getNombres());
            str.append("\",\"");
            str.append(usuario.getApellidos());
            str.append("\",\"");
            str.append(usuario.getUsuariologin());
            str.append("\",\"");
            str.append(usuario.getContrasena());
            str.append("\")");

            Statement st = this.getConnection().createStatement();
            st.executeUpdate(str.toString());
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
    }
}
