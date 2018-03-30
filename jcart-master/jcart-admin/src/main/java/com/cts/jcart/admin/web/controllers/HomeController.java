/**
 * 
 */
package com.cts.jcart.admin.web.controllers;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
			File serverFile = new File(WebUtils.IMAGES_USER_DIR + getCurrentUser().getUser().getId()+".jpg");
		    return Files.readAllBytes(serverFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/locale", method = RequestMethod.GET)
	public String getLocalePage(Model model) {
		return "home";
	}
	
	@ModelAttribute("payment")
	public BigDecimal getPayment(){
		return whRemainsData.getPaymentByDateOrYm(JCartConsts.BY_DAY, 
				StringUtils.formatDate(Calendar.getInstance().getTime()).replaceAll("/", ""));
	}
	
	@ModelAttribute("revenue")
	public BigDecimal getRevenue(){
		return whRemainsData.getRevenueByDateOrYm(JCartConsts.BY_DAY, 
				StringUtils.formatDate(Calendar.getInstance().getTime()).replaceAll("/", ""));
	}
}
