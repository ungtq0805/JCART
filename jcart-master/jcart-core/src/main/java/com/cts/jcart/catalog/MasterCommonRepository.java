/**
 * 
 */
package com.cts.jcart.catalog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.jcart.entities.MstCommon;

/**
 * @author ungtq
 *
 */
public interface MasterCommonRepository extends JpaRepository<MstCommon, Integer>{

	@Query("SELECT t FROM MstCommon t where t.commonNo = :commonNo AND t.classNo = :classNo")
	MstCommon getMstCommonByCommonNoAndClassNo(@Param("commonNo") String commonNo, 
            @Param("classNo") String classNo);
	
	@Query("SELECT t FROM MstCommon t where t.commonNo = :commonNo")
	List<MstCommon> getMstCommonByCommonNo(@Param("commonNo") String commonNo);
}
