package com.spring.controller;

import com.spring.model.User;
import com.spring.service.UserService;
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
@Api(value = "userinfo", description = "")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "getUser", notes = "id", response = User.class, httpMethod = "GET")
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public User helloWord(@RequestParam(value = "id", required = true) String id) {
        return userService.getUserById(id);
    }
}
