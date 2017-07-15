package com.ql.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import cn.itcast.servlet.BaseServlet;

@WebServlet("/UserManage/AdminServlet")
public class AdminServlet  extends BaseServlet {
    
	private static final Logger log = Logger.getLogger(AdminUserServlet.class);
	
	/*
	 * µÇÂ¼
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");		
		log.debug("username:"+username+"  password:"+password);	
		if("admin".equals(username) && "admin".equals(password)){
			request.getSession().setAttribute("username", username);
			return "r:/usermanage/Main.jsp";
		}
		else{
			request.setAttribute("msg", "ÓÃ»§Ãû»òÃÜÂë´íÎó£¡");
			request.setAttribute("u", username);
			request.setAttribute("p", password);
			return "f:/usermanage/Login.jsp";
		}
	}
	
	/*
	 * ÍË³öµÇÂ¼
	 */
	public String quit(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.getSession().invalidate();
		return "r:/usermanage/Login.jsp";
	}
}
