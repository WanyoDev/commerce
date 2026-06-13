package com.wanyoike.apigateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController{

    @Value("${test.value}")
    public String value;

    @GetMapping("/hello")
    public String helloApiGateway(){
        return "hello api gateway";
    }

    @GetMapping("/debug")
    public String debugValue(){
        return value;
    }
}
