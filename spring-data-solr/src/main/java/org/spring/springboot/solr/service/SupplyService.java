package org.spring.springboot.solr.service;

import org.spring.springboot.solr.document.SupplyDoument;

public interface SupplyService {

    SupplyDoument findById(String id);

}
