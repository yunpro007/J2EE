package com.ql.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ql.dao.BuilderDao;
import com.ql.model.Builder;

public class BuilderService {
	private static final  Logger log = Logger.getLogger(BuilderService.class);
	
	private BuilderDao builderDao = new BuilderDao() ;
	
	private Map dto = null ;
	public Map getDto() {
		return dto;
	}
	public void setDto(Map dto) {
		this.dto = dto;
	}
	public  List<Map>  queryDropDown() throws Exception{
		String sql = " select  bno , bName from construction.builder " ;
		Object  [] val = {} ;
		return builderDao.queryDropDown(sql,val) ;
	}
	
	public Builder querySingleBuilder() throws Exception {
		String sql = " select bno, bName , logo , brief , memo from construction.builder where bno = ? ";
		Object [] val = {this.dto.get("bno")} ;
		log.debug("querySingleBuilder : "+sql );
		log.debug("bno "+ dto.get("bno"));
		return builderDao.querySingleBuilder(sql,val);
	}
	
//	public String querySingleBuilderLogo() throws Exception{
//		String sql = " select  bName , logo , brief , memo from construction.builder where bno = ? ";
//		Object [] val = {this.dto.get("bno")} ;
//		log.debug("querySingleBuilder : "+sql);
//		Builder builder =  builderDao.querySingleBuilder(sql,val);
//		return builder.getLogo();
//	}
}
