package org.spring.springboot.elasticsearch.redis.service;

import org.spring.springboot.elasticsearch.redis.model.User;

public interface RedisTestService {

    User putUser(String id, String name);

}
