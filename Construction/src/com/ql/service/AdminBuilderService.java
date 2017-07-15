package com.ql.service;

import java.util.List;

import com.ql.dao.AdminBuilderDao;
import com.ql.model.Builder;
import com.ql.model.Limit;

public class AdminBuilderService {
	
	AdminBuilderDao adminBuilderDao = new AdminBuilderDao();

	public List<Builder> loadBuilderMsg(int flag) {
		return adminBuilderDao.loadBuilderMsg(flag);
	}

	public List<Builder> loaddelMsg(int flag) {
		return adminBuilderDao.loadBuilderMsg(flag);
	}

	public List<Builder> queryBuilder(String bName, int flag) {
		return adminBuilderDao.queryBuilder(bName,flag);
	}

	public void del(int bno, int fl) {
		adminBuilderDao.del(bno,fl);
	}

	public Builder findById(int bno) {
		return adminBuilderDao.findById(bno);
	}

	public void add(Builder builders) {
		adminBuilderDao.add(builders);
	}

	public void modify(Builder builders) {
		adminBuilderDao.modify(builders);
	}

	public void change(int uType, String val) {
		adminBuilderDao.change(uType,val);
	}

	public void dellimit(int uType) {
		adminBuilderDao.dellimit(uType);
	}

	public List<Limit> loadlimit(int uType) {
		return adminBuilderDao.loadlimit(uType);
	}

}
