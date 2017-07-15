package com.ql.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ql.common.BaseInfoType;
import com.ql.dao.UserDao;
import com.ql.model.Module;
import com.ql.model.User;

public class UserService {
	private static final Logger log = Logger.getLogger(UserService.class);
	private UserDao userDao = new UserDao();
	private Map dto = null;
	public Map getDto() {
		return dto;
	}
	public void setDto(Map dto) {
		this.dto = dto;
	}
	public UserService() {} 
	/**
	 * 检查用户登录
	 * @return
	 * @throws Exception
	 */
	public User checkLogin() throws Exception{
		 User result = null;
    	 StringBuilder sql = new StringBuilder()
    			 .append(" select uno , uid , password , uType , uName , idCard , tel  , city , memo ,unitName , bno ")
    			 .append(" from users  where uid = ? and  password = ? ");
    	 Object username = this.dto.get("username");
    	 Object password = this.dto.get("password");
    	 if(null != username && null != password){
    		 Object[] val ={username,password};
    		 log.debug(sql);
    		 log.debug(" parameter: username = "+username+"   password =" +password);
             result = userDao.query(sql.toString(), val);
    	 }
    	 return result ;
     }
	 public  User query(int uid ) throws Exception{
		 StringBuilder sql = new StringBuilder()
    			 .append(" select uno , uid , password , uType , uName , idCard , tel , city , memo,unitName  , bno")
    			 .append(" from users where uid = ?");
		 Object [] val = {uid};
		 log.debug(sql);
		 log.debug("parameter: uid = "+uid);
		 return userDao.query(sql.toString(), val);
	 }
	  public List<User> queryList() throws Exception{
		  StringBuilder sql = new StringBuilder()
	    			 .append("  select uno , uid , password , uType , uName , idCard , tel , city , memo , unitName , bno")
	    			 .append("  from users ");
		  List<User> results = null ;
		  log.debug(sql);
		  log.debug("parameter: ");
		  results = userDao.querylist(sql.toString());
		  log.debug(results.size());
		  return results;
	  }
	  /**
	   * 查询所有的用户
	   * @return
	   * @throws Exception
	   */
	  public List<User> queryAllUsers() throws Exception{
		  StringBuilder sql = new StringBuilder() 
				  .append("	select uno , uid , password , b.content typeName , uName , idCard , tel ,")
				  .append("		   c.REGION_NAME cityName , memo , unitName, a.bno	")
				  .append(" from  construction.users a   join construction.baseinfo b   on a.uType = b.pno and b.bType =  "+BaseInfoType.USERTYPE)
				  .append(" left JOIN construction.region c  on  a.city = c.REGION_ID ");
		  List<User> results = null ;
		  log.debug(sql);
		  results = userDao.querylist(sql.toString());
		  log.debug(results.size());
		  return results ;
	  }
	  public List<User> getUserType() throws Exception{
		  String  sql = "" ;
		  return null;
	  }
}
