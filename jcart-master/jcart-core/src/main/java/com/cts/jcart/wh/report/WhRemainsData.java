package com.cts.jcart.wh.report;

import java.util.List;

import com.cts.jcart.dto.WhRemainDto;
import com.cts.jcart.wh.entities.WhInflow;

public interface WhRemainsData {
    
    public List<WhInflow> get();
    
    public List<WhRemainDto> getRemainList();
}