package com.tujietg.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.tujietg.pojo.UserBean;
import com.tujietg.service.UserService;
import com.tujietg.util.JWTUtil;

public class MyRealm extends AuthorizingRealm {

	@Autowired
	UserService userService;

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JWTToken;
	}

	/**
	 * 获取授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		UserBean userBean = userService.getUser(JWTUtil.getUserFromToken(principals.toString()));
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addRole(userBean.getRole());
		return simpleAuthorizationInfo;
	}

	/**
	 * 获取登陆验证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
		String token = (String) auth.getCredentials();
		// 开始验证token 查看token携带的username是否为null -》 username和数据库中的数据比较 -》校验token
		String username = JWTUtil.getUserFromToken(token);
		if (username == null) {
			throw new AuthenticationException("Token invalid");
		}
		// 验证用户是否存在
		UserBean userBean = userService.getUser(username);
		if (userBean == null) {
			throw new AuthenticationException("User didn't existed");
		}
		// 校验token
		if (!JWTUtil.verify(token, username, userBean.getPassword())) {
			throw new AuthenticationException("Username or password error");
		}
		return new SimpleAuthenticationInfo(token, token, "MyRealm");
	}

}
