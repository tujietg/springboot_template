package com.tujietg.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
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

	/**
	 * 登陆与否访问的接口数据不同
	 * 
	 * @return
	 */
	@RequestMapping(value = "/needLogin", method = RequestMethod.POST)
	public ResponseData needLogin() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			return new ResponseData(200, "You are already login in ", null);
		} else {
			return new ResponseData(401, "don't Authenticated", null);
		}
	}

	/**
	 * 登陆才能访问的接口
	 * 
	 * @return
	 */
	@RequestMapping("/require_auth")
	@RequiresAuthentication
	public ResponseData requireAuth() {
		return new ResponseData(200, "You are authenticated", null);
	}

	/**
	 * 游客登陆访问的接口
	 */
	@RequestMapping(value = "/direct")
	public ResponseData direct() {
		return new ResponseData(200, "you are a guest", null);
	}

	/**
	 * 管理员才可以访问的接口
	 */
	@RequestMapping(value = "/admin")
	@RequiresRoles("admin")
	public ResponseData admin() {
		return new ResponseData(200, "you are a admin", null);
	}

	/**
	 * 用户可以访问的接口
	 */
	@RequestMapping(value = "/user")
	@RequiresRoles("user")
	public ResponseData user() {
		return new ResponseData(200, "you are a user", null);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseData toLogin(String username, String password) throws Exception {
		return new ResponseData(200, "login page", null);
	}

	@RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
	public ResponseData unAu() {
		return new ResponseData(410, "the current user don't have unauthorized", null);
	}

}
