/**
 * 
 */
package com.cts.jcart.catalog;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.jcart.entities.Product;

/**
 * @author ungtq
 *
 */
public interface ProductRepository extends JpaRepository<Product, Integer>{

	Product findByName(String name);

	Product findBySku(String sku);
	
	@Query("select p from Product p where p.name like ?1 or p.sku like ?1 or p.description like ?1")
	List<Product> search(String query);

	/**
	 * @author UNGTQ
	 * remove product by id
	 * @param id
	 */
	void deleteById(Integer id);
	
	@Query("select p from Product p where p.disabled = false")
	Page<Product> findActiveProducts(Pageable pageable);
	
	@Query("select p from Product p where p.disabled = false and p.category.id=?1")
	Page<Product> findActiveProductsByCatId(Pageable pageable, int catId);
}
