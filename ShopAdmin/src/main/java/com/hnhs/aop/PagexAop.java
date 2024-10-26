package com.hnhs.aop;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hnhs.info.PageInfo;
import com.hnhs.utils.LocalPageInfoutil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class PagexAop {

    //定义切入点
    @Pointcut("@annotation(com.hnhs.annotion.PageX)")
    public void point(){}

    //定义环绕通知的方式
    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestAttributes requestAttributes= RequestContextHolder.getRequestAttributes();
        String pageNum=((ServletRequestAttributes)requestAttributes).getRequest().getParameter("pageNum");
        String pageSize=((ServletRequestAttributes)requestAttributes).getRequest().getParameter("pageSize");
        //判断前端是否分页  是不是要解决查询全部的问题
        if(!ObjectUtil.isEmpty(pageNum) && !ObjectUtil.isEmpty(pageSize) ){
            PageHelper.startPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        }
        //从切点开始执行
        Object value=joinPoint.proceed();
        //判断结构是否为Page类型
        if(value instanceof Page){
            Page page= (Page) value;
            long total = page.getTotal();
            LocalPageInfoutil.set(PageInfo.builder().Total(total).build());
        }
    return value;
    }



}
