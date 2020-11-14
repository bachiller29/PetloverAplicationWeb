/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.local;

import edu.petlovers.entity.TipoMascotas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wsbachiller
 */
@Local
public interface TipoMascotasFacadeLocal {

    void create(TipoMascotas tipoMascotas);

    void edit(TipoMascotas tipoMascotas);

    void remove(TipoMascotas tipoMascotas);

    TipoMascotas find(Object id);

    List<TipoMascotas> findAll();

    List<TipoMascotas> findRange(int[] range);

    int count();
    
}
