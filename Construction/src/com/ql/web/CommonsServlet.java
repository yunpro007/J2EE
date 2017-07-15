package com.ql.web;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ql.service.ProjectService;
import com.ql.util.FileTools;
import com.ql.util.Tools;

@WebServlet("/commons")
public class CommonsServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger(CommonsServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		log.debug("CommonsSerlvet doGet  method = "+methodName);
		if(null != methodName && !"".equals(methodName)){
			try {
				Method method = this.getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
				method.invoke(this, request, response);
			} catch (Exception e) {
				log.error("CommonsServlet : e:["+e+"]");
			}
		}
		
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	public void getImage(HttpServletRequest request,HttpServletResponse response) throws Exception{
		log.debug("CommonsServlet  getImage ");
		String fileName =request.getParameter("fileName");
		String filePath =request.getParameter("filePath");
		log.debug("   filePath"+filePath+"  fileName"+ fileName);
		FileTools.getImage(request, response, FileTools.getPath(filePath),fileName);
	}
	
}
