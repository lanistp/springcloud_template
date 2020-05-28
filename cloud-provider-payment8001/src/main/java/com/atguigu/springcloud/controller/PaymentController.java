package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Set;


@RestController
@Slf4j
public class PaymentController {
    //服务发现
    @Autowired
    DiscoveryClient discoveryClient;
    //获得yml中的配置属性
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    PaymentService paymentService;
    CommonResult commonResult;
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){

        int i = paymentService.create(payment);
        if(i == 1){
            return new CommonResult(200,"添加成功,serverPort:"+serverPort,payment);
        }else {
            return new CommonResult(444,"添加失败",null);
        }
    }


    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Integer  id){

        Payment payment = paymentService.getPaymentById(id);
        log.info("hhhhaaa"+payment);
        if(payment != null){
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,payment);
        }else {
            return new CommonResult(444,"查询失败，ID为："+id,null);
        }
    }


    @GetMapping("/payment/lb")
    public String lb(){
        return serverPort;
    }

    //服务发现
    @GetMapping(value ="/payment/discovery")
    public Object discovery()
    {
        //获得Eureka上的服务名
        List<String> list = discoveryClient.getServices();
        for (String l : list){
            log.info(l);
        }
        //CLOUD-PAYMENT-SERVICE，此服务名称下面有几个实例。
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances){
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }
}
