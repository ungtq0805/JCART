package com.cts.jcart.wh.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.jcart.wh.entities.WhProduct;

/**
 * Provides methods which retrieve goods data
 * from the database
 */
@Repository
@Transactional(rollbackFor=Exception.class)
public class WhGoodsDataImpl implements WhGoodsData {

//    @Autowired
//    SessionFactory sessionFactory;
    
    /**
     * Gets goods from the database
     * ordered by name
     * @return list of goods
     */
    @SuppressWarnings("unchecked")
	public List<WhProduct> get() {
//        return sessionFactory.getCurrentSession()
//                .createCriteria(WhProduct.class)
//                .addOrder(Order.asc("name"))
//                .list();
    	return null;
    }

    /**
     * Stores a product into the database
     * @param product object that needs to be saved
     */
    public void add(WhProduct product) {
//        sessionFactory.getCurrentSession()
//                .save(product);
    }
    
}