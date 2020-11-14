/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.facade;

import edu.petlovers.local.TipoProductosFacadeLocal;
import edu.petlovers.entity.TipoProductos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author wsbachiller
 */
@Stateless
public class TipoProductosFacade extends AbstractFacade<TipoProductos> implements TipoProductosFacadeLocal {
    @PersistenceContext(unitName = "PetloversWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoProductosFacade() {
        super(TipoProductos.class);
    }
    
}
