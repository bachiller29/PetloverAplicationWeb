/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.local;

import edu.petlovers.entity.Criadero;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wsbachiller
 */
@Local
public interface CriaderoFacadeLocal {

    void create(Criadero criadero);

    void edit(Criadero criadero);

    void remove(Criadero criadero);

    Criadero find(Object id);

    List<Criadero> findAll();

    List<Criadero> findRange(int[] range);

    int count();
    
}