package com.cts.jcart.admin.web.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.cts.jcart.catalog.MasterCommonService;
import com.cts.jcart.constant.MstCmnConst;
import com.cts.jcart.entities.Customer;
import com.cts.jcart.entities.MstCommon;
import com.cts.jcart.entities.User;
import com.cts.jcart.utils.StringUtils;
import com.cts.jcart.wh.entities.WhInflow;
import com.cts.jcart.wh.entities.WhOutflow;

/**
 * Represents the database outflows table
 */
public class WhOutflowForm {

	private Long id;
    
    private WhInflow inflow;
    private long inflowId;
    
    @NotNull
    private int customerId;
    private Customer customer;
    
    @NotNull
    @Min(0)
    private Integer amount;

    @NotNull
    @Min(0)
    private BigDecimal price;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date outflowdate;
    
    private Date lastUpdDate;
    
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
		
		//set status kbn
		p.setStatusKbn(statusKbn);
		
		//apply person
		if (applyPersonId != null) {
			p.setApplyPerson(new User(applyPersonId));
		}
		
		//apply date
		p.setApplyDate(applyDate);
		
		//approve person
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
	public static WhOutflowForm fromWhOutflow(WhOutflow whOutflow,
			MasterCommonService masterCommonService){
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
		
		//set status kbn
		p.setStatusKbn(whOutflow.getStatusKbn());
		
		if (!StringUtils.isEmpty(whOutflow.getStatusKbn())) {
			//set status name
			MstCommon statusKbn = masterCommonService.getMstCommonByCommonNoAndClassNo(
					MstCmnConst.MST_STATUS, whOutflow.getStatusKbn()) ;
			p.setStatusName(statusKbn.getCharData1()); 
		}
		
		//apply person
		if (whOutflow.getApplyPerson() != null) {
			p.setApplyPerson(whOutflow.getApplyPerson());
			p.setApplyPersonId(whOutflow.getApplyPerson().getId());
		}
		
		//apply date
		p.setApplyDate(whOutflow.getApplyDate());
		
		if (whOutflow.getApprovePerson() != null) {
			p.setApprovePerson(whOutflow.getApprovePerson());
			p.setApplyPersonId(whOutflow.getApprovePerson().getId());
		}
		
	    p.setApproveDate(whOutflow.getApproveDate());
		
		//set outflow date
		p.setOutflowdate(whOutflow.getOutflowdate());
		
		//last update
		p.setLastUpdDate(whOutflow.getLastUpdDate());
		
		//set customer
		if (whOutflow.getCustomer() != null) {
			p.setCustomerId(whOutflow.getCustomer().getId());
			p.setCustomer(whOutflow.getCustomer());
		}
		
		return p;
	}
	
	/**
     * convert list entities to list form
     * @author ungtq
     * @param whInflow
     * @return WhInflowForm
     */
	public static List<WhOutflowForm> fromWhOutflows(
			List<WhOutflow> whOutflows,
			MasterCommonService masterCommonService){
		List<WhOutflowForm> lstWhInflowForm = new ArrayList<WhOutflowForm>();
		
		for (WhOutflow whOutflow : whOutflows) {
			lstWhInflowForm.add(fromWhOutflow(whOutflow, masterCommonService));
		}
		
		return lstWhInflowForm;
	}
	
	/**
     * convert list entities to list form and user id 
     * @author ungtq
     * @param whInflow
     * @return WhInflowForm
     */
	public static List<WhOutflowForm> fromWhOutflows(
			List<WhOutflow> whOutflows, 
			int userId,
			MasterCommonService masterCommonService){
		List<WhOutflowForm> lstWhOutflowForm = fromWhOutflows(
				whOutflows, masterCommonService);
		
		for (WhOutflowForm inflowForm : lstWhOutflowForm) {
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
		
		return lstWhOutflowForm;
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

	public Integer getApplyPersonId() {
		return applyPersonId;
	}

	public void setApplyPersonId(Integer applyPersonId) {
		this.applyPersonId = applyPersonId;
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

	public Integer getApprovePersonId() {
		return approvePersonId;
	}

	public void setApprovePersonId(Integer approvePersonId) {
		this.approvePersonId = approvePersonId;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}
}