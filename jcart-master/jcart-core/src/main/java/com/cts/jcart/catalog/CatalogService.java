/**
 * 
 */
package com.cts.jcart.catalog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	@Autowired MasterCommonRepository masterRepository;
	
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	/**
	 * pagination
	 * @param pageable
	 * @return
	 */
	public Page<Product> getAllProducts(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
	
	/**
	 * Find active product
	 * @author ungtq
	 * @param pageable
	 * @return Page<Product>
	 */
	public Page<Product> findActiveProducts(Pageable pageable) {
		return productRepository.findActiveProducts(pageable);
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
		persistedProduct.setName(product.getName());
		persistedProduct.setDescription(product.getDescription());
		persistedProduct.setDisabled(product.isDisabled());
		persistedProduct.setPrice(product.getPrice());
		
		if (product.getCategory() != null) {
			persistedProduct.setCategory(getCategoryById(product.getCategory().getId()));
		}
		
		if (product.getUnit() != null) {
			persistedProduct.setUnit(product.getUnit());
		}
		
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
		Product persistedProduct = getProductById(id);
		
		//set disable is true for delete product
		persistedProduct.setDisabled(true);
		
		productRepository.save(persistedProduct);
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
	
	/**
	 * get Preview for categories on the page all categories
	 * @author ungtq
	 * @return List<Category>
	 */
	public List<Category> getPreviewCategories() {
		List<Category> previewCategories = new ArrayList<>();
		List<Category> categories = getAllCategories();
		for (Category category : categories){
			if (category.isDisabled()) {
				continue;
			}
			
			Set<Product> products = category.getProducts();
			Set<Product> previewProducts = new HashSet<>();
//			int noOfProductsToDisplay = 4;
//			if(products.size() > noOfProductsToDisplay){
//				Iterator<Product> iterator = products.iterator();
//				for (int i = 0; i < noOfProductsToDisplay; i++)
//				{
//					previewProducts.add(iterator.next());
//				}
//			} else {
				previewProducts.addAll(products);
//			}	
			category.setProducts(previewProducts);
			previewCategories.add(category);
		}
		
		return previewCategories;
	}
	
	/**
	 * get Preview for category by CatId
	 * @author ungtq
	 * @return List<Category>
	 */
	public List<Category> getPreviewCategoryByCatId(int catId) {
		List<Category> previewCategories = new ArrayList<>();
		Category category = getCategoryById(catId);
		
		Set<Product> products = category.getProducts();
		Set<Product> previewProducts = new HashSet<>();
		int noOfProductsToDisplay = 4;
		if(products.size() > noOfProductsToDisplay){
			Iterator<Product> iterator = products.iterator();
			for (int i = 0; i < noOfProductsToDisplay; i++)
			{
				previewProducts.add(iterator.next());
			}
		} else {
			previewProducts.addAll(products);
		}	
		category.setProducts(previewProducts);
		previewCategories.add(category);
		
		return previewCategories;
	}
}
