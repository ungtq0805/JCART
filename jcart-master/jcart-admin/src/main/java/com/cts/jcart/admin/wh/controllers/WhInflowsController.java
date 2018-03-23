package com.cts.jcart.admin.wh.controllers;

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

import com.cts.jcart.catalog.CatalogService;
import com.cts.jcart.security.SecurityService;
import com.cts.jcart.wh.entities.WhInflow;
import com.cts.jcart.wh.entities.WhProduct;
import com.cts.jcart.wh.entities.WhShipper;
import com.cts.jcart.wh.entities.WhWarehouse;
import com.cts.jcart.wh.impl.WhGoodsData;
import com.cts.jcart.wh.impl.WhInflowsData;
import com.cts.jcart.wh.impl.WhShippersData;
import com.cts.jcart.wh.impl.WhWarehousesData;

/**
 * Provides methods which transfer inflows data
 * to the corresponding jsp view pages
 * and handle user's actions
 * 
 * Inflow is an action of storing goods
 * into the warehouse by shipper
 */
@Controller
public class WhInflowsController extends WhAbstractController {
    
	private static final String viewPrefix = "wh/";
	
	@Autowired 
	SecurityService securityService;
	
    @Autowired
    WhInflowsData inflowsData;
    
    @Autowired
    WhGoodsData goodsData;
    
    @Autowired
    WhShippersData shippersData;
    
    @Autowired
    WhWarehousesData warehousesData;
    
    @Autowired
	CatalogService catalogService;
    
    List<WhProduct> goods;
    List<WhShipper> shippers;
    List<WhWarehouse> warehouses;
    
    /**
     * Gets inflows and renders them
     * @param model map collection of parameters which can be used in the jsp file
     * @return name which will be resolved into the jsp page using
     * apache tiles configuration in the inflows.xml file
     */
    @RequestMapping(value="/wh/inflows", method = RequestMethod.GET)
    public String showInflows(ModelMap model) {
        List<WhInflow> inflows = inflowsData.get();
        model.addAttribute("inflows", inflows);
        return viewPrefix + "inflows";
    }
    
    /**
     * Shows the form for the new product creation routine;
     * data about currently available goods, shippers and warehouses
     * is provided for the user to choose
     * @param model model map collection of parameters which can be used in the jsp file
     * @return name which will be resolved into the jsp page using
     * apache tiles configuration in the inflows.xml file
     */
    @RequestMapping(value = "/wh/inflows/init/create", method = RequestMethod.GET)
    public String showInflowForm(ModelMap model) {
    	WhInflow inflow = new WhInflow();
        /*goods = goodsData.get();
        shippers = shippersData.get();
        warehouses = warehousesData.get();*/
        model.addAttribute("inflow", inflow);
        /*model.addAttribute("goods", goods);
        model.addAttribute("shippers", shippers);
        model.addAttribute("warehouses", warehouses);*/
        model.addAttribute("shippers", securityService.getAllUsers());
        model.addAttribute("warehousesList", warehousesData.get());
        model.addAttribute("productsList", catalogService.getAllProducts());
        return viewPrefix + "create_inflow";
    }
    
    /**
     * Handles the submit action for a new inflow
     * @param inflow object with all product input information
     * @param result validation information about the current action
     * @param model model map collection of parameters which can be used in the jsp file
     * @return name which will be resolved into the jsp page using
     * apache tiles configuration in the inflows.xml file
     */
    @RequestMapping(value = "/wh/inflows/create", method = RequestMethod.POST)
    public String createInflow(
    		@Valid @ModelAttribute("inflow") WhInflow inflow,
    		BindingResult result, 
    		ModelMap model) {
        if (result.hasErrors()) {
            /*model.addAttribute("goods", goods);
            model.addAttribute("shippers", shippers);
            model.addAttribute("warehouses", warehouses);*/
        	model.addAttribute("warehousesList", warehousesData.get());
            model.addAttribute("productsList", catalogService.getAllProducts());
            return viewPrefix + "create_inflow";
        } else {
            inflowsData.add(inflow);
            return "redirect:/wh/inflows";
        }
    }
    
    /**
     * edit warehouse form with mode edit 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value="/wh/edit/inflow/{id}", method=RequestMethod.GET)
	public String editInflowForm(@PathVariable Integer id, Model model) {
    	WhInflow whInflow = inflowsData.get((long)id);
		model.addAttribute("inflow", whInflow);
		model.addAttribute("shippers", securityService.getAllUsers());
		model.addAttribute("warehousesList", warehousesData.get());
        model.addAttribute("productsList", catalogService.getAllProducts());
		return viewPrefix + "edit_inflow";
	}
    
    /**
     * Handles the submit action for a update warehouse
     * @param warehouse object with all product input information
     * @param result validation information about the current action
     * @return name which will be resolved into the jsp page using
     * apache tiles configuration in the warehouses.xml file
     */
    @RequestMapping(value = "/wh/inflow/update/{id}", method = RequestMethod.POST)
    public String updateInflow(
    		@Valid @ModelAttribute("inflow") WhInflow whInflow,
    		BindingResult result) {
        if (result.hasErrors()) {
            return viewPrefix + "edit_inflow";
        } else {
        	inflowsData.update(whInflow);
            return "redirect:/wh/inflows";
        }
    }
    
    /**
     * Handles the submit action for a delete warehouse
     * @param warehouse object with all product input information
     * @param result validation information about the current action
     * @return name which will be resolved into the jsp page using
     * apache tiles configuration in the warehouses.xml file
     */
    @RequestMapping(value = "/wh/inflow/delete/{id}", method = RequestMethod.GET)
    public String delInflowById(
    		@PathVariable Integer id,
    		Model model) {
    	inflowsData.removeById((long)id);
        return "redirect:/wh/inflows";
    }
}