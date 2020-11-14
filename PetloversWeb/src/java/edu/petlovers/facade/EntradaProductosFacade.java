/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.facade;

import edu.petlovers.local.EntradaProductosFacadeLocal;
import edu.petlovers.entity.EntradaProductos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author wsbachiller
 */
@Stateless
public class EntradaProductosFacade extends AbstractFacade<EntradaProductos> implements EntradaProductosFacadeLocal {
    @PersistenceContext(unitName = "PetloversWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntradaProductosFacade() {
        super(EntradaProductos.class);
    }
    
}
