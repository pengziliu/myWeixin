package com.lzp.exception;

import com.lzp.bean.MyExceptionResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
 public class GlobalExceptionHandler {
	
	
     @ExceptionHandler(value =  NullParamException.class)
     @ResponseBody
     public MyExceptionResponse exceptionHandler() {
         MyExceptionResponse resp = new MyExceptionResponse();
         resp.setCode(101);
         resp.setMsg("参数为空");
         //        response.setStatus(600);
         return resp;
     }
 }
