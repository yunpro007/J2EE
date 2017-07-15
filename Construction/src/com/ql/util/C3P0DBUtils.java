package com.ql.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0DBUtils {
	private static ComboPooledDataSource ds = new ComboPooledDataSource(); 
	public static Connection getConnection() { 
	    try { 
	        return ds.getConnection(); 
	    } catch (SQLException e) { 
	        throw new RuntimeException(e); 
	    } 
	} 
	
	/**
	 * 关闭连接,
	 */
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(PreparedStatement pstm) {
		try {
			if (pstm != null) {
				pstm.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 销毁结果集
	 * 
	 * @param rs
	 */
	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}
