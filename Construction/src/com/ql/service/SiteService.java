package com.ql.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ql.dao.SiteDao;
import com.ql.model.Site;

public class SiteService {
	private static final Logger log = Logger.getLogger(UserService.class);
	private static SiteDao siteDao = null;
	private Map dto = null;
	public Map getDto() {
		return dto;
	}
	public void setDto(Map dto) {
		this.dto = dto;
	}
	static{
		siteDao = new SiteDao();
	}
	public List<Map> init(String city, int utype) throws Exception{
		return siteDao.init(city,utype);
	}
	public List<Map> serachByCity(String city) throws Exception{
		return siteDao.serachByCity(city);
	}
	public Map searchByNum(int sno) throws Exception{
		return siteDao.searchByNum(sno);
	}
	public List<Map> searchAllSites() throws Exception{
		return siteDao.searchAllSites();
	}
	
	public List<Site> querySitesByProject() throws Exception{
		StringBuilder  sql = new StringBuilder()
				.append(" select  sno      , projectNum , province , city , district , siteName ,")
				.append(" 		  end      , online     , over     , hide , image    , video ,")
				.append("         location , builder    , x        , y    , sType    , begin ,")
				.append("         memo ")
				.append(" from    construction.site  a")
				.append(" where  a.projectNum = ? ");
        Object [] val = {this.dto.get("projectNum")};		
        log.debug(sql);
		return siteDao.querySitesByProject(sql.toString(), val);
	}
	
	public Site querySingleSite() throws Exception{
		StringBuilder sql = new StringBuilder()
				.append("")
				.append("")
				.append("") ;
	    return null ;
	}
}
