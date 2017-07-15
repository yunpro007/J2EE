package com.ql.dao;

import java.util.List;
import java.util.Map;

public class DropDownDao extends BaseDao {

	public List<Map> queryList(String sql , Object...val ) throws Exception {
		return  this.queryAll(sql, val);
	}

	public List<Map> getManagementQ(String sql, Object...val) throws Exception {
		return this.queryAll(sql, val);
	}
}
