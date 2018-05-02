/**
 * 
 */
package com.cts.jcart.admin.web.chat.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.jcart.admin.security.SecurityUtil;
import com.cts.jcart.admin.session.keys.JCartAdminSessionKeys;
import com.cts.jcart.admin.web.controllers.JCartAdminBaseController;
import com.cts.jcart.admin.web.controllers.Pager;
import com.cts.jcart.admin.web.models.UserListForm;
import com.cts.jcart.entities.Role;
import com.cts.jcart.entities.User;
import com.cts.jcart.security.SecurityService;
import com.cts.jcart.security.UserRepository;

/**
 * @author ungtq
 *
 */
@Controller
@Secured(SecurityUtil.MANAGE_USERS)
public class UsersListController extends JCartAdminBaseController
{
	private static final String viewPrefix = "chat/";
	@Autowired protected SecurityService securityService;
	@Autowired UserRepository userRepository;
	
	@Override
	protected String getHeaderTitle(){
		return "Manage Users";
	}
	
	@ModelAttribute("rolesList")
	public List<Role> rolesList(){
		return securityService.getAllRoles();
	}
	
	@RequestMapping(value="/chat/users", method=RequestMethod.GET)
	public String listUsers(Model model,
			@RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("dispatch") Optional<String> dispatch,
            HttpServletRequest request) {
		UserListForm userListForm = (UserListForm) 
				request.getSession().getAttribute(JCartAdminSessionKeys.CHAT_LIST_OF_USERS_SESSION_KEYS);
		
		if ( (dispatch.isPresent() && "changePageAndSize".equals(dispatch.get())) || 
			userListForm == null) {
			//save session list form at the present
			UserListForm.saveSessionFormWithChat(model, pageSize, page, request);
		} else {
			pageSize = userListForm.getPageSize();
			page = userListForm.getPage();
		}
		
		// Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        
        Page<User> users = securityService.findActiveUsersWithChat(new PageRequest(evalPage, evalPageSize),
        		getCurrentUser().getUsername());
		
		Pager pager = new Pager(users.getTotalPages(), users.getNumber(), BUTTONS_TO_SHOW);
		
		model.addAttribute("users", users);
		model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);
		
		return viewPrefix + "users_list";
	}
}
