/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.local;

import edu.petlovers.entity.SalidaProductos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wsbachiller
 */
@Local
public interface SalidaProductosFacadeLocal {

    void create(SalidaProductos salidaProductos);

    void edit(SalidaProductos salidaProductos);

    void remove(SalidaProductos salidaProductos);

    SalidaProductos find(Object id);

    List<SalidaProductos> findAll();

    List<SalidaProductos> findRange(int[] range);

    int count();
    
}
