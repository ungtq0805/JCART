/**
 * 
 */
package com.cts.jcart.admin.web.models;

import org.hibernate.validator.constraints.NotEmpty;

import com.cts.jcart.entities.MstCommon;

/**
 * @author ungtq
 *
 */
public class MstCompanyInfoForm 
{
	private Integer id;
	
	@NotEmpty
	private String companyName;
	
	@NotEmpty
	private String phoneNo;
	
	private String directorName;
	
	private String webUrl;
	
	private String email;
	
	private String description;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MstCommon toMstCommon() {
		MstCommon p = new MstCommon();
		p.setId(id);
		p.setCharData1(companyName);
		p.setCharData2(phoneNo);
		p.setCharData3(directorName);
		p.setCharData4(webUrl);
		p.setCharData5(email);
		p.setCharData6(description);
		return p;
	}
	
	public static MstCompanyInfoForm fromMstCommon(MstCommon mstCommon) {
		MstCompanyInfoForm p = new MstCompanyInfoForm();
		p.setId(mstCommon.getId());
		p.setCompanyName(mstCommon.getCharData1());
		p.setPhoneNo(mstCommon.getCharData2());
		p.setDirectorName(mstCommon.getCharData3());
		p.setWebUrl(mstCommon.getCharData4());
		p.setEmail(mstCommon.getCharData5());
		p.setDescription(mstCommon.getCharData6());
		return p;
	}
}
