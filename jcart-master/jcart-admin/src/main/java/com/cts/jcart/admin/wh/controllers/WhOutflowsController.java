package com.cts.jcart.admin.wh.controllers;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.jcart.admin.security.SecurityUtil;
import com.cts.jcart.admin.web.models.WhOutflowForm;
import com.cts.jcart.admin.web.validators.OutflowFormValidator;
import com.cts.jcart.catalog.MasterCommonService;
import com.cts.jcart.constant.MstCmnConst;
import com.cts.jcart.customers.CustomerService;
import com.cts.jcart.entities.Customer;
import com.cts.jcart.entities.MstCommon;
import com.cts.jcart.entities.Permission;
import com.cts.jcart.entities.Role;
import com.cts.jcart.entities.User;
import com.cts.jcart.wh.entities.WhInflow;
import com.cts.jcart.wh.entities.WhOutflow;
import com.cts.jcart.wh.impl.WhInflowsData;
import com.cts.jcart.wh.impl.WhOutflowsData;

/**
 * Provides methods which transfer outflows data
 * to the corresponding jsp view pages
 * 
 * Outflow is an action of selling goods
 * from the warehouse to some customer
 */
@Controller
public class WhOutflowsController extends WhAbstractController {
    
	private static final String viewPrefix = "wh/";
	
	public static final String NOSUCHAMOUNT = "There is no such amount in the chosen warehouse";
	
    @Autowired
    WhOutflowsData outflowsData;
    
    @Autowired 
    private OutflowFormValidator outflowFormValidator;
    
    @Autowired 
	private CustomerService customerService;
    
    @Autowired
   	MasterCommonService masterCommonService;
    
    @Autowired
    WhInflowsData inflowsData;
    
    WhInflow inflow;
    
    @ModelAttribute("customers")
	public List<Customer> categoriesList(){
		return customerService.getAllCustomers();
	}
    
    /**
     * Gets outflows and renders them
     * @param model map collection of parameters which can be used in the jsp file
     * @return name which will be resolved into the jsp page using
     * apache tiles configuration in the outflows.xml file
     */
    @RequestMapping(value="/wh/outflows", method = RequestMethod.GET)
    public String showOutflows(ModelMap model) {
        List<WhOutflow> outflows = outflowsData.get();
        
        //set to DTO
        List<WhOutflowForm> outflowForms = WhOutflowForm.fromWhOutflows(
        		outflows, 
        		getCurrentUser().getUser().getId(),
        		masterCommonService);
        
        model.addAttribute("outflows", outflowForms);
        return viewPrefix + "outflows";
    }
    
    /**
     * Shows the form for buying currently selected inflow (outflow form)
     * @param inflow_id identifier for the selected inflow
     * @param model model map collection of parameters which can be used in the jsp file
     * @return name which will be resolved into the jsp page using
     * apache tiles configuration in the stock.xml file
     */
    @RequestMapping(value = "/wh/buy/{inflow_id}", method = RequestMethod.GET)
    public String showBuyingForm(@PathVariable("inflow_id") Long inflow_id, ModelMap model) {
        inflow = inflowsData.get(inflow_id);
        
        WhOutflowForm outflowForm = new WhOutflowForm();
        outflowForm.setInflow(inflow);
        if (inflow != null) {
        	outflowForm.setInflowId(inflow.getId());
        }
        
        model.addAttribute("outflow", outflowForm);
        model.addAttribute("product_name", inflow.getProduct().getName());
        
        return viewPrefix + "stock_buy";
    }
    
    /**
     * Savetemp with outflow
     * Handles the submit action for a new inflow
     * @param inflow object with all product input information
     * @param result validation information about the current action
     * @param model model map collection of parameters which can be used in the jsp file
     * @return name which will be resolved into the jsp page using
     * apache tiles configuration in the inflows.xml file
     */
    @RequestMapping(value = "/wh/outflows/create", method = RequestMethod.POST)
    public String saveInflowInModeNew(
    		@Valid @ModelAttribute("outflow") WhOutflowForm outflowForm,
    		BindingResult result, 
    		ModelMap model) {
    	return saveOrUpdate(SAVETEMP, outflowForm, result, model, MODE_NEW);
    }
    
    /**
     * Apply with outflow
     * @author ungtq
     * @param outflowForm
     * @param result
     * @param model
     * @return String
     */ 
    @RequestMapping(value = "/wh/outflows/create", params="apply", method = RequestMethod.POST)
    public String applyInflowInModeNew(
    		@Valid @ModelAttribute("outflow") WhOutflowForm outflowForm,
    		BindingResult result, 
    		ModelMap model) {
    	return saveOrUpdate(APPLY, outflowForm, result, model, MODE_NEW);
    }
    
    /**
     * @author ungtq
     * save or update
     * @param dispatch
     * @param outflowForm
     * @param result
     * @param model
     * @param mode
     * @return String
     */
    private String saveOrUpdate(
    		String dispatch,
    		@Valid @ModelAttribute("outflow") WhOutflowForm outflowForm,
    		BindingResult result, 
    		ModelMap model,
    		String mode) {
    	
    	outflowFormValidator.validate(outflowForm, result);
    	
    	//set product name
    	model.addAttribute("product_name", inflow.getProduct().getName());
    	
    	//set inflow
    	outflowForm.setInflow(inflow);
    	
    	if (inflow != null) {
    		outflowForm.setInflowId(inflow.getId());
    	}
    	
    	if (result.hasErrors()) {
    		if (MODE_NEW.equals(mode)) {
        		return viewPrefix + "stock_buy";
        	}
        	
            return viewPrefix + "stock_buy_edit";
    	}
    	
    	//check quantities
    	if (outflowForm.getAmount() > inflow.getLeft()) {
    		result.rejectValue("amount", "", NOSUCHAMOUNT);

    		if (MODE_NEW.equals(mode)) {
    			return viewPrefix + "stock_buy";
    		}
    		
    		return viewPrefix + "stock_buy_edit";
    	}
    	
    	
    	outflowForm.setLastUpdDate(Calendar.getInstance().getTime());
    	
    	WhOutflow outFlow = outflowForm.toWhOutflow();
    	
    	//set status
    	MstCommon status = getStatus(dispatch);
    	outFlow.setStatusKbn(status.getClassNo());
    	
    	//set apply person
    	outFlow.setApplyPerson(new User(getCurrentUser().getUser().getId()));
    	
    	//set apply date
    	outFlow.setApplyDate(Calendar.getInstance().getTime());
    	
    	outFlow.setLastUpdDate(Calendar.getInstance().getTime());
    	
    	//error invalid
    	if (MODE_NEW.equals(mode)) {
    		outflowsData.add(outFlow);
    	}
    	
    	if (MODE_UPD.equals(mode)) {
    		outflowsData.update(outFlow);
    	}
    	
        return "redirect:/wh/outflows";
    }
    
    /**
     * @author ungtq
     * determine status
     * @param dispatch
     * @return MstCommon
     */
    private MstCommon getStatus(String dispatch) {
    	MstCommon status = null;
    	
    	if (SAVETEMP.equals(dispatch)) {
    		status = masterCommonService.getStatusSaveTemp();
    	} 
    	
    	if (APPLY.equals(dispatch)) {
    		status = masterCommonService.getStatusApply();
    	}
    	
    	return status;
    }
    
    /**
     * edit warehouse form with mode edit 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value="/wh/edit/outflow/{id}", method=RequestMethod.GET)
	public String editOutflowForm(@PathVariable Integer id, Model model) {
    	WhOutflow whOutflow = outflowsData.get((long)id);
    	WhOutflowForm whOutflowForm = WhOutflowForm.fromWhOutflow(whOutflow,
    			masterCommonService);
		model.addAttribute("outflow", whOutflowForm);
		
		//get inflow
		inflow = whOutflow.getInflow();
		model.addAttribute("product_name", inflow.getProduct().getName());
		
		return viewPrefix + "stock_buy_edit";
	}
    
    /**
     * Handles the submit action for a update warehouse
     * @param warehouse object with all product input information
     * @param result validation information about the current action
     * @return name which will be resolved into the jsp page using
     * apache tiles configuration in the warehouses.xml file
     */
    @RequestMapping(value = "/wh/outflow/update/{id}", params="saveTemp", method = RequestMethod.POST)
    public String saveInflowWithModeUpd(
    		@Valid @ModelAttribute("outflow") WhOutflowForm outflowForm,
    		BindingResult result,
    		ModelMap model) {
    	return saveOrUpdate(SAVETEMP, outflowForm, result, model, MODE_UPD);
    }
    
    @RequestMapping(value = "/wh/outflow/update/{id}", params="apply", method = RequestMethod.POST)
    public String applyInflowInModeUpd(
    		@Valid @ModelAttribute("outflow") WhOutflowForm outflowForm,
    		BindingResult result, 
    		ModelMap model) {
    	return saveOrUpdate(APPLY, outflowForm, result, model, MODE_UPD);
    }
    
    /**
     * Handles the submit action for a delete warehouse
     * @param warehouse object with all product input information
     * @param result validation information about the current action
     * @return name which will be resolved into the jsp page using
     * apache tiles configuration in the warehouses.xml file
     */
    @RequestMapping(value = "/wh/outflow/delete/{id}", method = RequestMethod.GET)
    public String delInflowById(
    		@PathVariable Integer id,
    		Model model) {
    	outflowsData.removeById((long)id);
        return "redirect:/wh/outflows";
    }
    
    /**
     * Handles the submit action for a update warehouse
     * @param warehouse object with all product input information
     * @param result validation information about the current action
     * @return name which will be resolved into the jsp page using
     * apache tiles configuration in the warehouses.xml file
     */
    @RequestMapping(value = "/wh/view/outflow/{id}", method = RequestMethod.GET)
    public String viewInflowWithModeUpd(@PathVariable Integer id, Model model) {
    	
    	WhOutflow whOutflow = outflowsData.get((long)id);
    	WhOutflowForm whOutflowForm = WhOutflowForm.fromWhOutflow(whOutflow, 
    			masterCommonService);
		model.addAttribute("outflow", whOutflowForm);
		
		model.addAttribute("isApprove", "false");
		
		//if inflows has status kbn approve, not show the button approve
		if (!MstCmnConst.MST_STATUS_APPROVE.equals(whOutflowForm.getStatusKbn())
			&& !MstCmnConst.MST_STATUS_SAVETEMP.equals(whOutflowForm.getStatusKbn())) {
			User authUser = getCurrentUser().getUser();
			for (Role role : authUser.getRoles()){
				List<Permission> permissions = role.getPermissions();
				for (Permission permission : permissions){
					if (SecurityUtil.MANAGE_ROLE_INOUTFLOW_APPROVE.equals(
							permission.getName())) {
						model.addAttribute("isApprove", "true");
						break;
					}
				}
			}
		}
		
		return viewPrefix + "view_stock_buy";
    }
    
    /**
     * Handles the submit action for a update warehouse
     * @param warehouse object with all product input information
     * @param result validation information about the current action
     * @return name which will be resolved into the jsp page using
     * apache tiles configuration in the warehouses.xml file
     */
    @RequestMapping(value = "/wh/outflow/approve", method = RequestMethod.POST)
    public String approveInflow(
    		@Valid @ModelAttribute("outflow") WhOutflowForm outflowForm,
    		BindingResult result,
    		ModelMap model) {
    	WhOutflow whOutflow = outflowsData.get(outflowForm.getId());
    	whOutflow.setStatusKbn(MstCmnConst.MST_STATUS_APPROVE);
    	whOutflow.setLastUpdDate(Calendar.getInstance().getTime());
    	
    	whOutflow.setApproveDate(Calendar.getInstance().getTime());
    	whOutflow.setApprovePerson(new User(getCurrentUser().getUser().getId()));
    	outflowsData.update(whOutflow);
    	
    	return "redirect:/wh/outflows";
    }
    
    /**
     * Handles the submit action for a update warehouse
     * @param warehouse object with all product input information
     * @param result validation information about the current action
     * @return name which will be resolved into the jsp page using
     * apache tiles configuration in the warehouses.xml file
     */
    @RequestMapping(value = "/wh/outflow/sendBack", method = RequestMethod.POST)
    public String sendBackInflow(
    		@Valid @ModelAttribute("outflow") WhOutflowForm outflowForm,
    		BindingResult result,
    		ModelMap model) {
    	WhOutflow whOutflow = outflowsData.get(outflowForm.getId());
    	whOutflow.setStatusKbn(MstCmnConst.MST_STATUS_SAVETEMP);
    	whOutflow.setLastUpdDate(Calendar.getInstance().getTime());
    	
    	outflowsData.update(whOutflow);
    	
    	return "redirect:/wh/outflows";
    }
}