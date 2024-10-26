package com.hnhs.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryProductCountDao {
    //总类目数
    @Select("select count(*) from 202_category where status=1")
    public int CategoryCount();
    // 今日新增的数量
    @Select("select count(*) from 202_category WHERE DATE(lastUpdateTime) = CURDATE()")
    public int CategoryTodayCount();
    //总的商品数量
    @Select("select count(*) from 202_product where status=1")
    public int ProductCount();

    // 今日新增的数量
    @Select("select count(*) from 202_product WHERE DATE(lastUpdateTime) = CURDATE()")
    public int ProductTodayCount();

}
