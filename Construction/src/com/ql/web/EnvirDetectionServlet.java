package com.ql.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.ql.model.Project;
import com.ql.model.User;
import com.ql.service.EnvirDectionService;
import com.ql.service.ProjectService;
import com.ql.util.DateUtils;
import com.ql.util.Tools;


@WebServlet("/environment")
public class EnvirDetectionServlet extends HttpServlet {
	private static final  Logger log = Logger.getLogger(EnvirDetectionServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		Map dto = Tools.createDto(request);
		log.debug(" method = "+methodName);
		EnvirDectionService envirDectionService = new EnvirDectionService();
		envirDectionService.setDto(dto);
		try {
			Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class,
																EnvirDectionService.class	);
			method.invoke(this, request,response,envirDectionService);
		} catch (Exception e) {
			log.error("DetectionServlet doGet [e"+e+"]");
		}
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * ��ʼ��������ʾ���й���
	 * @param request
	 * @param response
	 * @param detectionService
	 * @throws Exception
	 */
	public void initMain(HttpServletRequest request,HttpServletResponse response,EnvirDectionService envirDectionService) throws Exception{
		//���������쳣����Ϊ���Session��û��userʱ��������ᱻ������������ֹ������ת����¼����
				List<Project> results ;
		        User  user =(User)request.getSession().getAttribute("user");
				ProjectService projectService = new ProjectService();
				projectService.setDto(new HashMap());
				try {
					projectService.getDto().put("user", user);
					log.debug(projectService.getDto());
					if(null != projectService){
						results = projectService.queryAllProject();
						log.debug("project  : "+results);
						log.debug("results.size = "+results.size());
						if(null != results && results.size() > 0){
							request.setAttribute("results", results);
							request.setAttribute("sum",results.size());
						}
					}
				} catch (Exception e) {
					log.error(" ProjectServlet showMain [e"+e+"]");
				}
				request.getRequestDispatcher("/views/environment/project.jsp").forward(request, response);
	}
	
	
	/**
	 * ��������ѯ����
	 * @param request
	 * @param response
	 * @param envirDectionService
	 * @throws Exception
	 */
	public  void queryByCondition(HttpServletRequest request,HttpServletResponse response , EnvirDectionService envirDectionService ) throws Exception{
		try {
		    User  user =(User)request.getSession().getAttribute("user");
			 ProjectService projectService = new ProjectService() ;
			 projectService.setDto(envirDectionService.getDto());
			 projectService.getDto().put("user", user);
			 log.debug(projectService.getDto());
			 
			 List<Map> results = projectService.queryByCondition();
			 if(null != results && results.size() > 0){
				 log.debug("results.size = "+results.size());
				 request.setAttribute("results", results);
				 request.setAttribute("sum",results.size());
 			 }
			 request.getRequestDispatcher("/views/environment/project.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" EnvirDetection queryByCondition  [e"+e+"]");
		}
	}
	
	/**
	 * ʵʱ��ѯ������������
	 * ͨ�����ݿ��ѯ�õ������ݣ�Ȼ��ת��Ϊjson��ʽ��ÿ�������ӵ�40��֮һȡһ������result����ͼ����õ��������������ֵ����Сֵ��ʱ�䡢�������ݡ�
	 * @param request
	 * @param response
	 * @param envirDectionService
	 * @throws Exception
	 */
	public void showSingleEnvirDetection(HttpServletRequest request,HttpServletResponse response, EnvirDectionService envirDectionService) throws Exception{
		PrintWriter out = null;
		response.setCharacterEncoding("UTF-8");  
		try {
			log.debug(envirDectionService.getDto());
			List<Map> results  = null ;
			results = envirDectionService.querySingleEnvirDetection();
			log.debug(results);
			if(null != results && results.size() > 0 ){
				StringBuilder data = new StringBuilder() ;
				StringBuilder timeStamp = new StringBuilder();
				Date  date = null;
				Float dataCurr = 0.0f ;
				Float dataMax = dataCurr =(Float)results.get(0).get("data") ;
				Float dataMin =dataCurr =(Float)results.get(0).get("data") ;
				Float dataStep = 0.0f ;
				int  yNumber = 40 ;
				int length = results.size() ;
				int step = length / 40 ;
				if(length < 400 ){
					step = 10 ;
				}
				for(int i = 1 ; i < length - 1 ; i += step){
					dataCurr =(Float)results.get(i).get("data");
					if(dataCurr > dataMax){
						dataMax = dataCurr ;
					}
					if(dataCurr < dataMin){
						dataMin = dataCurr;
					}
					data.append(dataCurr+",") ;
					date = (Date)results.get(i).get("Timestamp");
					timeStamp.append(DateUtils.format(date,"HH:mm:ss")+",");
				}
				dataStep = (float)Math.round(((dataMax-dataMin) / yNumber )*100) / 100 ;
				if(dataStep == 0.00f){
					dataStep = 0.01f;
				}
				data.append(results.get(length-1).get("data"));
				timeStamp.append(DateUtils.format((Date)results.get(length - 1).get("Timestamp"),"HH:mm:ss"));
				log.debug("data : "+data);
				log.debug("timeStamp : "+timeStamp);
				log.debug("dataMax : "+dataMax);
				log.debug("dataMin : "+dataMin);
				log.debug("dataStep : "+dataStep);
				log.debug("yNumber : "+yNumber);
				List<Map>  sets = new ArrayList<Map>();
				Map result = new HashMap<String ,Object>();
				result.put("data", data.toString());
				result.put("timeStamp",timeStamp.toString());
				result.put("dataMin", dataMin);
				result.put("dataStep",dataStep);
				result.put("yNumber", yNumber);
				Gson gson = new Gson() ;
				String json = gson.toJson(result);
				out = response.getWriter();
				out.print(json);
			}
		} catch (Exception e) {
		    log.error("EnvirDetectionServlet showSingleEnvirDetection   e =  "+e);
		}finally{
			if(null != out)
				out.close();
		}
	}
	
	//����ĳ���̵Ļ�����������
	public void showEnvirDetection(HttpServletRequest request, HttpServletResponse response , EnvirDectionService envirDectionService ) throws Exception {
			try {
				log.debug(envirDectionService.getDto());
				String backURL = request.getParameter("backURL");
				String projectName = request.getParameter("projectName");
				projectName = new String(projectName.getBytes("ISO-8859-1"),"UTF-8");
				if(null != backURL && !"".equals(backURL)){
					request.setAttribute("superBackURL", backURL);
				}
				else{
					request.setAttribute("backURL", "initMain");
				}
				request.setAttribute("pno",request.getParameter("pno"));
				request.setAttribute("aq", envirDectionService.getDto().get("aq"));
				request.setAttribute("projectName", projectName);
				request.getRequestDispatcher("/views/environment/environDetectionMain.jsp").forward(request, response);
			} catch (Exception e) {
				log.error("EnvirDetection showEnvirDetection  e = "+e);
			}
	}
	
	// ���ܻ��²�ѯ�������
	public void  showEnvirDetectionByCondition(HttpServletRequest request, HttpServletResponse response,EnvirDectionService envirDectionService) throws Exception{
		PrintWriter out = null;
		response.setCharacterEncoding("UTF-8");  
		try {
			log.debug(envirDectionService.getDto());
			List<Map> results  = null ;
			results = envirDectionService.queryEnvirDetectionByCondition();
			log.debug(results);
			String type =(String)envirDectionService.getDto().get("module");
			if(null != results && results.size() > 0 ){
				StringBuilder data = new StringBuilder() ;
				StringBuilder timeStamp = new StringBuilder();
				Date  date = null;
				Double dataCurr = 0.0 ;
				Double dataMax = dataCurr =(Double)results.get(0).get("data") ;
				Double dataMin =dataCurr =(Double)results.get(0).get("data") ;
				Double dataStep = 0.0 ;
				int  yNumber = 40 ;
				int length = results.size() ;
				Date  temDate = null;
				 if("week".equals(type)){
					 temDate = DateUtils.getStartTime(7);
				 }else{
					 temDate = DateUtils.getStartTime(30);
				 }
				 String  dateString = null;
				 String  temDateString = null;
				for(int i = 0 ; i < length  ; i++){
					 dateString =(String)results.get(i).get("Timestamp");
					 temDateString = DateUtils.format(temDate,"yyyy-MM-dd");
					 //�����м�ȱ�ٵ�����
					 while(!dateString.equals(temDateString)){
						 //���ݿ�û�еĸ�ʱ������ݡ���������
						 timeStamp.append(temDateString+",");
						 data.append(0.00+",");
						 temDate = DateUtils.getNextDay(temDate);
						 temDateString = DateUtils.format(temDate,"yyyy-MM-dd");
					 }
					dataCurr =(Double)results.get(i).get("data");
					if(dataCurr > dataMax){
						dataMax = dataCurr ;
					}
					if(dataCurr < dataMin){
						dataMin = dataCurr;
					}
					data.append(dataCurr+",") ;
					temDate = DateUtils.getNextDay(temDate);
					timeStamp.append(dateString+","); 
				}
				//����β����ȱ�ٵ�����
				 temDateString = DateUtils.format(temDate, "yyyy-MM-dd");
				 String endDateString = DateUtils.format(DateUtils.getNextDay(DateUtils.getCurrentTime()), "yyyy-MM-dd");
				 if(!temDateString.equals(endDateString)){
					 while(!temDateString.equals(endDateString)){
						 timeStamp.append(temDateString+",");
						 data.append(0.00+",");
						 temDate = DateUtils.getNextDay(temDate);
						 temDateString = DateUtils.format(temDate,"yyyy-MM-dd");
					 }
				 }
				//ȥ��β���� ,
				data.delete(data.length()-1, data.length()) ;
				timeStamp.delete(timeStamp.length()-1, timeStamp.length());
				dataStep = ((double) (Math.round(((dataMax-dataMin) / yNumber ) * 100) ) / 100);
				if(dataStep == 0.00){
					dataStep = 0.01;
				}
				log.debug("data : "+data);
				log.debug("timeStamp : "+timeStamp);
				log.debug("dataMax : "+dataMax);
				log.debug("dataMin : "+dataMin);
				log.debug("dataStep : "+dataStep);
				log.debug("yNumber : "+yNumber);
				List<Map>  sets = new ArrayList<Map>();
				Map result = new HashMap<String ,Object>();
				result.put("data", data.toString());
				result.put("timeStamp",timeStamp.toString());
				result.put("dataMin", dataMin);
				result.put("dataStep",dataStep);
				result.put("yNumber", yNumber);
				Gson gson = new Gson() ;
				String json = gson.toJson(result);
				out = response.getWriter();
				out.print(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		    log.error("EnvirDetectionServlet showEnvirDetectionByCondition   e =  "+e);
		}finally{
			if(null != out)
				out.close();
		}
	}
}
