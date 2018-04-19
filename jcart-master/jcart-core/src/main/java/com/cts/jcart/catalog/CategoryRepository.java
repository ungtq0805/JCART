/**
 * 
 */
package com.cts.jcart.catalog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.jcart.entities.Category;

/**
 * @author ungtq
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	Category getByName(String name);
	
	/**
	 * @author UNGTQ
	 * remove user by id
	 * @param id
	 */
	void deleteById(Integer id);
	
	@Query("select p from Category p where p.disabled = false")
	List<Category> findAllActive();
}
