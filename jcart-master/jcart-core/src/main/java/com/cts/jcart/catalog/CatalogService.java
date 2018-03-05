/**
 * 
 */
package com.cts.jcart.catalog;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.jcart.JCartException;
import com.cts.jcart.entities.Category;
import com.cts.jcart.entities.Product;

/**
 * @author ungtq
 *
 */
@Service
@Transactional
public class CatalogService {
	@Autowired CategoryRepository categoryRepository;
	@Autowired ProductRepository productRepository;
	
	public List<Category> getAllCategories() {
		
		return categoryRepository.findAll();
	}
	
	public List<Product> getAllProducts() {
		
		return productRepository.findAll();
	}

	public Category getCategoryByName(String name) {
		return categoryRepository.getByName(name);
	}
	
	public Category getCategoryById(Integer id) {
		return categoryRepository.findOne(id);
	}

	public Category createCategory(Category category) {
		Category persistedCategory = getCategoryByName(category.getName());
		if(persistedCategory != null){
			throw new JCartException("Category "+category.getName()+" already exist");
		}
		return categoryRepository.save(category);
	}

	public Category updateCategory(Category category) {
		Category persistedCategory = getCategoryById(category.getId());
		if(persistedCategory == null){
			throw new JCartException("Category "+category.getId()+" doesn't exist");
		}
		persistedCategory.setDescription(category.getDescription());
		persistedCategory.setDisplayOrder(category.getDisplayOrder());
		persistedCategory.setDisabled(category.isDisabled());
		return categoryRepository.save(persistedCategory);
	}

	public Product getProductById(Integer id) {
		return productRepository.findOne(id);
	}
	
	public Product getProductBySku(String sku) {
		return productRepository.findBySku(sku);
	}
	
	public Product createProduct(Product product) {
		Product persistedProduct = getProductBySku(product.getName());
		if(persistedProduct != null){
			throw new JCartException("Product SKU "+product.getSku()+" already exist");
		}
		return productRepository.save(product);
	}
	
	public Product updateProduct(Product product) {
		Product persistedProduct = getProductById(product.getId());
		if(persistedProduct == null){
			throw new JCartException("Product "+product.getId()+" doesn't exist");
		}
		persistedProduct.setDescription(product.getDescription());
		persistedProduct.setDisabled(product.isDisabled());
		persistedProduct.setPrice(product.getPrice());
		persistedProduct.setCategory(getCategoryById(product.getCategory().getId()));
		return productRepository.save(persistedProduct);
	}

	public List<Product> searchProducts(String query) {
		return productRepository.search("%"+query+"%");
	}
	
	/**
	 * @author UNGTQ
	 * remove user by id
	 * @param id
	 */
	public void deleteCatById(Integer id) {
		categoryRepository.deleteById(id);
	}
	
	/**
	 * @author UNGTQ
	 * remove user by id
	 * @param id
	 */
	public void deleteProductById(Integer id) {
		productRepository.deleteById(id);
	}
	
	/**
	 * @author ungtq
	 * Clone the new Product
	 * @param id
	 * @return Product
	 */
	public Product cloneProductById(Integer id) {
		Product productSource = productRepository.findOne(id);
		Product productTarget = new Product();
		
		BeanUtils.copyProperties(productSource, productTarget);
		productTarget.setId(null);
		
		return productTarget;
	}
}
