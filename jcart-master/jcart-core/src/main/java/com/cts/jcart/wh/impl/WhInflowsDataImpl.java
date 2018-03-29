package com.cts.jcart.wh.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.jcart.catalog.MasterCommonRepository;
import com.cts.jcart.wh.entities.WhInflow;

/**
 * Provides methods which retrieve inflows data
 * from the database
 */
@Repository
@Transactional(rollbackFor=Exception.class)
public class WhInflowsDataImpl implements WhInflowsData {

    @Autowired
    SessionFactory sessionFactory;
    
    @Autowired 
    MasterCommonRepository masterRepository;
    
    /**
     * Gets inflows from the database
     * ordered by the product name
     * @return list of inflows
     */
    @SuppressWarnings("unchecked")
	public List<WhInflow> get() {
        return sessionFactory.getCurrentSession()
                .createCriteria(WhInflow.class)
                .createAlias("product", "p")
                .addOrder(Order.asc("p.name"))
                .list();
    }

    /**
     * Stores an inflow into the database
     * @param inflow object that needs to be saved
     */
    public void add(WhInflow inflow) {
        sessionFactory.getCurrentSession()
                .save(inflow);
	}

    /**
     * Get an inflow by its identifier
     * @param id identifier of the inflow
     * @return inflow object
     */
    public WhInflow get(Long id) {
        return (WhInflow)sessionFactory.getCurrentSession()
                .get(WhInflow.class, id);
    }
    
    /**
     * Update a inflow into the database
     * @param warehouse object that needs to be saved
     */
    public void update(WhInflow whInflow) {
        sessionFactory.getCurrentSession()
                .update(whInflow);
    }
    
    /**
     * remove inflow by id
     * @author ungtq
     * @param id
     * @return WhWarehouse
     */
    public void removeById(Long id) {
    	WhInflow wInflow = get(id);
    	sessionFactory.getCurrentSession().delete(wInflow);
    }
}