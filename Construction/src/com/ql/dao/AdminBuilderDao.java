package com.ql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ql.model.Builder;
import com.ql.model.Limit;
import com.ql.model.User;

import cn.itcast.jdbc.TxQueryRunner;

public class AdminBuilderDao {
	QueryRunner qr = new TxQueryRunner();

	public List<Builder> loadBuilderMsg(int flag) {
		String sql = "select * from builder where flag=?";
		try {
			return qr.query(sql, new BeanListHandler<Builder>(Builder.class),flag);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Builder> queryBuilder(String bName, int flag) {
		String sql = "select * from builder where bName=? and flag=?";
		try {
			return qr.query(sql, new BeanListHandler<Builder>(Builder.class),bName,flag);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}

	public void del(int bno, int fl) {
		String sql = "update builder set flag=? where bno=?";
		try {
			qr.update(sql,fl,bno);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Builder findById(int bno) {
		String sql = "select * from Builder where bno=?";
		try {
			return qr.query(sql, new BeanHandler<Builder>(Builder.class),bno);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void add(Builder builders) {
		String sql = "insert into builder (bName,logo,brief,memo,flag) "
				+ "values (?,?,?,?,?)";
		try {
			Object[] params = {builders.getbName(),builders.getLogo()
					,builders.getBrief(),builders.getMemo(),builders.getFlag()};
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void modify(Builder builders) {
		String sql = "update builder set bName=?,logo=?,brief=?,memo=? where bno=?";
		try {
			Object[] params = {builders.getbName(),builders.getLogo()
					,builders.getBrief(),builders.getMemo(),builders.getBno()};
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void change(int uType, String val) {
		String sql = "insert into authority(uType,mno) values(?,?)";
		try {
			qr.update(sql,uType,val);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void dellimit(int uType) {
		String sql = "DELETE FROM authority WHERE uType=?";
		try {
			qr.update(sql,uType);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Limit> loadlimit(int uType) {
		String sql = "select * from authority where uType=?";
		try {
			return qr.query(sql, new BeanListHandler<Limit>(Limit.class),uType);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
