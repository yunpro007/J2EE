package com.ql.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ql.common.BaseInfoType;
import com.ql.common.ProgressType;
import com.ql.dao.ProgressDao;
import com.ql.model.Progress;

import sun.util.logging.resources.logging;

public class ProgressService {
	private static final Logger  log  = Logger.getLogger(ProgressService.class);
	
	private ProgressDao progressDao = new ProgressDao();
	
	private Map dto = null;
	
	public Map getDto() {
		return dto;
	}
	
	public void setDto(Map dto) {
		this.dto = dto;
	}
	

	public  List<Progress> listProgressForProject() throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" select  p.pno , projectNum ,b.content pType ,part , pTime , memo")
				.append("  from  construction.progress p , construction.baseinfo b ")
				.append("  where  projectNum =  ? ")
				.append("  and  b.bType = "+BaseInfoType.PROGRESS)
				.append("  and  p.pType = b.bno");
		sql.append(" order by p.pno DESC ");
		Object [] val = {this.dto.get("projectNum")} ;
		return  progressDao.querylist(sql.toString(), val);
	}
	
	/**
	 * 查询形象进度类型
	 * @return
	 * @throws Exception
	 */
	public List<Map> getProgressType() throws Exception{
		String sql  = " SELECT   b.bno  , b.content  pType FROM  construction.baseinfo b WHERE  b.bType = "+BaseInfoType.PROGRESS ;
		Object [] val = {} ;
		return progressDao.query(sql,val) ;
	}
	


	public boolean addProgress() throws Exception {
		String sql = "insert into progress (projectNum,pType,part,pTime,memo)"
				+ " values(?,?,?,?,?)";
        Object  pTimeObj = this.dto.get("pTime");
        if(null != pTimeObj){
        	if("".equals((String)pTimeObj)){
        		pTimeObj = null;
        	}
        }
        Object [] val = {this.dto.get("projectNum"),this.dto.get("pType"),this.dto.get("part"),
				pTimeObj,this.dto.get("memo")};
		log.debug(sql);
		return progressDao.insert(sql,val);
	}
	
	public boolean deleteOneProgress() throws Exception{
		String sql = "delete from Progress where 1 = 1"
					+ "  and pno = ?";
		Object []val = {dto.get("pno")};
		return progressDao.deleteOne(sql,val);
	}
	
	public boolean update() throws Exception {
		String sql = " update Progress set projectNum = ? ,pType = ?,part=?, pTime=?,"
				    + " memo = ? "
					+ " where  pno = ?";
	        Object  pTimeObj = this.dto.get("pTime");
	        if(null != pTimeObj){
	        	if("".equals((String)pTimeObj)){
	        		pTimeObj = null;
	        	}
	        }
	     Object [] val = {this.dto.get("projectNum"), this.dto.get("pType") , this.dto.get("part"),
	    		          pTimeObj                  , this.dto.get("memo")  , this.dto.get("pno")};
	     log.debug(sql);
		return progressDao.update(sql,val);
	}
	
	public Progress queryOne() throws Exception {
		StringBuilder sql = new StringBuilder()
				.append(" select  p.pno , projectNum ,b.content pType ,part , pTime , memo")
				.append("  from  construction.progress p , construction.baseinfo b ")
				.append("  where  p.pno = ?")
				.append("  and  b.bType = "+BaseInfoType.PROGRESS)
				.append("  and  p.pType = b.bno");
		Object[] val = {this.dto.get("pno")};
		return progressDao.queryOne(sql.toString(),val);
	}
	
	/**
	 * 按条件查询工程的形象进度
	 * @return
	 * @throws Exception
	 */
	public List<Progress> queryProgerssByCondition() throws  Exception{
		StringBuilder sql = new StringBuilder() 
				.append(" select  p.pno , projectNum ,b.content pType ,part , pTime , memo")
				.append("  from  construction.progress p , construction.baseinfo b ")
				.append("  where  projectNum =  ? ")
				.append("  and  b.bType = "+BaseInfoType.PROGRESS)
				.append("  and  p.pType = b.bno");
		List val = new ArrayList() ;
		val.add(this.dto.get("projectNum"));
		
		//还原查询条件
		 Object  qopart   = this.dto.get("qopart");
		 Object  qopType  = this.dto.get("qopType");
		 Object  qopTime  = this.dto.get("qopTime");
		 
		 if(null != qopart && !"".equals((String)qopart)){
			 sql.append(" and  p.part = ? ");
			 val.add(qopart) ;
		 }
		 if(null != qopType && !"".equals((String)qopType)){
			 sql.append(" and p.pType = ? ");
			 val.add(qopType);
		 }
		 
		 if(null != qopTime && !"".equals((String)qopTime)){
			 sql.append(" and pTime = ?");
			 val.add(qopTime) ;
		 }
		 sql.append(" order by p.pno DESC ");
		 log.debug(sql);
		 log.debug("parameter "+val);
		return progressDao.querylist(sql.toString(), val.toArray());
	}
}
