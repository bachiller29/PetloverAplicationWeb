/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.local;

import edu.petlovers.entity.ProveeInve;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wsbachiller
 */
@Local
public interface ProveeInveFacadeLocal {

    void create(ProveeInve proveeInve);

    void edit(ProveeInve proveeInve);

    void remove(ProveeInve proveeInve);

    ProveeInve find(Object id);

    List<ProveeInve> findAll();

    List<ProveeInve> findRange(int[] range);

    int count();
    
}
