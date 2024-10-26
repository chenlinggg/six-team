package com.hnhs.enums;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Optional;

//商品的状态信息
public enum ProductStatus {
    ONLINE(1,"上线"),
    OFFLINE(5,"下线"),
    DISABLE(10,"禁售");

    @Getter
    @Setter
    private Integer code;
    @Getter
    @Setter
    private String msg;


    //根据状态code 和msg做响应
    ProductStatus(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
    //静态方法用来接收所执行的状态 根据code
    public static ProductStatus findByCode(Integer code){
        if(code==null){
            return null;
        }
        //传过的枚举 需要导出 最后执行数据库
        //Optional 空处理函数
        Optional<ProductStatus> first= Arrays.stream(ProductStatus.values()).filter(
                item->item.getCode().equals(code)).findFirst();
        //如果first 有值就返回
        if(first.isPresent()){
            return first.get();
        }
        //如果没有值 直接返回null
        return first.orElse(null);
    }
}
