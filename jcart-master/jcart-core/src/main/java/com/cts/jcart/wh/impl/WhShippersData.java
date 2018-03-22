package com.cts.jcart.wh.impl;

import java.util.List;

import com.cts.jcart.wh.entities.WhShipper;


public interface WhShippersData {
    
    public List<WhShipper> get();
    public void add(WhShipper shipper);
    
}