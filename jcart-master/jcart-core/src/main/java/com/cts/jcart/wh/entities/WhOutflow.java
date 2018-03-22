package com.cts.jcart.wh.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Represents the database outflows table
 */
@Entity
@Table(name="wh_outflows")
public class WhOutflow implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id 
	@SequenceGenerator(name = "outflows_id_seq", sequenceName = "outflows_id_seq", initialValue = 6, allocationSize = 100)
    @GeneratedValue(generator = "outflows_id_seq")
    private Long id;
    
    @NotNull
    @ManyToOne(targetEntity=WhInflow.class)
    @JoinColumn(name="inflow")
    private WhInflow inflow;
    
    @NotNull
    @ManyToOne(targetEntity=WhCustomer.class)
    @JoinColumn(name="customer")
    private WhCustomer customer;
    
    @NotNull
    @Column(name="amount")
    private Integer amount;
    
    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="outflowdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date outflowdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WhInflow getInflow() {
        return inflow;
    }

    public void setInflow(WhInflow inflow) {
        this.inflow = inflow;
    }

    public WhCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(WhCustomer customer) {
        this.customer = customer;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getOutflowdate() {
        return outflowdate;
    }

    public void setOutflowdate(Date outflowdate) {
        this.outflowdate = outflowdate;
    }
}