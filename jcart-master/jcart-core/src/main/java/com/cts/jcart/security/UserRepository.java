/**
 * 
 */
package com.cts.jcart.security;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.jcart.entities.User;

/**
 * @author ungtq
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>
{

	User findByEmail(String email);
	
	/**
	 * @author UNGTQ
	 * remove user by id
	 * @param id
	 */
	void deleteById(Integer id);
	
	/**
	 * @author ungtq
	 * find by userName
	 * @param userName
	 * @return User
	 */
	User findByUserName(String userName);
	
	/**
	 * @author ungtq
	 * @param query
	 * @return 
	 */
	@Query("select p from User p where p.shipper=true")
	List<User> getShippers();
}
