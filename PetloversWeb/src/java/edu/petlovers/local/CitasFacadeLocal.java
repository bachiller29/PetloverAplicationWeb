/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.local;

import edu.petlovers.entity.Citas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wsbachiller
 */
@Local
public interface CitasFacadeLocal {

    void create(Citas citas);

    void edit(Citas citas);

    void remove(Citas citas);

    Citas find(Object id);

    List<Citas> findAll();

    List<Citas> findRange(int[] range);

    int count();

    public Citas buscarCita(int idCita);

    public int cantidadCitas(int idCliente, String fecha);
    
}
