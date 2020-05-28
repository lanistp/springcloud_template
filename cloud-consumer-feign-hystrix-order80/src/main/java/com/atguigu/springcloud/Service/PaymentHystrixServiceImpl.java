package com.atguigu.springcloud.Service;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "我是接口的消费者降级方法_OK";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "我是接口的消费者降级方法_timeOut";
    }
}
