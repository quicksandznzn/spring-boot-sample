package com.spring.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.spring.model.User;
import com.spring.service.RedisTestService;

@Service
public class RedisTestServiceImpl implements RedisTestService {


    @Cacheable(value = "usercache")
    @Override
    public User putUser(String id, String name) {
        return new User(id, name);
    }

}
