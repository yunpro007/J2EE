package com.ql.model;

import java.util.Date;

public class Acceptance {
	
	private int ano;
	private String projectNum;
	private String aType;
	private String detail;
	private Date aTime;
	private String result;
	private String image;
	private String memo;	
	public Acceptance() {
		super();
	}
	
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getProjectNum() {
		return projectNum;
	}
	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}
	public String getaType() {
		return aType;
	}
	public void setaType(String aType) {
		this.aType = aType;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String  detail) {
		this.detail = detail;
	}
	public Date getaTime() {
		return aTime;
	}
	public void setaTime(Date aTime) {
		this.aTime = aTime;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString(){
		return "Acceptance [ano="+ano+",projectNum="+projectNum+",aType="
				+ ""+aType+",detail="+detail+",aTime="+aTime+",result="+result
						+ ",image="+image+",memo="+memo+"]";
	}
	
}
