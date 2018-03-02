/**
 * 
 */
package com.cts.jcart.catalog;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.jcart.entities.Category;

/**
 * @author Siva
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	Category getByName(String name);

}
