package com.ql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ql.model.Door;
import com.ql.util.DBUtils;

public class DoorDao{
	private static  final  Logger log = Logger.getLogger(EnvirDetectionDao.class);
	private Connection connForKQ=null;  
    private PreparedStatement pstmForKQ=null;  
    private ResultSet rsForKQ=null;
	public List<Door> querylist(String sql, Object... val) throws Exception {
		connForKQ = DBUtils.getConnection();
		pstmForKQ = connForKQ.prepareStatement(sql);
		for(int i=0; i<val.length; i++){
			pstmForKQ.setObject(i+1,  val[i]);
		}
		rsForKQ = pstmForKQ.executeQuery();
		List<Door> list = new ArrayList<Door>();
		while(rsForKQ.next()){
			Door door = new Door();
			door.setwName(rsForKQ.getString(1));
			door.setCno(rsForKQ.getInt(2));
			door.setCheckTime(rsForKQ.getTimestamp(3));
			door.setwSex(rsForKQ.getString(4));
			door.setwType(rsForKQ.getString(5));
			door.setwDept(rsForKQ.getString(6));
			door.setcType(rsForKQ.getString(7));
			list.add(door);
		}
		return list;
	}
}
