package com.cts.jcart.wh.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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

import com.cts.jcart.entities.Customer;
import com.cts.jcart.entities.MstCommon;
import com.cts.jcart.entities.User;

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
    @ManyToOne(targetEntity=Customer.class)
    @JoinColumn(name="customer")
    private Customer customer;
    
    @NotNull
    @Column(name="amount")
    private Integer amount;

    @NotNull
    @Column(name="price")
    private BigDecimal price;
    
    @NotNull
    @Column(name="outflowdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date outflowdate;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastUpdDate;
    
    @ManyToOne(targetEntity=MstCommon.class)
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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
}