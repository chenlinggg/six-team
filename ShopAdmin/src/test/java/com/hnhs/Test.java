package com.hnhs;

import com.hnhs.dao.CategoryMapper;
import com.hnhs.model.Category;
import com.hnhs.model.CategoryQuery;
import com.hnhs.model.Product;
import com.hnhs.service.CategoryService;
import com.hnhs.service.ProductService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {

   //@Autowired
    @Resource
    private CategoryMapper categoryMapper;
    //注入service类
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @org.junit.Test
    public void findAll(){
        List<Category> categories = categoryMapper.selectAll();
        for (Category category : categories) {
            System.out.println(category);
        }
    }

    @org.junit.Test
    public void selectAll(){
//        List<Category> categories = categoryService.selectAll();
//        for (Category category : categories) {
//            System.out.println(category);
//        }
        CategoryQuery query=new CategoryQuery();
        query.setId(1);
        List<Category> select = categoryService.select(query);
        for (Category category : select) {
            System.out.println(category);
        }

    }

    @org.junit.Test
    public void insert(){
       Category category=new Category();
       //category.setId();
        category.setName("MPV");
        category.setImg("mpv.jpg");
        category.setLastUpdateBy("1089090");
       categoryService.insert(category);
    }
    @org.junit.Test
    public void update(){
        Category category=new Category();
        category.setId(15);
        category.setName("皮卡");
        category.setImg("pk.jpg");
        category.setLastUpdateBy("1089090");
        categoryService.update(category);
    }
    @org.junit.Test
    public void updateDelete(){

        int delete = categoryService.delete(16);
        System.out.println(delete);

    }
    @org.junit.Test
    public void proupdate(){
        Product product=new Product();
        product.setId(11);
        product.setName("奔驰E级PHEV");
        product.setSubName("北京奔驰/中大型车  插电混动排行榜 No.54");
        product.setImg("111.png");
        product.setCategoryId(6);
        product.setSeq(1);
        product.setPrice("45.66");
        int update = productService.update(product);
        System.out.println(update);


    }
}
