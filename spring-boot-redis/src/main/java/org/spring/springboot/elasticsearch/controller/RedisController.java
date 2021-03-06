package org.spring.springboot.elasticsearch.controller;

import org.spring.springboot.elasticsearch.redis.model.User;
import org.spring.springboot.elasticsearch.service.RedisTestService;
import org.spring.springboot.elasticsearch.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * redis
 *
 * @author quicksandzn@gmail.com
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTestService redisTestService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/putUser")
    public User putUser() {

        return redisTestService.putUser("2", "2");
    }

    @RequestMapping("/getValue")
    public Object getValue(@RequestParam(value = "key", required = true) String k) {
        Object obj;
        obj = redisUtil.get(k);
        return obj;
    }

    @RequestMapping("/put")
    public Object put(@RequestParam(value = "key", required = true) String k,
        @RequestParam(value = "value", required = true) String v) {

        return redisUtil.set(k, v);
    }
}
