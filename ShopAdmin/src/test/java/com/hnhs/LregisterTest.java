package com.hnhs;

import com.hnhs.info.RegisterInfo;
import com.hnhs.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LregisterTest {

    @Autowired
    private UserService service;

    @Test
    public void lrTest(){
        RegisterInfo registerInfo=new RegisterInfo();
        registerInfo.setTel("123445677");
        registerInfo.setPassword("23456");
        registerInfo.setUsername("test1");
        registerInfo.setEmail("123@567.com");
        Integer register = service.register(registerInfo);
        System.out.println(register);
    }
}
