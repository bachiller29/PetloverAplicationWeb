/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.facade;

import edu.petlovers.entity.Mascotas;
import edu.petlovers.local.MascotasFacadeLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author wsbachiller
 */
@Stateless
public class MascotasFacade extends AbstractFacade<Mascotas> implements MascotasFacadeLocal {
    @PersistenceContext(unitName = "PetloversWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MascotasFacade() {
        super(Mascotas.class);
    }
    
    @Override
    public int cantidadMascotas(int idCliente){
        try {
            Query q = em.createNativeQuery("SELECT COUNT(*) FROM mascotas WHERE id_Cliente = " + idCliente);
            int count = ((Number) q.getSingleResult()).intValue();
            return count;
        } catch (Exception e) {
            return 0;
        }
    }
    
    @Override
    public List<Mascotas> mascotasPorUsuario(int idUsuario){
        try {
            Query q = em.createNativeQuery("SELECT * FROM mascotas m INNER JOIN clientes c ON c.Id_Cliente = m.Id_Cliente INNER JOIN usuarios u ON u.Id_Usuario = c.id_usuario WHERE u.Id_Usuario = " + idUsuario, Mascotas.class);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}
