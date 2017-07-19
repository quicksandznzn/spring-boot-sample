package org.spring.springboot.mybatis.controller;

import org.spring.springboot.mybatis.model.Test;
import org.spring.springboot.mybatis.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * redis
 *
 * @author quicksandzn@gmail.com
 */
@RestController
@Api(value = "testinfo", description = "")
public class TestController {

    @Autowired
    private TestService testService;

    @ApiOperation(value = "getTest", notes = "id", response = Test.class, httpMethod = "GET")
    @RequestMapping(value = "/getTest", method = RequestMethod.GET)
    public Test helloWord(@RequestParam(value = "id", required = true) String id) {
        return testService.getTestById(id);
    }
}
