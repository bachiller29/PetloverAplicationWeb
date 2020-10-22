package co.edu.petlovers.dao;

import co.edu.petlovers.model.Citas;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class AdminAuxDao extends Dao {

    public void createCitas(Citas citas) throws Exception {
        try {
            this.Connet();
            StringBuilder str = new StringBuilder("insert into citas (Fecha_Cita, Hora_Cita, Estado_Cita, Id_Cliente, Id_Servicio) values(");
            str.append("'");
            str.append(new SimpleDateFormat("yyyy-MM-dd").format(citas.getFechaCita()));
            str.append("','");
            str.append(new SimpleDateFormat("hh:mm:ss").format(citas.getHoraCita()));
            str.append("','");
            str.append(citas.getEstadoCita());
            str.append("',");
            str.append(citas.getIdCliente().getIdCliente());
            str.append(",");
            str.append(citas.getIdServicio().getIdServicio());
            str.append(")");

            Statement st = this.getConnection().createStatement();
            st.executeUpdate(str.toString());
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
    }
}
