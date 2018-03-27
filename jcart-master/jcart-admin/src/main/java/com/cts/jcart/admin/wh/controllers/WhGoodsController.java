package com.cts.jcart.admin.wh.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.jcart.wh.entities.WhProduct;

//import com.cts.jcart.wh.entities.WhProduct;
//import com.cts.jcart.wh.impl.WhGoodsData;

/**
 * Provides methods which transfer goods data
 * to the corresponding jsp view pages
 * and handle user's actions
 */
@Controller
@RequestMapping(value = "/goods")
public class WhGoodsController extends WhAbstractController {
    
//    @Autowired
//    WhGoodsData goodsData;
    
    /**
     * Gets goods and renders them
     * @param model map collection of parameters which can be used in the jsp file
     * @return name which will be resolved into the jsp page using
     * apache tiles configuration in the goods.xml file
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showGoods(ModelMap model) {
//        List<WhProduct> goods = goodsData.get();
//        model.addAttribute("goods", goods);
        return "goods";
    }
    
    /**
     * Shows the form for the new product creation routine
     * @param model model map collection of parameters which can be used in the jsp file
     * @return name which will be resolved into the jsp page using
     * apache tiles configuration in the goods.xml file
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showProductForm(ModelMap model) {
//    	WhProduct product = new WhProduct();
//        model.addAttribute(product);
        return "product";
    }
    
    /**
     * Handles the submit action for a new product
     * @param product object with all product input information
     * @param result validation information about the current action
     * @return name which will be resolved into the jsp page using
     * apache tiles configuration in the goods.xml file
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createProduct(@Valid WhProduct product, BindingResult result) {
//        if (result.hasErrors()) {
//            return "product";
//        } else {
//            goodsData.add(product);
//            return "redirect:/goods";
//        }
    	return null;
    }
}