package com.ql.model;

import java.util.Date;

public class Door {
	private int cno;
	private Date checkTime;
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	private String wName;
	private String wSex;
	private String wType;
	private String wDept;
	private String cType;
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getwName() {
		return wName;
	}
	public void setwName(String wName) {
		this.wName = wName;
	}
	public String getwSex() {
		return wSex;
	}
	public void setwSex(String wSex) {
		this.wSex = wSex;
	}
	public String getwType() {
		return wType;
	}
	public void setwType(String wType) {
		this.wType = wType;
	}
	public String getwDept() {
		return wDept;
	}
	public void setwDept(String wDept) {
		this.wDept = wDept;
	}
	public String getcType() {
		return cType;
	}
	public void setcType(String cType) {
		this.cType = cType;
	}
	@Override
	public String toString() {
		return "Door [cno=" + cno + ", checkTime=" + checkTime + ", wName=" + wName + ", wSex=" + wSex + ", wType="
				+ wType + ", wDept=" + wDept + ", cType=" + cType + "]";
	}
	
	

}
