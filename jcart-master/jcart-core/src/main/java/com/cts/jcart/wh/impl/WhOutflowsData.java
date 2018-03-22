package com.cts.jcart.wh.impl;

import java.util.List;

import com.cts.jcart.wh.entities.WhOutflow;

public interface WhOutflowsData {
    
    public List<WhOutflow> get();
    public void add(WhOutflow outflow);
    
}