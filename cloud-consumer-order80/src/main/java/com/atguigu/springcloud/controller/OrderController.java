package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {
    //需要通讯的微服务的地址
//    public  static final String PAYMENT_URL = "http://localhost:8001";
    public  static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    //远程通讯使用的类
    @Autowired
    private RestTemplate restTemplate;

    //1.添加payment
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    //2.查询payment
    @GetMapping("consumer/payment/get/{id}")
    public  CommonResult<Payment> get(@PathVariable("id")Long id){
        return  restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
}
