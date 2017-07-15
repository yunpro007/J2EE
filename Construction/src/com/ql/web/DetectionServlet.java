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
import com.ql.service.ProjectService;
import com.ql.util.Tools;


@WebServlet("/detection")
public class DetectionServlet extends HttpServlet {
	private static final  Logger log = Logger.getLogger(DetectionServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		Map dto = Tools.createDto(request);
		log.debug(" method = "+methodName);
		log.debug(" dto = "+dto);
		DetectionService  detectionService = new DetectionService() ;
		detectionService.setDto(dto);
		try {
			Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class,
																DetectionService.class			);
			method.invoke(this, request,response,detectionService);
		} catch (Exception e) {
			log.error("DetectionServlet doGet [e"+e+"]");
		}
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	public void initMain(HttpServletRequest request,HttpServletResponse response,DetectionService detectionService) throws Exception{
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
						log.debug("results.size = "+results.size());
						if(null != results && results.size() > 0){
							request.setAttribute("results", results);
							request.setAttribute("sum",results.size());
						}
					}
				} catch (Exception e) {
					log.error(" ProjectServlet showMain [e"+e+"]");
				}
				request.getRequestDispatcher("/views/detection/project.jsp").forward(request, response);
	}
}