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
public class InflowValidator implements Validator
{
	@Autowired protected MessageSource messageSource;
	@Autowired protected CatalogService catalogService;
	
	@Override
	public boolean supports(Class<?> clazz){
		return Category.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors){
//		WhInflow whInflow = (WhInflow) target;
//		
//		if (!isValidInflowDate(whInflow)) {
//			errors.rejectValue("inflowdate", "error.date.input.invalid", 
//					new Object[]{"Inflow Date"}, "Date input Invalid");
//		}
	}
	
//	/**
//	 * @author ungtq
//	 * @param whInflow
//	 * @return boolean
//	 */
//	private boolean isValidInflowDate(WhInflow whInflow) {
//		SimpleDateFormat sdf = new SimpleDateFormat(ValidationConst.DATE_INPUT_PATTERN);
//		
//		try {
//			sdf.parse(whInflow.getInflowdate().toString());
//		} catch(ParseException e) {
//			e.printStackTrace();
//			return false;
//		}
//		
//		return true;
//	}
	
}
