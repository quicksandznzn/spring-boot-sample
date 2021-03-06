package org.spring.springboot.solr.controller;

import org.spring.springboot.solr.document.SupplyDoument;
import org.spring.springboot.solr.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupplyController {

    @Autowired
    private SupplyService supplyService;

    @RequestMapping(value = "/searchSupply.do", method = RequestMethod.GET)
    public SupplyDoument search(String id) {
        return supplyService.findById(id);
    }

}
