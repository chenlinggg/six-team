package com.hnhs.dao;

import com.hnhs.info.CateProInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface CateProductDao {

    @Select("select c.name as category_name , count(p.id) as product_count from " +
            "202_category c LEFT JOIN 202_product p " +
            "on c.id=p.categoryId where c.status =1 GROUP BY c.id,c.name")
    public List<CateProInfo> selectByCount();

    //柱状图的charts
    @Select("select  c.name ,COUNT(p.id) Ptotal  FROM 202_category c JOIN 202_product p on c.id=p.categoryId GROUP BY c.name")
    public List<Map> barselect();

}
