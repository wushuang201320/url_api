package com.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @JmsListener(destination = "ss.queue")
    public void read(String message){
        System.out.println("接收到"+message);
    }
}
