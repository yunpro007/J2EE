package com.ql.model;

import java.io.Serializable;

public class User implements Serializable {
	private int uno;
	private String uid;
	private String password;
	private Integer uType;
	private String uName;
	private String idCard;
	private String tel;
	private Integer city;
	private String memo;
	private String unitName;
	private Integer bno ;
	
	private String typeName ;
	private String cityName ;
	private int flag;
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	
	public User(){
		
	}
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getuType() {
		return uType;
	}
	public void setuType(Integer uType) {
		this.uType = uType;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "User [uno=" + uno + ", uid=" + uid + ", password=" + password + ", uType=" + uType + ", uName=" + uName
				+ ", idCard=" + idCard + ", tel=" + tel + ", city=" + city + ", memo=" + memo + ", unitName=" + unitName
				+ ", bno=" + bno + ", typeName=" + typeName + ", cityName=" + cityName + "]";
	}
	
    
}
