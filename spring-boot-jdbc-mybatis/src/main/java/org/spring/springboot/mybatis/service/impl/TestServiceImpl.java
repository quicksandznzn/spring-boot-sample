package org.spring.springboot.mybatis.service.impl;

import org.spring.springboot.mybatis.model.Test;
import org.spring.springboot.mybatis.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public Test getTestById(String id) {
        return jdbcTemplate.queryForObject("select id,name from test where id = ?",
            new BeanPropertyRowMapper<>(Test.class), id);
    }

}
