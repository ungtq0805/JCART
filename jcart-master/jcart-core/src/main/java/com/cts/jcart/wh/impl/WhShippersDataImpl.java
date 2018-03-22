package com.cts.jcart.wh.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.jcart.wh.entities.WhShipper;

/**
 * Provides methods which retrieve shippers data
 * from the database
 */
@Repository
@Transactional(rollbackFor=Exception.class)
public class WhShippersDataImpl implements WhShippersData {
    
//    @Autowired
//    SessionFactory sessionFactory;

    /**
     * Gets shippers from the database
     * ordered by name
     * @return list of shippers
     */
    @SuppressWarnings("unchecked")
	public List<WhShipper> get() {
//        return sessionFactory.getCurrentSession()
//                .createCriteria(WhShipper.class)
//                .addOrder(Order.asc("name"))
//                .list();
    	return null;
    }

    /**
     * Stores a shipper into the database
     * @param shipper object that needs to be saved
     */
    public void add(WhShipper shipper) {
//        sessionFactory.getCurrentSession()
//                .save(shipper);
    }
    
}