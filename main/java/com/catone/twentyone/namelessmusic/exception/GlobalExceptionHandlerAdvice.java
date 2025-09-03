//package com.catone.twentyone.namelessmusic.exception;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice  //统一处理
//public class GlobalExceptionHandlerAdvice {
//
//    Logger log= LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);
//
//    @ExceptionHandler({Exception.class})
//    public ResponseMessage HandlerException(Exception e, HttpServletRequest servletRequest, HttpServletResponse servletResponse){
//        log.error("统一信息",e);
//        return  new ResponseMessage(500,"错误，具体请查看日志",null);
//    }
//}
