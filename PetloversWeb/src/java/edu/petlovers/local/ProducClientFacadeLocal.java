/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.local;

import edu.petlovers.entity.ProducClient;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wsbachiller
 */
@Local
public interface ProducClientFacadeLocal {

    void create(ProducClient producClient);

    void edit(ProducClient producClient);

    void remove(ProducClient producClient);

    ProducClient find(Object id);

    List<ProducClient> findAll();

    List<ProducClient> findRange(int[] range);

    int count();
    
}
