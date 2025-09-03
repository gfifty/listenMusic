package com.catone.twentyone.namelessmusic.entity;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ResponseMessage<T> {
    private Integer code;
    private  String msg;
    private T data;

     public ResponseMessage(Integer code, String msg, T data){
         this.code = code;
         this.msg = msg;
         this.data = data;
     }
//     接口请求成功
    public static <T> ResponseMessage<T> success(T data){
         return new ResponseMessage(HttpStatus.OK.value(), "请求成功",data);
    }
}
