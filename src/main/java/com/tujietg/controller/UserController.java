package com.tujietg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tujietg.Exception.UnauthorizedException;
import com.tujietg.model.ResponseData;
import com.tujietg.pojo.UserBean;
import com.tujietg.service.UserService;
import com.tujietg.util.JWTUtil;

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

	/**
	 * 登陆模块
	 * 
	 * @param username
	 * @param password
	 * @return 登陆成功与否？
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseData login(@RequestParam("username") String username, @RequestParam("password") String password)
			throws Exception {
		UserBean userBean = userService.getUser(username);

		if (userBean == null) {
			return new ResponseData(400, "user not exists.", null);
		}

		if (!userBean.getPassword().equals(password)) {
			return new ResponseData(400, "username or password is false.", null);
		}

		if (userBean.getPassword().equals(password)) {
			return new ResponseData(200, "Login success.", JWTUtil.sign(username, password));
		} else {
			throw new UnauthorizedException();
		}
	}

}
