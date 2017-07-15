package com.ql.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import org.apache.log4j.Logger;

import com.ql.common.BaseInfoType;
import com.ql.common.ProgressType;
import com.ql.dao.AcceptanceDao;
import com.ql.model.Acceptance;


public class AcceptanceService {
	private static final Logger log = Logger.getLogger(AcceptanceService.class);
	
	private AcceptanceDao acceptanceDao = new AcceptanceDao();
	
	private Map dto = null;

	public Map getDto() {
		return dto;
	}

	public void setDto(Map dto) {
		this.dto = dto;
	}
	
	public List<Acceptance> querylist() throws Exception{
		String sql = "select ano,projectNum,aType,detail,aTime,result,image,memo "
				+ " from acceptance";
		return acceptanceDao.querylist(sql, null);
	}
	
	
	public List<Acceptance> listAcceptanceForProject() throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" select  ano , projectNum ,b.content aType , b1.content detail, aTime , result , image , memo ")
				.append("   from  construction.acceptance a , construction.baseinfo b ,construction.baseinfo b1")
				.append("  where  a.projectNum = ? ")
				.append("  and    b.bType = "+BaseInfoType.ACCEPTANCE)
				.append("  and    b1.bType = "+BaseInfoType.ACCEPTANCE)
				.append("  and    a.aType = b.bno")
				.append("  and    a.detail = b1.bno")
				.append("  and    b.bno = b1.pno");
		Object [] val = {this.dto.get("projectNum")};
		sql.append(" order by a.ano DESC ");
		log.debug(" AcceptanceService listAcceptanceForProject sql = "+sql);
		return acceptanceDao.querylist(sql.toString(), val);
	}
	
	public List<Map> getAcceptanceType(Integer pno) throws Exception {
		StringBuilder sql = new StringBuilder()
				.append(" SELECT  a.bno , a.content aType")
				.append(" FROM  construction.baseinfo a ")
				.append(" WHERE  a.bType =  "+BaseInfoType.ACCEPTANCE)
				.append(" AND    a.pno = ? ");
		Object [] val = {pno} ;
		return acceptanceDao.queryAll(sql.toString(), val);
	}
	
	public boolean addAcceptance() throws Exception {
		StringBuilder sql = new StringBuilder() 
				.append("INSERT INTO construction.acceptance (projectNum, aType, detail, aTime, result, image, memo)")
				.append("     VALUES ( ?, ?, ?, ?, ?, ?, ?) ");
		Object aTimeObj = this.dto.get("aTime");
		if(null != aTimeObj ){
			if("".equals((String)aTimeObj)){
				aTimeObj = null ;
			}
		}
		Object [] val = { this.dto.get("projectNum"),this.dto.get("aType") , this.dto.get("detail"),
						  aTimeObj                  ,this.dto.get("result"), this.dto.get("image") ,
						  this.dto.get("memo") };
		return acceptanceDao.update(sql.toString(), val);
	}
	
	public boolean deleteAcceptance()  throws Exception{
		String sql = "delete from acceptance where ano = ?";
		Object []val = {this.dto.get("ano")};
		return acceptanceDao.delete(sql, val);
	}
	
	public Acceptance querySingle() throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" select  ano , projectNum ,b.content aType , b1.content detail, aTime , result , image , memo ")
				.append("   from  construction.acceptance a , construction.baseinfo b ,construction.baseinfo b1")
				.append("  where  a.ano = ? ")
				.append("  and    b.bType = "+BaseInfoType.ACCEPTANCE)
				.append("  and    b1.bType = "+BaseInfoType.ACCEPTANCE)
				.append("  and    a.aType = b.bno")
				.append("  and    a.detail = b1.bno")
				.append("  and    b.bno = b1.pno");
		Object [] val = {this.dto.get("ano")};
		return  acceptanceDao.query(sql.toString(), val);
	}
	
	public boolean updateAcceptance() throws Exception {
		StringBuilder sql = new StringBuilder()
				.append("	UPDATE construction.acceptance")
				.append("	   SET projectNum=?, aType=?  , detail=?, ")
				.append("	       aTime=?     , result=? , memo=? ");
				
		Object aTimeObj = this.dto.get("aTime");
		if(null != aTimeObj ){
			if("".equals((String)aTimeObj)){
				aTimeObj = null ;
			}
		}   
		List val = new ArrayList() ;
		val.add(this.dto.get("projectNum")); val.add(this.dto.get("aType"))  ; val.add(this.dto.get("detail"));
		val.add(aTimeObj)                  ; val.add(this.dto.get("result")) ; val.add(this.dto.get("memo"));
		Object imageObj = this.dto.get("image");
		if(null != imageObj && !"".equals((String)imageObj) ){
			sql.append(" , image = ?");
			val.add(imageObj);
			
		}
		sql.append("   WHERE  ano=?  " );
        val.add(this.dto.get("ano"));
		log.debug(sql);
		log.debug("parameter "+val);
        return acceptanceDao.update(sql.toString(), val.toArray()) ;
	}
	
	/**
	 * 按条件查询 分布验收
	 * @return
	 * @throws Exception
	 */
	public List<Acceptance> queryAcceptanceByCondition() throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" select  ano , projectNum ,b.content aType , b1.content detail, aTime , result , image , memo ")
				.append("   from  construction.acceptance a , construction.baseinfo b ,construction.baseinfo b1")
				.append("  where  a.projectNum = ? ")
				.append("  and    b.bType = "+BaseInfoType.ACCEPTANCE)
				.append("  and    b1.bType = "+BaseInfoType.ACCEPTANCE)
				.append("  and    a.aType = b.bno")
				.append("  and    a.detail = b1.bno")
				.append("  and    b.bno = b1.pno");
		List val = new ArrayList() ;
		val.add(this.dto.get("projectNum"));
		
		//还原查询条件
		
		Object qoaType = this.dto.get("qoaType");
		Object qodetail =this.dto.get("qodetail");
		Object qopTime = this.dto.get("qopTime");
		
		if(null != qoaType && !"".equals((String)qoaType)){
			sql.append("  and  a.aType = ?");
			val.add(qoaType);
		}
		if(null != qodetail && !"".equals((String)qodetail)){
			sql.append(" and a.detail = ? ");
			val.add(qodetail);
		}
		if(null != qopTime && !"".equals((String)qopTime)){
			sql.append(" and a.aTime = ? ") ;
			val.add(qopTime) ;
		}
		
		sql.append(" order by a.ano DESC ");
		log.debug(sql);
		log.debug(" parameter : "+val);
		return acceptanceDao.querylist(sql.toString(), val.toArray());
	}
	
} 
