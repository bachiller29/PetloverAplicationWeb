/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.local;

import edu.petlovers.entity.EntradaProductos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wsbachiller
 */
@Local
public interface EntradaProductosFacadeLocal {

    void create(EntradaProductos entradaProductos);

    void edit(EntradaProductos entradaProductos);

    void remove(EntradaProductos entradaProductos);

    EntradaProductos find(Object id);

    List<EntradaProductos> findAll();

    List<EntradaProductos> findRange(int[] range);

    int count();
    
}
