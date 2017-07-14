package com.spring.data.solr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.data.solr.document.SupplyDoument;
import com.spring.data.solr.repository.SupplyRepository;
import com.spring.data.solr.service.SupplyService;

@Service
public class SupplyServiceImpl implements SupplyService{
	
	@Autowired
	private SupplyRepository supplyRepository;
	
	@Override
	public SupplyDoument findById(String id) {
		return supplyRepository.findOne(id);
	}

}
