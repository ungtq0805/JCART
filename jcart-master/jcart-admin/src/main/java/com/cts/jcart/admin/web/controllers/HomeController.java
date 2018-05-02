/**
 * 
 */
package com.cts.jcart.admin.web.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.jcart.admin.web.utils.WebUtils;
import com.cts.jcart.constant.JCartConsts;
import com.cts.jcart.utils.StringUtils;
import com.cts.jcart.wh.report.WhRemainsData;

/**
 * @author ungtq
 *
 */
@Controller
public class HomeController extends JCartAdminBaseController{
	@Autowired
    WhRemainsData whRemainsData;
	
	@Override
	protected String getHeaderTitle() {
		return "Home";
	}
	
	@RequestMapping("/home")
	public String home(Model model){
		setDefaultModel(model);
		model.addAttribute("dispatch", "by_day");
		return "home";
	}
	
	@RequestMapping("/home/by_day")
	public String byDay(Model model){
		setDefaultModel(model);
		model.addAttribute("dispatch", "by_day");
		return "home";
	}
	
	@RequestMapping("/home/by_month")
	public String byMonth(Model model){
		setDefaultModelByMonth(model);
		model.addAttribute("dispatch", "by_month");
		return "home";
	}
	
	
	/**
	 * load image with login user
	 * @param productId
	 * @param request
	 * @param response
	 * @return byte[]
	 */
	@RequestMapping(value="/user/login/image", method=RequestMethod.GET)
	@ResponseBody
	public static byte[] showUserImage(HttpServletRequest request, HttpServletResponse response) {
		try {
			File serverFile = new File(WebUtils.IMAGES_USERS_DIR + getCurrentUser().getUser().getId()+".jpg");
		    return Files.readAllBytes(serverFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * set default by day
	 * @param model
	 */
	private void setDefaultModel(Model model) {
		model.addAttribute("payment", whRemainsData.getPaymentByDateOrYm(JCartConsts.BY_DAY, 
				StringUtils.formatDate(Calendar.getInstance().getTime()).replaceAll("/", "")));
		
		model.addAttribute("revenue", whRemainsData.getRevenueByDateOrYm(JCartConsts.BY_DAY, 
				StringUtils.formatDate(Calendar.getInstance().getTime()).replaceAll("/", "")));
	}
	
	/**
	 * set default by day
	 * @param model
	 */
	private void setDefaultModelByMonth(Model model) {
		model.addAttribute("payment", whRemainsData.getPaymentByDateOrYm(JCartConsts.BY_MONTH, 
				StringUtils.formatYearMonth(Calendar.getInstance().getTime()).replaceAll("/", "")));
		
		model.addAttribute("revenue", whRemainsData.getRevenueByDateOrYm(JCartConsts.BY_MONTH, 
				StringUtils.formatYearMonth(Calendar.getInstance().getTime()).replaceAll("/", "")));
	}
}
