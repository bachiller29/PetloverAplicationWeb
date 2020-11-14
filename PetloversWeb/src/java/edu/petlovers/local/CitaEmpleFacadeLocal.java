/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.local;

import edu.petlovers.entity.CitaEmple;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wsbachiller
 */
@Local
public interface CitaEmpleFacadeLocal {

    void create(CitaEmple citaEmple);

    void edit(CitaEmple citaEmple);

    void remove(CitaEmple citaEmple);

    CitaEmple find(Object id);

    List<CitaEmple> findAll();

    List<CitaEmple> findRange(int[] range);

    int count();
    
}
