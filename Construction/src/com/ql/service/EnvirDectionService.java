package com.ql.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ql.dao.EnvirDetectionDao;
import com.ql.util.DateUtils;

public class EnvirDectionService {
	private static final Logger log = Logger.getLogger(EnvirDectionService.class);
	private Map dto = null;

	private EnvirDetectionDao envirDetectionDao = new EnvirDetectionDao();

	public EnvirDetectionDao getEnvirDetectionDao() {
		return envirDetectionDao;
	}

	public void setEnvirDetectionDao(EnvirDetectionDao envirDetectionDao) {
		this.envirDetectionDao = envirDetectionDao;
	}

	public Map getDto() {
		return dto;
	}

	public void setDto(Map dto) {
		this.dto = dto;
	}

	// 初始化时调用该函数，即实时查询
	public List<Map> querySingleEnvirDetection() throws Exception {
		String tableName = (String) this.dto.get("aq");
		if (null == tableName || "".equals(tableName)) {
			return null;
		}
		StringBuilder sql = new StringBuilder().append("	SELECT   u.TerminalID , u.Timestamp ")
				.append(", u." + this.dto.get("type") + "  data").append("	FROM     umini." + tableName + "  u")
				.append("	WHERE    u.Timestamp between ? and ? ").append("   order   by ID  ASC");
		 Object [] val = {DateUtils.getTodayStartTime(), DateUtils.getCurrentTime()};
		// Object[] val = { "2017-03-01 00:00:00", "2017-03-01 23:59:43" };
		log.debug(sql);
		log.debug(Arrays.asList(val));
		return envirDetectionDao.queryList(sql.toString(), val);
	}

	public List<Map> queryEnvirDetectionByCondition() throws Exception {
		String tableName = (String) this.dto.get("aq");
		if (null == tableName || "".equals(tableName)) {
			return null;
		}
		// 获得此次查询时按月还是按周
		String type = (String) this.dto.get("module");
		StringBuilder sql = new StringBuilder()
				.append("	select  u.TerminalID , DATE_FORMAT(u.Timestamp,'%Y-%m-%d') Timestamp   ")
				.append("  ,  AVG( u." + this.dto.get("type") + ")  data")
				.append("	FROM     umini." + tableName + "  u").append("   where   u.Timestamp between ? and ? ")
				.append("   group by  DATE_FORMAT(u.Timestamp,'%Y-%m-%d')");
		 List<Date> val = new ArrayList<Date>() ;
		if ("week".equals(type)) {
			 val.add(DateUtils.getStartTime(7));
//			Object[] val1 = { "2016-07-07 00:00:00", "2016-07-15 23:59:43" };
//			return envirDetectionDao.queryList(sql.toString(), val1);

		} else {
			 val.add(DateUtils.getStartTime(30));
//			Object[] val1 = { "2016-07-07 00:00:00", "2016-08-07 23:59:43" };
//			return envirDetectionDao.queryList(sql.toString(), val1);
		}
		 val.add(DateUtils.getNextDay(DateUtils.getCurrentTime()));
		//log.debug(sql);
		// log.debug(val);
		// log.debug(Arrays.asList(val1));
		 return envirDetectionDao.queryList(sql.toString(), val.toArray());

	}
}
