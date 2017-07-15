package com.ql.dao;

import java.util.List;

import com.ql.model.Camera;

public class CameraDao extends BaseDao<Camera>{
	 public List<Camera> queryList(String sql, Object...val) throws Exception{
		   return this.querylist(Camera.class, sql, val);
	 }
}
