package co.edu.petlovers.dao;

import co.edu.petlovers.model.Clientes;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.persistence.Query;

public class ClientesDAO extends Dao {

    public void create(Clientes clientes) throws Exception {
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
}
