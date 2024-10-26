package com.hnhs.service;

import com.hnhs.dao.CategoryMapper;
import com.hnhs.dao.ProductMapper;
import com.hnhs.model.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductService {

    @Resource
    private ProductMapper productMapper;

    public List<Product> selectAll(){
        List<Product> products = productMapper.selectAll();

        return products;
    }
    //查询所有数据并可以模糊搜索
    public List<Product> select(ProductQuery query){
        List<Product> select = productMapper.select(query);
        return select;
    }

    //添加
   public int insert(Product product){
        return productMapper.insert(product);
    }
    //修改
    public int update(Product product){
        return productMapper.update(product);
    }
    //软件删除 逻辑删除
    public int delete(Integer id){
        return productMapper.updateStatus(id,0);
    }


    public Integer updateStatus(ProductStatusUpdater updater) {
        return productMapper.updateStatus(updater.getId(),updater.getStatus());
    }
}
