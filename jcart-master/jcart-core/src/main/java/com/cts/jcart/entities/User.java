/**
 * 
 */
package com.cts.jcart.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author ungtq
 *
 */
@Entity
@Table(name="users")
public class User{
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable=false)
	@NotEmpty()
	private String userName;
	
	@Column(nullable=true)
	private String fullName;
	
	@Column(nullable=true)
	private String phoneNo;
	
	@Column(nullable=true)
	private String imageUrl;
	
	@Column(nullable=false, unique=true)
	@NotEmpty
	@Email(message="{errors.invalid_email}")
	private String email;
	
	@Column(nullable=false)
	@NotEmpty
	@Size(min=4)
	private String password;
	private String passwordResetToken;
	
	@Column(nullable=true)
	private Boolean shipper;
	
	@Column(nullable=true)
	private Boolean disabled = false;
	
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(
	      name="user_role",
	      joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
	      inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
	private List<Role> roles;
	
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public List<Role> getRoles()
	{
		return roles;
	}
	public void setRoles(List<Role> roles)
	{
		this.roles = roles;
	}
	public String getPasswordResetToken()
	{
		return passwordResetToken;
	}
	public void setPasswordResetToken(String passwordResetToken)
	{
		this.passwordResetToken = passwordResetToken;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Boolean isShipper() {
		return shipper;
	}
	public void setShipper(Boolean shipper) {
		this.shipper = shipper;
	}
	public Boolean getDisabled() {
		return disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
}
