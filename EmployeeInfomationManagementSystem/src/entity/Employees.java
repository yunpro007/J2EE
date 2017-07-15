package entity;

import java.util.Date;

public class Employees {
	private String eid;
	private String ename;
	private String gender;
	private Date birthday;
	private String address;
	
	public Employees()
	{
		
	}
	
	public Employees(String eid, String ename, String gender, Date birthday,
			String address) {
		//super();
		this.eid = eid;
		this.ename = ename;
		this.gender = gender;
		this.birthday = birthday;
		this.address = address;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employees [eid=" + eid + ", ename=" + ename + ", gender="
				+ gender + ", birthday=" + birthday + ", address=" + address
				+ "]";
	}

	
	

}
