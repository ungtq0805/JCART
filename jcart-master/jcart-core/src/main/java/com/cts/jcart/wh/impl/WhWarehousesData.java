package com.cts.jcart.wh.impl;

import java.util.List;

import com.cts.jcart.wh.entities.WhWarehouse;

public interface WhWarehousesData {
    
    public List<WhWarehouse> get();
    public void add(WhWarehouse warehouse);
    public WhWarehouse getById(Long id);
    public void update(WhWarehouse warehouse);
    public void removeById(Long id);
}