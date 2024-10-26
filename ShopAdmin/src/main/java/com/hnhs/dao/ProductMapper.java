package com.hnhs.dao;

import com.hnhs.annotion.PageX;
import com.hnhs.model.Category;
import com.hnhs.model.CategoryQuery;
import com.hnhs.model.Product;
import com.hnhs.model.ProductQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@Mapper
public interface ProductMapper {

    //查询所有分类的数据
    List<Product> selectAll();
    //根据名字和根据id查询
    @PageX
    List<Product> select(ProductQuery query);

    //添加
    int insert(Product product);
    //修改
    int update(Product product);
    //软件删除 逻辑删除
    int updateStatus(@Param("id") Integer id,@Param("status") Integer status);

}
