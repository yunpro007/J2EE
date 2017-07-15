package com.ql.web;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ql.model.Builder;
import com.ql.model.User;
import com.ql.service.BuilderService;
import com.ql.util.FileTools;
import com.ql.util.Tools;

@WebServlet("/builder")
public class BuilderServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger(BuilderServlet.class);

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		Map dto = Tools.createDto(request);
		BuilderService builderService = new BuilderService();
		builderService.setDto(dto);
		User user = (User) request.getSession().getAttribute("user");
		Integer bno = user.getBno();
		builderService.getDto().put("user",user);
		builderService.getDto().put("bno", bno);
		if (null != methodName && !"".equals(methodName)) {
			try {
				Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
						HttpServletResponse.class, BuilderService.class);
				method.invoke(this, request, response, builderService);
			} catch (Exception e) {
				log.debug("BuilderServlet doPost [e" + e + "]");
			}

		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	public void getLogo(HttpServletRequest request, HttpServletResponse response, BuilderService builderService) {
		try {
			Integer bno = (Integer) builderService.getDto().get("bno");
			Builder build = new Builder();
			if(bno != 0){
				build = builderService.querySingleBuilder();
//                 注释掉的是不存在这个建筑公司，或者该建筑公司没有图标时设置一个默认的图标
//				if(build.getLogo() == null || build.getLogo() == ""){
//					build.setLogo("member.png");
//				}
			}
//          else build.setLogo("member.png");
			log.debug("BuildServlet getLogo bulid ["+build.toString()+"]");
			request.getSession().setAttribute("build", build);
			request.getRequestDispatcher("/views/index.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			log.error("BuildServlet getLogo [e" + e + "]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("BuildServlet getLogo [e" + e + "]");
		} catch (Exception e) {
			log.error("BuildServlet  getLogo [e" + e + "]");
		}
	}

	// public void getLogo(HttpServletRequest request,HttpServletResponse
	// response,BuilderService builderService) throws Exception {
	// String bno = request.getParameter("bno");
	// builderService.getDto().put("bno",bno);
	// String filePath =request.getParameter("filePath");
	// log.debug("BuilderServlet getLogo bno = "+bno);
	// Builder builder = builderService.querySingleBuilder() ;
	// log.debug("BuilderServlet getLogo builder = "+builder);
	// log.debug(" filePath"+filePath+" fileName"+builder.getLogo());
	// FileTools.getImage(request, response,
	// FileTools.getPath(filePath),builder.getLogo());
	// }

}
