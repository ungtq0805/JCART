/**
 * 
 */
package com.cts.jcart.admin.web.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.cts.jcart.entities.Role;
import com.cts.jcart.entities.User;
import com.cts.jcart.utils.StringUtils;

/**
 * @author ungtq
 *
 */
public class UserForm
{
	private Integer id;
	
	@NotEmpty()
	private String userName;
	
	private String fullName;
	
	private String phoneNo;
	
	private String imageUrl;
	private MultipartFile image;
	
	@NotEmpty
	@Email(message="{errors.invalid_email}")
	private String email;
	
	private String password;
	private String passwordResetToken;
	
	private String passwordConfirm;
	private String passwordConfirmLast;
	
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
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public String getPasswordConfirmLast() {
		return passwordConfirmLast;
	}
	public void setPasswordConfirmLast(String passwordConfirmLast) {
		this.passwordConfirmLast = passwordConfirmLast;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public User toUser() {
		User p = new User();
		p.setId(id);
		p.setUserName(userName);
		p.setFullName(fullName);
		p.setEmail(email);
		p.setPassword(password);
		p.setPasswordResetToken(passwordResetToken);
		p.setRoles(roles);
		p.setPhoneNo(phoneNo);
		return p;
	}
	
	public static UserForm fromUser(User user){
		UserForm p = new UserForm();
		p.setId(user.getId());
		p.setUserName(user.getUserName());
		p.setFullName(user.getFullName());
		p.setEmail(user.getEmail());
		
		//password
		p.setPassword(user.getPassword());
		p.setPasswordConfirm(user.getPassword());
		p.setPasswordConfirmLast(user.getPassword());
		p.setPasswordResetToken(user.getPasswordResetToken());
		
		p.setRoles(user.getRoles());
		
		p.setPhoneNo(user.getPhoneNo());
		
		return p;
	}
	
	/**
	 * update my account object
	 * @author ungtq
	 * @param user
	 * @return
	 */
	public User updateMyAccount(User user) {
		user.setId(id);
		user.setUserName(userName);
		user.setFullName(fullName);
		user.setEmail(email);
		
		if (!StringUtils.isEmpty(this.password)) {
			user.setPassword(password);
			user.setPasswordResetToken(passwordResetToken);
		}
		
		user.setPhoneNo(phoneNo);
		
		return user;
	}
}
