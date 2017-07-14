package com.spring.service.impl;

import com.spring.model.User;
import com.spring.service.RedisTestService;
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
