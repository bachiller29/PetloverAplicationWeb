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
    
    public int cantidadCitas(int idCliente, String fecha) {
        try {
            Query q = em.createNativeQuery("SELECT COUNT(*) FROM citas WHERE Id_Cliente = " + idCliente + " AND Fecha_Cita = '" + fecha + "'");
            int count = ((Number) q.getSingleResult()).intValue();
            return count;
        } catch (Exception e) {
            e.getMessage();
            return 0;
        }
    }
    
    public Citas buscarCita(int idCita){
        try {
            Query q = em.createQuery("SELECT c FROM Citas c WHERE c.idCitas = :idCita");
            q.setParameter("idCita", idCita);
            return (Citas) q.getSingleResult();
        } catch (Exception e) {
            return new Citas();
        }
    }
}
