package co.edu.petlovers.dao;

import co.edu.petlovers.model.Clientes;
import co.edu.petlovers.model.Inventario;
import co.edu.petlovers.model.MarcaProductos;
import co.edu.petlovers.model.Productos;
import co.edu.petlovers.model.Proveedores;
import co.edu.petlovers.model.TipoProductos;
import co.edu.petlovers.model.TipoRol;
import co.edu.petlovers.model.Usuarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

public class ClientesDAO extends Dao {

    public void createCliente(Clientes clientes) throws Exception {
        try {
            this.Connet();
            StringBuilder str = new StringBuilder("insert into clientes (direccion, ciudad, barrio, telefono, email) values (");
            str.append("'");
            str.append(clientes.getDireccion());
            str.append("','");
            str.append(clientes.getCiudad());
            str.append("','");
            str.append(clientes.getBarrio());
            str.append("',");
            str.append(clientes.getTelefono());
            str.append(",'");
            str.append(clientes.getEmail());
            str.append("')");

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
            str.append("'");
            str.append(producto.getNombreProducto());
            str.append("','");
            str.append(producto.getDescripcion());
            str.append("','");
            str.append(producto.getTamanoProducto());
            str.append("','");
            str.append(producto.getColorProducto());
            str.append("','");
            str.append(producto.getSaborProducto());
            str.append("','");
            str.append(producto.getPrecioProducto());
            str.append("','");
            str.append(producto.getCodigoBarrasProducto());
            str.append("',");
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
            StringBuilder str = new StringBuilder("insert into usuarios (Nombres, Apellidos, Usuario_login, Contrasena) values (");
            str.append("'");
            str.append(usuario.getNombres());
            str.append("','");
            str.append(usuario.getApellidos());
            str.append("','");
            str.append(usuario.getUsuariologin());
            str.append("','");
            str.append(usuario.getContrasena());
            str.append("')");

            Statement st = this.getConnection().createStatement();
            st.executeUpdate(str.toString());
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
    }

    public List<Clientes> listCliente() throws Exception {
        List<Clientes> listCliente;
        ResultSet result;
        try {
            this.Connet();
            PreparedStatement st = this.getConnection().prepareCall("SELECT Id_Cliente, Direccion, Ciudad, Barrio, Telefono, Email, id_usuario FROM clientes");
            result = st.executeQuery();
            listCliente = new ArrayList();
            while (result.next()) {
                Clientes cliente = new Clientes();
                cliente.setIdCliente(result.getInt("Id_Cliente"));
                cliente.setDireccion(result.getString("Direccion"));
                cliente.setCiudad(result.getString("Ciudad"));
                cliente.setBarrio(result.getString("Barrio"));
                cliente.setTelefono(result.getInt("Telefono"));
                cliente.setEmail(result.getString("Email"));
                cliente.setIdUsuario(new Usuarios(result.getInt("id_usuario")));

                listCliente.add(cliente);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
        return listCliente;
    }

    public List<Proveedores> listProveedor() throws Exception {
        List<Proveedores> listProveedores;
        ResultSet result;
        try {
            this.Connet();
            PreparedStatement st = this.getConnection().prepareCall("SELECT Nit_Proveedor, Nombres_Proveedor, Direccion_Proveedor, Ciudad_Proveedor, Email_Proveedor, Telefono_Proveedor FROM proveedores");
            result = st.executeQuery();
            listProveedores = new ArrayList();
            while (result.next()) {
                Proveedores proveedores = new Proveedores();
                proveedores.setNitProveedor(result.getInt("Nit_Proveedor"));
                proveedores.setNombresProveedor(result.getString("Nombres_Proveedor"));
                proveedores.setDireccionProveedor(result.getString("Direccion_Proveedor"));
                proveedores.setCiudadProveedor(result.getString("Ciudad_Proveedor"));
                proveedores.setEmailProveedor(result.getString("Email_Proveedor"));
                proveedores.setTelefonoProveedor(result.getInt("Telefono_Proveedor"));

                listProveedores.add(proveedores);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
        return listProveedores;
    }

    public List<Productos> listProducto() throws Exception {
        List<Productos> listProductos;
        ResultSet result;
        try {
            this.Connet();
            PreparedStatement st = this.getConnection().prepareCall("SELECT Id_Producto, Id_Tipo_Producto, Nombre_Producto, Descripcion, Tamano_Producto, Color_Producto, Sabor_Producto, Precio_Producto, Codigo_Barras_Producto, Nit_Proveedor, Id_Inventario, Id_Marca_Producto FROM productos");
            result = st.executeQuery();
            listProductos = new ArrayList();
            while (result.next()) {
                Productos producto = new Productos();
                producto.setIdProducto(result.getInt("Id_Producto"));
                producto.setIdTipoProducto(new TipoProductos(result.getInt("Id_Tipo_Producto")));
                producto.setNombreProducto(result.getString("Nombre_Producto"));
                producto.setDescripcion(result.getString("Descripcion"));
                producto.setTamanoProducto(result.getString("Tamano_Producto"));
                producto.setColorProducto(result.getString("Color_Producto"));
                producto.setSaborProducto(result.getString("Sabor_Producto"));
                producto.setPrecioProducto(result.getInt("Precio_Producto"));
                producto.setCodigoBarrasProducto(result.getInt("Codigo_Barras_Producto"));
                producto.setNitProveedor(result.getInt("Nit_Proveedor"));
                producto.setIdInventario(new Inventario(result.getInt("Id_Inventario")));
                producto.setIdMarcaProducto(new MarcaProductos(result.getInt("Id_Marca_Producto")));

                listProductos.add(producto);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
        return listProductos;
    }

    public List<Usuarios> listUsuario() throws Exception {
        List<Usuarios> listUsuario;
        ResultSet result;
        try {
            this.Connet();
            PreparedStatement st = this.getConnection().prepareCall("SELECT Id_Usuario, Nombres, Apellidos, Usuario_login, Contrasena, Id_Tipo_Rol FROM usuarios");
            result = st.executeQuery();
            listUsuario = new ArrayList();
            while (result.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setIdUsuario(result.getInt("Id_Usuario"));
                usuario.setNombres(result.getString("Nombres"));
                usuario.setApellidos(result.getString("Apellidos"));
                usuario.setUsuariologin(result.getString("Usuario_login"));
                usuario.setContrasena(result.getString("Contrasena"));
                usuario.setIdTipoRol(new TipoRol(result.getInt("Id_Tipo_Rol")));

                listUsuario.add(usuario);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
        return listUsuario;
    }

    public void updateCliente(Clientes clientes) throws Exception {
        try {
            this.Connet();
            StringBuilder str = new StringBuilder("update clientes set Direccion='");
            str.append(clientes.getDireccion());
            str.append("', Ciudad='");
            str.append(clientes.getCiudad());
            str.append("', Barrio='");
            str.append(clientes.getBarrio());
            str.append("', Telefono=");
            str.append(clientes.getTelefono());
            str.append(", Email='");
            str.append(clientes.getEmail());
            str.append("' WHERE Id_Cliente=");
            str.append(clientes.getIdCliente());

            Statement st = this.getConnection().createStatement();
            st.executeUpdate(str.toString());
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
    }

    public void updateProveedor(Proveedores proveedor) throws Exception {
        try {
            this.Connet();
            StringBuilder str = new StringBuilder("update proveedores set Nombres_Proveedor='");
            str.append(proveedor.getNombresProveedor());
            str.append("', Direccion_Proveedor='");
            str.append(proveedor.getDireccionProveedor());
            str.append("', Ciudad_Proveedor='");
            str.append(proveedor.getCiudadProveedor());
            str.append("', Telefono_Proveedor=");
            str.append(proveedor.getTelefonoProveedor());
            str.append(", Email_Proveedor='");
            str.append(proveedor.getEmailProveedor());
            str.append("' WHERE Nit_Proveedor=");
            str.append(proveedor.getNitProveedor());

            Statement st = this.getConnection().createStatement();
            st.executeUpdate(str.toString());
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
    }

    public void updateProducto(Productos producto) throws Exception {
        try {
            this.Connet();
            StringBuilder str = new StringBuilder("update productos set Nombre_Producto='");
            str.append(producto.getNombreProducto());
            str.append("', Descripcion='");
            str.append(producto.getDescripcion());
            str.append("', Tamano_Producto='");
            str.append(producto.getTamanoProducto());
            str.append("', Color_Producto='");
            str.append(producto.getColorProducto());
            str.append("', Sabor_Producto='");
            str.append(producto.getSaborProducto());
            str.append("', Precio_Producto='");
            str.append(producto.getPrecioProducto());
            str.append("', Codigo_Barras_Producto='");
            str.append(producto.getCodigoBarrasProducto());
            str.append("', Nit_Proveedor='");
            str.append(producto.getNitProveedor());
            str.append("' WHERE Id_Producto=");
            str.append(producto.getIdProducto());

            Statement st = this.getConnection().createStatement();
            st.executeUpdate(str.toString());
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
    }

    public void updateUsuario(Usuarios usuario) throws Exception {
        try {
            this.Connet();
            StringBuilder str = new StringBuilder("update usuarios set Nombres='");
            str.append(usuario.getNombres());
            str.append("', Apellidos='");
            str.append(usuario.getApellidos());
            str.append("', Usuario_login='");
            str.append(usuario.getUsuariologin());
//            str.append("', Id_Tipo_Rol=");
//            str.append(usuario.getIdTipoRol());
            str.append("', Contrasena='");
            str.append(usuario.getContrasena());
            str.append("' WHERE Id_Usuario=");
            str.append(usuario.getIdUsuario());

            Statement st = this.getConnection().createStatement();
            st.executeUpdate(str.toString());
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
    }

    public void deleteCliente(Clientes clientes) throws Exception {
        try {
            this.Connet();
            StringBuilder str = new StringBuilder("delete from clientes where Id_Cliente=");
            str.append(clientes.getIdCliente());

            Statement st = this.getConnection().createStatement();
            st.executeUpdate(str.toString());
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
    }

    public void deleteProveedor(Proveedores proveedor) throws Exception {
        try {
            this.Connet();
            StringBuilder str = new StringBuilder("delete from proveedores where Nit_Proveedor=");
            str.append(proveedor.getNitProveedor());

            Statement st = this.getConnection().createStatement();
            st.executeUpdate(str.toString());
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
    }

    public void deleteProducto(Productos producto) throws Exception {
        try {
            this.Connet();
            StringBuilder str = new StringBuilder(" delete from productos where Id_Producto=");
            str.append(producto.getIdProducto());

            Statement st = this.getConnection().createStatement();
            st.executeUpdate(str.toString());
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
    }

    public Boolean validateDetalleOrdenPagoByIdProduct(Integer idProduct) throws Exception {
        Boolean exist = false;
        int size = 0;

        List<Productos> listCliente;
        ResultSet result;
        try {
            this.Connet();
            PreparedStatement st = this.getConnection().prepareCall("SELECT * FROM detalle_orden_de_pago WHERE Id_Producto=" + idProduct);
            result = st.executeQuery();
//            size = result.getRow();
            listCliente = new ArrayList();
            while (result.next()) {
                Productos producto = new Productos();
                producto.setIdProducto(result.getInt("Id_Producto"));
                listCliente.add(producto);
            }
            if (listCliente.size() != 0) {
                exist = true;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
        return exist;

    }

    public Boolean validateTipoRolByIdTipoRol(Integer idTipoRol) throws Exception {
        Boolean exist = false;
        int size = 0;

        List<TipoRol> listTipoRol;
        ResultSet result;
        try {
            this.Connet();
            PreparedStatement st = this.getConnection().prepareCall("SELECT * FROM tipo_rol WHERE Id_tipo_Rol=" + idTipoRol);
            result = st.executeQuery();
//            size = result.getRow();
            listTipoRol = new ArrayList();
            while (result.next()) {
                TipoRol tiporol = new TipoRol();
                tiporol.setIdTipoRol(result.getInt("Id_Tipo_Rol"));
                listTipoRol.add(tiporol);
            }
            if (listTipoRol.size() != 0) {
                exist = true;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
        return exist;

    }

    public void deleteUsuario(Usuarios usuario) throws Exception {
        try {
            this.Connet();
            StringBuilder str = new StringBuilder("delete from usuarios where Id_Usuario=");
            str.append(usuario.getIdUsuario());

            Statement st = this.getConnection().createStatement();
            st.executeUpdate(str.toString());
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
    }

    public List<Usuarios> validateCredentials(String user, String pass) throws Exception {
        List<Usuarios> listCliente;
        ResultSet result;
        try {
            this.Connet();
            StringBuilder str = new StringBuilder("SELECT * FROM petlovers.usuarios WHERE Usuario_login = '");
            str.append(user);
            str.append("' AND Contrasena='");
            str.append(pass);
            str.append("'");

            PreparedStatement st = this.getConnection().prepareCall(str.toString());
            result = st.executeQuery();
            listCliente = new ArrayList();
            while (result.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setIdUsuario(result.getInt("Id_Usuario"));
                usuario.setNombres(result.getString("Nombres"));
                usuario.setApellidos(result.getString("Apellidos"));
                usuario.setUsuariologin(result.getString("Usuario_login"));
                usuario.setContrasena(result.getString("Contrasena"));
                usuario.setIdTipoRol(new TipoRol(result.getInt("Id_Tipo_Rol")));

                listCliente.add(usuario);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
        return listCliente;
    }
}
