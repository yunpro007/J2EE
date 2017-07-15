package com.ql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ql.model.User;

import cn.itcast.jdbc.TxQueryRunner;

public class AdminUserDao {
	QueryRunner qr = new TxQueryRunner();

	public List<User> loadUserMsg(int flag) {
		
		String sql = "select * from users where flag=?";
		try {
			return qr.query(sql, new BeanListHandler<User>(User.class),flag);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<User> loadUserMsg(String uName, int city, int flag) {
		
		String sql = "select * from users where uName=? and city=? and flag=?";
		try {
			return qr.query(sql, new BeanListHandler<User>(User.class),uName,city,flag);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<User> loadUserMsg(String uName, int flag) {
		
		String sql = "select * from users where uName=? and flag=?";
		try {
			return qr.query(sql, new BeanListHandler<User>(User.class),uName,flag);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<User> loadUserMsg(int city, int flag) {
		
		String sql = "select * from users where city=? and flag=?";
		try {
			return qr.query(sql, new BeanListHandler<User>(User.class),city,flag);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void del(int uno, int flag) {
		String sql = "update users set flag=? where uno=?";
		try {
			qr.update(sql,flag,uno);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public User findById(int uno) {
		String sql = "select * from users where uno=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class),uno);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void add(User users) {
		String sql = "insert into users (uid,password,uType,uName,idCard,"
				+ "tel,city,bno,memo,unitName,flag) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			Object[] params = {users.getUid(),users.getPassword(),users.getuType(),users.getuName()
					,users.getIdCard(),users.getTel(),users.getCity(),users.getBno()
					,users.getMemo(),users.getUnitName(),users.getFlag()};
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void modify(User users) {
		String sql = "update users set uid=?,password=?,uType=?,uName=?"
				+ ",idCard=?,tel=?,city=?,bno=?"
				+ ",memo=?,unitName=?,flag=? where uno=?";
		try {
			Object[] params = {users.getUid(),users.getPassword(),users.getuType(),users.getuName()
					,users.getIdCard(),users.getTel(),users.getCity(),users.getBno()
					,users.getMemo(),users.getUnitName(),users.getFlag(),users.getUno()};
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
