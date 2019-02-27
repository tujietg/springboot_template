package com.tujietg.shiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tujietg.model.ResponseData;
import com.tujietg.pojo.UserBean;
import com.tujietg.service.UserService;

@Controller
@ResponseBody
public class UserController {

	@Autowired
	UserService userService;

	// 获取登陆界面
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseData toLogin(String username, String password) throws Exception {
		return new ResponseData(200, "login page", null);
	}

	// 登陆模块
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseData login(String username, String password) throws Exception {
		UserBean userBean = userService.getUser(username);
		if (userBean.getPassword().equals(password)) {
			return new ResponseData(200, "succss", 123);
		} else {
			throw new Exception();
		}
	}

}
