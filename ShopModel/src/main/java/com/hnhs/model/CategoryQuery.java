package com.hnhs.model;

import lombok.Data;

@Data
public class CategoryQuery {
    private Integer id;  //where id=？？
    private Integer[] ids; // id in (1,2,3)
    private String name; //where name like ‘%变量%’ concat
    private Integer status = 1;
    private Integer parentId;
    //做分页
    private Integer pageNum;
    private Integer pageSize;

}
