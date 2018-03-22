package com.cts.jcart.wh.impl;

import java.util.List;

import com.cts.jcart.wh.entities.WhProduct;

public interface WhGoodsData {
    
    public List<WhProduct> get();
    public void add(WhProduct product);
    
}