package com.ql.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ql.model.Project;
import com.ql.model.User;
import com.ql.service.ProjectService;
import com.ql.util.Tools;

@WebFilter("/views/project/map.jsp")
public class MapFilter implements Filter {
	private static final Logger log = Logger.getLogger(MapFilter.class) ;
	
	public void destroy() {
		
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request ;
		HttpServletResponse res = (HttpServletResponse)response ;
		Map dto = Tools.createDto(req);
		ProjectService projectService = new ProjectService() ;
		projectService.setDto(dto);
		User  user = (User)req.getSession().getAttribute("user");
		projectService.getDto().put("user",user) ;
		try {
		   List<Project> projects = projectService.queryAllProject();
		   if(null != projects && projects.size() > 0){
			   request.setAttribute("projects", projects);
		   }
			
		} catch (Exception e) {
			log.error(" MapFilter doFilter  [e "+e+"]");
		}
		chain.doFilter(request, response);
        
	}
	public void init(FilterConfig arg0) throws ServletException {
		
	}
		
}
