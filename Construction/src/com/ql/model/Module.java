package com.ql.model;

import java.io.Serializable;

/**
 * х╗оч
 * @author Administrator
 *
 */
public class Module implements Serializable {
	private int mno;
	private String mname;
	private String hyperlink;
	
	public Module() {
	}
	
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getHyperlink() {
		return hyperlink;
	}
	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hyperlink == null) ? 0 : hyperlink.hashCode());
		result = prime * result + ((mname == null) ? 0 : mname.hashCode());
		result = prime * result + mno;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Module other = (Module) obj;
		if (hyperlink == null) {
			if (other.hyperlink != null)
				return false;
		} else if (!hyperlink.equals(other.hyperlink))
			return false;
		if (mname == null) {
			if (other.mname != null)
				return false;
		} else if (!mname.equals(other.mname))
			return false;
		if (mno != other.mno)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Model [mno=" + mno + ", mname=" + mname + ", hyperlink=" + hyperlink + "]";
	}
	
	
	
}
