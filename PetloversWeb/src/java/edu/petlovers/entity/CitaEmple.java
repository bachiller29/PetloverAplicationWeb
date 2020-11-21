/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
@Entity
@Table
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CitaEmple.findAll", query = "SELECT c FROM CitaEmple c")})
public class CitaEmple implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;

    public CitaEmple() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
