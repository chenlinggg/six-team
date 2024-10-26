package com.hnhs.dao;

import com.hnhs.annotion.PageX;
import com.hnhs.model.Category;
import com.hnhs.model.CategoryQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

//@Mapper
public interface CategoryMapper {

    //查询所有分类的数据
    List<Category> selectAll();
    //根据名字和根据id查询
//    List<Category> select(@Param("id") Integer id, @Param("name")String name, Integer parentId);
    @PageX
    List<Category> select(CategoryQuery query);

    //添加
    int insert(Category category);
    //修改
    int update(Category category);
    //软件删除 逻辑删除
    int updateStatus(@Param("id") Integer id,@Param("status") Integer status);

}
