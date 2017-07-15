package com.ql.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ql.model.Module;
import com.ql.model.User;
import com.ql.service.ModuleService;
/**
 * ��ʼ���������ڣ�����һ�ι��̽�ִ��һ�Ρ�
 * ����modelService.getModulesByUtype(user.getuType())��ȡ���ӳ�����
 * @author 
 * @version 1.0
 */
@WebServlet("/init")
public class InitServlet extends HttpServlet {
    
	private static final Logger log = Logger.getLogger(InitServlet.class) ;
    /**
     * ��ȡ�����ӣ�����ת��ʼ����ͼ
     */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		ModuleService modelService = new ModuleService() ;
		try {
			List<Module> modules = modelService.getModulesByUtype(user.getuType());
			log.debug(modules);
			if(null != modules && modules.size() > 0 ){
				request.getSession().setAttribute("modules", modules);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		request.removeAttribute("method");
		request.getRequestDispatcher("/project?method=initBMap").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
