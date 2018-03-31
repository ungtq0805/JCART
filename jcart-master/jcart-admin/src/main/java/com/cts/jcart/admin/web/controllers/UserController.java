/**
 * 
 */
package com.cts.jcart.admin.web.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cts.jcart.JCartException;
import com.cts.jcart.admin.security.CustomUserDetailsService;
import com.cts.jcart.admin.security.SecurityUtil;
import com.cts.jcart.admin.web.models.UserForm;
import com.cts.jcart.admin.web.utils.WebUtils;
import com.cts.jcart.admin.web.validators.UserValidator;
import com.cts.jcart.entities.Role;
import com.cts.jcart.entities.User;
import com.cts.jcart.security.SecurityService;
import com.cts.jcart.security.UserRepository;
import com.cts.jcart.utils.StringUtils;

/**
 * @author ungtq
 *
 */
@Controller
@Secured(SecurityUtil.MANAGE_USERS)
public class UserController extends JCartAdminBaseController
{
	private static final String viewPrefix = "users/";
	@Autowired protected SecurityService securityService;
	@Autowired private CustomUserDetailsService customUserDetailsService;
	@Autowired private UserValidator userValidator;
	@Autowired protected PasswordEncoder passwordEncoder;
	@Autowired UserRepository userRepository;
	
	@Override
	protected String getHeaderTitle(){
		return "Manage Users";
	}
	
	@ModelAttribute("rolesList")
	public List<Role> rolesList(){
		return securityService.getAllRoles();
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String listUsers(Model model,
			@RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page) {
		// Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        
        Page<User> users = securityService.findActiveUsers(new PageRequest(evalPage, evalPageSize));
		
		Pager pager = new Pager(users.getTotalPages(), users.getNumber(), BUTTONS_TO_SHOW);
		
		model.addAttribute("users", users);
		model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);
		
		return viewPrefix+"users";
	}
	
	@RequestMapping(value="/users/new", method=RequestMethod.GET)
	public String createUserForm(Model model) {
		UserForm user = new UserForm();
		model.addAttribute("user",user);
		
		return viewPrefix+"create_user";
	}

	@RequestMapping(value="/users", method=RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute("user") UserForm userForm, BindingResult result, 
			Model model, RedirectAttributes redirectAttributes) {
		userValidator.validateWithForm(userForm, result);
		if(result.hasErrors()){
			return viewPrefix+"create_user";
		}
		
		//encode password
		String password = userForm.getPassword();
		String encodedPwd = passwordEncoder.encode(password);
		userForm.setPassword(encodedPwd);
		
		//convert from Form to Entity
		User user = userForm.toUser();
		
		User persistedUser = securityService.createUser(user);
		
		//save image path
		userForm.setId(user.getId());
		saveUserImageToDisk(userForm);
		
		logger.debug("Created new User with id : {} and name : {}", persistedUser.getId(), persistedUser.getUserName());
		redirectAttributes.addFlashAttribute("info", "User created successfully");
		return "redirect:/users";
	}
	
	/**
	 * @author ungtq
	 * Remove user 
	 * @param user
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return URL
	 */
	@RequestMapping(value="/users/remove/{id}", method=RequestMethod.GET)
	public String removeUser(@PathVariable Integer id, Model model) {
		customUserDetailsService.removeUserById(id);
		return "redirect:/users";
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	public String editUserForm(@PathVariable Integer id, Model model) {
		User user = securityService.getUserById(id);
		Map<Integer, Role> assignedRoleMap = new HashMap<>();
		List<Role> roles = user.getRoles();
		for (Role role : roles){
			assignedRoleMap.put(role.getId(), role);
		}
		List<Role> userRoles = new ArrayList<>();
		List<Role> allRoles = securityService.getAllRoles();
		for (Role role : allRoles)
		{
			if(assignedRoleMap.containsKey(role.getId())){
				userRoles.add(role);
			} else {
				userRoles.add(null);
			}
		}
		user.setRoles(userRoles);
		model.addAttribute("user", UserForm.fromUser(user));

		return viewPrefix+"edit_user";
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") UserForm userForm, BindingResult result, 
			Model model, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()){
			return viewPrefix+"edit_user";
		}
		
		//convert to entity
		User user = userForm.toUser();
		
		//encode password
		if (!StringUtils.isEmpty(userForm.getPassword())) {
			String password = userForm.getPassword();
			String encodedPwd = passwordEncoder.encode(password);
			user.setPassword(encodedPwd);
		}
		
		//update DB
		User persistedUser = securityService.updateUser(user);
		
		//save image to Disk
		saveUserImageToDisk(userForm);
		
		logger.debug("Updated user with id : {} and name : {}", persistedUser.getId(), persistedUser.getUserName());
		redirectAttributes.addFlashAttribute("info", "User updates successfully");
		return "redirect:/users";
	}
	
	/**
	 * edit user account only with user login
	 * @author ungtq
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping(value="/myAccount/{id}", method=RequestMethod.GET)
	public String editMyAccount(@PathVariable Integer id, Model model) {
		User user = securityService.getUserById(id);
		model.addAttribute("user", UserForm.fromUser(user));
		return viewPrefix + "my_account";
	}
	
	/**
	 * update my account page
	 * @param userForm
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return String
	 */
	@RequestMapping(value="/myAccount/update/{id}", method=RequestMethod.POST)
	public String updateMyAccount(@ModelAttribute("user") UserForm userForm, BindingResult result, 
			Model model, RedirectAttributes redirectAttributes) {
		User myAccount = securityService.getUserById(userForm.getId());
		
		userValidator.validateWithMyAccount(userForm, result, myAccount, passwordEncoder);
		
		if(result.hasErrors()){
			return viewPrefix + "my_account";
		}

		//encode password
		String password = userForm.getPasswordConfirm();
		if (!StringUtils.isEmpty(password)) {
			String encodedPwd = passwordEncoder.encode(password);
			userForm.setPassword(encodedPwd);
		}
				
		//convert to entity
		User user = userForm.updateMyAccount(myAccount);
		
		//update DB
		User persistedUser = securityService.updateUser(user);
		
		//save image to Disk
		saveUserImageToDisk(userForm);
		
		logger.debug("Updated user with id : {} and name : {}", persistedUser.getId(), persistedUser.getUserName());
		redirectAttributes.addFlashAttribute("info", "Your account updates successfully");
		return "redirect:/home";
	}
	
	/**
	 * 
	 * @param productForm
	 */
	private void saveUserImageToDisk(UserForm userForm) {
		MultipartFile file = userForm.getImage();
		if (file!= null && !file.isEmpty()) {
			String name = WebUtils.IMAGES_USER_DIR + userForm.getId() + ".jpg";
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				throw new JCartException(e);
			}
		}
	}
	
	/**
	 * ungtq fix bug load image
	 * @param productId
	 * @param request
	 * @param response
	 * @return byte[]
	 */
	@RequestMapping(value="/users/images/{id}", method=RequestMethod.GET)
	@ResponseBody
	public byte[] showUserImage(@PathVariable String id, 
			HttpServletRequest request, 
			HttpServletResponse response) {
		try {
			File serverFile = new File(WebUtils.IMAGES_USER_DIR +id+".jpg");
		    return Files.readAllBytes(serverFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
