package com.tujietg.service.Impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.tujietg.model.DataSource;
import com.tujietg.pojo.UserBean;
import com.tujietg.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public UserBean getUser(String username) {
		if (!DataSource.getData().containsKey(username)) {
			return null;
		}
		UserBean user = new UserBean();
		@SuppressWarnings("unchecked")
		Map<String, String> detail = (Map<String, String>) DataSource.getData().get(username);
		user.setUsername(username);
		user.setPassword(detail.get("password"));
		user.setRole(detail.get("role"));
		return user;
	}

}
