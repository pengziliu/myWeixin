package com.lzp.service;


import com.google.gson.Gson;
import com.lzp.bean.TranslateResult;
import com.lzp.util.Constants;
import com.lzp.util.HttpRequestUtil;

/**
 * 百度翻译,json解析
 * @author Administrator
 *
 */
public class BaiduTranslateService {
    /** 
     * 翻译（中->英 英->中 日->中 ） 
     *  
     * @param source 
     * @return 
     */  
    public static String translate(String source) {  
        String dst = null;  
  
        // 组装查询地址  
        String requestUrl = Constants.SENDPATH8;
        // 对参数q的值进行urlEncode utf-8编码  
        requestUrl = requestUrl.replace("{keyWord}", HttpRequestUtil.urlEncodeUTF8(source));
  
        // 查询并解析结果  
        try {  
            // 查询并获取返回结果  
            String json = HttpRequestUtil.httpRequest(requestUrl);  
            // 通过Gson工具将json转换成TranslateResult对象  
            TranslateResult translateResult = new Gson().fromJson(json, TranslateResult.class);
            // 取出translateResult中的译文  
            dst = translateResult.getTrans_result().get(0).getDst();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        if (null == dst)  
            dst = "翻译系统异常，请稍候尝试！";  
        return dst;  
    }  
  
    public static void main(String[] args) {  
        // 翻译结果：The network really powerful  
        System.out.println(translate("网络真强大"));  
    }
}
