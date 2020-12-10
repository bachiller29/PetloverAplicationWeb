/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.facade;

import edu.petlovers.local.HistoriaClinicaFacadeLocal;
import edu.petlovers.entity.HistoriaClinica;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author wsbachiller
 */
@Stateless
public class HistoriaClinicaFacade extends AbstractFacade<HistoriaClinica> implements HistoriaClinicaFacadeLocal {
    @PersistenceContext(unitName = "PetloversWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoriaClinicaFacade() {
        super(HistoriaClinica.class);
    }
    
    @Override
    public int cantidadHistoriasC(int idCita, int idMascota, int idCliente) {
        try {
            Query q = em.createNativeQuery("SELECT COUNT(*) FROM historia_clinica WHERE Id_Citas = " + idCita + " AND Id_Mascota = " + idMascota + " AND Id_Cliente = " + idCliente);
            int count = ((Number) q.getSingleResult()).intValue();
            return count;
        } catch (Exception e) {
            return 0;
        }
    }
    
    @Override
    public HistoriaClinica buscarHistoriaClinica(int idHistoria) {
        try {
            Query q = em.createQuery("SELECT hc FROM HistoriaClinica hc WHERE hc.idHistoriaClinica = :idHistoria");
            q.setParameter("idHistoria", idHistoria);
            return (HistoriaClinica) q.getSingleResult();
        } catch (Exception e) {
            return new HistoriaClinica();
        }
    }
}
