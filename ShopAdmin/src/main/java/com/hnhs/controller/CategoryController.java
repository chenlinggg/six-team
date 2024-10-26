package com.hnhs.controller;

import com.hnhs.dto.ResponseDTO;
import com.hnhs.exception.MyException;
import com.hnhs.model.Category;
import com.hnhs.model.CategoryQuery;
import com.hnhs.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin  //跨域注解
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/selectAll")
    public  List<Category> selectAll(){
        List<Category> categories = categoryService.selectAll();
        return categories;
    }
    @GetMapping
    public List<Category> select(CategoryQuery query){
        List<Category> select = categoryService.select(query);
        return select;
    }
    @PostMapping
    public int insert(@RequestBody Category category){
        return categoryService.insert(category);
    }
    @PutMapping
    public int update(@RequestBody Category category){
        return categoryService.update(category);
    }

    @DeleteMapping
    public int delete(Integer id){
        return categoryService.delete(id);
    }
}
