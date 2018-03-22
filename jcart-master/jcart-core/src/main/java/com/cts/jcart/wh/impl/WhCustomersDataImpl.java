package com.cts.jcart.wh.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.jcart.wh.entities.WhCustomer;

/**
 * Provides methods which retrieve customers data
 * from the database
 */
@Repository
@Transactional(rollbackFor=Exception.class)
public class WhCustomersDataImpl implements WhCustomersData {
    
//    @Autowired SessionFactory sessionFactory;

    /**
     * Gets customers from the database
     * ordered by name
     * @return list of customers
     */
    @SuppressWarnings("unchecked")
	public List<WhCustomer> get() {
//        return sessionFactory.getCurrentSession()
//                .createCriteria(WhCustomer.class)
//                .addOrder(Order.asc("name"))
//                .list();
    	return null;
    }

    /**
     * Stores a customer into the database
     * @param customer object that needs to be saved
     */
    public void add(WhCustomer customer) {
//        sessionFactory.getCurrentSession()
//                .save(customer);
    }
    
}