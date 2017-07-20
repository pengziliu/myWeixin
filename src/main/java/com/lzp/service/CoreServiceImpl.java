package com.lzp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lzp.bean.Weather;
import com.lzp.message.model.Article;
import com.lzp.message.model.Music;
import com.lzp.message.resp.MusicMessage;
import com.lzp.message.resp.NewsMessage;
import com.lzp.message.resp.TextMessage;
import com.lzp.service.CoreService;
import com.lzp.util.GetFreeVPNUtil;
import com.lzp.util.MessageUtil;
import com.lzp.util.WXUtil;
import org.slf4j.LoggerFactory;


/**
 * 核心处理类
 *
 * @author Administrator
 *
 */
public class CoreServiceImpl implements CoreService {

    private org.slf4j.Logger log = LoggerFactory
            .getLogger(this.getClass());
    /**
     * 处理微信发来的请求
     *
     * @param request
     * @return
     */
    public  String processRequest(HttpServletRequest request) {
        // 返回给微信服务器的消息,默认为null
        String respMessage = null;

        try {

            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);

            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            log.info("收到{}的{}消息",fromUserName,msgType);
            // 默认回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);
        //    textMessage.setContent("功能正在开发中，请使用说明书");//
            respMessage = MessageUtil.messageToXml(textMessage);
           //  默认回复主菜单，回复错误时回复主菜单
             textMessage.setContent("请求处理出错，系统自动返回主菜单 \n" + mainMenu());
             //将文本消息对象转换成xml字符串
             respMessage = MessageUtil.messageToXml(textMessage);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String content = requestMap.get("Content").trim();
				if("面试".equals(content.trim())){
					textMessage.setContent("请在公众号主体信息里面进入小程序'程序员面试宝典',找工作就靠它了!");
					respMessage = MessageUtil.messageToXml(textMessage);
				}else if("3".equals(content.trim())){
					//从数据库获取最新的三篇文章

				}
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				// 取得图片地址
				String picUrl = requestMap.get("PicUrl");
				// 人脸检测
				String detectResult = FaceService.detect(picUrl);
				textMessage.setContent(detectResult);
				respMessage = MessageUtil.messageToXml(textMessage);
			}
			// 语音消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				// 语音消息文件的标识
				String mediaId = requestMap.get("MediaId");
				// 语音格式：amr
				String format = requestMap.get("Format");
				// 语音识别结果
				String recognition = requestMap.get("Recognition");
				textMessage.setContent("您发送的是语音消息！\n 消息内容为：" + recognition);
				respMessage = MessageUtil.messageToXml(textMessage);
			}
			// 视频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				textMessage.setContent("您发送的是视频消息！暂未开通该类消息相关功能，敬请期待！");
				respMessage = MessageUtil.messageToXml(textMessage);
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {

			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				textMessage.setContent("您发送的是链接消息！暂未开通该类消息相关功能，敬请期待！");
				respMessage = MessageUtil.messageToXml(textMessage);
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 关注
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					textMessage
							.setContent("亲，想你了，怎么现在才来呢？进击的程序猿是您学习娱乐的好帮手！\n\n 回复'0'进入主菜单,赶紧来体验吧,保证不会让您失望噢！！O(∩_∩)O~~");
					respMessage = MessageUtil.messageToXml(textMessage);
				}
				// 取消关注
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 扫描带参二维码
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// TODO 处理带参数扫描二维码事件
				}
				// 上报地理位置
				else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// TODO 处理上报地理位置事件
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");
					log.info("用户点击菜单事件,当前菜单key为{}",eventKey);
				}
			}
        } catch (Exception e) {
            e.printStackTrace();
        }

        return respMessage;
    }

    // 主菜单
    private static String mainMenu() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("目前本订阅号开通的功能如下：").append("\n");
        buffer.append("1.发送图片即可图片人脸识别").append("\n");
        buffer.append("2.回复'面试'为你职业晋级加油鼓劲").append("\n");
        buffer.append("3.回复'3'为你推送最新文章").append("\n");
        return buffer.toString();
    }

}