package org.spring.springboot.mybatis.service;

import org.spring.springboot.mybatis.model.User;

public interface UserService {

    User getUserById(String id);
}
