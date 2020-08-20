package com.wushuang.springcloudserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableEurekaServer
@RestController
public class SpringcloudserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudserverApplication.class, args);
    }

    @RequestMapping("/sys")
    public String sys(){
        return "nihao";
    }


}
