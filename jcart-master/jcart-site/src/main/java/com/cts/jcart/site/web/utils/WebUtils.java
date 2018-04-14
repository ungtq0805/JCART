/**
 * 
 */
package com.cts.jcart.site.web.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ungtq
 *
 */
public class WebUtils
{
	private WebUtils()
	{
	}
	public static final String IMAGES_PREFIX = "/products/images/";
	public static final String IMAGES_DIR = "img.product.path.dir";
	
	public static String getURLWithContextPath(HttpServletRequest request)
	{
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
	}
}
