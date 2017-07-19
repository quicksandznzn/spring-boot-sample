package org.spring.springboot.mybatis.service.impl;

import org.spring.springboot.mybatis.model.User;
import org.spring.springboot.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUserById(String id) {
        return jdbcTemplate.queryForObject("select id,name from test where id = ?",
            new BeanPropertyRowMapper<>(User.class), id);
    }

}
