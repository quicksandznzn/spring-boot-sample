package org.spring.springboot.solr.service.impl;

import org.spring.springboot.solr.document.SupplyDoument;
import org.spring.springboot.solr.repository.SupplyRepository;
import org.spring.springboot.solr.service.SupplyService;
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
