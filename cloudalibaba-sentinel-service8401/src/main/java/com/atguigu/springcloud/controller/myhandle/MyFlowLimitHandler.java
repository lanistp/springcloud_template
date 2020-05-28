package com.atguigu.springcloud.controller.myhandle;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class MyFlowLimitHandler {

    public static String handler_01(BlockException exception){
        return "我是1号兜底方法。";
    }

    public static String handler_02(BlockException exception){
        return "我是2222号兜底方法。";
    }
}
