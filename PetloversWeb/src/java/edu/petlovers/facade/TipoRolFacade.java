/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.facade;

import edu.petlovers.local.TipoRolFacadeLocal;
import edu.petlovers.entity.TipoRol;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author wsbachiller
 */
@Stateless
public class TipoRolFacade extends AbstractFacade<TipoRol> implements TipoRolFacadeLocal {
    @PersistenceContext(unitName = "PetloversWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoRolFacade() {
        super(TipoRol.class);
    }
    
}
