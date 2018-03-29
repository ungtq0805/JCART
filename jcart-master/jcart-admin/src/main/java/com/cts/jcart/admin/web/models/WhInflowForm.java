package com.cts.jcart.admin.web.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.cts.jcart.entities.Product;
import com.cts.jcart.entities.User;
import com.cts.jcart.wh.entities.WhInflow;
import com.cts.jcart.wh.entities.WhOutflow;
import com.cts.jcart.wh.entities.WhWarehouse;

/**
 * Represents the database inflows table
 */
public class WhInflowForm  {
    
    private Long id;
    
    private Product product;
    private int productId;
    
    @NotNull
    private Integer amount;
    
    @NotNull
    private BigDecimal price;
    
    private User shipper;
    private int shipperId;
    
    private WhWarehouse warehouse;
    private long warehouseId;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date inflowdate;
    
    private Set<WhOutflow> outflows =
            new HashSet<WhOutflow>(0);
    
    /**
     * From FORM to entity
     * @return
     */
    public WhInflow toWhInflow() {
    	WhInflow p = new WhInflow();
		p.setId(id);
		
		//set Product
		p.setProduct(new Product(productId));
		
		//set amount
		p.setAmount(amount);
		
		//set price
		p.setPrice(price);
		
		//set shipper
		p.setShipper(new User(shipperId));
		
		//set warehouse
		p.setWarehouse(new WhWarehouse(warehouseId));
		
		//inflow date
		p.setInflowdate(inflowdate);
		
		return p;
	}
    
    /**
     * copy properties to form
     * @author ungtq
     * @param whInflow
     * @return WhInflowForm
     */
	public static WhInflowForm fromWhInflow(WhInflow whInflow){
		WhInflowForm p = new WhInflowForm();
		p.setId(whInflow.getId());
		
		//set product
		if (whInflow.getProduct() != null) {
			p.setProduct(whInflow.getProduct());
			p.setProductId(whInflow.getProduct().getId());
		}
		
		//set amount
		p.setAmount(whInflow.getAmount());
		
		//set price
		p.setPrice(whInflow.getPrice());
		
		//set shipper
		if (whInflow.getShipper() != null) {
			p.setShipper(whInflow.getShipper());
			p.setShipperId(whInflow.getShipper().getId());
		}
		
		//set warehouse
		if (whInflow.getWarehouse() != null) {
			p.setWarehouse(whInflow.getWarehouse());
			p.setWarehouseId(whInflow.getWarehouse().getId());
		}
		
		//set inflow date
		p.setInflowdate(whInflow.getInflowdate());
		
		//set outflows
		if (whInflow.getOutflows() != null) {
			p.setOutflows(whInflow.getOutflows());
		}
		
		return p;
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

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	public int getShipperId() {
		return shipperId;
	}

	public void setShipperId(int shipperId) {
		this.shipperId = shipperId;
	}

	public WhWarehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WhWarehouse warehouse) {
		this.warehouse = warehouse;
	}

	public long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(long warehouseId) {
		this.warehouseId = warehouseId;
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