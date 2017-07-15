package com.ql.dao;

import java.util.List;
import java.util.Map;

import com.ql.model.Acceptance;

public class AcceptanceDao extends BaseDao<Acceptance> {
	public List<Acceptance> querylist(String sql,Object...val) throws Exception{
		return super.querylist(Acceptance.class,sql, val);
	}
	public Acceptance query(String sql,Object...val) throws Exception{
		return super.query(Acceptance.class,sql, val);
	}
	public List<Map> queryAll(String sql,Object...val) throws Exception{
		return super.queryAll(sql, val);
	}
	public boolean update(String sql,Object...val) throws Exception{
		return super.update(sql, val);
	}
	public boolean delete(String sql,Object...val) throws Exception{
		return super.delete(sql, val);
	}
}
