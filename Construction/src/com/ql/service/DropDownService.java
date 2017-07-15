package com.ql.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ql.dao.DropDownDao;

public class DropDownService {
    private static final Logger log = Logger.getLogger(DropDownDao.class) ;
    private Map dto = null ;
    
	public Map getDto() {
		return dto;
	}
	public void setDto(Map dto) {
		this.dto = dto;
	}

	private DropDownDao  dropDownDao = new DropDownDao() ;

	
	public List<Map> queryList(Integer parent_id) throws Exception {
    	String sql = "select region_id,region_name from region where parent_id = ?" ;
    	return  dropDownDao.queryList(sql,parent_id);
    }
	public List<Map> getManagementQ() throws Exception {
		String sql = "select uno,uName from Construction.users where uType = ?";
		String uType = "2";
		return dropDownDao.getManagementQ(sql, uType);
	}
}
