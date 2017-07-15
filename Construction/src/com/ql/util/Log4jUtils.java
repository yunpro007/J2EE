package com.ql.util;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class Log4jUtils {
private static final String configFile = "log4j.xml";
	
	static{
		BasicConfigurator.configure();
	}
	
	public Log4jUtils() {
	}
	
	public static String getConfigFile(){
		return configFile;
	}
	
	public static Logger getLogger(Class clazz){
		return Logger.getLogger(clazz);
	}
	
	public static Logger getLogger(String strClass){
		return Logger.getLogger(strClass);
	}
	
	public static Logger getLogger(String strClass,LoggerFactory loggerFactory){
		return Logger.getLogger(strClass, loggerFactory);
	}
	
}
