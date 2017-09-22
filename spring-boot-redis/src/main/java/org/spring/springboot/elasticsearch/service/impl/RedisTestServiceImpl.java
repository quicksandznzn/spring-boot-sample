package org.spring.springboot.elasticsearch.service.impl;

import org.spring.springboot.elasticsearch.redis.model.User;
import org.spring.springboot.elasticsearch.service.RedisTestService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RedisTestServiceImpl implements RedisTestService {


    @Cacheable(value = "usercache")
    @Override
    public User putUser(String id, String name) {
        return new User(id, name);
    }

}
