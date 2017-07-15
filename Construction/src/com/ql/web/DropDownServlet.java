package com.ql.web;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
import com.ql.service.AcceptanceService;
import com.ql.service.BuilderService;
import com.ql.service.DropDownService;
import com.ql.service.ProgressService;
import com.ql.service.ProjectService;
import com.ql.service.UserService;

@WebServlet("/dropDown")
public class DropDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(DropDownServlet.class);
	
	public  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		log.debug("DropDownServlet doGet  method = "+methodName);
		try {
			Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("DropDownServlet doGet   [e"+e+"]");
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	public void  getPalces(HttpServletRequest request, HttpServletResponse response) {
		String parentId = request.getParameter("parentId");
		if(parentId == null || parentId==""){
			parentId = "0";
		}
		List<Map> results = new ArrayList() ;
		 String json  = null;
		try {
			DropDownService dropDownService = new DropDownService();
			results = dropDownService.queryList(Integer.parseInt(parentId)) ;
			Gson gson = new Gson();
	        json = gson.toJson(results);
	        log.debug(json);
	        response.setCharacterEncoding("UTF-8");
			response.getWriter().print(json);
		} catch (Exception e) {
			 log.debug(" DropDownServlet doGet  [e"+e+"]");
		}
	}
	
	public void getBuilder(HttpServletRequest request,HttpServletResponse response){
		List<Map> results = new ArrayList() ;
	    String json  = null;
		try {
			BuilderService builderService = new BuilderService() ;
			results = builderService.queryDropDown() ;
			Gson gson = new Gson();
	        json = gson.toJson(results);
	        log.debug(json);
	        response.setCharacterEncoding("UTF-8");
			response.getWriter().print(json);
		} catch (Exception e) {
			 log.error(" DropDownServlet doGet  [e"+e+"]");
		}
	}
	
	public void getManagementQ(HttpServletRequest request,HttpServletResponse response){
		List<Map> result = new ArrayList();
		String json = null;
		try{
			DropDownService dropDownService = new DropDownService();
			result = dropDownService.getManagementQ();
			Gson gson = new Gson();
			json = gson.toJson(result);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(json);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
    
	public void  getProgressType(HttpServletRequest request,HttpServletResponse response){
		List<Map> results = new ArrayList() ;
		String  json = null ;
		try {
			ProgressService progressService = new ProgressService() ;
			results = progressService.getProgressType() ;
			Gson gson = new Gson();
	        json = gson.toJson(results);
	        log.debug(json);
	        response.setCharacterEncoding("UTF-8");
			response.getWriter().print(json);
		} catch (Exception e) {
			log.error(" DropDownServlet getProgressType  [e"+"]");
		}
		
	}
	
	public void getAcceptanceType(HttpServletRequest request,HttpServletResponse response){
		List<Map> results = new ArrayList() ;
		String  pno = request.getParameter("pno");
		if(null == pno || "".equals(pno)){
			pno = "0";
		}
		String json = null ;
		try {
			AcceptanceService acceptanceService = new AcceptanceService();
           results = acceptanceService.getAcceptanceType(Integer.parseInt(pno)) ;
			Gson gson = new Gson();
	        json = gson.toJson(results);
	        log.debug(json);
	        response.setCharacterEncoding("UTF-8");
			response.getWriter().print(json);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" DropDownServlet getProgressType  [e"+"]");
		}
	}
	
	public void getUserType(HttpServletRequest request,HttpServletResponse response ) throws Exception{
		List<Map> results = new ArrayList();
		String json = null;
		try {
			UserService userService = new UserService() ;
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
    //获取环境监测设备号
	public void  getAq(HttpServletRequest request ,HttpServletResponse response) throws Exception{
		List<Map> results = new  ArrayList<Map>();
		List<Map> temMaps = null ;
		String pno = request.getParameter("pno") ;
		System.out.println("&&&"+pno);
		if(null == pno || "".equals(pno)){
			
		}
		String  json = null ;
		try {
			ProjectService projectService = new ProjectService() ;
			temMaps = projectService.getAq(Integer.parseInt(pno));
			log.debug("AQ : "+temMaps);
			Map ins  = null;
			if(null != temMaps && temMaps.size() > 0){
				    int length = temMaps.size() ;
					Object aq1Obj = (String)temMaps.get(length-1 ).get("aq1");
					Object aq2Obj = (String)temMaps.get(length-1 ).get("aq2");
					Object aq3Obj = (String)temMaps.get(length-1 ).get("aq3");
					if(null != aq1Obj){
						String aq1 = (String)aq1Obj ;
						if(!"".equals(aq1)){
							ins = new HashMap<String ,Object>();
							ins.put("aq",aq1);
							ins.put("aqV", aq1);
							results.add(ins);
						}
					}
					if(null != aq2Obj){
						String aq2 = (String)aq2Obj ;
						if(!"".equals(aq2)){
							ins = new HashMap<String ,Object>();
							ins.put("aq",aq2);
							ins.put("aqV", aq2);
							results.add(ins);
						}
					}
					if(null != aq3Obj){
						String aq3 = (String)aq3Obj ;
						if(!"".equals(aq3)){
							ins = new HashMap<String ,Object>();
							ins.put("aq",aq3);
							ins.put("aqV", aq3);
							results.add(ins);
						}
					}
				 Gson gson = new Gson() ;
				 json = gson.toJson(results);
				 log.debug(json);
				 response.setCharacterEncoding("UTF-8");
				 response.getWriter().print(json);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
