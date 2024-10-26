package com.hnhs.model;

import cn.hutool.core.annotation.Alias;
import lombok.*;

import java.time.LocalDateTime;
//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
@Data
@AllArgsConstructor //有参构造
@NoArgsConstructor // 无参构造
public class Category {
    @Alias("编号")
    private Integer id;
    @Alias("名称")
    private String name;
    private String img;
    @Alias("排序")
    private Integer seq;
    private Integer parentId;
    private Integer status;
    @Alias("最近更新人")
    private String  lastUpdateBy;
    private LocalDateTime lastUpdateTime;

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
