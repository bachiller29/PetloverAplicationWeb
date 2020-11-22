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

    public Usuarios buscarUsuario(int idUsuario) {
        try {
            Query q = em.createQuery("SELECT u FROM Usuarios u WHERE u.idUsuario = :idUsuario");
            q.setParameter("idUsuario", idUsuario);
            return (Usuarios) q.getSingleResult();
        } catch (Exception e) {
            return new Usuarios();
        }
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

    @Override
    public Usuarios loginUsuario(String email, String contrasena) {
        try {
            Query q = em.createQuery("SELECT u FROM Usuarios u WHERE u.email = :email AND u.contrasena = :contrasena");
            q.setParameter("email", email);
            q.setParameter("contrasena", contrasena);
            return (Usuarios) q.getSingleResult();
        } catch (Exception e) {
            return new Usuarios();
        }
    }
}
