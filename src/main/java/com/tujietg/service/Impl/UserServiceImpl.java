package com.tujietg.service.Impl;

import org.springframework.stereotype.Service;

import com.tujietg.model.DataSource;
import com.tujietg.pojo.UserBean;
import com.tujietg.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public UserBean getUser(String username) {
		if (!DataSource.getDate().containsKey(username)) {
			return null;
		}
		UserBean userBean = new UserBean();
		userBean.setPassword((String) DataSource.getDate().get(username));
		return userBean;
	}

}
