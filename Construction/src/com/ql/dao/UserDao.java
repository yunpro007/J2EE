package com.ql.dao;

import java.util.List;
import java.util.Map;

import com.ql.model.User;

public class UserDao extends BaseDao<User> {

	public User query(String sql, Object... val) throws Exception {
		return super.query(User.class , sql, val);
	}

	public List<User> querylist(String sql, Object... val) throws Exception {
		return super.querylist(User.class, sql, val);
	}

	@Override
	public boolean update(String sql, Object... val) throws Exception {
		return super.update(sql, val);
	}
	public Map  querySingle(String sql , Object...val ) throws Exception{
		return super.querySingle(sql, val);
	}
}
