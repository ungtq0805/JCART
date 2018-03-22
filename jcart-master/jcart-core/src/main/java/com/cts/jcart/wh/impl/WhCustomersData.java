package com.cts.jcart.wh.impl;

import java.util.List;

import com.cts.jcart.wh.entities.WhCustomer;

public interface WhCustomersData {
    
    public List<WhCustomer> get();
    public void add(WhCustomer customer);
    
}