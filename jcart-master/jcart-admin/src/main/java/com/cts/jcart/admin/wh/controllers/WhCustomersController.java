package com.cts.jcart.admin.wh.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.jcart.wh.entities.WhCustomer;
import com.cts.jcart.wh.impl.WhCustomersData;

/**
 * Provides methods which transfer customers data
 * to the corresponding jsp view pages
 * and handle user's actions
 */
@Controller
@RequestMapping(value = "/customers")
public class WhCustomersController extends WhAbstractController {
    
    @Autowired
    WhCustomersData customersData;
    
    /**
     * Gets customers and renders them
     * @param model map collection of parameters which can be used in the jsp file
     * @return name which will be resolved into the jsp page using
     * apache tiles configuration in the customers.xml file
     */
    @RequestMapping(value = "/showCust", method = RequestMethod.GET)
    public String showCustomers(ModelMap model) {
        List<WhCustomer> customers = customersData.get();
        model.addAttribute("customers", customers);
        return "customers";
    }
    
    /**
     * Shows the form for the new customer creation routine
     * @param model model map collection of parameters which can be used in the jsp file
     * @return name which will be resolved into the jsp page using
     * apache tiles configuration in the customers.xml file
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCustomersForm(ModelMap model) {
    	WhCustomer customer = new WhCustomer();
        model.addAttribute(customer);
        return "customer";
    }
    
    /**
     * Handles the submit action for a new customer
     * @param customer object with all customer input information
     * @param result validation information about the current action
     * @return name which will be resolved into the jsp page using
     * apache tiles configuration in the customers.xml file
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createCustomer(@Valid WhCustomer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "customer";
        } else {
            customersData.add(customer);
            return "redirect:/customers";
        }
    }
}