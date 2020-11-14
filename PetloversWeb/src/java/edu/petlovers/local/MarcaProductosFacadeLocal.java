/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.local;

import edu.petlovers.entity.MarcaProductos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wsbachiller
 */
@Local
public interface MarcaProductosFacadeLocal {

    void create(MarcaProductos marcaProductos);

    void edit(MarcaProductos marcaProductos);

    void remove(MarcaProductos marcaProductos);

    MarcaProductos find(Object id);

    List<MarcaProductos> findAll();

    List<MarcaProductos> findRange(int[] range);

    int count();
    
}
