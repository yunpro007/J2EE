package com.ql.web;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ql.model.Project;
import com.ql.model.User;
import com.ql.service.DetectionService;
import com.ql.service.PeopleService;
import com.ql.service.ProjectService;
import com.ql.util.Tools;


@WebServlet("/people")
public class PeopleServlet extends HttpServlet {
	private static final  Logger log = Logger.getLogger(PeopleServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		Map  dto = Tools.createDto(request);
		
		PeopleService peopleService = new PeopleService() ;
		peopleService.setDto(dto);
		
		try {
			Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class,
															 PeopleService.class );
			method.invoke(this, request,response ,peopleService);
		} catch (Exception e) {
			log.error("DetectionServlet doGet [e"+e+"]");
		}
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	public void initMain(HttpServletRequest request,HttpServletResponse response , PeopleService peopleService) throws Exception{
		//不可能有异常，因为如果Session中没有user时，该请求会被过滤器过滤阻止，而跳转到登录界面
				List<Project> results ;
		       User  user =(User)request.getSession().getAttribute("user");
				ProjectService projectService = new ProjectService();
				projectService.setDto(new HashMap());
				try {
					projectService.getDto().put("user", user);
					log.debug(projectService.getDto());
					if(null != projectService){
						results = projectService.queryAllProject();
						log.debug("results.size = "+results.size());
						if(null != results && results.size() > 0){
							request.setAttribute("results", results);
							request.setAttribute("sum",results.size());
						}
					}
				} catch (Exception e) {
					log.error(" PeopleServlet initMain [e"+e+"]");
				}
				request.getRequestDispatcher("/views/people/project.jsp").forward(request, response);
	}
	
	public void showSiteMain(HttpServletRequest request,HttpServletResponse response,PeopleService peopleService)throws Exception{
		try {
			String backURL = request.getParameter("backURL");
			String projectName = request.getParameter("projectName");
			//String people = request.getParameter("people");
			projectName = new String(projectName.getBytes("ISO-8859-1"),"UTF-8");
			if(null != backURL && !"".equals(backURL)){
				request.setAttribute("superBackURL", backURL);
			}
			else{
				request.setAttribute("backURL", "initMain");
			}
			request.setAttribute("pno",request.getParameter("pno"));
			request.setAttribute("projectName", projectName);
			//request.setAttribute("people", people);
			request.getRequestDispatcher("/views/people/peopleMain.jsp").forward(request,response);
		}catch(Exception e){
			log.error("People showSiteMain e="+e);
		}
	}
}
