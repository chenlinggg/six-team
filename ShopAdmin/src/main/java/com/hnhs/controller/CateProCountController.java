package com.hnhs.controller;

import com.hnhs.dao.CateProductDao;
import com.hnhs.info.CateProInfo;
import com.hnhs.service.CateProCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class CateProCountController {
    @Autowired
    private CateProCountService cpcservice;
    @GetMapping("cateCount")
    public int categoryCount(){

        return cpcservice.categoryCount();
    }
    @GetMapping("catetodayCount")
    public int categoryTodayCount(){
        return cpcservice.categoryTodayCount();
    }

    //类目总条数
    @GetMapping("productCount")
    public int productCount(){
        return cpcservice.productCount();
    }
    //当天新增类目条数
    @GetMapping("productToday")
    public int productTodayCount(){
        return cpcservice.productTodayCount();
    }
    @Resource
    private CateProductDao cateProductDao;

    //饼图的操作 后台操作数据
    @GetMapping("cateprod")
    public List<Map<String,Object>> selectByCount(){
        List<CateProInfo> cateProInfos = cateProductDao.selectByCount();
        List<Map<String,Object>> mapList=new ArrayList<>();
        for (CateProInfo cateProInfo : cateProInfos) {
            //如果为0 不要
            if (cateProInfo.getProduct_count()==0){
                continue;
            }
            Map<String,Object> map=new HashMap<>();
            map.put("name",cateProInfo.getCategory_name());
            map.put("value",cateProInfo.getProduct_count());
            mapList.add(map);
        }
        return mapList;
    }
}
