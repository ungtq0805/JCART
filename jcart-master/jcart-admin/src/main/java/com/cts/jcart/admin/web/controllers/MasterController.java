/**
 * 
 */
package com.cts.jcart.admin.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
