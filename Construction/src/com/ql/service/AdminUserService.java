package com.ql.service;

import java.util.List;

import com.ql.dao.AdminUserDao;
import com.ql.model.User;

public class AdminUserService {
	private AdminUserDao adminUserDao = new AdminUserDao();

	public List<User> loadUserMsg(int flag) {
		
		return adminUserDao.loadUserMsg(flag);
	}

	public List<User> loadUserMsg(String uName, int city, int flag) {
		
		return adminUserDao.loadUserMsg(uName,city,flag);
	}

	public List<User> loadUserMsg(String uName, int flag) {
		
		return adminUserDao.loadUserMsg(uName,flag);
	}

	public List<User> loadUserMsg(int city, int flag) {
		
		return adminUserDao.loadUserMsg(city,flag);
	}

	public void del(int uno, int flag) {
		adminUserDao.del(uno,flag);
	}

	public User findById(int uno) {
		return adminUserDao.findById(uno);
	}

	public void add(User users) {
		adminUserDao.add(users);
	}

	public void modify(User users) {
		adminUserDao.modify(users);
	}
	
}
