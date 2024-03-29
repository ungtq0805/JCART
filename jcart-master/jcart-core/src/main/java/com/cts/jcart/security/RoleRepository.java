/**
 * 
 */
package com.cts.jcart.security;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.jcart.entities.Role;

/**
 * @author ungtq
 *
 */
public interface RoleRepository extends JpaRepository<Role, Integer>
{

	Role findByName(String name);

}
