/**
 * 
 */
package com.cts.jcart.admin.web.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.jcart.admin.security.AuthenticatedUser;
import com.cts.jcart.admin.web.utils.WebUtils;
import com.cts.jcart.common.services.JCLogger;

/**
 * @author ungtq
 *
 */
public abstract class JCartAdminBaseController
{
	protected final JCLogger logger = JCLogger.getLogger(getClass());
	
	@Autowired protected MessageSource messageSource;
	
	protected abstract String getHeaderTitle();
	
	public String getMessage(String code)
	{
		return messageSource.getMessage(code, null, null);
	}
	
	public String getMessage(String code, String defaultMsg)
	{
		return messageSource.getMessage(code, null, defaultMsg, null);
	}
	
	@ModelAttribute("authenticatedUser")
    public AuthenticatedUser authenticatedUser(@AuthenticationPrincipal AuthenticatedUser authenticatedUser){
        return authenticatedUser;
    }
	
	public static AuthenticatedUser getCurrentUser() {

	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    if (principal instanceof AuthenticatedUser) {
	    	return ((AuthenticatedUser) principal);
	    }
	    // principal object is either null or represents anonymous user -
	    // neither of which our domain User object can represent - so return null
	    return null;
	}

	public static boolean isLoggedIn() {
	    return getCurrentUser() != null;
	}
	
//	@ModelAttribute("userLoginImage")
//	@ResponseBody
//	public byte[] showUserImage(HttpServletRequest request, HttpServletResponse response) {
//		try {
//			File serverFile = new File(WebUtils.IMAGES_USER_DIR +getCurrentUser().getUser().getId()+".jpg");
//		    return Files.readAllBytes(serverFile.toPath());
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
}
