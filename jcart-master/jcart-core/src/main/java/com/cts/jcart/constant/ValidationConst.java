/**
 *
 */
package com.cts.jcart.constant;

/**
 * @author ungtq
 * Constant for validation
 * Example validation for email, ....
 */
public class ValidationConst {
	
	/**
	 * Regex for validation phone Number
	 */
	public static final String REGEX_PHONE_NO = "^\\+(?:[0-9] ?){6,14}[0-9]$";
	
	/**
	 * Regex for validation UserName input
	 */
	public static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
	
}
