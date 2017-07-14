package com.spring.data.solr.service;

import com.spring.data.solr.document.SupplyDoument;

public interface SupplyService {

    SupplyDoument findById(String id);

}
