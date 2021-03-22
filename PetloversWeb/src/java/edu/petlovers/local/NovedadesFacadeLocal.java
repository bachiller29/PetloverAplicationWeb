/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.local;

import edu.petlovers.entity.Novedades;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author HP
 */
@Local
public interface NovedadesFacadeLocal {

    void create(Novedades novedades);

    void edit(Novedades novedades);

    void remove(Novedades novedades);

    Novedades find(Object id);

    List<Novedades> findAll();

    List<Novedades> findRange(int[] range);

    int count();

    public int cantidadNovedades(int idCita);

    public Novedades buscarNovedad(int idNovedad);
    
}
