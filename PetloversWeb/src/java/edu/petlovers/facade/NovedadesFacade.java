/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.facade;

import edu.petlovers.local.NovedadesFacadeLocal;
import edu.petlovers.entity.Novedades;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HP
 */
@Stateless
public class NovedadesFacade extends AbstractFacade<Novedades> implements NovedadesFacadeLocal {

    @PersistenceContext(unitName = "PetloversWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NovedadesFacade() {
        super(Novedades.class);
    }
    
    @Override
    public int cantidadNovedades(int idCita) {
        try {
            Query q = em.createNativeQuery("SELECT COUNT(*) FROM novedades WHERE Id_Citas = " + idCita);
            int count = ((Number) q.getSingleResult()).intValue();
            return count;
        } catch (Exception e) {
            return 0;
        }
    }
    
    @Override
    public Novedades buscarNovedad(int idNovedad) {
        try {
            Query q = em.createQuery("SELECT n FROM Novedades n WHERE n.idNovedad = :idNovedad");
            q.setParameter("idNovedad", idNovedad);
            return (Novedades) q.getSingleResult();
        } catch (Exception e) {
            return new Novedades();
        }
    }
    
}
