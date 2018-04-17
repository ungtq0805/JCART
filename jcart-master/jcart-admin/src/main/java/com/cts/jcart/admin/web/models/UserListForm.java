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
public class UserListForm {
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
		UserListForm userListForm = (UserListForm) 
				request.getSession().getAttribute(JCartAdminSessionKeys.ADMIN_LIST_OF_USERS_SESSION_KEYS);
		if (userListForm == null || 
			userListForm.getModel() == null) {
			userListForm = new UserListForm();
		}
		userListForm.setModel(model);
		userListForm.setPageSize(pageSize);
		userListForm.setPage(page);
		request.getSession().setAttribute(JCartAdminSessionKeys.ADMIN_LIST_OF_USERS_SESSION_KEYS, 
			userListForm);
	}
}
