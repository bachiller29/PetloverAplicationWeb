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
