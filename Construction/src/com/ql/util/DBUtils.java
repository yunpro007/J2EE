package com.ql.util;
import java.sql.*; 
import java.util.ResourceBundle;
public class DBUtils {
	
	private static String driver=null ;
	private static String url = null ;
	private static String username= null ;
	private static String password= null ;
	private static ResourceBundle rb = ResourceBundle.getBundle("uminiDB");
	
	static{
		driver = rb.getString("DRIVER");
		url = rb.getString("URL");
		username = rb.getString("USERNAME");
		password = rb.getString("PASSWORD");
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 返回从当前线程中获取连接,或者从当前线程中创建新的连接,并与当前线程绑定
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception{
		     
     	Connection conn  = DriverManager.getConnection(url, username, password);		
	    return conn ;
	}
	/**
	 * 关闭连接,
	 */
	public static void close(Connection conn ){
		try {
			if(conn != null && !conn.isClosed()){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(PreparedStatement pstm){
		try{
			if(pstm != null){
				pstm.close() ;
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
	}
	
	/**
	 * 销毁结果集
	 * @param rs
	 */
	public static void close(ResultSet rs){
		try{
			if(rs != null ){
				rs.close();
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}
		
	}
	public static void main(String[] args) {
		  try {
			System.out.println(DBUtils.getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
