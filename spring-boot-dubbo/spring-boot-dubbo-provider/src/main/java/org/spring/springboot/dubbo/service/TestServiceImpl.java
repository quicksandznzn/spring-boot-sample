package org.spring.springboot.dubbo.service;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public String helloWord() {
        return "hello";
    }

}
