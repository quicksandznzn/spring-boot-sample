package com.mikemunhall.hbasedaotest.controller;

import com.mikemunhall.hbasedaotest.service.HbaseService;
import java.util.List;
import org.apache.hadoop.hbase.client.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by quicksandzn@gmail.com on 2017/8/3.
 */
@RestController
public class TestController {

    @Autowired
    private HbaseService hbaseService;

    @GetMapping(value = "/get")
    public List<Result> get(@RequestParam String name) {
        return hbaseService.scaner(name);
    }

}
