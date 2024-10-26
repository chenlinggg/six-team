package com.hnhs.model;

import cn.hutool.core.annotation.Alias;
import com.hnhs.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class Product {
    @Alias("编号")
    private Integer id;
    @Alias("名称")
    private String name;
    @Alias("标题")
    private String subName;
    private String img;
    @Alias("排序")
    private Integer seq;
    private Integer categoryId;
    private Integer status;
    private String price;
    private String brief;
    @Alias("最近更新人")
    private String  lastUpdateBy;
    private LocalDateTime lastUpdateTime;

    //状态信息展示
    private String statusX;
    public String getStatusX(){
        ProductStatus productStatus=ProductStatus.findByCode(this.getStatus());
        if (productStatus == null){
            return "";
        }
        return productStatus.getMsg();
    }




//    public Category() {
//
//    }
//
//    public Category(Integer id, String name, String img, Integer seq, Integer parentId, Integer status, String lastUpdateBy, LocalDateTime lastUpdateTime) {
//        this.id = id;
//        this.name = name;
//        this.img = img;
//        this.seq = seq;
//        this.parentId = parentId;
//        this.status = status;
//        this.lastUpdateBy = lastUpdateBy;
//        this.lastUpdateTime = lastUpdateTime;
//    }

}
