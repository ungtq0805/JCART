/**
 * 
 */
package com.cts.jcart.admin.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cts.jcart.admin.security.AuthenticatedUser;
import com.cts.jcart.common.services.JCLogger;

/**
 * @author ungtq
 *
 */
public abstract class JCartAdminBaseController{
	
	/**
	 * PAGINATION
	 */
	public static final int BUTTONS_TO_SHOW = 5;
	public static final int INITIAL_PAGE = 0;
	public static final int INITIAL_PAGE_SIZE = 5;
	public static final int[] PAGE_SIZES = {5, 10, 20};
	
	public static final String SAVETEMP = "SAVETEMP";
	public static final String APPLY = "APPLY";
	public static final String MODE_NEW = "MODE_NEW";
	public static final String MODE_UPD = "MODE_UPD";
    
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
