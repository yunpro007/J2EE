package com.ql.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;




public class DateUtils {
	private static  SimpleDateFormat  sdf = null;
	
	public static String format (Date date , String format) throws Exception{
		sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	public static void main(String[] args) {
		Date date = new Date() ;
		try{
			System.out.println(getStartTime(6));
		}
		catch( Exception e ){
			e.printStackTrace(); 
		}
	}

	public static  Date getTodayStartTime() throws Exception{
		Calendar c1 = new GregorianCalendar();
	    c1.set(Calendar.HOUR_OF_DAY, 0);
	    c1.set(Calendar.MINUTE, 0);
	    c1.set(Calendar.SECOND, 0);
		return (Date)c1.getTime().clone() ;
	}
	
	public static  Date  getStartTime(int  subDays) throws Exception{
		Date dateBegin = getCurrentTime() ;
	    Calendar calendar = Calendar.getInstance() ;
	    calendar.setTime(dateBegin);
	    calendar.set(Calendar.DATE,calendar.get(Calendar.DATE) - subDays );
	    String  dateString = format(calendar.getTime(),"yyyy-MM-dd");
	    dateBegin = sdf.parse(dateString);
	    return dateBegin;
	}
	
	public static Date  getTodayEndTime(){
		  Calendar c2 = new GregorianCalendar();
		    c2.set(Calendar.HOUR_OF_DAY, 23);
		    c2.set(Calendar.MINUTE, 59);
		    c2.set(Calendar.SECOND, 59);
		  return (Date)c2.getTime().clone() ;
	}
	
	public static  Date getNextDay(Date today) throws Exception{
	    Calendar calendar = Calendar.getInstance() ;
	    calendar.setTime(today);
	    calendar.set(Calendar.DATE,calendar.get(Calendar.DATE) + 1 );
	    String  dateString = format(calendar.getTime(),"yyyy-MM-dd");
	    return sdf.parse(dateString);
	}
	
	public  static  Date  parseStringToDate(String dateString) throws Exception{
		return sdf.parse(dateString);
	}
	
	public static  Date getCurrentTime() throws Exception{
		/*SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str="2016-10-01 23:59:43";
		Date d= sim.parse(str);	*/
		return new Date() ;
	}
}
