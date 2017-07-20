package com.lzp.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzp.service.CoreService;
import com.lzp.service.CoreServiceImpl;
import com.lzp.util.SignUtil;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	

    private org.slf4j.Logger log = LoggerFactory
            .getLogger(this.getClass());



    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

    //验证是否来自微信服务器的消息
    @RequestMapping(value = "core",method = RequestMethod.GET)
    public String checkSignature(@RequestParam(name = "signature" ,required = false) String signature  ,
                                 @RequestParam(name = "nonce",required = false) String  nonce ,
                                 @RequestParam(name = "timestamp",required = false) String  timestamp ,
                                 @RequestParam(name = "echostr",required = false) String  echostr){
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            log.info("接入成功");
            return echostr;
        }
        log.error("接入失败");
        return "";
    }
    // 调用核心业务类接收消息、处理消息跟推送消息
    @RequestMapping(value = "core",method = RequestMethod.POST)
    public  String post(HttpServletRequest req){
        CoreService coreService = new CoreServiceImpl();

        String respMessage = coreService.processRequest(req);
        return respMessage;
    }
    

}