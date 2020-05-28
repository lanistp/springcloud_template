package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.Service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderHystrixController {
    @Autowired
    PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment/hytrix/OK/{id}")
    public String paymentInfo_OK(@PathVariable("id")Integer id) {
        String info_ok = paymentHystrixService.paymentInfo_OK(id);
        return info_ok;
    }

    @GetMapping("/payment/hytrix/timeOut/{id}")
    public  String paymentInfo_TimeOut(@PathVariable("id")Integer id){
        String timeOut = paymentHystrixService.paymentInfo_TimeOut(id);
        return timeOut;
    }
}
