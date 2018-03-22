package com.cts.jcart.wh.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Represents the database inflows table
 */
@Entity
@Table(name="wh_inflows")
public class WhInflow implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id 
	@SequenceGenerator(name = "inflows_id_seq", sequenceName = "inflows_id_seq", initialValue = 6, allocationSize = 100)
    @GeneratedValue(generator = "inflows_id_seq")
    private Long id;
    
    @NotNull
    @ManyToOne(targetEntity=WhProduct.class)
    @JoinColumn(name="product")
    private WhProduct product;
    
    @NotNull
    @Column(name="amount")
    private Integer amount;
    
    @Digits(integer=9, fraction=2)
    @DecimalMin("0.00")
    @Column(name="price")
    private BigDecimal price;
    
    @NotNull
    @ManyToOne(targetEntity=WhShipper.class)
    @JoinColumn(name="shipper")
    private WhShipper shipper;
    
    @NotNull
    @ManyToOne(targetEntity=WhWarehouse.class)
    @JoinColumn(name="warehouse")
    private WhWarehouse warehouse;
    
    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="inflowdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inflowdate;
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy="inflow")
    @Fetch(FetchMode.SELECT)
    private Set<WhOutflow> outflows =
            new HashSet<WhOutflow>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WhProduct getProduct() {
        return product;
    }

    public void setProduct(WhProduct product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public WhShipper getShipper() {
        return shipper;
    }

    public void setShipper(WhShipper shipper) {
        this.shipper = shipper;
    }

    public WhWarehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WhWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Date getInflowdate() {
        return inflowdate;
    }

    public void setInflowdate(Date inflowdate) {
        this.inflowdate = inflowdate;
    }

    public Set<WhOutflow> getOutflows() {
        return outflows;
    }

    public void setOutflows(Set<WhOutflow> outflows) {
        this.outflows = outflows;
    }
    
    /**
     * Gets the amount of goods which are left
     * in the currently available stock for the current inflow
     * @return amount of goods
     */
    public Integer getLeft() {
        Integer left = amount;
        for (WhOutflow outflow : outflows) {
            left -= outflow.getAmount();
        }
        return left;
    }
}