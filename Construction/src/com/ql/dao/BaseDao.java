package com.ql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ql.util.C3P0DBUtils;
import com.ql.util.ReflectionUtils;


public abstract class BaseDao<T> {
	private Connection conn=null;  
    private PreparedStatement pstm=null;  
    private ResultSet rs=null;
    /**
     * 查结果集ID专用
     * @param sql
     * @param val
     * @return
     * @throws Exception
     */
    protected String getResultSet(String sql ,  Object...val) throws Exception {
    	try{
			conn = C3P0DBUtils.getConnection() ; 
			pstm = conn.prepareStatement(sql);
			int size = val.length ;
			StringBuilder s = new StringBuilder();
			s.append("(-1");
			for(int i = 0 ; i < size ; i++){
				pstm.setObject(i+1, val[i]);
			}
			rs = pstm.executeQuery() ;
			while(rs.next()){
				ResultSetMetaData rsmd = rs.getMetaData();  
	            int count=rsmd.getColumnCount(); 
	            for(int i = 1 ; i <= count ; i++){  
	                Object colValue = rs.getObject(i); 
	                s.append(","+colValue);
	            }  
			}
        	s.append(")");
            return s.toString();
		}finally{
			   C3P0DBUtils.close(rs);
	           C3P0DBUtils.close(pstm);
	           C3P0DBUtils.close(conn);
		}
		
    }
	//查询一条记录，返回对应的对象  
    protected  T query(Class<T> type,String sql,Object...val) throws Exception{  
        T example=null;  
        if(null == val){
        	return null;
        }
        try {  
            //1、获取connection  
            conn = C3P0DBUtils.getConnection() ;  
            //2、获取PreparedStatement  
            pstm=conn.prepareStatement(sql);  
            //3、填充占位符  
            for(int i = 0; i < val.length ; i++){  
                pstm.setObject(i+1, val[i]);  
            }  
            //4、进行查询，得到ResultSet  
            rs=pstm.executeQuery();  
            //5、准备一个Map<String,Object>:(前提是结果集中要有记录)
            Map<String,Object> result = null;
            if(rs.next()){  
                result =new HashMap<String,Object>();  
                //6、得到ResultSetMetaData对象  
                ResultSetMetaData rsmd = rs.getMetaData();  
                //7、处理ResultSet,把指针向下移动一个单位  
                //8、由ResultSetMetaData对象得到结果集中有多少列  
                int count=rsmd.getColumnCount();  
                //9、由ResultSetMetaData对象得到每一列的别名，由ResultSet得到具体每一列的值  
                for(int i = 1 ; i <= count ; i++){  
                    String colName = rsmd.getColumnLabel(i);  
                    Object colValue = rs.getObject(i);  
                    //10、填充Map对象  
                   result.put(colName,colValue);  
                }  
                //11、用反射创建Class对应的对象  
                example = type.newInstance();  
                //12、遍历Map对象，用反射填充对象的属性值，  
                for(Map.Entry<String, Object> entry : result.entrySet()){  
                    String name=entry.getKey();  
                    Object value=entry.getValue();  
                    //用反射赋值  
                    ReflectionUtils.setFieldValue(example, name,  value);  
                }  
            }  
        }finally{
        	C3P0DBUtils.close(rs);
        	C3P0DBUtils.close(pstm);
        	C3P0DBUtils.close(conn);
        }  
        return example;  
          
    }  
    //查询多条记录，返回多个对应的对象  
    protected List<T>  querylist(Class<T> type,String sql,Object...val) throws Exception{  
        T example=null;  
        List<T> results = new ArrayList<T>();  
        try {  
            //1、获取connection  
            conn = C3P0DBUtils.getConnection();  
            //2、获取PreparedStatement  
            pstm=conn.prepareStatement(sql);  
            //3、填充占位符  
            if(val != null){
	            for(int i = 0 ; i < val.length;i++){  
	                pstm.setObject(i+1, val[i]);  
	            }  
            }
            //4、进行查询，得到ResultSet  
            rs=pstm.executeQuery();  
            //5、准备一个Map<String,Object>:(前提是结果集中要有记录)
            Map<String,Object> result;
            //7、处理ResultSet,把指针向下移动一个单位  
            while(rs.next()){  
                result =new HashMap<String,Object>();  
                //6、得到ResultSetMetaData对象  
                ResultSetMetaData rsmd = rs.getMetaData();  
                //8、由ResultSetMetaData对象得到结果集中有多少列  
                int count = rsmd.getColumnCount();  
                //9、由ResultSetMetaData对象得到每一列的别名，由ResultSet得到具体每一列的值  
                for(int i = 1 ; i <= count ; i++){  
                    String colName = rsmd.getColumnLabel(i);  
                    Object colValue = rs.getObject(i);  
                    //10、填充Map对象  
                    result.put(colName,colValue);  
                }  
                //11、用反射创建Class对应的对象  
                example= type.newInstance();  
                //12、遍历Map对象，用反射填充对象的属性值，  
                for(Map.Entry<String, Object> entry :  result.entrySet()){  
                    String name = entry.getKey();  
                    Object value = entry.getValue();  
                    //用反射赋值  
                    ReflectionUtils.setFieldValue(example, name,  value);  
                }
                results.add(example);  
            }
        }finally{  
           C3P0DBUtils.close(rs);
           C3P0DBUtils.close(pstm);
           C3P0DBUtils.close(conn);
        }  
        return results;  
    }  
    /**
     * 更新操作
     * @param sql
     * @param val
     * @return
     * @throws Exception
     */
    protected boolean update(String sql , Object...val) throws Exception{
		try{
			conn = C3P0DBUtils.getConnection() ;
			pstm = conn.prepareStatement(sql);
			int size = val.length ;
			for(int i = 0 ; i < size ;i++){
				pstm.setObject(i+1, val[i]);
			}
			return pstm.executeUpdate() > 0 ;
		}finally{
	           C3P0DBUtils.close(pstm);
	           C3P0DBUtils.close(conn);
		}
	}
    
    /**
     * 增加操作
     * @param sql
     * @param val
     * @return
     * @throws Exception
     */
    protected boolean insert(String sql, Object...val) throws Exception{
    	try{
    		conn = C3P0DBUtils.getConnection();
    		pstm = conn.prepareStatement(sql);
    		int size = val.length;
    		for(int i=0; i<size; i++){
    			pstm.setObject(i+1, val[i]);
    		}
    		return pstm.executeUpdate() != -1;
    	} finally{
    		C3P0DBUtils.close(pstm);
    		C3P0DBUtils.close(conn);
    	}
    }
    
    /**
     * 删除操作
     * @param sql
     * @param val
     * @return
     * @throws Exception
     */
    protected boolean delete(String sql, Object...val) throws Exception{
    	try{
    		conn = C3P0DBUtils.getConnection();
    		pstm = conn.prepareStatement(sql);
    		int size = val.length;
    		for(int i=0; i<size; i++){
    			pstm.setObject(i+1, val[i]);
    		}
    		return pstm.executeUpdate() != -1;
    	} finally{
    		C3P0DBUtils.close(pstm);
    		C3P0DBUtils.close(conn);
    	}
    }
    
    /**
     * 查询单条记录，结果为非model类型,结果以Map形式返回
     * @param sql
     * @param val
     * @return
     * @throws Exception
     */
    protected Map  querySingle(String sql , Object...val ) throws Exception{
		try{
			conn = C3P0DBUtils.getConnection() ; 
			pstm = conn.prepareStatement( sql);
			int size = val.length ;
			for(int i = 0 ; i < size ; i++){
				pstm.setObject(i+1, val[i]);
			}
			rs = pstm.executeQuery() ;
			Map ins = null ;
			if(rs.next()){
				ins = new HashMap<String,Object>();
				ResultSetMetaData rsmd = rs.getMetaData();
				int count = rsmd.getColumnCount() ;
				for(int i = 1; i <= count ;i++){
					String  colName = rsmd.getColumnLabel(i);
					Object  colValue = rs.getObject(i);
					ins.put(colName, colValue);
				}
			}
			return ins ;
		}finally{
			   C3P0DBUtils.close(rs);
	           C3P0DBUtils.close(pstm);
	           C3P0DBUtils.close(conn);
		}
		
	}
   
    /**
     * 查询多条记录，查询结果以List<Map>形式返回，
     * @param sql
     * @param val
     * @return
     * @throws Exception
     */
    protected List<Map> queryAll(String sql ,  Object...val) throws Exception {
    	if(null == val){
    		return null ;
    	}
    	try{
    		conn = C3P0DBUtils.getConnection() ;
    		pstm = conn.prepareStatement(sql);
    		int size = val.length ;
    		for(int i = 0 ; i < size ;i++){
    			pstm.setObject(i+1, val[i]);
    		}
    		rs = pstm.executeQuery() ;
    		List<Map> rows = new ArrayList<Map>();
    		Map ins = null ;
    		while(rs.next()){
    			ins = new HashMap<String,Object>();
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
    	}finally{
    		C3P0DBUtils.close(rs);
            C3P0DBUtils.close(pstm);
            C3P0DBUtils.close(conn);
    	}
    }
    
}
