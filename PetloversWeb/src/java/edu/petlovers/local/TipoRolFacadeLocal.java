/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.local;

import edu.petlovers.entity.TipoRol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wsbachiller
 */
@Local
public interface TipoRolFacadeLocal {

    void create(TipoRol tipoRol);

    void edit(TipoRol tipoRol);

    void remove(TipoRol tipoRol);

    TipoRol find(Object id);

    List<TipoRol> findAll();

    List<TipoRol> findRange(int[] range);

    int count();
    
}
