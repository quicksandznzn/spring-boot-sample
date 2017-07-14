package com.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.spring.model.Test;
import com.spring.service.TestService;

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
