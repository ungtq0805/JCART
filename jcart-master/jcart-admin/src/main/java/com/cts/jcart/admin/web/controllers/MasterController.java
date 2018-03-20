/**
 * 
 */
package com.cts.jcart.admin.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cts.jcart.admin.web.models.MstCompanyInfoForm;
import com.cts.jcart.catalog.MasterCommonService;
import com.cts.jcart.constant.MstCmnConst;
import com.cts.jcart.entities.MstCommon;

/**
 * @author ungtq
 *
 */
@Controller
public class MasterController extends JCartAdminBaseController{
	
	private static final String viewPrefix = "master/";
	@Autowired private MasterCommonService mstCommonService;
	
	@Override
	protected String getHeaderTitle() {
		return "Company Information";
	}
	
	/**
	 * @author ungtq
	 * edit company info to Form
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit/companyInfo")
	public String editCompanyInfoHome(Model model){
		//GET COMPANY INFO
		MstCommon mstCommon = mstCommonService.getMstCommonByCommonNoAndClassNo(
				MstCmnConst.MST_COMPANY_INFO, 
				MstCmnConst.MST_COMPANY_INFO_RELATED);
		
		if (mstCommon != null) {
			model.addAttribute("mstCompanyInfo", MstCompanyInfoForm.fromMstCommon(mstCommon));
		}
		
		return viewPrefix + "company_info";
	}
	
	/**
	 * @author ungtq
	 * @param mstCompanyInfoForm
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return Update company
	 */
	@RequestMapping(value="/update/companyInfo", method=RequestMethod.POST)
	public String updateCompanyInfo(
			@ModelAttribute("mstCompanyInfo") MstCompanyInfoForm mstCompanyInfoForm, 
			BindingResult result, 
			Model model, 
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()){
			return viewPrefix + "company_info";
		}
		
		
		//convert to entity
		MstCommon mstCompanyInfo = mstCompanyInfoForm.toMstCommon();
		
		//update Master Company Info
		mstCommonService.updateMstCompanyInfo(mstCompanyInfo);
		
		redirectAttributes.addFlashAttribute("info", "Company Info updates successfully");
		return viewPrefix + "company_info";
	}
}
