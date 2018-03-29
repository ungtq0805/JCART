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

import com.cts.jcart.entities.MstCommon;
import com.cts.jcart.entities.Product;
import com.cts.jcart.entities.User;

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
    @ManyToOne(targetEntity=Product.class)
    @JoinColumn(name="product")
    private Product product;
    
    @NotNull
    @Column(name="amount")
    private Integer amount;
    
    @Digits(integer=9, fraction=2)
    @DecimalMin("0.00")
    @Column(name="price")
    private BigDecimal price;
    
    @ManyToOne(targetEntity=User.class)
    @JoinColumn(name="shipper")
    private User shipper;
    
    @NotNull
    @ManyToOne(targetEntity=WhWarehouse.class)
    @JoinColumn(name="warehouse")
    private WhWarehouse warehouse;
    
    @NotNull
    @DateTimeFormat(pattern="dd/MM/yyyy")
    @Column(name="inflowdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inflowdate;
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy="inflow")
    @Fetch(FetchMode.SELECT)
    private Set<WhOutflow> outflows =
            new HashSet<WhOutflow>(0);
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastUpdDate; 
    
    @ManyToOne(targetEntity=User.class)
    @JoinColumn(name="status")
    private MstCommon status;
    
    @ManyToOne(targetEntity=User.class)
    @JoinColumn(name="applyPerson")
    private User applyPerson;
    
    @DateTimeFormat(pattern="dd/MM/yyyy")
    @Column(name="applyDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date applyDate;
    
    @ManyToOne(targetEntity=User.class)
    @JoinColumn(name="approvePerson")
    private User approvePerson;
    
    @DateTimeFormat(pattern="dd/MM/yyyy")
    @Column(name="approveDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date approveDate;
    
    public WhInflow(){ 
    	super();
    }
    
    public WhInflow(Long idval){ 
    	super();
    	id = idval;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
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

    public User getShipper() {
        return shipper;
    }

    public void setShipper(User shipper) {
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
    
    public Date getLastUpdDate() {
		return lastUpdDate;
	}

	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}

	public MstCommon getStatus() {
		return status;
	}

	public void setStatus(MstCommon status) {
		this.status = status;
	}

	public User getApplyPerson() {
		return applyPerson;
	}

	public void setApplyPerson(User applyPerson) {
		this.applyPerson = applyPerson;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public User getApprovePerson() {
		return approvePerson;
	}

	public void setApprovePerson(User approvePerson) {
		this.approvePerson = approvePerson;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
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