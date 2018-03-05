/**
 * 
 */
package com.cts.jcart.catalog;

import org.springframework.data.jpa.repository.JpaRepository;

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
}
