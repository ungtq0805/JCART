package com.cts.jcart.admin.wh.reports;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.jcart.admin.wh.controllers.WhAbstractController;
import com.cts.jcart.dto.WhRemainDto;
import com.cts.jcart.wh.entities.WhWarehouse;
import com.cts.jcart.wh.impl.WhWarehousesData;
import com.cts.jcart.wh.report.WhRemainsData;

/**
 * Provides methods which transfer inflows data
 * without outflow part, that is the remains data,
 * to the corresponding jsp view page
 * 
 * The jsp page renders cross-table report
 * about currently available goods in all warehouses
 */
@Controller
public class RemainsController extends WhAbstractController {
	
	private static final String viewPrefix = "reports/";
    
    @Autowired
    WhRemainsData remainsData;
    
    @Autowired
    WhWarehousesData warehousesData;
    
    /**
     * Gets inflows and renders them (without outflows)
     * @param model map collection of parameters which can be used in the jsp file
     * @return name which will be resolved into the jsp page using
     * apache tiles configuration in the remains.xml file
     */
    @RequestMapping(value="/wh/reports/remain", method = RequestMethod.GET)
    public String showRemains(ModelMap model) {
        //List<WhInflow> inflows = remainsData.get();
    	List<WhRemainDto> remainList = remainsData.getRemainList();
    	
        List<WhWarehouse> warehouses = warehousesData.get();
        //model.addAttribute("inflows", inflows);
        model.addAttribute("remains", remainList);
        
        model.addAttribute("warehouses", warehouses);
        return viewPrefix + "remains";
    }
}