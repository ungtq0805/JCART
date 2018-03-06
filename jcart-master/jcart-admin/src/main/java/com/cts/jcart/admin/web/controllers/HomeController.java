/**
 * 
 */
package com.cts.jcart.admin.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ungtq
 *
 */
@Controller
public class HomeController extends JCartAdminBaseController
{	
	@Override
	protected String getHeaderTitle() {
		return "Home";
	}
	
	@RequestMapping("/home")
	public String home(Model model){
		return "home";
	}
}
