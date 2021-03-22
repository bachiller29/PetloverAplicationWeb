/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.facade;

import edu.petlovers.local.ClientesFacadeLocal;
import edu.petlovers.entity.Clientes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author wsbachiller
 */
@Stateless
public class ClientesFacade extends AbstractFacade<Clientes> implements ClientesFacadeLocal {
    @PersistenceContext(unitName = "PetloversWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientesFacade() {
        super(Clientes.class);
    }
    
    @Override
    public int cantidadDatosCliente(int idUsuario) {
        try {
            Query q = em.createNativeQuery("SELECT COUNT(*) FROM clientes WHERE id_usuario = " + idUsuario);
            int count = ((Number) q.getSingleResult()).intValue();
            return count;
        } catch (Exception e) {
            return 0;
        }
    }
    
    @Override
    public Clientes buscarCliente(int idCliente){
        try {
            Query q = em.createQuery("SELECT c FROM Clientes c WHERE c.idCliente = :idCliente");
            q.setParameter("idCliente", idCliente);
            return (Clientes) q.getSingleResult();
        } catch (Exception e) {
            return new Clientes();
        }
    }
    
    @Override
    public Clientes datosPorUsuario(int idUsuario){
        try {
            Query q = em.createNativeQuery("SELECT * FROM clientes c INNER JOIN usuarios u ON u.Id_Usuario = c.id_usuario WHERE u.Id_Usuario = " + idUsuario, Clientes.class);
            return (Clientes) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
