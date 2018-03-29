/**
 * 
 */
package com.cts.jcart.catalog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.jcart.JCartException;
import com.cts.jcart.constant.MstCmnConst;
import com.cts.jcart.entities.MstCommon;

/**
 * @author ungtq
 *
 */
@Service
@Transactional
public class MasterCommonService {
	@Autowired MasterCommonRepository masterCommonRepository;
	
	/**
	 * @author ungtq
	 * Get master common By CommonNo and ClassNo
	 * @param commonNo
	 * @param classNo
	 * @return MstCommon
	 */
	public MstCommon getMstCommonByCommonNoAndClassNo(String commonNo, String classNo) {
		return masterCommonRepository.getMstCommonByCommonNoAndClassNo(commonNo, classNo);
	}
	
	/**
	 * @author ungtq
	 * @param mstCompanyInfo
	 * @return MstCommon
	 */
	public MstCommon updateMstCompanyInfo(MstCommon mstCompanyInfo) {
		MstCommon mstLatestCompanyInfo = getMstCommonByCommonNoAndClassNo(
				MstCmnConst.MST_COMPANY_INFO, 
				MstCmnConst.MST_COMPANY_INFO_RELATED);
		if(mstLatestCompanyInfo == null){
			throw new JCartException("mstLatestCompanyInfo doesn't exist");
		}
		
		mstLatestCompanyInfo.setCharData1(mstCompanyInfo.getCharData1());
		mstLatestCompanyInfo.setCharData2(mstCompanyInfo.getCharData2());
		mstLatestCompanyInfo.setCharData3(mstCompanyInfo.getCharData3());
		mstLatestCompanyInfo.setCharData4(mstCompanyInfo.getCharData4());
		mstLatestCompanyInfo.setCharData5(mstCompanyInfo.getCharData5());
		mstLatestCompanyInfo.setCharData6(mstCompanyInfo.getCharData6());
		
		return masterCommonRepository.save(mstLatestCompanyInfo);
	}
	
	/**
	 * @author ungtq
	 * get Unit List
	 * @return List Of Commons
	 */
	public List<MstCommon> getUnitsList() {
		return masterCommonRepository.getMstCommonByCommonNo(MstCmnConst.MST_UNIT);
	}
	
	/**
	 * get STATUS save temp
	 * @return
	 */
	public MstCommon getStatusSaveTemp() {
    	return masterCommonRepository.getMstCommonByCommonNoAndClassNo(
			MstCmnConst.MST_STATUS, MstCmnConst.MST_STATUS_SAVETEMP);
    }
    
	/**
	 * get STATUS apply
	 * @return
	 */
    public MstCommon getStatusApply() {
    	return masterCommonRepository.getMstCommonByCommonNoAndClassNo(
			MstCmnConst.MST_STATUS, MstCmnConst.MST_STATUS_APPLY);
    }
    
    /**
     * get STATUS approve
     * @return
     */
    public MstCommon getStatusApprove() {
    	return masterCommonRepository.getMstCommonByCommonNoAndClassNo(
			MstCmnConst.MST_STATUS, MstCmnConst.MST_STATUS_APPROVE);
    }
}
