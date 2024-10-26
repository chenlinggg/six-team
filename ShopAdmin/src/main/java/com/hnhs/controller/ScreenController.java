package com.hnhs.controller;

import com.hnhs.dao.CateProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ScreenController {

    @Resource
    private CateProductDao cateProductDao;

    //类目下有多少商品
    @GetMapping("/barcatepro")
    public List<Map> barselect(){
      return   cateProductDao.barselect();
    }


}
