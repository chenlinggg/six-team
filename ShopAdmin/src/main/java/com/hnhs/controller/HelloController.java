package com.hnhs.controller;

import com.hnhs.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() throws InterruptedException {
//        Thread.sleep(2000);
   //     int a=1/0;
//        if(1==1){
//            throw new MyException(888,"报错了。。");
//        }

        return "hello SpringBoot";
    }
}
