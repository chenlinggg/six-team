package com.hnhs.controller;

import com.hnhs.model.*;
import com.hnhs.service.CategoryService;
import com.hnhs.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> select(ProductQuery query){
        List<Product> select = productService.select(query);
        return select;
    }
    @PostMapping
    public int insert(@RequestBody Product product){
        return productService.insert(product);
    }
    @PutMapping
    public int update(@RequestBody Product product){
        return productService.update(product);
    }

    @DeleteMapping
    public int delete(Integer id){
        return productService.delete(id);
    }
    @PatchMapping
    public Integer updateStatus(@RequestBody ProductStatusUpdater updater){
        return productService.updateStatus(updater);
    }
}
