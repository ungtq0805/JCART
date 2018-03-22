package com.cts.jcart.wh.impl;

import java.util.List;

import com.cts.jcart.wh.entities.WhInflow;

public interface WhInflowsData {
    
    public List<WhInflow> get();
    public void add(WhInflow inflow);
    public WhInflow get(Long id);
    
}