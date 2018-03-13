/**
 * 
 */
package com.cts.jcart.admin.web.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.h2.value.ValueStringIgnoreCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cts.jcart.admin.web.models.UserForm;
import com.cts.jcart.constant.ValidationConst;
import com.cts.jcart.entities.User;
import com.cts.jcart.security.SecurityService;
import com.cts.jcart.utils.StringUtils;

/**
 * @author ungtq
 *
 */
@Component
public class UserValidator implements Validator
{
	@Autowired protected MessageSource messageSource;
	@Autowired protected SecurityService securityService;
	
	@Override
	public boolean supports(Class<?> clazz)
	{
		return User.class.isAssignableFrom(clazz);
	}
	
	public void validateWithForm(Object target, Errors errors){
		
		//check is valid with email
		if (!isValidEmail(target, errors, true)) {
			return;
		}
		
		//check is valid with phone No
		if (!isValidPhoneNo(target, errors, true)) {
			return;
		}
	}
	
	@Override
	public void validate(Object target, Errors errors){
		
		//check is valid with email
		if (!isValidEmail(target, errors, false)) {
			return;
		}
		
		//check is valid with phone No
		if (!isValidPhoneNo(target, errors, false)) {
			return;
		}
	}
	
	/**
	 * @author ungtq
	 * check is valid with email
	 * @param target
	 * @param errors
	 * @return
	 */
	private boolean isValidEmail(Object target, Errors errors, boolean isForm) {
		String email = null;
		if(isForm) {
			UserForm userForm = (UserForm) target;
			email = userForm.getEmail();
		} else {
			User user = (User) target;
			email = user.getEmail();
		}
		
		User userByEmail = securityService.findUserByEmail(email);
		if(userByEmail != null){
			errors.rejectValue("email", "error.exists", new Object[]{email}, "Email "+email+" already in use");
			return false;
		}
		
		return true;
	}
	
	/**
	 * @author ungtq
	 * check is valid with phone No
	 * @param target
	 * @param errors
	 * @return
	 */
	private boolean isValidPhoneNo(Object target, Errors errors, boolean isForm) {
		String phoneNo = null;
		
		if(isForm) {
			UserForm userForm = (UserForm) target;
			phoneNo = userForm.getPhoneNo();
		} else {
			User user = (User) target;
			phoneNo = user.getPhoneNo();
		}
		
		if(StringUtils.isEmpty(phoneNo)) {
			return true;
		}
		
		Pattern pattern = Pattern.compile(ValidationConst.REGEX_PHONE_NO);
	    Matcher matcher = pattern.matcher(phoneNo);
	      
		if (!matcher.matches()) {
			errors.rejectValue("phoneNo", "error.invalid", new Object[]{phoneNo}, "Phone Number " + phoneNo + " is invalid");
			return false;
		}
		return true;
	}
	
	/**
	 * validate with my account page
	 * @param target
	 * @param errors
	 */
	public void validateWithMyAccount(Object target, Errors errors){
		
		
	}
}
