package com.spring.data.solr.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.spring.data.solr.document.SupplyDoument;

public interface SupplyRepository extends SolrCrudRepository<SupplyDoument, String>{
	
}
