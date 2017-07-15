package com.ql.web;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ql.common.UserType;
import com.ql.model.User;
import com.ql.service.UserService;
import com.ql.util.Tools;
/**
 * 
 * @author 
 * @version 1.0
 *
 * user管理
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private  static final  Logger log = Logger.getLogger(UserServlet.class);
	@Override
	public  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String methodName = request.getParameter("method");
		 UserService userService = new UserService();
		 Map dto = Tools.createDto(request);
		 userService.setDto(dto);
		 if(null != methodName && !"".equals(methodName)){
				try {
					 Method method = this.getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class,UserService.class);
	                 method.invoke(this,request,response,userService);
				} catch (Exception e) {
					log.error("UserServlet: exp:"+e);
				}
		 }
	}
	public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			this.doGet(request, response);
	}
	/**
	 * 登陆检查
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkLogin(HttpServletRequest request, HttpServletResponse response,UserService  userService)throws ServletException, IOException{
		User  user = null;
		String path = request.getContextPath();
		try {
			user = userService.checkLogin();
			log.debug("用户信息"+user);
			if(null != user){
				request.getSession().setAttribute("user", user);
				if(UserType.SUPER_USER == user.getuType()){
					List<User> users = userService.queryAllUsers();
					log.debug(users);
					if(null != users && users.size() > 0 ){
						request.getSession().setAttribute("users", users);
						request.getSession().setAttribute("sum",users.size());
					}
					request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
					return ;
				}
				else{
					request.getRequestDispatcher("/init").forward(request, response);
				}
			    return ;
			}
		} catch (Exception e) {
		   log.error("UserServlet.checkLogin: e:"+e);
		}
		request.setAttribute("msg","用户名或密码错误");
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
	}
	
	/**
	 * 注册
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void register(HttpServletRequest request, HttpServletResponse response,UserService userService) throws ServletException, IOException{
		//TODO  注册  用户    
		
	}
	/**
	 * 注销
	 * @param request
	 * @param response
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response,UserService userService ) throws ServletException, IOException{
		HttpSession session  = request.getSession(false);
		String path = request.getContextPath();
		if(null == session){
			response.sendRedirect(path+"/views/login.jsp");
			return ;
		}
		//获取Session,并清空Session中的所有内容。
		request.getSession().invalidate();
		response.sendRedirect(path+"/views/login.jsp");
	}
	
	public void touristLogin(HttpServletRequest request, HttpServletResponse response,UserService userService ){
		User user = new User();
		user.setuType(11);
		request.getSession().setAttribute("user", user);
		try {
			request.getRequestDispatcher("/init").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			log.error("UserServlet  method=touristLogin request:ServletException ["+e+"]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("UserServlet  method=touristLogin request:IOException ["+e+"]");
		}
	}
}
