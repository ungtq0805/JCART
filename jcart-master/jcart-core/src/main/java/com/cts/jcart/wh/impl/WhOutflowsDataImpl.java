package com.cts.jcart.wh.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.jcart.wh.entities.WhOutflow;

/**
 * Provides methods which retrieve outflows data
 * from the database
 */
@Repository
@Transactional(rollbackFor=Exception.class)
public class WhOutflowsDataImpl implements WhOutflowsData {

    @Autowired
    SessionFactory sessionFactory;
    
    /**
     * Gets outflows data from the database
     * ordered by selling date
     * @return list of outflows
     */
    @SuppressWarnings("unchecked")
	public List<WhOutflow> get() {
        return sessionFactory.getCurrentSession()
                .createCriteria(WhOutflow.class)
                .addOrder(Order.desc("outflowdate"))
                .list();
    }

    /**
     * Stores an outflow into the database
     * @param outflow object that needs to be saved
     */
    public void add(WhOutflow outflow) {
        sessionFactory.getCurrentSession()
                .save(outflow);
    }
    
    /**
     * Get an inflow by its identifier
     * @param id identifier of the inflow
     * @return inflow object
     */
    public WhOutflow get(Long id) {
        return (WhOutflow)sessionFactory.getCurrentSession()
                .get(WhOutflow.class, id);
    }
    
    /**
     * Update a inflow into the database
     * @param warehouse object that needs to be saved
     */
    public void update(WhOutflow whOutflow) {
        sessionFactory.getCurrentSession()
                .update(whOutflow);
    }
    
    /**
     * remove inflow by id
     * @author ungtq
     * @param id
     * @return WhWarehouse
     */
    public void removeById(Long id) {
    	WhOutflow outflow = get(id);
    	sessionFactory.getCurrentSession().delete(outflow);
    }
}