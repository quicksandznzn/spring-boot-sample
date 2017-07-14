package com.spring.dubbo.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.spring.dubbo.DubboTestProvider;
import com.spring.dubbo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")
public class DubboTestProviderImpl implements DubboTestProvider {

    @Autowired
    private TestService testService;

    @Override
    public String helloWord() {
        return testService.helloWord();
    }


}