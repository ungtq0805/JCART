/**
 * 
 */
package com.cts.jcart.admin.web.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cts.jcart.catalog.CatalogService;
import com.cts.jcart.entities.Category;

/**
 * @author ungtq
 *
 */
@Component
public class InflowFormValidator implements Validator
{
	@Autowired protected MessageSource messageSource;
	@Autowired protected CatalogService catalogService;
	
	@Override
	public boolean supports(Class<?> clazz){
		return Category.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors){
		
	}
}
