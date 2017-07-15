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
     * ������IDר��
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
	//��ѯһ����¼�����ض�Ӧ�Ķ���  
    protected  T query(Class<T> type,String sql,Object...val) throws Exception{  
        T example=null;  
        if(null == val){
        	return null;
        }
        try {  
            //1����ȡconnection  
            conn = C3P0DBUtils.getConnection() ;  
            //2����ȡPreparedStatement  
            pstm=conn.prepareStatement(sql);  
            //3�����ռλ��  
            for(int i = 0; i < val.length ; i++){  
                pstm.setObject(i+1, val[i]);  
            }  
            //4�����в�ѯ���õ�ResultSet  
            rs=pstm.executeQuery();  
            //5��׼��һ��Map<String,Object>:(ǰ���ǽ������Ҫ�м�¼)
            Map<String,Object> result = null;
            if(rs.next()){  
                result =new HashMap<String,Object>();  
                //6���õ�ResultSetMetaData����  
                ResultSetMetaData rsmd = rs.getMetaData();  
                //7������ResultSet,��ָ�������ƶ�һ����λ  
                //8����ResultSetMetaData����õ���������ж�����  
                int count=rsmd.getColumnCount();  
                //9����ResultSetMetaData����õ�ÿһ�еı�������ResultSet�õ�����ÿһ�е�ֵ  
                for(int i = 1 ; i <= count ; i++){  
                    String colName = rsmd.getColumnLabel(i);  
                    Object colValue = rs.getObject(i);  
                    //10�����Map����  
                   result.put(colName,colValue);  
                }  
                //11���÷��䴴��Class��Ӧ�Ķ���  
                example = type.newInstance();  
                //12������Map�����÷��������������ֵ��  
                for(Map.Entry<String, Object> entry : result.entrySet()){  
                    String name=entry.getKey();  
                    Object value=entry.getValue();  
                    //�÷��丳ֵ  
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
    //��ѯ������¼�����ض����Ӧ�Ķ���  
    protected List<T>  querylist(Class<T> type,String sql,Object...val) throws Exception{  
        T example=null;  
        List<T> results = new ArrayList<T>();  
        try {  
            //1����ȡconnection  
            conn = C3P0DBUtils.getConnection();  
            //2����ȡPreparedStatement  
            pstm=conn.prepareStatement(sql);  
            //3�����ռλ��  
            if(val != null){
	            for(int i = 0 ; i < val.length;i++){  
	                pstm.setObject(i+1, val[i]);  
	            }  
            }
            //4�����в�ѯ���õ�ResultSet  
            rs=pstm.executeQuery();  
            //5��׼��һ��Map<String,Object>:(ǰ���ǽ������Ҫ�м�¼)
            Map<String,Object> result;
            //7������ResultSet,��ָ�������ƶ�һ����λ  
            while(rs.next()){  
                result =new HashMap<String,Object>();  
                //6���õ�ResultSetMetaData����  
                ResultSetMetaData rsmd = rs.getMetaData();  
                //8����ResultSetMetaData����õ���������ж�����  
                int count = rsmd.getColumnCount();  
                //9����ResultSetMetaData����õ�ÿһ�еı�������ResultSet�õ�����ÿһ�е�ֵ  
                for(int i = 1 ; i <= count ; i++){  
                    String colName = rsmd.getColumnLabel(i);  
                    Object colValue = rs.getObject(i);  
                    //10�����Map����  
                    result.put(colName,colValue);  
                }  
                //11���÷��䴴��Class��Ӧ�Ķ���  
                example= type.newInstance();  
                //12������Map�����÷��������������ֵ��  
                for(Map.Entry<String, Object> entry :  result.entrySet()){  
                    String name = entry.getKey();  
                    Object value = entry.getValue();  
                    //�÷��丳ֵ  
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
     * ���²���
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
     * ���Ӳ���
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
     * ɾ������
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
     * ��ѯ������¼�����Ϊ��model����,�����Map��ʽ����
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
     * ��ѯ������¼����ѯ�����List<Map>��ʽ���أ�
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
