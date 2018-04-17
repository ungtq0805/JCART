/**
 * 
 */
package com.cts.jcart.admin.web.models;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.cts.jcart.admin.session.keys.JCartAdminSessionKeys;

/**
 * @author ungtq
 * Use for saving in session 
 */
public class WhInflowListForm {
	private Model model;
	private Optional<Integer> pageSize;
	private Optional<Integer> page;
	
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public Optional<Integer> getPageSize() {
		return pageSize;
	}
	public void setPageSize(Optional<Integer> pageSize) {
		this.pageSize = pageSize;
	}
	public Optional<Integer> getPage() {
		return page;
	}
	public void setPage(Optional<Integer> page) {
		this.page = page;
	}
	
	/**
	 * save session list form
	 * @param request
	 */
	public static void saveSessionForm(Model model, 
			Optional<Integer> pageSize,
			Optional<Integer> page,
			HttpServletRequest request) {
		WhInflowListForm whInflowlistForm = (WhInflowListForm) 
				request.getSession().getAttribute(JCartAdminSessionKeys.ADMIN_LIST_OF_INFLOWS_SESSION_KEYS);
		if (whInflowlistForm == null || 
			whInflowlistForm.getModel() == null) {
			whInflowlistForm = new WhInflowListForm();
		}
		whInflowlistForm.setModel(model);
		whInflowlistForm.setPageSize(pageSize);
		whInflowlistForm.setPage(page);
		request.getSession().setAttribute(JCartAdminSessionKeys.ADMIN_LIST_OF_INFLOWS_SESSION_KEYS, 
			whInflowlistForm);
	}
}
