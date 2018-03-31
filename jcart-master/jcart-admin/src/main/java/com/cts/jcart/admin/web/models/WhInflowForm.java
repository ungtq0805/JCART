package com.cts.jcart.admin.web.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.cts.jcart.catalog.MasterCommonService;
import com.cts.jcart.constant.MstCmnConst;
import com.cts.jcart.entities.MstCommon;
import com.cts.jcart.entities.Product;
import com.cts.jcart.entities.User;
import com.cts.jcart.utils.StringUtils;
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
    @Min(0)
    private Integer amount;
    
    @NotNull
    @Min(0)
    private BigDecimal price;
    
    @NotNull
    private int shipperId;
    private User shipper;
    
    private WhWarehouse warehouse;
    private long warehouseId;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date inflowdate;
    
    private Set<WhOutflow> outflows =
            new HashSet<WhOutflow>(0);
    
    /**
     * status
     */
    private String statusKbn;
    private String statusName;
    
    /**
     * apply Person
     */
    private User applyPerson;
    private Integer applyPersonId;
    
    private Date applyDate;
    
    private User approvePerson;
    private Integer approvePersonId;
    
    private Date approveDate;
    
    private boolean isEdit = false;
    
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
		
		p.setStatusKbn(statusKbn);
		
		if (applyPersonId != null) {
			p.setApplyPerson(new User(applyPersonId));
		}
		
		p.setApplyDate(applyDate);
		
		if (approvePersonId != null) {
			p.setApprovePerson(new User(approvePersonId));
		}
		
		p.setApproveDate(approveDate);
		
		return p;
	}
    
    /**
     * copy properties to form
     * @author ungtq
     * @param whInflow
     * @return WhInflowForm
     */
	public static WhInflowForm fromWhInflow(WhInflow whInflow,
			MasterCommonService masterCommonService){
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
		
		p.setStatusKbn(whInflow.getStatusKbn());
		
		if (!StringUtils.isEmpty(whInflow.getStatusKbn())) {
			//set status name
			MstCommon statusKbn = masterCommonService.getMstCommonByCommonNoAndClassNo(
					MstCmnConst.MST_STATUS, whInflow.getStatusKbn()) ;
			p.setStatusName(statusKbn.getCharData1()); 
		}
		
		//apply person
		if (whInflow.getApplyPerson() != null) {
			p.setApplyPerson(whInflow.getApplyPerson());
			p.setApplyPersonId(whInflow.getApplyPerson().getId());
		}
		
		//apply date
		p.setApplyDate(whInflow.getApplyDate());
		
		if (whInflow.getApprovePerson() != null) {
			p.setApprovePerson(whInflow.getApprovePerson());
			p.setApplyPersonId(whInflow.getApprovePerson().getId());
		}
		
	    p.setApproveDate(whInflow.getApproveDate());
		
		return p;
	}
	
	/**
     * convert list entities to list form
     * @author ungtq
     * @param whInflow
     * @return WhInflowForm
     */
	public static List<WhInflowForm> fromWhInflows(
			List<WhInflow> whInflows,
			MasterCommonService masterCommonService){
		List<WhInflowForm> lstWhInflowForm = new ArrayList<WhInflowForm>();
		
		for (WhInflow whInflow : whInflows) {
			lstWhInflowForm.add(fromWhInflow(whInflow, masterCommonService));
		}
		
		return lstWhInflowForm;
	}
	
	/**
     * convert list entities to list form and user id 
     * @author ungtq
     * @param whInflow
     * @return WhInflowForm
     */
	public static List<WhInflowForm> fromWhInflows(
			List<WhInflow> whInflows, 
			int userId,
			MasterCommonService masterCommonService){
		List<WhInflowForm> lstWhInflowForm = fromWhInflows(
				whInflows, masterCommonService);
		
		for (WhInflowForm inflowForm : lstWhInflowForm) {
			if (inflowForm.getApplyPersonId() == userId) {
				inflowForm.setEdit(true);
			}
			
			if (MstCmnConst.MST_STATUS_APPLY.equals(inflowForm.getStatusKbn())) {
				inflowForm.setEdit(false);
			}
			
			if (MstCmnConst.MST_STATUS_APPROVE.equals(inflowForm.getStatusKbn())) {
				inflowForm.setEdit(false);
			}
			
			if (inflowForm.getApprovePerson() == null) {
				inflowForm.setApprovePerson(new User());
			}
		}
		
		return lstWhInflowForm;
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

	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	public String getStatusKbn() {
		return statusKbn;
	}

	public void setStatusKbn(String statusKbn) {
		this.statusKbn = statusKbn;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
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

	public Integer getApplyPersonId() {
		return applyPersonId;
	}

	public void setApplyPersonId(Integer applyPersonId) {
		this.applyPersonId = applyPersonId;
	}

	public Integer getApprovePersonId() {
		return approvePersonId;
	}

	public void setApprovePersonId(Integer approvePersonId) {
		this.approvePersonId = approvePersonId;
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