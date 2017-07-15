package com.ql.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import com.jspsmart.upload.Request;import com.sun.xml.internal.ws.message.stream.PayloadStreamReaderMessage;

public class Tools {
	/**
	 * ��ҳ���ȡ�����ݷ�װ��Map����
	 * @param request
	 * @return
	 */
	public static Map createDto(HttpServletRequest request)
	{
		Map<String,String[]> tem=request.getParameterMap();
        Set<Map.Entry<String, String[]>> entrySet=tem.entrySet();
        Map dto=new HashMap();
        String val[]=null;
        for(Map.Entry<String, String[]> entry:entrySet)
        {
        	val=entry.getValue();
        	if(val.length==1)
        	{
        		dto.put(entry.getKey(),val[0]);
        	}
        	else
        	{
        		dto.put(entry.getKey(), val);
        	}	
        }
        return dto;
	}
	
	/**
	 * ����form���д����ϴ��ļ���ѡ��ʱ��ʹ��smartUpload���ж�request�����ķ�װ
	 * @param request
	 * @return
	 */
	public static Map createDtoByMultipartFormat(Request request){
		Map dto = new HashMap();
		String paramName = null;
		Enumeration enumeration = request.getParameterNames();
		String[] paramValues = null;
				while(enumeration.hasMoreElements()){
					paramName = (String) enumeration.nextElement();
					paramValues = request.getParameterValues(paramName);
					if(paramValues.length == 1){
						dto.put(paramName, paramValues[0]);
					} else{
						dto.put(paramName, paramValues);
					}
				}
		return dto;
	}
}
