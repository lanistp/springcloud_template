package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.controller.myhandle.MyFlowLimitHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        return "im testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "im testB";
    }



    // 本类处理，代码冗余，耦合度高
    @GetMapping("/aaaa")
    @SentinelResource(value = "aaaa",blockHandler = "bbbb")
    public String aaaa(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2) {
        //int age = 10/0;
        return "------testHotKey";
    }
    //兜底方法
    public String bbbb (String p1, String p2, BlockException exception){
        return "------deal_testHotKey,o(╥﹏╥)o";
    }


    //配置全局的限流方法
    @GetMapping("/customer/globalTest")
    @SentinelResource(value = "globalTest",
            blockHandlerClass = MyFlowLimitHandler.class,
            blockHandler = "handler_01")
    public String globalTest() {
        return "------globalTest";
    }


}
