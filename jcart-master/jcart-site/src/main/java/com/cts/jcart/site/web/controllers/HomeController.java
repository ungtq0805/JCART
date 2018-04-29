/**
 * 
 */
package com.cts.jcart.site.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cts.jcart.catalog.CatalogService;
import com.cts.jcart.entities.Category;

/**
 * @author ungtq
 *
 */
@Controller
public class HomeController extends JCartSiteBaseController
{	
	@Autowired
	private CatalogService catalogService;
	
	@Override
	protected String getHeaderTitle(){
		return "Home";
	}
	
	@RequestMapping("/home")
	public String home(Model model){
		List<Category> previewCategories = catalogService.getPreviewCategories();
		model.addAttribute("categories", previewCategories);
		
		//set the first category
		model.addAttribute("products", previewCategories.get(0).getProducts());
		model.addAttribute("dispatch", "home");
		return "home";
	}
	
	@RequestMapping("/categories/{name}")
	public String category(@PathVariable String name, Model model){
		Category category = catalogService.getCategoryByName(name);
		model.addAttribute("category", category);
		return "category";
	}
	
	@RequestMapping("/about")
	public String gotoAbout(){
		return "about";
	}
	
	@RequestMapping("/services")
	public String gotoServices(){
		return "services";
	}
	
	@RequestMapping("/contact")
	public String gotoContact(){
		return "contact";
	}
}
