package com.ql.dao;

import java.util.List;
import java.util.Map;

import com.ql.model.Progress;

public class ProgressDao extends BaseDao<Progress>{

	public List<Progress> querylist(String sql, Object... val) throws Exception {
		return super.querylist(Progress.class, sql, val);
	}

	public List<Map> query(String sql ,Object...val) throws Exception{
		return super.queryAll(sql, val);
	}
	
	public boolean insert(String sql,Object...val) throws Exception{
		return super.insert(sql, val);
	}

	public boolean deleteOne(String sql,Object...dto) throws Exception {
		return super.delete(sql, dto);
	}
	
	public boolean update(String sql,Object...val) throws Exception{
		return super.update(sql, val);
	}

	public Progress queryOne(String sql, Object[] val) throws Exception {
		return super.query(Progress.class, sql, val);
	}
	
}
