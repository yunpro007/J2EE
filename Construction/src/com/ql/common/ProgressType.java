package com.ql.common;

public class ProgressType {
     public static final String [] options={"","����","����","�ⶥ","���νṹ","��װ","װ��"} ;
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
