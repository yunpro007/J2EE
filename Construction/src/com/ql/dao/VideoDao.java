package com.ql.dao;

import java.util.List;
import java.util.Map;

import com.ql.model.Video;

public class VideoDao extends BaseDao<Video>{

	public List<Video> querylist(String sql, Object... val) throws Exception {
		return super.querylist(Video.class, sql, val);
	}

	public List<Map> query(String sql ,Object...val) throws Exception{
		return super.queryAll(sql, val);
	}
	
	
	public Video queryOne(String sql, Object[] val) throws Exception {
		return super.query(Video.class, sql, val);
	}
	
}
