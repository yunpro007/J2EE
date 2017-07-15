package com.ql.dao;

import java.util.List;

import com.ql.model.Module;

public class ModuleDao extends BaseDao {

	public List<Module> queryList(String sql , Object...val) throws Exception{
		return this.querylist(Module.class, sql, val);
	}
	
}
