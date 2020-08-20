package com;

import org.jboss.logging.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.**"}) //扫描DAO
@MapperScan(basePackages={"com.mapper.**"})
public class SpringcloudproviderApplication {
    /** 日志对象. */
    private static Logger logger = Logger.getLogger(SpringcloudproviderApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudproviderApplication.class, args);
    }

    @Value("${server.port}")
    private String port;

    @RequestMapping("/hello")
    public String hello(){
        logger.info("--------"+port);
        return "hello;端口："+port;
    }

}
