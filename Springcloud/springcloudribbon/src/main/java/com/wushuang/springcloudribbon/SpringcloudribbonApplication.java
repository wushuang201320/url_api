package com.wushuang.springcloudribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableCircuitBreaker
@EnableHystrixDashboard
public class SpringcloudribbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudribbonApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/te")
    @HystrixCommand(fallbackMethod = "fallbackinfo")
    public String te(){
        return restTemplate().getForObject("http://springcloud-provider/user/userList", String.class);
    }

    /**
     * 返回服务信息.
     * @return String
     */
    public String fallbackinfo(){
        return "服务不可用，请稍后再试";
    }

}
