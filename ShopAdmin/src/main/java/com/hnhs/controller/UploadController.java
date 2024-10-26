package com.hnhs.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.hnhs.info.ImgInfo;
import com.hnhs.info.UploadInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @PostMapping
    public String upload(@RequestBody UploadInfo uploadInfo){
        String name=uploadInfo.getName();
        String base64 = uploadInfo.getBase64();
        //切分字符串，让后系统能够读取  定义数组类  从base64，
        String[] base64s = StrUtil.splitToArray(base64, "base64,");
        //转换为字节数组
        byte[] bytes = Base64.decode(base64s[1]);
        //使用雪花算法
       name=IdUtil.getSnowflakeNextIdStr()+"_"+name;

        //写入到niginx中
        FileUtil.writeBytes(bytes,"D:/software/nginx-1.21.4/html/images/"+name);


        return "http://www.hphs.com/images/"+name;
    }

    @GetMapping
    public List<ImgInfo> getImg(){
        //获取指定目录下的文件
        List<String> fileNames =
                FileUtil.listFileNames("D:/software/nginx-1.21.4/html/images/");
        System.out.println("==="+fileNames);
        //stream 里面有一个map元素类型转化方法
        //collect
        List<ImgInfo> collect = fileNames.stream().map(
                item -> {
                    return new ImgInfo(item, "http://www.hphs.com/images/" + item);
                }).collect(Collectors.toList());

        return collect;
    }
}
