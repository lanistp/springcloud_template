package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return "线程池"+Thread.currentThread().getName()+"  paymentInfo_OK,id" +id;
    }


/**     添加服务降级处理方式
 *          fallbackMethod()处理降级的方法
  */

    @HystrixCommand(fallbackMethod="paymentInfo_TimeOutHandler",
    commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public  String paymentInfo_TimeOut(Integer id){
        int timeNumber = 5;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "线程池"+Thread.currentThread().getName()+"  paymentInfo_TimerOut,id" +id;
    }

    public  String paymentInfo_TimeOutHandler(Integer id){
        return "我是降级方法，服务超时";
    }
}
