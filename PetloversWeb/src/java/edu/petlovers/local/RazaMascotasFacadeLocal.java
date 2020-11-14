/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.local;

import edu.petlovers.entity.RazaMascotas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wsbachiller
 */
@Local
public interface RazaMascotasFacadeLocal {

    void create(RazaMascotas razaMascotas);

    void edit(RazaMascotas razaMascotas);

    void remove(RazaMascotas razaMascotas);

    RazaMascotas find(Object id);

    List<RazaMascotas> findAll();

    List<RazaMascotas> findRange(int[] range);

    int count();
    
}
