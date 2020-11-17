/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.facade;

import edu.petlovers.local.UsuariosFacadeLocal;
import edu.petlovers.entity.Usuarios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author wsbachiller
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> implements UsuariosFacadeLocal {

    @PersistenceContext(unitName = "PetloversWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }

    @Override
    public Usuarios recuperarClave(String emailIn) {
        try {
            Query qt = em.createQuery("SELECT p FROM Usuarios p WHERE p.email = :emailIn");
            qt.setParameter("emailIn", emailIn);
            return (Usuarios) qt.getSingleResult();
        } catch (Exception e) {
            return new Usuarios();
        }
    }

}
