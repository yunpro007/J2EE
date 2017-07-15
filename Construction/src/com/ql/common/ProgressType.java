package com.ql.common;

public class ProgressType {
     public static final String [] options={"","基础","主体","封顶","二次结构","安装","装饰"} ;
     public static int index(String value){
    	 if(null != value){
    		 int length = options.length ;
    		 for(int i = 0 ; i < length ; i++){
    			 if(options[i].equals(value)){
    				 return i ;
    			 }
    		 }
    	 }
    	 return -1 ;
     }
}
