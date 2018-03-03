/**
 * 
 */
package com.cts.jcart.orders;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.jcart.entities.Order;

/**
 * @author ungtq
 *
 */
public interface OrderRepository extends JpaRepository<Order, Integer>
{
	Order findByOrderNumber(String orderNumber);
}
