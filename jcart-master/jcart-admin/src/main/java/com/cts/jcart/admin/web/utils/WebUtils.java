/**
 * 
 */
package com.cts.jcart.admin.web.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 * @author ungtq
 *
 */
public class WebUtils{
	@Autowired 
	private static MessageSource messageSource;
	
	private WebUtils(){
		
	}
	
	public static final String IMAGES_PREFIX = "/products/images/";
	public static final String IMAGES_DIR =  "D:/jcart/products/";
			//getMessageSource().getMessage("spring.jcart.dir.path.image", null, null); // "D:/jcart/products/";
	
	public static String getURLWithContextPath(HttpServletRequest request){
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
	}
	
//	private static MessageSource getMessageSource() {
//		return messageSource;
//	}
}
