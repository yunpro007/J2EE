package com.ql.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ql.dao.DoorDao;
import com.ql.model.Door;
import com.ql.model.DoorForSum;

public class DoorService {
	private Map dto = null;
	private DoorDao doorDao = new DoorDao();
	public Map getDto() {
		return dto;
	}

	public void setDto(Map dto) {
		this.dto = dto;
	}

	
	/**
	 * 查询禁考勤信息。如果查询时间字段不为空，则查询该日信息。如果为空，则默认查询今天的信息
	 * @return
	 * @throws Exception
	 */
	public List<Door> listDoorForProject() throws Exception {
		//该工程的门禁考勤表名
		//TODO: 每个工程对应的projectNum 不能为空
		String DoorName = "kq"+(String) dto.get("projectNum");
		StringBuilder sql = new StringBuilder("");
		String TimeForCondition = (String) dto.get("TimeForCondition");
		String dateForCondition;
		if(TimeForCondition != null && !"".equals(TimeForCondition)){
			dateForCondition = TimeForCondition;
			System.out.println("时间不为空！"+TimeForCondition);
		} else{
			//默认查询系统当天时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			dateForCondition = sdf.format(date);
		}
		System.out.println("时间是"+dateForCondition);
		try{
			if(DoorName != null && !"".equals(DoorName)){ 
				sql.append("select wName,cno,checkTime,wSex,wType,wDept,cType from `")
					.append(DoorName)
					.append("` where checkTime like ?")
					.append(" order by cno");
			}
			System.out.println("============================="+sql);
			return doorDao.querylist(sql.toString(), dateForCondition+"%");
		} 
		catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 获得当天人数信息 
	 * @throws Exception 
	 */
	public DoorForSum getDoorForSum() throws Exception{
		String DoorName = "kq"+(String) dto.get("projectNum");
		StringBuilder sql = new StringBuilder("");
		String TimeForCondition = (String) dto.get("TimeForCondition");
		String dateForCondition;
		if(TimeForCondition != null && !"".equals(TimeForCondition)){
			dateForCondition = TimeForCondition;
		} else{
			//默认查询系统当天时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			dateForCondition = sdf.format(date);
		}
		if(DoorName != null && !"".equals(DoorName)){ 
			sql.append("select k.wName, k.cno,k.checkTime,k.wSex,k.wType,k.wDept,k.cType from `")
				.append(DoorName)
				.append("`  k where k.checkTime like ?")
				.append(" group by k.wName");
//				.append("` k right join (select max(cno) cno from `")
//				.append(DoorName)
//				.append("` ) c on c.cno = k.cno ")
//				.append(" where k.cno is not null and k.checkTime like ?");
		}
		System.out.println("============================="+sql);
		List<Door> list = doorDao.querylist(sql.toString(), dateForCondition+"%");
		DoorForSum doorForSum = new DoorForSum();
		Door door = null;
		if(list != null && list.size() != 0){
			doorForSum.setWorkersums(list.size());
			String wType = null;
			for(int i=0; i<list.size();i++){
				door = list.get(i);
				wType = door.getwType();
				if(wType != null){
					if(wType.equals("水泥工"))
						doorForSum.setNsums(doorForSum.getNsums()+1);
					if(wType.equals("瓦工"))
						doorForSum.setWsums(doorForSum.getWsums()+1);
					if(wType.equals("木工"))
						doorForSum.setMsums(doorForSum.getMsums()+1);
					if(wType.equals("电工"))
						doorForSum.setDsums(doorForSum.getDsums()+1);
					if(wType.equals("施工员"))
						doorForSum.setSsums(doorForSum.getSsums()+1);
					if(wType.equals("焊工"))
						doorForSum.setHsums(doorForSum.getHsums()+1);
					if(wType.equals("放线员"))
						doorForSum.setFsums(doorForSum.getFsums()+1);
					if(wType.equals("监理"))
						doorForSum.setJsums(doorForSum.getJsums()+1);
					if(wType.equals("力工"))
						doorForSum.setLsums(doorForSum.getLsums()+1);
					if(wType.equals("塔吊驾驶员"))
						doorForSum.setTsums(doorForSum.getTsums()+1);
					if(wType.equals("铲车驾驶员"))
						doorForSum.setCsums(doorForSum.getCsums()+1);
					
				}
			}
		}
		
		return doorForSum;
	}
	
	
}
