package com.hnhs.advice;

import cn.hutool.json.JSONUtil;
import com.hnhs.dto.ResponseDTO;
import com.hnhs.exception.MyException;
import com.hnhs.info.PageInfo;
import com.hnhs.utils.LocalPageInfoutil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

//增强类  ResponseBody 返回的字符串
@RestControllerAdvice
public class MyResponseAdvice implements ResponseBodyAdvice<Object> {
    // 对哪些内容增强 也就是哪些方法  默认是关闭的
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
       // 代表对所有接口方法增强
        return true;
    }
    //错误的数据信息展示方法
    @ExceptionHandler
    public Object processException(Exception ex){
        if(ex instanceof MyException){
            MyException myException= (MyException) ex;
            return ResponseDTO.error(myException.getCode(),ex.getMessage());
        }
//        ResponseDTO dto=ResponseDTO.error(886, ex.getMessage());
        return ResponseDTO.error(886, ex.getMessage());
    }


    @Override
    public Object beforeBodyWrite(
            Object body, MethodParameter methodParameter,
            MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass,
            ServerHttpRequest serverHttpRequest,
            ServerHttpResponse serverHttpResponse) {
        //判断解决双层嵌套问题
        if (body instanceof ResponseDTO){
            return  body;
        }

        ResponseDTO dto=ResponseDTO.success(body);
        //如果线程上有分页的信息
        PageInfo pageInfo= LocalPageInfoutil.get();
        if(pageInfo !=null){
            Map<Object,Object> map=new HashMap<>();
            map.put("total",pageInfo.getTotal());
            map.put("items",body);
            dto=ResponseDTO.success(map);
            LocalPageInfoutil.remove();
                    }

    if (aClass == StringHttpMessageConverter.class){
        //改成json字符串
        return JSONUtil.toJsonStr(dto);
    }
        return dto;
    }
}
