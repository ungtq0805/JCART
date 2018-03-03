/**
 * 
 */
package com.cts.jcart.security;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.jcart.entities.Permission;

/**
 * @author ungtq
 *
 */
public interface PermissionRepository extends JpaRepository<Permission, Integer>
{

}
