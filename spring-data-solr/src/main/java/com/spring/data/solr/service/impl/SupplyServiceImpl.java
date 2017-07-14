package com.spring.data.solr.service.impl;

import com.spring.data.solr.document.SupplyDoument;
import com.spring.data.solr.repository.SupplyRepository;
import com.spring.data.solr.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    private SupplyRepository supplyRepository;

    @Override
    public SupplyDoument findById(String id) {
        return supplyRepository.findOne(id);
    }

}
