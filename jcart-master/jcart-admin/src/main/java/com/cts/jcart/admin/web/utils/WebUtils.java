/**
 * 
 */
package com.cts.jcart.admin.web.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ungtq
 *
 */
public class WebUtils{
	
	private WebUtils(){
		
	}
	
	public static final String IMAGES_PREFIX = "/products/images/";
	public static final String IMAGES_DIR =  "D:/jcart/products/";
	public static final String IMAGES_USER_DIR =  "D:/jcart/user/";
	
	public static String getURLWithContextPath(HttpServletRequest request){
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
	}
}
