package com.wushuang.springcloudconfigclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringcloudconfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudconfigClientApplication.class, args);
    }



    @Value("${clientParam}")
    private String clientParam;

    @RequestMapping("/clientParam")
    public String getClientParam(){
        return this.clientParam;
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

}
