package com.ql.service;


import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.ql.dao.VideoDao;
import com.ql.model.Video;



public class VideoService {
	private static final Logger  log  = Logger.getLogger(VideoService.class);
	
	private VideoDao videoDao = new VideoDao();
	
	private Map dto = null;
	
	public Map getDto() {
		return dto;
	}
	
	public void setDto(Map dto) {
		this.dto = dto;
	}
	

	public  List<Video> listVideoForProject() throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" select  ano , projectNum ,caNo ,caCh , caName , caAd")
				.append("  from  construction.camera")
				.append("  where  projectNum =  ? ");
		sql.append(" order by ano ASC ");
		Object [] val = {this.dto.get("projectNum")} ;
		return  videoDao.querylist(sql.toString(), val);
	}
}
	
	/**
	 * 查询
	 * @return
	 * @throws Exception
	 *//*
	public List<Map> getVideo() throws Exception{
		String sql  = " SELECT   b.bno  , b.content  pType FROM  construction.baseinfo b WHERE  b.bType = "+BaseInfoType.PROGRESS ;
		Object [] val = {} ;
		return videoDao.query(sql,val) ;
	}
	


	
	
	public Video queryOne() throws Exception {
		StringBuilder sql = new StringBuilder()
				.append(" select  p.pno , projectNum ,b.content pType ,part , pTime , memo")
				.append("  from  construction.progress p , construction.baseinfo b ")
				.append("  where  p.pno = ?")
				.append("  and  b.bType = "+BaseInfoType.PROGRESS)
				.append("  and  p.pType = b.bno");
		Object[] val = {this.dto.get("pno")};
		return videoDao.queryOne(sql.toString(),val);
	}
	
	*//**
	 * 按条件查询工程的摄像头
	 * @return
	 * @throws Exception
	 *//*
	public List<Video> queryVideoByCondition() throws  Exception{
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
		return videoDao.querylist(sql.toString(), val.toArray());
	}
}
*/