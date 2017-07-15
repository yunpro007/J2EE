package com.ql.model;

import java.util.Date;

public class Project {
	  private  Integer pno ;
	  private  String projectNum ;
	  private  String projectName ;
	  private  String location ;
	  private  String investor ;
	  private  String  bName ;
	  private  Integer bno ;
	  private  String price ;
	  private  String supervisor ;
	  private  String scale ;
	  private  String managementS ;
	  private  String managementQ ;
	  private  Date sTime ;
	  private  String permission ;
	  private  String brief ;
	  private  Integer province ;
	  private  Integer city ;
	  private  Integer district ;
	  private  Double x ;
	  private  Double y ;
	  private  Integer sType ;
	  private  Date beginTime ;
	  private  Date endTime ;
	  private  Integer isOnline ;
	  private  Integer over ;
	  private  Integer hide ;
	  private  String image ;
	  private  String video ;
	  private  String memo ;
	  private String  aq1 ;
	  private String  aq2 ;
	  private String  aq3 ;
	  
	  
	public String getAq1() {
		return aq1;
	}

	public void setAq1(String aq1) {
		this.aq1 = aq1;
	}

	public String getAq2() {
		return aq2;
	}

	public void setAq2(String aq2) {
		this.aq2 = aq2;
	}

	public String getAq3() {
		return aq3;
	}

	public void setAq3(String aq3) {
		this.aq3 = aq3;
	}
	  public  Project(){
		  
	  }
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	public Integer getPno() {
		return pno;
	}
	public void setPno(Integer pno) {
		this.pno = pno;
	}
	public String getProjectNum() {
		return projectNum;
	}
	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getInvestor() {
		return investor;
	}
	public void setInvestor(String investor) {
		this.investor = investor;
	}
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	
	public String getManagementS() {
		return managementS;
	}
	public void setManagementS(String managementS) {
		this.managementS = managementS;
	}
	public String getManagementQ() {
		return managementQ;
	}
	public void setManagementQ(String managementQ) {
		this.managementQ = managementQ;
	}
	
	
	public Date getsTime() {
		return sTime;
	}
	public void setsTime(Date sTime) {
		this.sTime = sTime;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public Integer getDistrict() {
		return district;
	}
	public void setDistrict(Integer district) {
		this.district = district;
	}
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	public Integer getsType() {
		return sType;
	}
	public void setsType(Integer sType) {
		this.sType = sType;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}
	public Integer getOver() {
		return over;
	}
	public void setOver(Integer over) {
		this.over = over;
	}
	public Integer getHide() {
		return hide;
	}
	public void setHide(Integer hide) {
		this.hide = hide;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "Project [pno=" + pno + ", projectNum=" + projectNum + ", projectName=" + projectName + ", location="
				+ location + ", investor=" + investor + ", bName=" + bName + ", bno=" + bno + ", supervisor="
				+ supervisor + ", price=" + price + ", scale=" + scale + ", managementS=" + managementS
				+ ", managementQ=" + managementQ + ", sTime=" + sTime + ", permission=" + permission + ", brief="
				+ brief + ", province=" + province + ", city=" + city + ", district=" + district + ", x=" + x + ", y="
				+ y + ", sType=" + sType + ", beginTime=" + beginTime + ", endTime=" + endTime + ", isOnline="
				+ isOnline + ", over=" + over + ", hide=" + hide + ", image=" + image + ", video=" + video + ", memo="
				+ memo + ", aq1=" + aq1 + ", aq2=" + aq2 + ", aq3=" + aq3 + "]";
	}
	
	
}
