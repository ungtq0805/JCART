package com.cts.jcart.admin.web.models;

import java.math.BigDecimal;
import java.util.Date;

import com.cts.jcart.entities.Customer;
import com.cts.jcart.wh.entities.WhInflow;
import com.cts.jcart.wh.entities.WhOutflow;

/**
 * Represents the database outflows table
 */
public class WhOutflowForm {

	private Long id;
    
    private WhInflow inflow;
    private long inflowId;
    
    private Customer customer;
    private int customerId;
    
    private Integer amount;

    private BigDecimal price;
    
    private Date outflowdate;
    
    private Date lastUpdDate;
    
    /**
     * From FORM to entity
     * @return
     */
    public WhOutflow toWhOutflow() {
    	WhOutflow p = new WhOutflow();
		p.setId(id);
		
		//set inflow
		p.setInflow(new WhInflow(inflowId));
		
		//set customer
		p.setCustomer(new Customer(customerId));
		
		//set amount
		p.setAmount(amount);
		
		//set price
		p.setPrice(price);
		
		//set outflowDate
		p.setOutflowdate(outflowdate);
		
		//last update date
		p.setLastUpdDate(lastUpdDate);
		
		return p;
    }
    
    /**
     * copy properties to form
     * @author ungtq
     * @param whInflow
     * @return WhInflowForm
     */
	public static WhOutflowForm fromWhOutflow(WhOutflow whOutflow){
		WhOutflowForm p = new WhOutflowForm();
		p.setId(whOutflow.getId());
		
		//set inflow
		if (whOutflow.getInflow() != null) {
			p.setInflow(whOutflow.getInflow());
			p.setInflowId(whOutflow.getInflow().getId());
		}
		
		//set customer
		if (whOutflow.getCustomer() != null) {
			p.setCustomer(whOutflow.getCustomer());
			p.setCustomerId(whOutflow.getCustomer().getId());
		}
		
		//set amount
		p.setAmount(whOutflow.getAmount());
		
		//set price
		p.setPrice(whOutflow.getPrice());
		
		//set outflow date
		p.setOutflowdate(whOutflow.getOutflowdate());
		
		//last update
		p.setLastUpdDate(whOutflow.getLastUpdDate());
		
		return p;
	}

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

	public long getInflowId() {
		return inflowId;
	}

	public void setInflowId(long inflowId) {
		this.inflowId = inflowId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public Date getOutflowdate() {
		return outflowdate;
	}

	public void setOutflowdate(Date outflowdate) {
		this.outflowdate = outflowdate;
	}

	public Date getLastUpdDate() {
		return lastUpdDate;
	}

	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}
}