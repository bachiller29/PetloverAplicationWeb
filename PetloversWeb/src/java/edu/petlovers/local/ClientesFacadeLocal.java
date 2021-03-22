/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.local;

import edu.petlovers.entity.Clientes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wsbachiller
 */
@Local
public interface ClientesFacadeLocal {

    void create(Clientes clientes);

    void edit(Clientes clientes);

    void remove(Clientes clientes);

    Clientes find(Object id);

    List<Clientes> findAll();

    List<Clientes> findRange(int[] range);

    int count();

    public int cantidadDatosCliente(int idUsuario);
    
    public Clientes buscarCliente(int idCliente);

    public Clientes datosPorUsuario(int idUsuario);
    
}
