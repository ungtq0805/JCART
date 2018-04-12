/**
 * 
 */
package com.cts.jcart.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;

import com.cts.jcart.constant.JCartConsts;

/**
 * @author ungtq
 *
 */
@Entity
@Table(name="categories")
public class Category
{
	@Id 
	@SequenceGenerator(name = "category_id_seq", sequenceName = "category_id_seq", initialValue = 6, allocationSize = 100)
    @GeneratedValue(generator = "category_id_seq")
	private Integer id;
	
	@Column(nullable=false, unique=true)
	@NotEmpty
	private String name;
	
	@Column(length=1024)
	private String description;
	
	@Column(name="disp_order")
	private Integer displayOrder;
	private boolean disabled;
	
	@OneToMany(mappedBy="category")
	@Where(clause = "disabled = '" + JCartConsts.CONST_FALSE + "'")
	private Set<Product> products;
	
	public Category(){
		super();
	}
	
	public Category(Integer idVal){
		super();
		id = idVal;
	}
	
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public Integer getDisplayOrder()
	{
		return displayOrder;
	}
	public void setDisplayOrder(Integer displayOrder)
	{
		this.displayOrder = displayOrder;
	}
	
	public boolean isDisabled()
	{
		return disabled;
	}
	public void setDisabled(boolean disabled)
	{
		this.disabled = disabled;
	}
	public Set<Product> getProducts()
	{
		return products;
	}
	public void setProducts(Set<Product> products)
	{
		this.products = products;
	}
	
}
