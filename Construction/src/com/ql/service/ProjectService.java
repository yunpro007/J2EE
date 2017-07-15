package com.ql.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jspsmart.upload.Request;
import com.ql.common.UserType;
import com.ql.dao.ProjectDao;
import com.ql.model.Project;
import com.ql.model.User;



public class ProjectService  {
	private static final Logger log = Logger.getLogger(ProjectService.class);
	private ProjectDao projectDao = new ProjectDao();
	private Map  dto = null;
	public Map getDto() {
		return dto;
	}
	/**
	 * 设置 Map 对象
	 * @param dto
	 */
	public void setDto(Map dto) {
		this.dto = dto;
	}
	/**
	 * 查询所有该用户权限范围内工程的 工程ID
	 * @return
	 * @throws Exception
	 */
	public  String queryAllProjectsID() throws Exception{
		StringBuilder sql = new StringBuilder()
				 .append(" SELECT  p.pno  ")
				.append("  FROM   construction.project  p , construction.builder b ")
				.append("  where  p.bno = b.bno  ") ;
		User user = (User)this.dto.get("user");
        int userType = user.getuType() ;
        List val = new ArrayList(); 
        if(UserType.SUPER_USER == userType){
        	//超级用户  TODO 
        }
        else if(UserType.TGCOC  == userType){
        	//施工总包单位
        	sql.append("  and    b.bno = ?");
        	val.add(user.getBno());
        }
        else if(UserType.DETECTION <= userType && UserType.QUALITY_SUPERVISION >= userType){
        	//政府机构
            sql.append(" and  p.pno  in ")
            	.append(" ( select DISTINCT r.pno from  construction.relationship r  where  r.uType = ?  and r.city = ?   )  ");
            val.add(user.getuType());
            val.add(user.getCity()) ;
        }
        else {
			//企业机构
        	if(UserType.CONSTRUCTION_UNIT == userType){
        		//建设单位
        		sql.append(" and  p.investor = ? ");
        		val.add(user.getUnitName());
        	}
        	else if(UserType.SUPERVISOR == userType){
        		//监理单位
        		sql.append(" and p.supervisor = ?");
        		val.add(user.getUnitName());
        	}
        	else if(UserType.TOURIST == userType){
        		
        	}
        	else {
        		//TODO  项目部 ， 租赁建筑公司
        		sql.append(" and  p.pno  in ")
             	.append(" ( select DISTINCT u.pno from construction.userproject  u  where  u.uno = ?    )  ");
        		val.add(user.getUno()) ;
        	}
		}
        log.debug(sql);
	    return projectDao.getResultSet(sql.toString(), val.toArray());
	}
	/**
	 * 按用户类型，查询所有的工程的信息
	 * @return
	 * @throws Exception
	 */
	public  List<Project> queryAllProject() throws Exception{
		StringBuilder sql = new StringBuilder()
				 .append(" SELECT  p.pno      , p.projectNum , p.projectName , p.location , p.investor ,")
				 .append("         b.bName    , p.supervisor , p.price       , p.scale    , p.managementQ , ")
				 .append("         p.sTime    , p.permission , p.brief       , p.province , p.city ,")
				 .append("         p.district , p.x          , p.y           , p.sType    , p.beginTime ,")
				 .append("         p.endTime  , p.isOnline   , p.over        , p.hide     , p.image ,")
				 .append("         p.video    , p.memo       , b.bno         , p.managementS 	,")
				 .append("         p.aq1      , p.aq2        , p.aq3         ")
				.append("  FROM   construction.project  p , construction.builder b ")
				.append("  where  p.bno = b.bno  ") ;
		User user = (User)this.dto.get("user");
        int userType = user.getuType() ;
        log.debug("ProjectService queryAllProject  user  = "+user);
        List val = new ArrayList(); 
		if(UserType.SUPER_USER == userType||UserType.TOURIST == userType){
        	//超级用户  TODO 
        }
        else if(UserType.TGCOC  == userType){
        	//施工总包单位
        	sql.append("  and    b.bno = ?");
        	val.add(user.getBno());
        }
        else if(UserType.DETECTION <= userType && UserType.QUALITY_SUPERVISION >= userType){
        	//政府机构，TODO 是否包含租赁建筑公司，
            sql.append(" and  p.pno  in ")
            	.append(" ( select DISTINCT r.pno from  construction.relationship r  where  r.uType = ?  and r.city = ?   )  ");
            val.add(user.getuType());
            val.add(user.getCity()) ;
        }
        else {
			//企业机构
        	if(UserType.CONSTRUCTION_UNIT == userType){
        		//建设单位
        		sql.append(" and  p.investor = ? ");
        		val.add(user.getUnitName());
        	}
        	else if(UserType.SUPERVISOR == userType){
        		//监理单位
        		sql.append(" and p.supervisor = ?");
        		val.add(user.getUnitName());
        	}
        	else {
        		//TODO  项目部 ， 租赁建筑公司
        		sql.append(" and  p.pno  in ")
             	.append(" ( select DISTINCT u.pno from construction.userproject  u  where  u.uno = ?    )  ");
        		val.add(user.getUno()) ;
        	}
		}
        log.debug(sql);
	    return projectDao.queryList(sql.toString(),val.toArray());
	}

	/**
	 * 根据条件查询 
	 * @return
	 * @throws Exception
	 */
	public List<Map> queryByCondition() throws Exception{
		StringBuilder sql = new StringBuilder()
				.append("  SELECT  p.pno      , p.projectNum , p.projectName , ")
				.append("          p.location , p.investor   , b.bName ,     ")
				.append("          p.memo")
				.append("  FROM   construction.project  p , construction.builder b ")
				.append("  where  p.bno = b.bno  ") ;
		//还原查询条件
		Object qoinvestorObj = this.dto.get("qoinvestor");
		Object qoprojectNameObj = this.dto.get("qoprojectName");
		Object qolocationObj = this.dto.get("qolocation");
		Object qobuilderObj = this.dto.get("qobuilder") ;
		
		List  val = new ArrayList() ;
		User  user = (User)this.dto.get("user");
        int   userType = user.getuType() ;
        log.debug("ProjectService queryByCondition  user  = "+user);
		if(UserType.SUPER_USER == userType){
        	//超级用户  TODO 
        }
        else if(UserType.TGCOC  == userType){
        	//施工总包单位
        	sql.append("  and    b.bno = ?");
        	val.add(user.getBno());
        }
        else if(UserType.DETECTION <= userType && UserType.QUALITY_SUPERVISION >= userType){
        	//政府机构，TODO 是否包含租赁建筑公司，
            sql.append(" and  p.pno  in ")
            	.append(" ( select DISTINCT r.pno from  construction.relationship r  where  r.uType = ?  and r.city = ?   )  ");
            val.add(user.getuType());
            val.add(user.getCity()) ;
        }
        else {
			//企业机构
        	if(UserType.CONSTRUCTION_UNIT == userType){
        		//建设单位
        		sql.append(" and  p.investor = ? ");
        		val.add(user.getUnitName());
        	}
        	else if(UserType.SUPERVISOR == userType){
        		//监理单位
        		sql.append(" and p.supervisor = ?");
        		val.add(user.getUnitName());
        	}
        	else {
        		 sql.append(" and  p.pno  in ")
             	.append(" ( select DISTINCT u.pno from construction.userproject  u  where  u.uno = ?    )  ");
        		val.add(user.getUno()) ;
        	}
		}
		
		if(null != qoprojectNameObj && !"".equals((String)qoprojectNameObj)){
			sql.append(" and p.projectName like ?");
			val.add(qoprojectNameObj+"%");
		}
		if(null != qoinvestorObj && !"".equals((String)qoinvestorObj)){
			sql.append(" and p.investor like ?");
			val.add(qoinvestorObj+"%");
		}
		if(null != qolocationObj && !"".equals((String)qolocationObj)){
			sql.append(" and p.location like ?") ;
			val.add(qolocationObj+"%");
		}
		if(null != qobuilderObj && !"".equals((String)qobuilderObj)){
			sql.append(" and  b.bName like ?");
			val.add(qobuilderObj+"%");
		}
		sql.append(" order by  p.pno ");
		log.debug(sql);
		log.debug(val);
		return projectDao.queryAll(sql.toString(), val.toArray()) ;
	}
	
	public boolean delete() throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" DELETE FROM project WHERE pno = ?");
		Object pnoObj = this.dto.get("pno");
		Integer pno = null;
		List var = new ArrayList() ;
		if(null !=  pnoObj){
			pno = Integer.parseInt((String)pnoObj);
			var.add(pno);
		}
		log.debug(sql);
		log.debug("parameter: "+var);
		return  projectDao.delete(sql.toString(), var.toArray());
	}
	
	public  Project querySite() throws Exception{
	 StringBuilder sql  = new StringBuilder() 
			 .append(" SELECT  p.pno      , p.projectNum , p.projectName , p.location , p.investor ,")
			 .append("         b.bName    , p.supervisor , p.price       , p.scale    , p.managementQ ,  p.managementS ,")
			 .append("         p.sTime    , p.permission , p.brief       , p.province , p.city ,")
			 .append("         p.district , p.x          , p.y           , p.sType    , p.beginTime ,")
			 .append("         p.endTime  , p.isOnline   , p.over        , p.hide     , p.image ,")
			 .append("         p.video    , p.memo       , b.bno         , ")
			 .append("         p.aq1      , p.aq2        , p.aq3   ")
			 .append("  FROM   construction.project p , construction.builder b")
			 .append(" where   p.bno = b.bno ")
			 .append("   and   p.pno = ?") ;
	    Object [] val = {this.dto.get("pno")} ;
	    log.debug(sql);
		return projectDao.query(sql.toString(), val);
	}
	
	public List<Map> init(String city, int utype) throws Exception{
		if(utype==10||city==null){
			String sql = "SELECT p.city, COUNT(p.city) number from project s GROUP BY p.city";
			return projectDao.queryAll(sql);
		}else{
			String sql = "SELECT p.city, COUNT(p.city) number from project s where p.city=? GROUP BY p.city";
			return projectDao.queryAll(sql,city);
		}
	}
	
	public List<Map> searchByCity(String city) throws Exception{
		String sql = "SELECT p.pno,p.projectNum,p.city,p.projectName,p.location,p.builder,p.x,"
				+ " p.y,p.sType,p.begin,p.end,p.online,p.hide,p.image,p.video,p.memo,p.projectName FROM project s,project p where p.projectNum=p.projectNum and p.city=?";
		return projectDao.queryAll(sql, city);
	}
	
	public boolean  addProject() throws Exception{
		StringBuilder sql = new StringBuilder() 
				.append(" INSERT INTO  construction.project(")
				.append("   		projectNum , projectName , location , investor    ,   bno         ,")
				.append("			supervisor , price       , scale    , managementQ ,   managementS ,")
				.append("			sTime      , brief       , province , city        ,   district    ,")
				.append("			x          , y           , sType    , beginTime   ,   endTime     ,")
				.append("			isOnline   , over        , hide     , permission  ,   image       ,      ")
				.append("			video      , memo   ,aq1, aq2, aq3)")
				.append("      VALUES (?,?,?,?,?,")
				.append("			   ?,?,?,?,?,")
				.append("			   ?,?,?,?,?,")
				.append("			   ?,?,?,?,?,")
				.append("              ?,?,?,?,?,")
				.append("			   ?,?,?,?,? ) ");
		 Object sTimeObj = this.dto.get("sTime");
		 Object beginTimeObj = this.dto.get("beginTime");
		 Object endTimeObj = this.dto.get("endTime");
		 Object aq1 = this.dto.get("aq1");
		 Object aq2 = this.dto.get("aq2");
		 Object aq3 = this.dto.get("aq3");
		 String a1 = "";
		 String a2 = "";
		 String a3 = "";
		 //当页面获取的时间为""时，特殊处理时间类型
		 if(null != sTimeObj && "".equals((String)sTimeObj)){
			sTimeObj = null ; 
		 }
		 if(null != beginTimeObj && "".equals((String)beginTimeObj)){
			 beginTimeObj = null ;
		 }
		 if(null != endTimeObj && "".equals((String)endTimeObj)){
			 endTimeObj = null ;
		 }
		 if(null != aq1 && "".equals((String)aq1)){
			 aq1 = null ;
		 } else{
			 a1 = aq1.toString();
		 }
		 if(null != aq2 && "".equals((String)aq2)){
			 aq2 = null ;
		 } else{
			 a2 = aq2.toString();
		 }
		 if(null != aq3 && "".equals((String)aq3)){
			 aq3 = null ;
		 } else{
			 a3 = aq3.toString();
		 }
		 
		Object [] val ={this.dto.get("projectNum")  , this.dto.get("projectName"), this.dto.get("location"),
						this.dto.get("investor")    , this.dto.get("bno")        , this.dto.get("supervisor"),
						this.dto.get("price")       , this.dto.get("scale")      , this.dto.get("managementQ"),
						this.dto.get("managementS") , sTimeObj                   , this.dto.get("brief"),
						this.dto.get("province")    , this.dto.get("city")       , this.dto.get("district"),
						this.dto.get("x")           , this.dto.get("y")          , this.dto.get("sType"),
						beginTimeObj                , endTimeObj                 , this.dto.get("isOnline"),
						this.dto.get("over")        , this.dto.get("hide")       , this.dto.get("permission"),
						this.dto.get("image")       , this.dto.get("video")      , this.dto.get("memo"),
						aq1			, aq2        , aq3} ;
		log.debug(sql);
		boolean flag = projectDao.add(a1, a2, a3, sql.toString(), val);
		return flag;
	}
	
	public Map searchProjectByID(int pno) throws Exception{
		String sql = "SELECT * FROM project where pno=?";
		return projectDao.querySingle(sql, pno);

	}
	
	public List<Map> searchAllProjects() throws Exception{
		String sql = "SELECT * FROM project";
		return projectDao.queryAll(sql);
	}
	/**
	 * 查找该地区下所有工程
	 * @param district
	 * @return
	 * @throws Exception
	 */
	public List<Map> searchProjectsByDistrict(int district) throws Exception{
		String sql = "SELECT * FROM project WHERE district  = ?";
		return projectDao.queryAll(sql,district);
		
	}
	/**
	 * 由工程编号查找该工程类型
	 * @param pno
	 * @return
	 * @throws Exception
	 */
	public Map searchProjectTypeByPno(int pno) throws Exception{
		String sql = "SELECT content FROM baseinfo WHERE bno IN(SELECT sType FROM project WHERE pno = ?)";
		return projectDao.querySingle(sql, pno);
	}
	
	
	public boolean modifyProject() throws Exception {
		StringBuilder sql = new StringBuilder()
				.append("	UPDATE  construction.project SET ")
				.append("			projectNum = ?  , projectName = ?  , location = ?  , investor   = ?  , bno   = ? ,")
				.append("			supervisor = ?  , price       = ?  , scale    = ?  , managementQ = ?  , managementS= ? , sTime = ? ,")
				.append("			brief      = ?  , province    = ?  , city     = ?  , district   = ?  , x     = ? ,")
				.append("			y          = ?  , sType       = ?  , beginTime = ?   , endTime  = ?  , isOnline   = ?  ,")
				.append("			over       = ?  , hide        = ?  , memo  = ? ");
		
		 Object sTimeObj = this.dto.get("sTime");
		 Object beginTimeObj = this.dto.get("beginTime");
		 Object endTimeObj = this.dto.get("endTime");
		 //当页面获取的时间为""时，特殊处理时间类型
		 if(null != sTimeObj && "".equals((String)sTimeObj)){
			sTimeObj = null ; 
		 }
		 if(null != beginTimeObj && "".equals((String)beginTimeObj)){
			 beginTimeObj = null ;
		 }
		 if(null != endTimeObj && "".equals((String)endTimeObj)){
			 endTimeObj = null ;
		 } 
		 List val = new ArrayList();
		 Object [] valObj = {this.dto.get("projectNum") , this.dto.get("projectName") , this.dto.get("location")    ,
				             this.dto.get("investor")   , this.dto.get("bno")         , this.dto.get("supervisor")  ,
				             this.dto.get("price")	    , this.dto.get("scale")       , this.dto.get("managementQ")  , this.dto.get("managementS"), 
				             sTimeObj                   , this.dto.get("brief")       , this.dto.get("province")    ,
				             this.dto.get("city")       , this.dto.get("district")    , this.dto.get("x")           ,
				             this.dto.get("y")          , this.dto.get("sType")	      , beginTimeObj                , 
				             endTimeObj                 , this.dto.get("isOnline")    , this.dto.get("over")        , 
				             this.dto.get("hide")       ,this.dto.get("memo")
		 } ;
		 val.addAll(Arrays.asList(valObj));
		 Object permission = this.dto.get("permission");
		 Object image = this.dto.get("image");
		 Object video = this.dto.get("video");
		 if(null != permission && !"".equals((String)permission)){
			 sql.append(" , permission = ? ");
			 val.add(permission) ;
		 }
		 if(null != image && !"".equals((String)image)){
			 sql.append(" , image = ? ");
			 val.add(image) ;
		 }
		 if(null != video && !"".equals((String)video)){
			 sql.append(" , video = ? ");
			 val.add(video);
		 }
		 
		 sql.append(" where pno = ?  ") ;
		 val.add(this.dto.get("pno"));
		
		 log.debug(sql);
		 log.debug("parameter "+val);
		return projectDao.modify(sql.toString(),val.toArray()) ;
	}
	/**
	 * 由工程编号查找该工程地区详细信息
	 * @param pno
	 * @return
	 * @throws Exception
	 */
	public Map searchProjectDistrictContentByPno(int pno) throws Exception{
		String sql = "SELECT content FROM baseinfo WHERE bno IN(SELECT district FROM project WHERE pno = ?)";
		return projectDao.querySingle(sql, pno);
	}
	public Map searchDistrictContent(int district) throws Exception{
		String sql = "SELECT REGION_NAME FROM region WHERE REGION_ID = ?";
		return projectDao.querySingle(sql, district);
	}
	/**
	 * 查找相同地区工程数目
	 * @param district
	 * @return
	 * @throws Exception
	 */
	public Map searchProjectNumByDistrict(int district) throws Exception{
		String rs = (String) this.dto.get("pnoSet");
		String sql = "SELECT COUNT(*) a FROM project  WHERE district = ? and pno in " + rs + "";
		return projectDao.querySingle(sql, district);
	}
	/**查找同地区相同类别工程的个数
	 * @param district
	 * @param sType
	 * @return
	 * @throws Exception
	 */
	public Map searchProjectNumByDistrictAndSType(int district,int sType) throws Exception{
		String rs = (String) this.dto.get("pnoSet");
		String sql = "SELECT COUNT(*) a FROM project  WHERE pno in " + rs + " AND district = ? AND sType = ?";
		return projectDao.querySingle(sql, district,sType);
	}
	/**
	 * 通过工程类型的详细内容查找该类型在基础信息表中的编号
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public Map searchSTypeByContent(String content) throws Exception{
		String sql = "SELECT bno FROM baseinfo  WHERE content = ?";
		return projectDao.querySingle(sql, content);
	}
	/**
	 * 查找同一城市下的地区
	 * @param city
	 * @return
	 * @throws Exception
	 */
	public List<Map> searchDistrictsByCity(int city) throws Exception{
		String rs = (String) this.dto.get("pnoSet");
		String sql = "SELECT DISTINCT district FROM project WHERE pno in " + rs + " AND city = ?";
		return projectDao.queryAll(sql, city);
	}
	/**
	 * 查找该地区今年开工工程数
	 * @param district
	 * @param beginTime
	 * @return
	 * @throws Exception
	 */
	public Map searchBeginProjecstNum(int district,String beginTime) throws Exception{
		String rs = (String) this.dto.get("pnoSet");
		String sql = "SELECT COUNT(*) a FROM project WHERE pno in " + rs + " AND district = ? AND beginTime like ('" + beginTime + "%');";
		return projectDao.querySingle(sql, district);
	}
	/**
	 * 查找该地区今年完工工程数
	 * @param district
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public Map searchEndProjecstNum(int district,String endTime) throws Exception{
		String rs = (String) this.dto.get("pnoSet");
		String sql = "SELECT COUNT(*) a FROM project WHERE pno in " + rs + " AND district = ? AND endTime like ('" + endTime + "%');";
		return projectDao.querySingle(sql, district);
	}
	public Map searchProjectPositionByID(int pno) throws Exception{
		String sql = "SELECT x, y FROM project WHERE pno = ?";
		return projectDao.querySingle(sql, pno);
	}
	public Map searchDistrictContentByID(int pno) throws Exception{
		String sql = "SELECT r.REGION_NAME FROM region r , project p WHERE p.district = r.REGION_ID AND p.pno = ?";
		return projectDao.querySingle(sql, pno);
	}
	  /**
	   *通过城市编号查找用户城市的详细内容
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public Map searchCityContent(int city) throws Exception{
		  StringBuilder sql = new StringBuilder().append(" SELECT REGION_NAME FROM region WHERE REGION_ID = ?");
		  log.debug(sql);
		  log.debug("parameter: city = "+city);
		  return projectDao.querySingle(sql.toString(),city);
		  
	  }
	/**
	 * 查询所有城市
	 * @return
	 */
	public List<Map> searchCitys() throws Exception{
		 String rs = (String) this.dto.get("pnoSet");
		 StringBuilder sql = new StringBuilder().append("SELECT DISTINCT city FROM project WHERE pno in ").append(rs);
		 log.debug(sql);
		 return projectDao.queryAll(sql.toString());
	}
	/**
	 * 城市下某类型工程的数目
	 * @param city
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public Map searchProjectNumByCityAndSType(int city,int type) throws Exception{
		String rs = (String) this.dto.get("pnoSet");
		String sql = "SELECT COUNT(*) a FROM project  WHERE pno in " + rs + " AND city = ? AND sType = ?";
		return projectDao.querySingle(sql, city,type);
	}
	/**
	 * 查找相同城市工程数目
	 * @param district
	 * @return
	 * @throws Exception
	 */
	public Map searchProjectNumByCity(int district) throws Exception{
		String rs = (String) this.dto.get("pnoSet");
		String sql = "SELECT COUNT(*) a FROM project  WHERE city = ? and pno in " + rs + "";
		return projectDao.querySingle(sql, district);
	}
	/**
	 * 查找该城市今年开工工程数
	 * @param district
	 * @param beginTime
	 * @return
	 * @throws Exception
	 */
	public Map searchBeginProjecstNumByCity(int city,String beginTime) throws Exception{
		String rs = (String) this.dto.get("pnoSet");
		String sql = "SELECT COUNT(*) a FROM project WHERE pno in " + rs + " AND city = ? AND beginTime like ('" + beginTime + "%');";
		return projectDao.querySingle(sql, city);
	}
	/**
	 * 查找该城市今年完工工程数
	 * @param district
	 * @param beginTime
	 * @return
	 * @throws Exception
	 */
	public Map searchEndProjecstNumByCity(int city,String beginTime) throws Exception{
		String rs = (String) this.dto.get("pnoSet");
		String sql = "SELECT COUNT(*) a FROM project WHERE pno in " + rs + " AND city = ? AND endTime like ('" + beginTime + "%');";
		return projectDao.querySingle(sql, city);
	}
	public List<Map> searchProjectsByCity(int city) throws 	Exception{
		String rs = (String) this.dto.get("pnoSet");
		String sql = "SELECT * FROM project WHERE pno in " + rs + " AND city = ?";
		return projectDao.queryAll(sql, city);
	}
	
	
	public  List<Map>  getAq(int pno) throws Exception{
		String sql = "SELECT   a.aq1 , a.aq2 , a.aq3  FROM  construction.project a  WHERE  a.pno = ? " ;
		Object []  val = {pno} ;
	   return  projectDao.queryAll(sql, val);
	}
} 
