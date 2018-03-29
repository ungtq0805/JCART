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

import com.cts.jcart.admin.web.models.WhOutflowForm;
import com.cts.jcart.admin.web.validators.OutflowFormValidator;
import com.cts.jcart.customers.CustomerService;
import com.cts.jcart.entities.Customer;
import com.cts.jcart.wh.entities.WhInflow;
import com.cts.jcart.wh.entities.WhOutflow;
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
        model.addAttribute("outflows", outflows);
        return viewPrefix + "outflows";
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
    	WhOutflowForm whOutflowForm = WhOutflowForm.fromWhOutflow(whOutflow);
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
    @RequestMapping(value = "/wh/outflow/update/{id}", method = RequestMethod.POST)
    public String updateInflow(
    		@Valid @ModelAttribute("outflow") WhOutflowForm outflowForm,
    		BindingResult result,
    		ModelMap model) {
    	outflowFormValidator.validate(outflowForm, result);
        if (result.hasErrors()) {
            return viewPrefix + "stock_buy_edit";
        } else {
        	
        	//check quantities
        	if (outflowForm.getAmount() > inflow.getLeft()) {
        		result.rejectValue("amount", "", NOSUCHAMOUNT);
        		return viewPrefix + "stock_buy";
        	}
        	
        	WhOutflow persistedInflow = outflowForm.toWhOutflow();
        	persistedInflow.setLastUpdDate(Calendar.getInstance().getTime());
        	outflowsData.update(persistedInflow);
            return "redirect:/wh/outflows";
        }
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
}