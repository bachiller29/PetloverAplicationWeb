package co.edu.petlovers.bean;

import co.edu.petlovers.dao.AdminAuxDao;
import co.edu.petlovers.dao.ServiciosDAO;
import co.edu.petlovers.dto.CitasDTO;
import co.edu.petlovers.model.Citas;
import co.edu.petlovers.model.Clientes;
import co.edu.petlovers.model.Servicios;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "adminAuxBean")
@SessionScoped
public class AdminAuxBean {

    //Se crea variable para poder optener sus atributos
    private CitasDTO cita;
    private List<Servicios> listServicios;

    public AdminAuxBean() throws Exception {
        ServiciosDAO servicioDao = new ServiciosDAO();
        cita = new CitasDTO();
        listServicios = servicioDao.listServicios();
    }

    // invocamos los metodos del patron de acceso DAO
    // insercion a la base de datos 
    public String createCita() throws Exception {
        try {
            AdminAuxDao dao = new AdminAuxDao();
            dao.createCitas(this.mapDtoToObject(cita));
            cita = new CitasDTO();
            return "client-list";
        } catch (Exception e) {
            throw e;
        }
    }

    private Citas mapDtoToObject(CitasDTO dto) throws ParseException {
        Citas objCitas = new Citas();
        Clientes objClientes = new Clientes();
        Servicios objServicios = new Servicios();

        objClientes.setIdCliente(dto.getIdCliente());
        objServicios.setIdServicio(dto.getIdServicio());

        Date fechaCita = new SimpleDateFormat("yyyy-MM-dd").parse(dto.getFechaCita());
        Date horaCita = new SimpleDateFormat("HH:mm").parse(dto.getHoraCita());

        objCitas.setFechaCita(fechaCita);
        objCitas.setHoraCita(horaCita);
        objCitas.setEstadoCita(dto.getEstadoCita());
        objCitas.setIdCliente(objClientes);
        objCitas.setIdServicio(objServicios);
        return objCitas;
    }

    public CitasDTO getCitas() {
        return cita;
    }

    public void setCitas(CitasDTO cita) {
        this.cita = cita;
    }

    public List<Servicios> getListServicios() {
        return listServicios;
    }

    public void setListServicios(List<Servicios> listServicios) {
        this.listServicios = listServicios;
    }

}
