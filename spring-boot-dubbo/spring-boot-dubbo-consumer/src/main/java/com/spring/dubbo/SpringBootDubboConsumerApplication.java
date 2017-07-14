package com.spring.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringBootDubboConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDubboConsumerApplication.class, args);
    }
}


@RestController
class DubboConsumer {

    @Reference(version = "1.0.0")
    private DubboTestProvider dubboTestProvider;

    @RequestMapping("/hello")
    public String helloWord() {
        return dubboTestProvider.helloWord();
    }
}
