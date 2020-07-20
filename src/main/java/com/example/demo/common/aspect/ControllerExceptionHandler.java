package com.example.demo.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

@Slf4j
@ResponseBody
@ControllerAdvice
public class ControllerExceptionHandler{
    @ExceptionHandler(Throwable.class)
    @Order(Integer.MAX_VALUE)//最后捕获这个异常
    @ResponseBody
//    @ResponseStatus(value = HttpStatus.ACCEPTED) 返回202
    public void exceptionHandler(Exception ex, HttpServletResponse response){
        response.setStatus(-1);//或者手动设置返回-1
    }
}
