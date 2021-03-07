/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.facade;

import edu.petlovers.local.CitasFacadeLocal;
import edu.petlovers.entity.Citas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author wsbachiller
 */
@Stateless
public class CitasFacade extends AbstractFacade<Citas> implements CitasFacadeLocal {
    @PersistenceContext(unitName = "PetloversWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitasFacade() {
        super(Citas.class);
    }
    
    @Override
    public int cantidadCitas(int idMascota, String fecha) {
        try {
            Query q = em.createNativeQuery("SELECT COUNT(*) FROM citas WHERE Id_Mascota = " + idMascota + " AND Fecha_Cita = '" + fecha + "'");
            int count = ((Number) q.getSingleResult()).intValue();
            return count;
        } catch (Exception e) {
            e.getMessage();
            return 0;
        }
    }
/*    
    public String nombrePropietario(int idMascota){
        try {
            Query q = em.createNativeQuery("SELECT Nombres, Apellidos FROM mascotas m INNER JOIN clientes c ON m.Id_Cliente = c.Id_Cliente INNER JOIN usuarios u ON u.Id_Usuario = c.id_usuario WHERE Id_Mascota = " + idMascota);
            String nombrePro = (String) q.getSingleResult();
            return nombrePro;
        } catch (Exception e) {
            return "";
        }
    }
*/    
    @Override
    public Citas buscarCita(int idCita){
        try {
            Query q = em.createQuery("SELECT c FROM Citas c WHERE c.idCitas = :idCita");
            q.setParameter("idCita", idCita);
            return (Citas) q.getSingleResult();
        } catch (Exception e) {
            return new Citas();
        }
    }
    
    @Override
    public Citas buscarCitaXCliente(int idMascota, String fecha){
        try {
            Query q = em.createQuery("SELECT c FROM Citas c WHERE c.id_mascota = :idMascota AND c.Estado_Cita = 'Reservada' And c.Fecha_Cita = :fechaCita order by hora_cita desc;");
            q.setParameter("idMascota", idMascota);
            q.setParameter("fechaCita", fecha);
            return (Citas) q.getSingleResult();
        } catch (Exception e) {
            return new Citas();
        }
    }
}
