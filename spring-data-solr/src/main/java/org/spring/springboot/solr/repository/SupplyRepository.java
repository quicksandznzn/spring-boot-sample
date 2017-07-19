package org.spring.springboot.solr.repository;

import org.spring.springboot.solr.document.SupplyDoument;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface SupplyRepository extends SolrCrudRepository<SupplyDoument, String> {

}
