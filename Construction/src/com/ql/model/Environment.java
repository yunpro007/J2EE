package com.ql.model;

import java.util.Date;

public class Environment {
	private Integer ID;
	private String TerminalID;
	private Float Temperature;
	private Float Humidity;
	private Float PM25;
	private Float PM10;
	private Float SO2;
	private Float NO2;
	private Float O3;
	private Float CO;
	private Float CO2;
	private Float VOC;
	private Float CH2O;
	private Float O2;
	private Float Gas;
	private Float Wind_S;
	private Float Wind_D;
	private Float Noise;
	private Float CL2;
	private Float HCL;
	private Float H2S;
	private Float NH3;
	private Date Timestamp;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getTerminalID() {
		return TerminalID;
	}

	public void setTerminalID(String terminalID) {
		TerminalID = terminalID;
	}

	public Float getTemperature() {
		return Temperature;
	}

	public void setTemperature(Float temperature) {
		Temperature = temperature;
	}

	public Float getHumidity() {
		return Humidity;
	}

	public void setHumidity(Float humidity) {
		Humidity = humidity;
	}

	public Float getPM25() {
		return PM25;
	}

	public void setPM25(Float pM25) {
		PM25 = pM25;
	}

	public Float getPM10() {
		return PM10;
	}

	public void setPM10(Float pM10) {
		PM10 = pM10;
	}

	public Float getSO2() {
		return SO2;
	}

	public void setSO2(Float sO2) {
		SO2 = sO2;
	}

	public Float getNO2() {
		return NO2;
	}

	public void setNO2(Float nO2) {
		NO2 = nO2;
	}

	public Float getO3() {
		return O3;
	}

	public void setO3(Float o3) {
		O3 = o3;
	}

	public Float getCO() {
		return CO;
	}

	public void setCO(Float cO) {
		CO = cO;
	}

	public Float getCO2() {
		return CO2;
	}

	public void setCO2(Float cO2) {
		CO2 = cO2;
	}

	public Float getVOC() {
		return VOC;
	}

	public void setVOC(Float vOC) {
		VOC = vOC;
	}

	public Float getCH2O() {
		return CH2O;
	}

	public void setCH2O(Float cH2O) {
		CH2O = cH2O;
	}

	public Float getO2() {
		return O2;
	}

	public void setO2(Float o2) {
		O2 = o2;
	}

	public Float getGas() {
		return Gas;
	}

	public void setGas(Float gas) {
		Gas = gas;
	}

	public Float getWind_S() {
		return Wind_S;
	}

	public void setWind_S(Float wind_S) {
		Wind_S = wind_S;
	}

	public Float getWind_D() {
		return Wind_D;
	}

	public void setWind_D(Float wind_D) {
		Wind_D = wind_D;
	}

	public Float getNoise() {
		return Noise;
	}

	public void setNoise(Float noise) {
		Noise = noise;
	}

	public Float getCL2() {
		return CL2;
	}

	public void setCL2(Float cL2) {
		CL2 = cL2;
	}

	public Float getHCL() {
		return HCL;
	}

	public void setHCL(Float hCL) {
		HCL = hCL;
	}

	public Float getH2S() {
		return H2S;
	}

	public void setH2S(Float h2s) {
		H2S = h2s;
	}

	public Float getNH3() {
		return NH3;
	}

	public void setNH3(Float nH3) {
		NH3 = nH3;
	}

	public Date getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(Date timestamp) {
		Timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Environment [ID=" + ID + ", TerminalID=" + TerminalID + ", Temperature=" + Temperature + ", Humidity="
				+ Humidity + ", PM25=" + PM25 + ", PM10=" + PM10 + ", SO2=" + SO2 + ", NO2=" + NO2 + ", O3=" + O3
				+ ", CO=" + CO + ", CO2=" + CO2 + ", VOC=" + VOC + ", CH2O=" + CH2O + ", O2=" + O2 + ", Gas=" + Gas
				+ ", Wind_S=" + Wind_S + ", Wind_D=" + Wind_D + ", Noise=" + Noise + ", CL2=" + CL2 + ", HCL=" + HCL
				+ ", H2S=" + H2S + ", NH3=" + NH3 + ", Timestamp=" + Timestamp + "]";
	}

	
	
}
