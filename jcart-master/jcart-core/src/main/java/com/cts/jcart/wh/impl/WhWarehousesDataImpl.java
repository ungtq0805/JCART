package com.cts.jcart.wh.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.jcart.wh.entities.WhWarehouse;

/**
 * Provides methods which retrieve warehouses data
 * from the database
 */
@Repository
@Transactional(rollbackFor=Exception.class)
public class WhWarehousesDataImpl implements WhWarehousesData {
    
    @Autowired
    SessionFactory sessionFactory;

    /**
     * Gets warehouses from the database
     * ordered by name
     * @return list of warehouses
     */
    @SuppressWarnings("unchecked")
	public List<WhWarehouse> get() {
        return sessionFactory.getCurrentSession()
                .createCriteria(WhWarehouse.class)
                .addOrder(Order.asc("name"))
                .list();
    }

    /**
     * Stores a warehouse into the database
     * @param warehouse object that needs to be saved
     */
    public void add(WhWarehouse warehouse) {
        sessionFactory.getCurrentSession()
                .save(warehouse);
    }
    
    /**
     * get warehouse by id
     * @author ungtq
     * @param id
     * @return WhWarehouse
     */
    public WhWarehouse getById(Long id) {
    	return (WhWarehouse)sessionFactory.getCurrentSession().get(WhWarehouse.class, id);
    }
    
    /**
     * Update a warehouse into the database
     * @param warehouse object that needs to be saved
     */
    public void update(WhWarehouse warehouse) {
        sessionFactory.getCurrentSession()
                .update(warehouse);
    }
    
    /**
     * remove warehouse by id
     * @author ungtq
     * @param id
     * @return WhWarehouse
     */
    public void removeById(Long id) {
    	WhWarehouse wh = getById(id);
    	sessionFactory.getCurrentSession().delete(wh);
    }
}