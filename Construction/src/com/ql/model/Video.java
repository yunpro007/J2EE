package com.ql.model;

public class Video {
	private int ano;
	private String projectNum;
	public String getProjectNum() {
		return projectNum;
	}
	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}
	private String caNo;
	private String caCh;
	private String caName;
	private String caAd;
	public Video(){
		
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getCaNo() {
		return caNo;
	}
	public void setCaNo(String caNo) {
		this.caNo = caNo;
	}
	public String getCaCh() {
		return caCh;
	}
	public void setCaCh(String caCh) {
		this.caCh = caCh;
	}
	public String getCaName() {
		return caName;
	}
	public void setCaName(String caName) {
		this.caName = caName;
	}
	public String getCaAd() {
		return caAd;
	}
	public void setCaAd(String caAd) {
		this.caAd = caAd;
	}
	@Override
	public String toString() {
		return "Video [ano=" + ano + ", projectNum=" + projectNum + ", caNo=" + caNo + ", caCh=" + caCh + ", caName="
				+ caName + ", caAd=" + caAd + "]";
	}
	
	

}
