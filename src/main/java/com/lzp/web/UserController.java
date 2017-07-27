package com.lzp.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

	private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 测试页面跳转和thymleaf取值
	 * 
	 * @return
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String hello(Model model) {
		model.addAttribute("name", "Dear");
		return "hello";
	}

	@RequestMapping("/login")
	public String login(Model model) {

		return "login";
	}

	@RequestMapping("/loginUser")
	public String loginUser(String username, String password, HttpSession session) {
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(usernamePasswordToken); // 完成登录
//			User user = (User) subject.getPrincipal();
//			session.setAttribute("user", user);
			return "index";
		} catch (Exception e) {
			return "login";// 返回登录页面
		}

	}

	@RequestMapping(value="logout",method =RequestMethod.GET)
	public String logOut() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		// session.removeAttribute("user");
		return "login";
	}

}
