package com.hnhs.utils;

import com.hnhs.info.PageInfo;

public class LocalPageInfoutil {

    //创建线程对象把你需要共享的实例对象贴上去
    private static ThreadLocal<PageInfo> localpageInfo=new ThreadLocal<>();

    //在线程上面设置一个标签，{k,v}
    public static void set(PageInfo pageInfo){
        localpageInfo.set(pageInfo);
    }
    //从线程上获取标签数据，只是读取 没有删除
    public static PageInfo get(){
        return localpageInfo.get();
    }

    //删除 一定要删除 不然内存泄露
    public static void remove(){
        localpageInfo.remove();
    }
}
