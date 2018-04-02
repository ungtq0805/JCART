/**
 * 
 */
package com.cts.jcart.wh.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.jcart.wh.entities.WhInflow;

/**
 * @author ungtq
 *
 */
public interface WhInflowsRepository extends JpaRepository<WhInflow, Integer>{
	@Query("select p from WhInflow p order by lastUpdDate desc")
	Page<WhInflow> findAllDesc(Pageable pageable);
}
