package com.hnhs.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hnhs.dao.CategoryMapper;
import com.hnhs.info.PageInfo;
import com.hnhs.model.Category;
import com.hnhs.model.CategoryQuery;
import com.hnhs.utils.LocalPageInfoutil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {

    @Resource
    private  CategoryMapper categoryMapper;

    public List<Category> selectAll(){
        List<Category> categories = categoryMapper.selectAll();

        return categories;
    }
    //查询所有数据并可以模糊搜索
    public List<Category> select(CategoryQuery query){
//        //解决不能全部查询的问题
//        if(query.getPageNum()!=null && query.getPageSize() !=null){
//            PageHelper.startPage(query.getPageNum(),query.getPageSize());
//        }

        List<Category> select = categoryMapper.select(query);
//        if(select instanceof Page){
//            Page page= (Page) select;
//            long total = page.getTotal();
//            LocalPageInfoutil.set(PageInfo.builder().Total(total).build());
//        }

        return select;
    }

    //添加
   public int insert(Category category){
        return categoryMapper.insert(category);
    }
    //修改
    public int update(Category category){
        return categoryMapper.update(category);
    }
    //软件删除 逻辑删除
    public int delete(Integer id){
        return categoryMapper.updateStatus(id,0);
    }



}
