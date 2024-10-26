package com.hnhs.service;

import com.hnhs.dao.CategoryProductCountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CateProCountService {
    @Autowired
    private CategoryProductCountDao cpcdao;
    //类目总条数
    public int categoryCount(){
        return cpcdao.CategoryCount();
    }
    //当天新增类目条数
    public int categoryTodayCount(){
    return cpcdao.CategoryTodayCount();
    }

    //类目总条数
    public int productCount(){
    return cpcdao.ProductCount();
    }
    //当天新增类目条数
    public int productTodayCount(){
    return cpcdao.ProductTodayCount();
    }
}
