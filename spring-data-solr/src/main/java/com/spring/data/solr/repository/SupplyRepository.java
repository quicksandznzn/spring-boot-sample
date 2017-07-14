package com.spring.data.solr.repository;

import com.spring.data.solr.document.SupplyDoument;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface SupplyRepository extends SolrCrudRepository<SupplyDoument, String> {

}
