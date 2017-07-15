package com.ql.dao;

import java.nio.file.PathMatcher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ql.util.DBUtils;

public class EnvirDetectionDao {
	private static  final  Logger log = Logger.getLogger(EnvirDetectionDao.class);
	private Connection conn=null;  
    private PreparedStatement pstm=null;  
    private ResultSet rs=null;
	
	
	public List<Map> queryList(String sql , Object...val) throws Exception{
		try {
			conn = DBUtils.getConnection();
            pstm = conn.prepareStatement(sql) ;
            int  size = val.length ;
            for(int i = 0 ; i < size ; i ++){
            	pstm.setObject(i+1, val[i]);
            }
			rs = pstm.executeQuery() ;
			List<Map> rows = new ArrayList<Map>() ;
			Map ins = null ;
			while(rs.next()){
				ins = new HashMap<String, Object>() ;
				ResultSetMetaData rsmd = rs.getMetaData() ;
				int count = rsmd.getColumnCount() ;
    			for(int i = 1 ;i <= count ; i++){
    				String colName = rsmd.getColumnLabel(i);
    				Object colValue = rs.getObject(i);
    				ins.put(colName,colValue);
    			}
    			rows.add(ins);
			}
			return rows ;
		}
		finally{
			DBUtils.close(rs);
			DBUtils.close(pstm);
			DBUtils.close(conn);
		}
	}
	
	
}
