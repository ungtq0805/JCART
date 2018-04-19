/**
 * 
 */
package com.cts.jcart.admin.web.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cts.jcart.JCartException;
import com.cts.jcart.admin.security.SecurityUtil;
import com.cts.jcart.admin.session.keys.JCartAdminSessionKeys;
import com.cts.jcart.admin.web.models.ProductForm;
import com.cts.jcart.admin.web.models.ProductListForm;
import com.cts.jcart.admin.web.utils.WebUtils;
import com.cts.jcart.admin.web.validators.ProductFormValidator;
import com.cts.jcart.catalog.CatalogService;
import com.cts.jcart.catalog.MasterCommonService;
import com.cts.jcart.entities.Category;
import com.cts.jcart.entities.MstCommon;
import com.cts.jcart.entities.Product;

/**
 * @author ungtq
 *
 */
@Controller
@Secured(SecurityUtil.MANAGE_PRODUCTS)
public class ProductController extends JCartAdminBaseController
{
	private static final String viewPrefix = "products/";
	@Autowired
	private CatalogService catalogService;
	
	@Autowired 
	private ProductFormValidator productFormValidator;
	
	@Autowired 
	private MasterCommonService masterCommonService;
	
	@Override
	protected String getHeaderTitle(){
		return "Manage Products";
	}
	
	@ModelAttribute("categoriesList")
	public List<Category> categoriesList(){
		return catalogService.findAllActiveCategories(true);
	}
	
	@ModelAttribute("unitsList")
	public List<MstCommon> unitsList(){
		return masterCommonService.getUnitsList();
	}
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public String listProducts(Model model,
			@RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("dispatch") Optional<String> dispatch,
            @RequestParam("selectedCat") Optional<Integer> selectedCat,
            HttpServletRequest request) {
		
		ProductListForm productListForm = (ProductListForm) 
				request.getSession().getAttribute(JCartAdminSessionKeys.ADMIN_LIST_OF_PRODUCTS_SESSION_KEYS);
		
		Integer catId = null;

		if ((dispatch.isPresent() && "changePageAndSize".equals(dispatch.get())) || 
			productListForm == null) {
			
			if ((dispatch.isPresent() && "changePageAndSize".equals(dispatch.get()))) {
				catId = selectedCat.get();
			}
			
			//save session list form at the present
			ProductListForm.saveSessionForm(model, pageSize, page, request, catId);
		} else {
			pageSize = productListForm.getPageSize();
			page = productListForm.getPage();
			catId = productListForm.getSelectedCatId();
		}
		
		// Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        
        Page<Product> products = catalogService.findActiveProductsByCatId(
        			new PageRequest(evalPage, evalPageSize), catId);
        
		Pager pager = new Pager(products.getTotalPages(), products.getNumber(), BUTTONS_TO_SHOW);
		
		model.addAttribute("products", products);
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);
        model.addAttribute("catId", catId);
		
		return viewPrefix+"products";
	}

	@RequestMapping(value="/products/new", method=RequestMethod.GET)
	public String createProductForm(Model model) {
		ProductForm product = new ProductForm();
		model.addAttribute("product",product);
		return viewPrefix+"create_product";
	}

	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String createProduct(@Valid @ModelAttribute("product") ProductForm productForm, 
			BindingResult result, 
			Model model, 
			RedirectAttributes redirectAttributes) {
		productFormValidator.validate(productForm, result);
		if(result.hasErrors()){
			return viewPrefix+"create_product";
		}
		Product product = productForm.toProduct();
		Product persistedProduct = catalogService.createProduct(product);
		productForm.setId(product.getId());
		this.saveProductImageToDisk(productForm);
		logger.debug("Created new product with id : {} and name : {}", persistedProduct.getId(), persistedProduct.getName());
		redirectAttributes.addFlashAttribute("info", "Product created successfully");
		return "redirect:/products";
	}
	
	@RequestMapping(value="/products/{id}", method=RequestMethod.GET)
	public String editProductForm(@PathVariable Integer id, Model model) {
		Product product = catalogService.getProductById(id);
		model.addAttribute("product",ProductForm.fromProduct(product));
		return viewPrefix+"edit_product";
	}
	
	@RequestMapping(value="/products/{id}", method=RequestMethod.POST)
	public String updateProduct(@Valid @ModelAttribute("product") ProductForm productForm, BindingResult result, 
			Model model, RedirectAttributes redirectAttributes) {
		
		Product product = productForm.toProduct();
		Product persistedProduct = catalogService.updateProduct(product);
		this.saveProductImageToDisk(productForm);
		logger.debug("Updated product with id : {} and name : {}", persistedProduct.getId(), persistedProduct.getName());
		redirectAttributes.addFlashAttribute("info", "Product updated successfully");
		return "redirect:/products";
	}
	
	private void saveProductImageToDisk(ProductForm productForm) {
		MultipartFile file = productForm.getImage();
		if (file!= null && !file.isEmpty()) {
			String name = messageSource.getMessage(WebUtils.IMAGES_PRODUCTS_DIR, null, null) + 
					productForm.getId() + 
					".jpg";
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				throw new JCartException(e);
			}
		}
	}
	
	/**
	 * @author ungtq
	 * Remove Product 
	 * @param user
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return URL
	 */
	@RequestMapping(value="/products/remove/{id}", method=RequestMethod.GET)
	public String removeProduct(@PathVariable Integer id, Model model) {
		catalogService.deleteProductById(id);
		return "redirect:/products";
	}
	
	/**
	 * ungtq fix bug load image
	 * @param productId
	 * @param request
	 * @param response
	 * @return byte[]
	 */
	@RequestMapping(value="/products/images/{productId}", method=RequestMethod.GET)
	@ResponseBody
	public byte[] showProductImage(
			@PathVariable String productId, 
			HttpServletRequest request, 
			HttpServletResponse response) {
		try {
			File serverFile = new File(messageSource.getMessage(WebUtils.IMAGES_PRODUCTS_DIR, null, null) + productId + ".jpg");
		    return Files.readAllBytes(serverFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @author ungtq
	 * copy the product with mode new and refer
	 * @param id
	 * @param model
	 * @return 
	 */
	@RequestMapping(value="/products/copy/{id}", method=RequestMethod.GET)
	public String editProductFormCopy(@PathVariable Integer id, Model model) {
		Product product = catalogService.cloneProductById(id);
		model.addAttribute("product",ProductForm.fromProduct(product));
		return viewPrefix+"create_product";
	}
	
	@RequestMapping(value="/products/back", method=RequestMethod.POST)
	public String backToList(Model model) {
		return "redirect:/products";
	}
}
