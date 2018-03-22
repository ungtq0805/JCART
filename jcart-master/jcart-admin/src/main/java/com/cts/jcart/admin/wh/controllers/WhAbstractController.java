package com.cts.jcart.admin.wh.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import com.cts.jcart.admin.web.controllers.JCartAdminBaseController;

/**
 *
 * Base controller class, used for exeption handling
 */
public class WhAbstractController extends JCartAdminBaseController {
    
    /**
     * If some of the methods of the derived classes
     * or classes injected into derived classes
     * throw an exception this method will be called to handle it
     * 
     * @param ex exception which needs to be handled
     * @param request current request
     * @return the exception.jsp view page with exception information
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMessage())
                .append(" - ");
        StackTraceElement element = ex.getStackTrace()[0];
        if (element != null) {
            builder.append(element.getClassName())
                    .append(":")
                    .append(element.getLineNumber());
        }
        request.setAttribute("exception", "An error has occured: " + ex.getMessage(),
                RequestAttributes.SCOPE_REQUEST);
        return "exception";
    }
    
    @Override
	protected String getHeaderTitle(){
		return "WhAbstractController";
	}
    
}