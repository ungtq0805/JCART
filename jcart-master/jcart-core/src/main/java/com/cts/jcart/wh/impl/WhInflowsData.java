package com.cts.jcart.wh.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cts.jcart.wh.entities.WhInflow;

public interface WhInflowsData {
    public List<WhInflow> get();
    
    public Page<WhInflow> get(Pageable pageable);
    
    public List<WhInflow> getInFlowsActive();
    
    public List<WhInflow> getInFlowsByApplyPerson(int applyPersonId);
    
    public void add(WhInflow inflow);
    
    public WhInflow get(Long id);
    
    public void update(WhInflow whInflow);
    
    public void removeById(Long id);
}