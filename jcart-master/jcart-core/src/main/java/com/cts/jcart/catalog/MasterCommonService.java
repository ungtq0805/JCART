/**
 * 
 */
package com.cts.jcart.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
