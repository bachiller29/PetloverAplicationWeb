/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.local;

import edu.petlovers.entity.TipoServicios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wsbachiller
 */
@Local
public interface TipoServiciosFacadeLocal {

    void create(TipoServicios tipoServicios);

    void edit(TipoServicios tipoServicios);

    void remove(TipoServicios tipoServicios);

    TipoServicios find(Object id);

    List<TipoServicios> findAll();

    List<TipoServicios> findRange(int[] range);

    int count();
    
}
