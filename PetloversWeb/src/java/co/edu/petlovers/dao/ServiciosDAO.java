package co.edu.petlovers.dao;

import co.edu.petlovers.model.Servicios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServiciosDAO extends Dao {
    
    public List<Servicios> listServicios() throws Exception {
        List<Servicios> listCliente;
        ResultSet result;
        try {
            this.Connet();
            PreparedStatement st = this.getConnection().prepareCall("SELECT * FROM petlovers.servicios");
            result = st.executeQuery();
            listCliente = new ArrayList();
            while (result.next()) {
                Servicios servicio = new Servicios();
                servicio.setIdServicio(result.getInt("Id_Servicio"));
                servicio.setDescripcion(result.getString("Descripcion"));
                
                listCliente.add(servicio);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
        return listCliente;
    }
}
