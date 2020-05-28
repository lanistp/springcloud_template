package com.atguigu.springcloud.service.controller;

import com.atguigu.springcloud.service.IMessageProvider;
import com.atguigu.springcloud.service.MessageProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

    @Autowired
    IMessageProvider iMessageProvider;
    @GetMapping("/sendMessage")
    public String sendMessage(){
        System.out.println("im here");
        return iMessageProvider.send();
    }
}
