package com.hnhs.model;

import lombok.Data;

@Data
public class ProductQuery {
    private Integer id;  //where id=？？
    private Integer[] ids; // id in (1,2,3)
    private String name; //where name like ‘%变量%’ concat
    private Integer status;
    private Integer categoryId;
    //做分页
    private Integer pageNum;
    private Integer pageSize;

}
