package com.ql.model;

public class Limit {
	private int ano;
	private int uType;
	private int mno;
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getuType() {
		return uType;
	}
	public void setuType(int uType) {
		this.uType = uType;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	@Override
	public String toString() {
		return "Limit [ano=" + ano + ", uType=" + uType + ", mno=" + mno + "]";
	}
}
