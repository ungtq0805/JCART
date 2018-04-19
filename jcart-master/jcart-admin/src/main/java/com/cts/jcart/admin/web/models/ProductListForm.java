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
public class ProductListForm {
	private Model model;
	private Optional<Integer> pageSize;
	private Optional<Integer> page;
	private Integer selectedCatId;
	
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
	public Integer getSelectedCatId() {
		return selectedCatId;
	}
	public void setSelectedCatId(Integer selectedCatId) {
		this.selectedCatId = selectedCatId;
	}
	/**
	 * save session list form
	 * @param request
	 */
	public static void saveSessionForm(Model model, 
			Optional<Integer> pageSize,
			Optional<Integer> page,
			HttpServletRequest request,
			Integer catId) {
		ProductListForm productListForm = (ProductListForm) 
				request.getSession().getAttribute(JCartAdminSessionKeys.ADMIN_LIST_OF_PRODUCTS_SESSION_KEYS);
		if (productListForm == null || 
			productListForm.getModel() == null) {
			productListForm = new ProductListForm();
		}
		productListForm.setModel(model);
		productListForm.setPageSize(pageSize);
		productListForm.setPage(page);
		productListForm.setSelectedCatId(catId);
		request.getSession().setAttribute(JCartAdminSessionKeys.ADMIN_LIST_OF_PRODUCTS_SESSION_KEYS, 
			productListForm);
	}
}
