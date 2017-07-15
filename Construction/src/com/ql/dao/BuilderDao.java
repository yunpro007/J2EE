package com.ql.dao;

import java.util.List;
import java.util.Map;

import com.ql.model.Builder;

public class BuilderDao extends BaseDao<Builder>{

	public List<Map> queryDropDown(String sql , Object...val) throws Exception {
		return this.queryAll(sql, val);
	}
	
	public Builder querySingleBuilder(String sql,Object...val) throws Exception{
		return this.query(Builder.class, sql, val);
	}
	
}
