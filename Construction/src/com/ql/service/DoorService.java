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
	 * ��ѯ��������Ϣ�������ѯʱ���ֶβ�Ϊ�գ����ѯ������Ϣ�����Ϊ�գ���Ĭ�ϲ�ѯ�������Ϣ
	 * @return
	 * @throws Exception
	 */
	public List<Door> listDoorForProject() throws Exception {
		//�ù��̵��Ž����ڱ���
		//TODO: ÿ�����̶�Ӧ��projectNum ����Ϊ��
		String DoorName = "kq"+(String) dto.get("projectNum");
		StringBuilder sql = new StringBuilder("");
		String TimeForCondition = (String) dto.get("TimeForCondition");
		String dateForCondition;
		if(TimeForCondition != null && !"".equals(TimeForCondition)){
			dateForCondition = TimeForCondition;
			System.out.println("ʱ�䲻Ϊ�գ�"+TimeForCondition);
		} else{
			//Ĭ�ϲ�ѯϵͳ����ʱ��
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			dateForCondition = sdf.format(date);
		}
		System.out.println("ʱ����"+dateForCondition);
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
	 * ��õ���������Ϣ 
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
			//Ĭ�ϲ�ѯϵͳ����ʱ��
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
					if(wType.equals("ˮ�๤"))
						doorForSum.setNsums(doorForSum.getNsums()+1);
					if(wType.equals("�߹�"))
						doorForSum.setWsums(doorForSum.getWsums()+1);
					if(wType.equals("ľ��"))
						doorForSum.setMsums(doorForSum.getMsums()+1);
					if(wType.equals("�繤"))
						doorForSum.setDsums(doorForSum.getDsums()+1);
					if(wType.equals("ʩ��Ա"))
						doorForSum.setSsums(doorForSum.getSsums()+1);
					if(wType.equals("����"))
						doorForSum.setHsums(doorForSum.getHsums()+1);
					if(wType.equals("����Ա"))
						doorForSum.setFsums(doorForSum.getFsums()+1);
					if(wType.equals("����"))
						doorForSum.setJsums(doorForSum.getJsums()+1);
					if(wType.equals("����"))
						doorForSum.setLsums(doorForSum.getLsums()+1);
					if(wType.equals("������ʻԱ"))
						doorForSum.setTsums(doorForSum.getTsums()+1);
					if(wType.equals("������ʻԱ"))
						doorForSum.setCsums(doorForSum.getCsums()+1);
					
				}
			}
		}
		
		return doorForSum;
	}
	
	
}
