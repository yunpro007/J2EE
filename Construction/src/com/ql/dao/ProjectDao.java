package com.ql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ql.model.Project;
import com.ql.util.DBUtils;

public class ProjectDao extends BaseDao<Project> {
	private static  final  Logger log = Logger.getLogger(EnvirDetectionDao.class);
	private Connection connForKQ=null;  
    private PreparedStatement pstmForKQ=null;  
    private ResultSet rsForKQ=null;
    private PreparedStatement pstmForAq = null;
   public List<Project> queryList(String sql, Object...val) throws Exception{
	   return this.querylist(Project.class, sql, val);
   }
   
   public Project query(String sql, Object...val) throws Exception{
	   return this.query(Project.class,sql,val);
   }
   
   public boolean  delete(String sql , Object...val) throws Exception{
	   return this.update(sql, val);
   }
   public List<Map> queryAll(String sql ,  Object...val) throws Exception {
		return super.queryAll(sql, val);
	}
   /**
    * ��������
    * �����빤����صĻ�������
    * ���������Ž����ڱ�
    * @param aq1
    * @param aq2
    * @param aq3
    * @param sql
    * @param val
    * @return
    * @throws Exception
    */
   public boolean add(String aq1, String aq2, String aq3, String sql ,Object...val) throws Exception{
	   try{
		   connForKQ = DBUtils.getConnection();
		   String projectNum = val[0].toString();
		   connForKQ.setAutoCommit(false);
		   //�����ù�����صĿ��ڱ�
		   if(null != projectNum && !("").equals(projectNum)){
			   projectNum = "`kq" + projectNum + "`";
			   String sqlForCreateKQTable = "create table "+projectNum +"("
			   		+ "cno int NOT NULL AUTO_INCREMENT comment '���',"
			   		+ "checkTime datetime DEFAULT NULL comment '����ʱ��',"
			   		+ "wName varchar(20) DEFAULT NULL comment '��������',"
			   		+ "wSex varchar(2) DEFAULT NULL comment '�����Ա�',"
			   		+ "wType varchar(20) DEFAULT NULL comment '����',"
			   		+ "wDept varchar(20) DEFAULT NULL comment '����',"
			   		+ "cType varchar(20) DEFAULT NULL comment '������״̬'"
			   		+ ",primary key (cno)"
			   		+ ");";
			   pstmForKQ = connForKQ.prepareStatement(sqlForCreateKQTable);
			   pstmForKQ.executeUpdate();
		   }
		   String[] str = {aq1,aq2,aq3};
		   for(int i=0; i<3; i++){
		   //������������
		   if(str[i] != null && !"".equals(str[i])){
			   String sqlAq = "CREATE TABLE  `"+ str[i] +
			  "`   (`ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '??',`TerminalID` varchar(20) NOT NULL,`Temperature` float(11,2) DEFAULT '0.00' COMMENT '??',`Humidity` float(11,2) unsigned DEFAULT '0.00' COMMENT '??',`PM25` float(11,2) DEFAULT '0.00' COMMENT 'PM2.5',`PM10` float(11,2) DEFAULT '0.00' COMMENT 'PM10',`SO2` float(11,2) DEFAULT '0.00' COMMENT '????',"
			  +"  `NO2` float(11,2) DEFAULT '0.00' COMMENT '????',"
			  +"  `O3` float(11,2) DEFAULT '0.00' COMMENT '??',"
			  +"  `CO` float(11,2) DEFAULT '0.00' COMMENT '????',"
			  +"  `CO2` float(11,2) DEFAULT '0.00' COMMENT '????',"
			  +"  `VOC` float(11,2) DEFAULT '0.00' COMMENT '????????',"
			  +"  `CH2O` float(11,2) DEFAULT '0.00' COMMENT '??',"
			  +"  `O2` float(11,2) DEFAULT '0.00' COMMENT '??',"
			  +"`Gas` float(11,2) DEFAULT '0.00' COMMENT '???',"
			  +"`Wind_S` float(11,2) DEFAULT '0.00' COMMENT '??',"
			  +"`Wind_D` float(11,2) DEFAULT '0.00' COMMENT '??(??)',"
			  +"`Noise` float(11,2) DEFAULT '0.00' COMMENT '??',"
			  +"`CL2` float(11,2) DEFAULT '0.00' COMMENT '??',"
			  +"`HCL` float(11,2) DEFAULT '0.00' COMMENT '???',"
			  +"`H2S` float(11,2) DEFAULT '0.00' COMMENT '???',"
			  +"`NH3` float(11,2) DEFAULT '0.00' COMMENT '??',"
			  +"`Timestamp` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '??',"
			  +"PRIMARY KEY (`ID`,`TerminalID`)"+
			  ") ENGINE=InnoDB AUTO_INCREMENT=29659 DEFAULT CHARSET=gbk;";
			  pstmForAq = connForKQ.prepareStatement(sqlAq);
			  pstmForAq.executeUpdate();
		   }
		   }
		   
		   connForKQ.commit();
		   //��ӹ���
		   this.update(sql, val);
		   return true;
	   } catch(Exception e){
		   System.out.print("**********************");
		   e.printStackTrace();
		   connForKQ.rollback();
	   } finally {
		   DBUtils.close(rsForKQ);
		   DBUtils.close(pstmForKQ);
		   DBUtils.close(connForKQ);
	   }
	   return false;
   }
	public Map  querySingle(String sql , Object...val ) throws Exception{
		return super.querySingle(sql, val);
	}
   public boolean  modify(String sql , Object...val) throws Exception{
	   return this.update(sql, val);
   }
   public String getResultSet(String sql ,  Object...val) throws Exception {
	   return super.getResultSet(sql, val);
   }
}
