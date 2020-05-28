package com.atguigu.springcloud.controller;


import com.alibaba.druid.support.logging.Log;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
public class PaymentController {
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

    //返回端口号
    @GetMapping("/payment/lb")
    public String lb(){
        return serverPort;
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
}
