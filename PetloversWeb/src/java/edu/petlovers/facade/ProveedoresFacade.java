/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.facade;

import edu.petlovers.local.ProveedoresFacadeLocal;
import edu.petlovers.entity.Proveedores;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author wsbachiller
 */
@Stateless
public class ProveedoresFacade extends AbstractFacade<Proveedores> implements ProveedoresFacadeLocal {
    @PersistenceContext(unitName = "PetloversWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProveedoresFacade() {
        super(Proveedores.class);
    }
    
    public Proveedores buscarProveedor(int idProveedor) {
        try {
            Query q = em.createQuery("SELECT p FROM Proveedores p WHERE p.nitProveedor = :idProveedor");
            q.setParameter("idProveedor", idProveedor);
            return (Proveedores) q.getSingleResult();
        } catch (Exception e) {
            return new Proveedores();
        }
    }
}
