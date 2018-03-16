/**
 * 
 */
package com.cts.jcart.admin.web.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public boolean supports(Class<?> clazz){
		return User.class.isAssignableFrom(clazz);
	}
	
	public void validateWithForm(Object target, Errors errors){
		//check input pattern userName
		if(!isValidUsername(target, errors)) {
			return;
		}
		
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
	public boolean validateWithMyAccount(Object target, Errors errors, User user){
		boolean isValid = true;
		UserForm userForm = (UserForm) target;
		
		String password = userForm.getPassword();
		String passwordConfirm1 = userForm.getPasswordConfirm();
		String passwordConfirm2 = userForm.getPasswordConfirmLast();
		
		if (StringUtils.isEmpty(password) 
			&& StringUtils.isEmpty(passwordConfirm1)
			&& StringUtils.isEmpty(passwordConfirm2)) {
			return true;
		}
		
		//check required input password
		isValid = checkRequiredPasswordInputMyAccount(errors, userForm);
		
		//check current password
		if (!userForm.getPassword().equals(user.getPassword())) {
			errors.rejectValue("password", "error.myaccount.password.incorrect");
			isValid = false;
		}
		
		//check password confirm 1 and password confirm 2
		if (!userForm.getPasswordConfirm().equals(userForm.getPasswordConfirmLast())) {
			errors.rejectValue("passwordConfirm", "error.myaccount.password.confirm.incorrect");
			isValid = false;
		}
		
		//check current pass and new pass
		if (userForm.getPasswordConfirm().equals(userForm.getPassword())) {
			errors.rejectValue("passwordConfirmLast", "error.myaccount.password.and.password.confirm.incorrect");
			isValid = false;
		}
		
		return isValid;
	}
	
	/**
	 * require input password my account
	 * @param errors
	 * @param userForm
	 * @return boolean
	 */
	private boolean checkRequiredPasswordInputMyAccount(Errors errors,
			UserForm userForm) {
		String password = userForm.getPassword();
		String passwordConfirm1 = userForm.getPasswordConfirm();
		String passwordConfirm2 = userForm.getPasswordConfirmLast();
		
		boolean isValid = true;
		if (StringUtils.isEmpty(password)) {
			errors.rejectValue("password", "error.required", new Object[]{password}, "error.required.default");
			isValid = false;
		}
		
		if (StringUtils.isEmpty(passwordConfirm1)) {
			errors.rejectValue("passwordConfirm", "error.required", new Object[]{passwordConfirm1}, "error.required.default");
			isValid = false;
		}
		
		if (StringUtils.isEmpty(passwordConfirm2)) {
			errors.rejectValue("passwordConfirmLast", "error.required", new Object[]{passwordConfirm2}, "error.required.default");
			isValid = false;
		}
		
		return isValid;
	}
	
	/**
	 * check input pattern userName
	 * @param errors
	 * @param userForm
	 * @return boolean
	 */
	private boolean isValidUsername(Object target, Errors errors) {
		UserForm userForm = (UserForm) target;
		Pattern pattern = Pattern.compile(ValidationConst.USERNAME_PATTERN);
		Matcher matcher = pattern.matcher(userForm.getUserName());
		
		boolean match = matcher.matches();
		if (!match) {
			errors.rejectValue("userName", "error.check.validate.input.username");
		}
		return match;
	}
}
