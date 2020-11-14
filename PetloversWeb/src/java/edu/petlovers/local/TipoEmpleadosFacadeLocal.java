/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.local;

import edu.petlovers.entity.TipoEmpleados;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wsbachiller
 */
@Local
public interface TipoEmpleadosFacadeLocal {

    void create(TipoEmpleados tipoEmpleados);

    void edit(TipoEmpleados tipoEmpleados);

    void remove(TipoEmpleados tipoEmpleados);

    TipoEmpleados find(Object id);

    List<TipoEmpleados> findAll();

    List<TipoEmpleados> findRange(int[] range);

    int count();
    
}
