package com.ql.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ql.model.Site;
public class SiteDao extends BaseDao<Site> {
	/**
	 * @param city
	 * @param utype
	 * @return
	 * @throws Exception
	 */
	public List<Map> init(String city, int utype) throws Exception{
		if(utype==10||city==null){
			String sql = "SELECT s.city, COUNT(s.city) number from site s GROUP BY s.city";
			return super.queryAll(sql);
		}else{
			String sql = "SELECT s.city, COUNT(s.city) number from site s where s.city=? GROUP BY s.city";
			return super.queryAll(sql,city);
		}
	}
	/**
	 * 按城市查找工地及所属工程信息
	 * @param city
	 * @return
	 * @throws Exception
	 */
	public List<Map> serachByCity(String city) throws Exception{
		String sql = "SELECT s.sno,s.projectNum,s.city,s.siteName,s.location,s.builder,s.x,"
				+ " s.y,s.sType,s.begin,s.end,s.online,s.hide,s.image,s.video,s.memo,p.projectName FROM site s,project p where p.projectNum=s.projectNum and s.city=?";
		return super.queryAll(sql, city);
	}
	
	/**
	 * 按照工程序号查询工地
	 * @param sno
	 * @return
	 * @throws Exception
	 */
	public Map searchByNum(int sno) throws Exception{
		String sql = "SELECT s.sno,s.projectNum,s.city,s.siteName,s.location,s.builder,s.x," +
				" s.y,s.sType,s.begin,s.end,s.online,s.hide,s.image,s.video,s.memo FROM site s where s.sno=?";
		return super.querySingle(sql, sno);
	}
	
	/**查询所有工地
	 * @return
	 * @throws Exception
	 */
	public List<Map> searchAllSites() throws Exception{
		String sql = "SELECT * FROM site";
		return super.queryAll(sql);
	}
	
	public List<Site> querySitesByProject(String sql , Object...val) throws Exception{
		return this.querylist(Site.class, sql, val);
	}
}
