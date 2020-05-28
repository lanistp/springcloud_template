package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    PaymentService paymentService;


    @Value("${server.port}")
    String serverPort ;



    @GetMapping("/payment/hytrix/OK/{id}")
    public String paymentInfo_OK(@PathVariable("id")Integer id) {

        String info_ok = paymentService.paymentInfo_OK(id);

        return serverPort+info_ok;
    }
    @GetMapping("/payment/hytrix/timeOut/{id}")
    public  String paymentInfo_TimeOut(@PathVariable("id")Integer id){
        String infoTimeOut = paymentService.paymentInfo_TimeOut(id);
        return serverPort+infoTimeOut;
    }
}
