package com.cts.jcart.admin.wh.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.jcart.wh.entities.WhInflow;
import com.cts.jcart.wh.impl.WhCustomersData;
import com.cts.jcart.wh.impl.WhInflowsData;
import com.cts.jcart.wh.impl.WhOutflowsData;

/**
 * Provides methods which transfer stock data
 * to the corresponding jsp view pages
 * and handle user's actions
 * 
 * Stock represents currently available goods
 * in the warehouse
 */
@Controller
public class WhStockController extends WhAbstractController {
    
    public static final String NOSUCHAMOUNT = "There is no such amount in the chosen warehouse";
    
    private static final String viewPrefix = "wh/";
    
    @Autowired
    WhInflowsData inflowsData;
    
    @Autowired
    WhOutflowsData outflowsData;
    
    @Autowired
    WhCustomersData customersData;
    
    /**
     * Gets inflows (with the amount left, i.e. without outflow data) and renders them
     * @param model map collection of parameters which can be used in the jsp file
     * @return name which will be resolved into the jsp page using
     * apache tiles configuration in the stock.xml file
     */
    @RequestMapping(value = "/wh/stocks", method = RequestMethod.GET)
    public String showInflows(ModelMap model) {
        List<WhInflow> inflows = inflowsData.getInFlowsActive();
        model.addAttribute("inflows", inflows);
        return viewPrefix + "stock";
    }
}