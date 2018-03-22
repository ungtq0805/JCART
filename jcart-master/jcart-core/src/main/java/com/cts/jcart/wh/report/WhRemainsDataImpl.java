package com.cts.jcart.wh.report;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.jcart.wh.entities.WhInflow;

/**
 * Provides methods which retrieve inflow data
 * from the database in the way needed
 * to form the current stock remains
 */
@Repository
@Transactional(rollbackFor=Exception.class)
public class WhRemainsDataImpl implements WhRemainsData {
    
//    @Autowired
//    SessionFactory sessionFactory;

    /**
     * Gets inflows from the database
     * ordered by the product name
     * and by the warehouse name
     * @return list of inflows
     */
    @SuppressWarnings("unchecked")
	public List<WhInflow> get() {
//        return sessionFactory.getCurrentSession()
//                .createCriteria(WhInflow.class)
//                .createAlias("product", "p")
//                .createAlias("warehouse", "w")
//                .addOrder(Order.asc("p.name"))
//                .addOrder(Order.asc("w.name"))
//                .list();
    	return null;
    }
    
}