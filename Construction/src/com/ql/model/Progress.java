package com.ql.model;

import java.util.Date;

public class Progress {
	private int pno;
	private String projectNum;
	private String pType;
	private String part;
	private Date pTime;
	private String memo;
	
	public Progress(){
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getProjectNum() {
		return projectNum;
	}
	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}
	public String getpType() {
		return pType;
	}
	public void setpType(String pType) {
		this.pType = pType;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public Date getpTime() {
		return pTime;
	}
	public void setpTime(Date pTime) {
		this.pTime = pTime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "Progress [pno=" + pno + ", projectNum=" + projectNum + ", pType=" + pType + ", part=" + part
				+ ", pTime=" + pTime + ", memo=" + memo + "]";
	}
	
}
