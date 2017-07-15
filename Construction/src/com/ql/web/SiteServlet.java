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

import com.ql.model.Module;
import com.ql.model.Site;
import com.ql.model.User;
import com.ql.service.SiteService;
import com.ql.service.UserService;
import com.ql.util.Tools;

@WebServlet("/site")
public class SiteServlet extends HttpServlet{
	private static final Logger log = Logger.getLogger(SiteServlet.class) ;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 String methodName = request.getParameter("method");
		 log.debug("SiteServlet : "+methodName);
		 SiteService siteService = new SiteService();
		 Map dto = Tools.createDto(request);
		 siteService.setDto(dto);
		 if(null != methodName && !"".equals(methodName)){
			 try {
				 Method method = this.getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class,SiteService.class);
                 method.invoke(this,request,response,siteService);
				 
			} catch (Exception e) {
				log.error("SiteServlet: exp:"+e);
			}
				
		 }
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO 自动生成的方法存根
		this.doGet(request, response);
	}
	/**
	 * 初始化百度地图
	 * @param request
	 * @param response
	 * @param siteService
	 * @throws ServletException
	 * @throws IOException
	 */
	public void initBMap(HttpServletRequest request, HttpServletResponse response,SiteService siteService)throws ServletException, IOException{
		try {
			List<Map> sites = siteService.searchAllSites();
			request.getSession().setAttribute("sites", sites);
			request.getRequestDispatcher("/views/index.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 查询所有工地信息
	 * @param request
	 * @param response
	 * @param siteService
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchAllSites(HttpServletRequest request, HttpServletResponse response,SiteService siteService)throws ServletException, IOException{
		try {
			List<Map> sites = siteService.searchAllSites();
			request.setAttribute("sites", sites);
			request.getRequestDispatcher("/views/map.jsp").forward(request, response);
		} catch (Exception e) {
			log.error("SiteServlet: [e="+e+"]");
		}
	}
	public void showSiteMain(HttpServletRequest request, HttpServletResponse response, SiteService siteService)throws ServletException, IOException{
		try {
			log.debug(siteService.getDto());
			List<Site> sites = siteService.querySitesByProject() ;
			log.debug(sites);
			if(null != sites && sites.size()> 0){
				request.setAttribute("sites", sites);
			}
		} catch (Exception e) {
			log.error("SiteServlet  showSiteMain  [e"+e+"]");
		}
		request.getRequestDispatcher("/views/site/siteMain.jsp").forward(request, response);
	}
	public void showSingleSite(HttpServletRequest request, HttpServletResponse response , SiteService siteService){
		try {
			Site  site = siteService.querySingleSite() ;
		} catch (Exception e) {

		}
	}
}
