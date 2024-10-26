package com.hnhs.dto;

import lombok.Data;

@Data
public class ResponseDTO {

    private  int code; //代表返回的数据 状态码 0 ， 1
    private String message; //如果返回正确数据 就不会显示msg的消息  非0情况 会打印具体的消息内容
    private Object data; //如果状态为0  就要输出实际数据

    //返回正确的响应方法
    public static ResponseDTO success(Object data){
        ResponseDTO dto= new ResponseDTO();
        dto.setCode(0);
        dto.setData(data);
        return dto;
    }

    //返回错误的响应方法
    public static ResponseDTO error(int code,String message){
        ResponseDTO dto=new ResponseDTO();
        dto.setCode(code);
        dto.setMessage(message);
        return dto;
    }
}
