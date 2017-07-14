package com.spring.service;

import com.spring.model.User;

public interface RedisTestService {

    User putUser(String id, String name);

}
