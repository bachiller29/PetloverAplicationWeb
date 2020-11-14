/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.local;

import edu.petlovers.entity.TipoProductos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wsbachiller
 */
@Local
public interface TipoProductosFacadeLocal {

    void create(TipoProductos tipoProductos);

    void edit(TipoProductos tipoProductos);

    void remove(TipoProductos tipoProductos);

    TipoProductos find(Object id);

    List<TipoProductos> findAll();

    List<TipoProductos> findRange(int[] range);

    int count();
    
}
