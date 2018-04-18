/**
 * 
 */
package com.cts.jcart.site.web.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.jcart.catalog.CatalogService;
import com.cts.jcart.entities.Category;
import com.cts.jcart.entities.Product;
import com.cts.jcart.site.web.utils.WebUtils;

/**
 * @author ungtq
 *
 */
@Controller
public class ProductController extends JCartSiteBaseController{	
	@Autowired
	private CatalogService catalogService;
	
	@Override
	protected String getHeaderTitle(){
		return "Product";
	}
	
	@ModelAttribute("categories")
	public List<Category> categoriesList(){
		return catalogService.getPreviewCategories();
	}
	
	@RequestMapping("/products/{sku}")
	public String product(@PathVariable String sku, Model model){
		Product product = catalogService.getProductBySku(sku);
		model.addAttribute("product", product);
		return "product";
	}
	
	@RequestMapping("/products")
	public String searchProducts(@RequestParam(name="q", defaultValue="") String query, Model model)
	{
		List<Product> products = catalogService.searchProducts(query);
		model.addAttribute("products", products);
		model.addAttribute("dispatch", "product");
		return "products";
	}

	@RequestMapping(value="/products/images/{productId}", method=RequestMethod.GET)
	@ResponseBody
	public byte[] showProductImage(@PathVariable String productId, HttpServletRequest request, HttpServletResponse response) {
		try {
			File serverFile = new File(messageSource.getMessage(WebUtils.IMAGES_DIR, null, null) +productId+".jpg");
		    return Files.readAllBytes(serverFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/products/categories/{catId}", method=RequestMethod.GET)
	public String showProductByCat(@PathVariable String catId, 
			Model model) {
		Category category = catalogService.getCategoryById(Integer.parseInt(catId));
		model.addAttribute("catSelectedId", catId);
		model.addAttribute("products", category.getProducts());
		return "products";
	}
}
