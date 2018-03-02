/**
 * 
 */
package com.cts.jcart.security;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.jcart.entities.Permission;

/**
 * @author Siva
 *
 */
public interface PermissionRepository extends JpaRepository<Permission, Integer>
{

}
