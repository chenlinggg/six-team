package com.hnhs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//启动类
@SpringBootApplication
@MapperScan("com.hnhs.dao")
@EnableAspectJAutoProxy //开启aop动态代理
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class,args);
    }

}
