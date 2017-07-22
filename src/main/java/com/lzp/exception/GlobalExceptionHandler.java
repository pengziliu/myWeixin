package com.lzp.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzp.bean.MyExceptionResponse;

@ControllerAdvice
 public class GlobalExceptionHandler {
	
	
     @ExceptionHandler(value =  NullParamException.class)
     @ResponseBody
     public MyExceptionResponse exceptionHandler(NullParamException e, HttpServletResponse response) {
         MyExceptionResponse resp = new MyExceptionResponse();
         resp.setCode(101);
         resp.setMsg("参数为空");
         //        response.setStatus(600);
         return resp;
     }
 }
