package com.ql.model;

import java.util.Date;

public class Site {
	private Integer sno;
	private String projectNum;
	private Integer province;
	private Integer city;
	private Integer district;
	private String siteName;
	private String location;
	private String builder;
	private Double x;
	private Double y;
	private Integer sType;
	private Date  begin;
	private Date  end;
	private Integer online;
	private Integer over;
	private Integer hide;
	private String image;
	private String video;
	private String memo;
	public Site(){
		
	}
	
	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public String getProjectNum() {
		return projectNum;
	}

	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
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

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
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

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
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
		return "Site [sno=" + sno + ", projectNum=" + projectNum + ", province=" + province + ", city=" + city
				+ ", district=" + district + ", siteName=" + siteName + ", location=" + location + ", builder="
				+ builder + ", x=" + x + ", y=" + y + ", sType=" + sType + ", begin=" + begin + ", end=" + end
				+ ", online=" + online + ", over=" + over + ", hide=" + hide + ", image=" + image + ", video=" + video
				+ ", memo=" + memo + "]";
	}

}
