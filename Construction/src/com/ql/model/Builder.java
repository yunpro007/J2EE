package com.ql.model;

public class Builder {
	 private Integer   bno ;  
	 private String   bName ;  
	 private String   logo ;  
	 private String   brief ;  
	 private String   memo  ;
	 private int flag;
	 public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public Builder() {
		super();
	 }
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "Builder [bno=" + bno + ", bName=" + bName + ", logo=" + logo + ", brief=" + brief + ", memo=" + memo
				+ "]";
	}
	
	 
}
