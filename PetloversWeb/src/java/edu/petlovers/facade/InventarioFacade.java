/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.facade;

import edu.petlovers.local.InventarioFacadeLocal;
import edu.petlovers.entity.Inventario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author wsbachiller
 */
@Stateless
public class InventarioFacade extends AbstractFacade<Inventario> implements InventarioFacadeLocal {
    @PersistenceContext(unitName = "PetloversWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InventarioFacade() {
        super(Inventario.class);
    }
    
    public Inventario buscarInventario(int idInventario) {
        try {
            Query q = em.createQuery("SELECT i FROM Inventario i WHERE i.idInventario = :idInventario");
            q.setParameter("idInventario", idInventario);
            return (Inventario) q.getSingleResult();
        } catch (Exception e) {
            return new Inventario();
        }
    }
}
