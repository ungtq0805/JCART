package com.cts.jcart.wh.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Represents the database warehouses table
 */
@Entity
@Table(name="wh_warehouses")
public class WhWarehouse implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id 
   	@SequenceGenerator(name = "warehouse_id_seq", sequenceName = "warehouse_id_seq", initialValue = 6, allocationSize = 100)
    @GeneratedValue(generator = "warehouse_id_seq")
    private Long id;
    
    @NotEmpty
    @Size(min=1, max=45)
    @Column(name="name")
    private String name;
    
    @NotEmpty
    @Size(min=1, max=128)
    @Column(name="address")
    private String address;
    
    @NotNull
    @Column(name="employees")
    private Integer employees;
    
    public WhWarehouse() {
    	super();
    }
    
    public WhWarehouse(Long idVal) {
    	super();
    	id = idVal;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getEmployees() {
        return employees;
    }

    public void setEmployees(Integer employees) {
        this.employees = employees;
    }
}