package com.lzp.web;


import javax.servlet.http.HttpServletRequest;
import com.lzp.service.CoreService;
import com.lzp.util.SignUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppMainController {
	

    private org.slf4j.Logger log = LoggerFactory
            .getLogger(this.getClass());


    @Autowired
    private CoreService coreService ;



    @RequestMapping("/hello")
    public String index() {

        return "Hello World";
    }

    //验证是否来自微信服务器的消息
    @RequestMapping(value = "core",method = RequestMethod.GET)
    public String checkSignature(String signature  ,
                                  String  nonce ,
                                  String  timestamp ,
                                  String  echostr){
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
        return coreService.processRequest(req);
    }
    

}