package com.ql.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ql.model.User;
import com.ql.service.AdminUserService;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

@WebServlet("/UserManage/AdminUserServlet")
public class AdminUserServlet extends BaseServlet {
    
	private static final Logger log = Logger.getLogger(AdminUserServlet.class);
	
	private AdminUserService adminUserService = new AdminUserService();

	/*
	 * 加载未删除用户信息
	 */
	public String loadUserMsg(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int flag = 1;
		List<User> list = adminUserService.loadUserMsg(flag);
		request.setAttribute("flag", flag);
		request.setAttribute("list", list);
		return "f:/usermanage/Users.jsp";
	}
	
	/*
	 * 加载已删除用户信息
	 */
	public String loadDelUserMsg(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int flag = 0;
		List<User> list = adminUserService.loadUserMsg(flag);
		request.setAttribute("flag", flag);
		request.setAttribute("list", list);
		return "f:/usermanage/Users.jsp";
	}
	
	/*
	 * 查询用户信息
	 */
	public String queryUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String sflag = request.getParameter("flag");
		int flag = 1;
		if(null != sflag)
			flag = Integer.parseInt(sflag);
		String scity = request.getParameter("cmbCity");
		int city = Integer.parseInt(scity);
		String uName = request.getParameter("uName");
		
		List<User> list;
		if(city!=-1 && ""!=uName)
			list = adminUserService.loadUserMsg(uName,city,flag);
		else if(city==-1 && ""!=uName)
			list = adminUserService.loadUserMsg(uName,flag);
		else if(city!=-1 && ""==uName)
			list = adminUserService.loadUserMsg(city,flag);
		else
			list = adminUserService.loadUserMsg(flag);
		request.setAttribute("flag", flag);
		request.setAttribute("list", list);
		return "f:/usermanage/Users.jsp";
	}
	
	/*
	 * 删除、恢复
	 */
	public String del(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String sflag = request.getParameter("flag");
		request.setAttribute("flag", sflag);
		int flag = 1;
		if(null != sflag)
			flag = Integer.parseInt(sflag);
		int fl = flag==0 ? 1:0;
		String suno = request.getParameter("uno");
		int uno = Integer.parseInt(suno);

		adminUserService.del(uno,fl);
		if(flag == 1)
			return loadUserMsg(request,response);
		else
			return loadDelUserMsg(request,response);
	}
	
	/*
	 * 按id查询客户
	 */
	public String findUserById(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String suno = request.getParameter("uno");
		int uno = Integer.parseInt(suno);
		User users = adminUserService.findById(uno);
		request.setAttribute("user", users);
		return "f:/usermanage/AddUsers.jsp";
	}
	
	/*
	 * 添加客户
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		User users = CommonUtils.toBean(request.getParameterMap(), User.class);
		
		String cmbCity = request.getParameter("cmbCity");
		if(null == cmbCity)
			cmbCity = "-1";
		int city = Integer.parseInt(cmbCity);
		
		users.setCity(city);
		users.setFlag(1);
		log.debug("method:add cmbCity:"+cmbCity+" city:"+city);
		adminUserService.add(users);
		return loadUserMsg(request,response);
	}
	
	/*
	 * 编辑客户
	 */
	public String modify(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		User users = CommonUtils.toBean(request.getParameterMap(), User.class);
		String cmbCity = request.getParameter("cmbCity");
		int city = Integer.parseInt(cmbCity);
		users.setCity(city);
		users.setFlag(1);
		String suno = request.getParameter("uno");
		int uno = Integer.parseInt(suno);
		users.setUno(uno);
		log.debug("method:modify cmbCity:"+cmbCity+" city:"+city+" uno:"+uno);
		adminUserService.modify(users);
		return loadUserMsg(request,response);
	}
}
