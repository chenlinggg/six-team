package com.hnhs;

import com.hnhs.service.CateProCountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class CountTest {
    @Autowired
    private CateProCountService cpcservice;
    @Test
    public void todayCount(){
        int i = cpcservice.categoryTodayCount();
        System.out.println(i);

    }

}
