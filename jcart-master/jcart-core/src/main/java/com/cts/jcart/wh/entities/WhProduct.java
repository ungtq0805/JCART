package com.cts.jcart.wh.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Represents the database goods table
 */
@Entity
@Table(name="wh_goods")
public class WhProduct implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id 
	@SequenceGenerator(name = "products_id_seq", sequenceName = "products_id_seq", initialValue = 6, allocationSize = 100)
    @GeneratedValue(generator = "products_id_seq")
    private Long id;
    
    @NotEmpty
    @Size(min=1, max=45)
    @Column(name="name")
    private String name;
    
    @NotEmpty
    @Size(min=1, max=20)
    @Column(name="units")
    private String units;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}