package com.ql.web;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ql.common.UserType;
import com.ql.model.Project;
import com.ql.model.User;
import com.ql.service.ProjectService;
import com.ql.service.UserService;
import com.ql.util.FileTools;
import com.ql.util.Tools;
/**
 * 
 * @author 
 * @version 1.0
 */
@WebServlet("/project")
public class ProjectServlet extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
	
    private static final  Logger log  = Logger.getLogger(ProjectService.class);
   
	public  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		log.debug(" target Method = "+methodName);
		Map dto = Tools.createDto(request);
		ProjectService projectService = new ProjectService() ;
		projectService.setDto(dto);
		User user = (User) request.getSession().getAttribute("user");
		projectService.getDto().put("user",user);
		try {
			Method method = this.getClass().getDeclaredMethod(methodName,HttpServletRequest.class,
					                                          HttpServletResponse.class,ProjectService.class);
			method.invoke(this, request, response, projectService);
		} catch (Exception e) {
			log.error("ProjectServlet : e:["+e+"]");
		}
		
	}
	public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	/**
	 * 显示工程管理的主界面
	 * @param request
	 * @param response
	 * @param projectService
	 * @throws Exception
	 */
	public void showMain(HttpServletRequest request, HttpServletResponse response,ProjectService projectService) throws Exception{
		List<Project> results = null ;
		//不可能有异常，因为如果Session中没有user时，该请求会被过滤器过滤阻止，而跳转到登录界面
		try {
			log.debug(projectService.getDto());
			if(null != projectService){
				results = projectService.queryAllProject();
				Object msgObj  = request.getAttribute("msg");
				if(null != msgObj && !"".equals((String)msgObj)){
					request.setAttribute("msg", msgObj);
				}
				else{
					request.setAttribute("msg", "");
				}
				
				log.debug("results.size = "+results.size());
				if(null != results && results.size() > 0){
					request.setAttribute("results", results);
					request.setAttribute("sum",results.size());
				}
			}
		} catch (Exception e) {
			log.error(" ProjectServlet showMain [e"+e+"]");
		}
		
		request.getRequestDispatcher("/views/project/projectMain.jsp").forward(request, response);
	}
	
	/**
	 * 初始化修改界面的数据
	 * @param request
	 * @param response
	 * @param projectService
	 * @throws Exception
	 */
	public void initModify(HttpServletRequest request,HttpServletResponse response , ProjectService projectService) throws Exception{
		Project  project = null;
		log.debug(projectService.getDto());
		try {
				project = projectService.querySite() ;
				log.debug(project);
				if(null != project){
					request.setAttribute("project", project);
			    }
		} catch (Exception e) {
			log.error("ProjectServlet  initModify  [e"+e+"]");
		}
		request.getRequestDispatcher("/views/project/modifyProject.jsp").forward(request, response);
	}
	
	public void  modifyProject(HttpServletRequest request, HttpServletResponse response,ProjectService projectService) throws Exception{
		try {
			Map dto = new HashMap<String,Object>() ;
			String desFileTem = FileTools.getPath("tem");
			String desFileDic = FileTools.getPath("projectPath");
			FileTools.upload(request, response, dto, desFileDic, desFileTem);
			dto.put("pno", request.getParameter("pno"));
			log.debug("ProgressServlet  modifyProject: dto = "+dto);
			projectService.setDto(dto);
			boolean flag = projectService.modifyProject();
			request.removeAttribute("method");
			if(!flag){
				request.setAttribute("msg", "修改失败");
				log.debug("修改失败");
			}
			else{
				request.setAttribute("msg", "修改成功");
				log.debug("修改成功");
			}
		} catch (Exception e) {
			log.error(" ProjectServlet modifyProject  [e"+e+"]");
		}
		request.getRequestDispatcher("/project?method=showMain").forward(request, response);
		
		
	}
	public void deleteProject(HttpServletRequest request,HttpServletResponse response,ProjectService projectService) throws Exception{
		boolean sign = false ;
	    log.debug("ProjectServlet:" + projectService.getDto());
	    List<Map> results = null;
		if (null != projectService) {
			sign = projectService.delete();
			log.debug("ProjectServlet:" + sign);
			if (sign) {
				request.setAttribute("msg", "删除成功");
			}
		}
		request.removeAttribute("method");
	    request.getRequestDispatcher("/project?method=showMain").forward(request, response);
	}
	public void addProject(HttpServletRequest request, HttpServletResponse response , ProjectService projectService) throws Exception{
		try {
			Map dto = new HashMap<String,Object>() ;
			String desFileTem = FileTools.getPath("tem");
			String desFileDic = FileTools.getPath("projectPath");
			FileTools.upload(request, response, dto, desFileDic, desFileTem);
			log.debug("ProgressServlet  addProject: dto = "+dto);
			projectService.setDto(dto);
			boolean flag = projectService.addProject();
			request.removeAttribute("method");
			if(!flag){
				request.setAttribute("msg", "添加失败");
				log.debug("添加失败");
			}
			else{
				request.setAttribute("msg", "添加成功");
				log.debug("添加成功");
			}
		} catch (Exception e) {
			log.error(" ProjectServlet addProject  [e"+e+"]");
		}
		request.getRequestDispatcher("/project?method=showMain").forward(request, response);
	}
	/**
	 * 初始化地图
	 * @param request
	 * @param response
	 * @param projectService
	 * @throws ServletException
	 * @throws IOException
	 */
	public void initBMap(HttpServletRequest request,
			HttpServletResponse response, ProjectService projectService)
			throws ServletException, IOException {
		try {
			String pnoSet = projectService.queryAllProjectsID();
			log.debug("ProjectServlet  initBMap pnoSet:"+pnoSet);
			request.getSession().setAttribute("pnoSet", pnoSet);
	        getUserProjectsInfos(request, response, projectService);
//			request.getRequestDispatcher("/views/index.jsp").forward(request,
//					response);
	        request.removeAttribute("method");
	        User user = (User) request.getSession().getAttribute("user");
	        if(user.getuType()==11){
	        	request.getRequestDispatcher("/views/index.jsp").forward(request, response);
	        }
	        request.getRequestDispatcher("/builder?method=getLogo").forward(request, response);
		} catch (Exception e) {
			log.error(" ProjectServlet  [e = "+e+" ]");
		}
	}

	
	public void showSiteMain(HttpServletRequest request,HttpServletResponse response, ProjectService projectService) throws Exception{
		try {
			log.debug(projectService.getDto());
			Project project = projectService.querySite() ;
			log.debug(project);
			request.setAttribute("superBackURL","showMain");
			request.setAttribute("backURL","showSiteMain");
			if(null != project){
				request.setAttribute("project", project);
			}
		} catch (Exception e) {
			log.error("SiteServlet  showSiteMain  [e"+e+"]");
		}
		User user = (User) request.getSession().getAttribute("user");
		//游客
		if(UserType.TOURIST == user.getuType()){
			request.getRequestDispatcher("/views/project/singleSiteTourist.jsp").forward(request, response);
		}	
		request.getRequestDispatcher("/views/project/singleSite.jsp").forward(request, response);
	}
	/**
	 * 获取城市或者地区下的工程
	 * @param request
	 * @param response
	 * @param projectService
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getCityOrDistrictProjects(HttpServletRequest request,
			HttpServletResponse response, ProjectService projectService)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		int userType = user.getuType();
		if(UserType.DETECTION <= userType && UserType.QUALITY_SUPERVISION >= userType){
			getDistrictProjects(request, response, projectService);
		}else{
			getCityProjects(request, response, projectService);
		}
	}
	/**
	 * 获取用户工程的信息
	 * @param request
	 * @param response
	 * @param projectService
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getUserProjectsInfos(HttpServletRequest request,
			HttpServletResponse response, ProjectService projectService)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		int userType = user.getuType();
		if(UserType.DETECTION <= userType && UserType.QUALITY_SUPERVISION >= userType){
			getUserProjectsInfos1(request, response, projectService);
		}else{
			getUserProjectsInfos2(request, response, projectService);
		}
	}
	/**
	 * 获取该城市下所有工程
	 * @param request
	 * @param response
	 * @param projectService
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getCityProjects(HttpServletRequest request,
			HttpServletResponse response, ProjectService projectService)
			throws ServletException, IOException {
		try {
			UserService userService = new UserService();
			User user = (User) request.getSession().getAttribute("user");
			String pnoSet = (String) request.getSession().getAttribute("pnoSet");
			
			log.debug("ProjectServlet getCityProjects pnoSet:"+pnoSet);
			
			projectService.getDto().put("pnoSet", pnoSet);
			List<Map> citys = new ArrayList<Map>(); // 所有城市
			int city = Integer.valueOf(request.getParameter("district"));
			if(city == -1){
				citys = projectService.searchCitys(); // 所有城市
			}else{
				Map<String,Object> m = new HashMap<String,Object>();
				m.put("city", city);
				citys.add(m);
			}
			List<Map> infos2 = new ArrayList<Map>(); // 存储二表信息
			for(Map m : citys){
				city = (Integer) m.get("city"); // 工程城市
				int sType1 = (Integer) projectService.searchSTypeByContent("市政工程").get("bno"); // 市政工程的编号
				int sType2 = (Integer) projectService.searchSTypeByContent("房屋建设").get("bno"); // 房屋建设的编号
				String districtContent;
				List<Map> projects = projectService.searchProjectsByCity(city);
				for(Map s : projects){
					int pno = (Integer) s.get("pno"); // @项目ID
					
					log.debug("ProjectServlet getCityProjects pno:"+pno);
					districtContent = (String) projectService.searchDistrictContentByID(pno).get("REGION_NAME");
					String projectName = (String) s.get("projectName"); // @工程名称
					String isOver = String.valueOf(s.get("over")); // @是否完工
					if("1".equals(isOver)){
						isOver = "完工";
					}else{
						isOver = "未完工";
					}
					String isOnline = String.valueOf(s.get("isOnline")); // @是否在线
					if("1".equals(isOnline)){
						isOnline = "在线";
					}else{
						isOnline = "未在线";
					}
					Long sType1Num = (Long) projectService.searchProjectNumByCityAndSType(city, sType1).get("a") ;//  该地区市政工程数目
					Long sType2Num = (Long) projectService.searchProjectNumByCityAndSType(city, sType2).get("a") ;//  该地区房屋建设数目
					String projectType = String.valueOf(s.get("sType")); // @工程类型
					if(sType1Num != 0){
						if(String.valueOf(sType1).equals(projectType)){
							projectType = "市政工程";
						}else{
							projectType = "房屋建设";
						}
					}else{
						if(String.valueOf(sType2).equals(projectType)){
							projectType = "房屋建设";
						}else{
							projectType = "市政工程";
						}
					}
					String belong = districtContent; // @城市
					Map<String,Object> info2 = new HashMap<String,Object>();
					info2.put("pno",pno);
					info2.put("projectName", projectName);
					info2.put("isOver", isOver);
					info2.put("isOnline", isOnline);
					info2.put("projectType", projectType);
					info2.put("belong", belong);
					infos2.add(info2);
				}
			}
			request.setAttribute("mark", true);
			request.getSession().setAttribute("infos2", infos2);
			request.getRequestDispatcher("/views/project/mapTable.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	/**
	 * 获取该地区下所有工程
	 * @param request
	 * @param response
	 * @param projectService
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getDistrictProjects(HttpServletRequest request,
			HttpServletResponse response, ProjectService projectService)
			throws ServletException, IOException {
		try {
			UserService userService = new UserService();
			User user = (User) request.getSession().getAttribute("user");
			String pnoSet = (String) request.getSession().getAttribute("pnoSet");
			projectService.getDto().put("pnoSet", pnoSet);
			int city = user.getCity(); // 用户城市编号
			List<Map> districts = new ArrayList<Map>(); // 城市下所有地区
			int district = Integer.valueOf(request.getParameter("district"));
			if(district == -1){
				districts = projectService.searchDistrictsByCity(city); // 城市下所有地区
			}else{
				Map<String,Object> m = new HashMap<String,Object>();
				m.put("district", district);
				districts.add(m);
			}
			List<Map> infos2 = new ArrayList<Map>(); // 存储二表信息
			for(Map m : districts){
				district = (Integer) m.get("district"); // 工程地区
				int sType1 = (Integer) projectService.searchSTypeByContent("市政工程").get("bno"); // 市政工程的编号
				int sType2 = (Integer) projectService.searchSTypeByContent("房屋建设").get("bno"); // 房屋建设的编号
				String districtContent = (String) projectService.searchDistrictContent(district).get("content"); // @工程地区的详细表示
				List<Map> projects = projectService.searchProjectsByDistrict(district);
				for(Map s : projects){
					int pno = (Integer) s.get("pno"); // @项目ID
					String projectName = (String) s.get("projectName"); // @工程名称
					String isOver = String.valueOf(s.get("over")); // @是否完工
					if("1".equals(isOver)){
						isOver = "完工";
					}else{
						isOver = "未完工";
					}
					String isOnline = String.valueOf(s.get("isOnline")); // @是否在线
					if("1".equals(isOnline)){
						isOnline = "在线";
					}else{
						isOnline = "未在线";
					}
					Long sType1Num = (Long) projectService.searchProjectNumByDistrictAndSType(district, sType1).get("a") ;//  该地区市政工程数目
					Long sType2Num = (Long) projectService.searchProjectNumByDistrictAndSType(district, sType2).get("a") ;//  该地区房屋建设数目
					String projectType = String.valueOf(s.get("sType")); // @工程类型
					if(sType1Num != 0){
						if(String.valueOf(sType1).equals(projectType)){
							projectType = "市政工程";
						}else{
							projectType = "房屋建设";
						}
					}else{
						if(String.valueOf(sType2).equals(projectType)){
							projectType = "房屋建设";
						}else{
							projectType = "市政工程";
						}
					}
					String belong = districtContent; // @区域
					Map<String,Object> info2 = new HashMap<String,Object>();
					info2.put("pno",pno);
					info2.put("projectName", projectName);
					info2.put("isOver", isOver);
					info2.put("isOnline", isOnline);
					info2.put("projectType", projectType);
					info2.put("belong", belong);
					infos2.add(info2);
				}
			}
			request.setAttribute("mark", true);
			request.getSession().setAttribute("infos2", infos2);
			request.getRequestDispatcher("/views/project/mapTable.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	/**
	 * 获取政府机构项目信息
	 * @param request
	 * @param response 
	 * @param projectService
	 * @throws ServletException
	 * @throws IOException  
	 */
	private void getUserProjectsInfos1(HttpServletRequest request,
			HttpServletResponse response, ProjectService projectService)
			throws ServletException, IOException {
		try {
			User user = (User) request.getSession().getAttribute("user");
			String pnoSet = (String) request.getSession().getAttribute("pnoSet");
			projectService.getDto().put("pnoSet", pnoSet);
			int city = user.getCity(); // 用户城市编号
			String cityContent = (String) projectService.searchCityContent(city).get("REGION_NAME"); // @用户城市的详细内容
			Long totalProjectsNum = 0L; // @工地总数
			Long totalSType1Num = 0L; // @市政工程总数
			Long totalSType2Num = 0L; // @房屋建设总数
			Long totalBeginNum = 0L; // @今年完工总数
			Long totalEndNum = 0L; // @今年完工总数
			List<Map> districts = projectService.searchDistrictsByCity(city); // 城市下所有地区
			List<Map> infos1 = new ArrayList<Map>(); // 存储表一信息
			Map<String,Object> cityMap = new HashMap<String,Object>(); // 存储城市信息
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String nowYear = format.format(date); // 今年的年份
			int sType1 = (Integer) projectService.searchSTypeByContent("市政工程").get("bno"); // 市政工程的编号
			int sType2 = (Integer) projectService.searchSTypeByContent("房屋建设").get("bno"); // 房屋建设的编号
			for(Map m : districts){
				int district = (Integer) m.get("district"); // 工程地区
				String districtContent = (String) projectService.searchDistrictContent(district).get("REGION_NAME"); // @工程地区的详细表示
				Long projectsNum; // @同地区工程总数
				Long sType1Num;//  @该地区市政工程数目
				Long sType2Num = (Long) projectService.searchProjectNumByDistrictAndSType(district, sType2).get("a") ;//  @该地区房屋建设数目
				projectsNum = (Long) projectService.searchProjectNumByDistrict(district).get("a");
				sType1Num = (Long) projectService.searchProjectNumByDistrictAndSType(district, sType1).get("a");
				sType2Num = (Long) projectService.searchProjectNumByDistrictAndSType(district, sType2).get("a");
				if(sType1Num == 0){
					sType1Num = projectsNum - sType2Num;
				}else{
					sType2Num = projectsNum - sType1Num;
				}
				Long beginNum ; // @今年开工数
				Long endNum ; // @今年完工数
				beginNum = (Long) projectService.searchBeginProjecstNum(district, nowYear).get("a");
				endNum = (Long) projectService.searchEndProjecstNum(district, nowYear).get("a");
				Map<String,Object> info1 = new HashMap<String,Object>();
				info1.put("district", district);
				info1.put("districtContent", districtContent);
				info1.put("projectsNum", projectsNum);
				info1.put("sType1Num", sType1Num);
				info1.put("sType2Num", sType2Num);
				info1.put("beginNum", beginNum);
				info1.put("endNum",endNum);
				infos1.add(info1);
				totalProjectsNum += projectsNum;
				totalSType1Num += sType1Num;
				totalSType2Num += sType2Num;
				totalBeginNum += beginNum;
				totalEndNum += endNum;
			}
			cityMap.put("cityContent",cityContent);
			cityMap.put("totalProjectsNum", totalProjectsNum);
			cityMap.put("totalSType1Num", totalSType1Num);
			cityMap.put("totalSType2Num", totalSType2Num);
			cityMap.put("totalBeginNum", totalBeginNum);
			cityMap.put("totalEndNum",totalEndNum);
			request.getSession().setAttribute("cityMap", cityMap);
			request.getSession().setAttribute("infos1", infos1);
		} catch (Exception e) {
			log.error("ProjectServlet: [e=" + e + "]");
		}
	}
	/**
	 * 获取非政府的机构项目信息
	 * @param request
	 * @param response
	 * @param projectService
	 */
	private void getUserProjectsInfos2(HttpServletRequest request,
			HttpServletResponse response, ProjectService projectService) {
		try {
			User user = (User) request.getSession().getAttribute("user");
			String pnoSet = (String) request.getSession().getAttribute("pnoSet");
			projectService.getDto().put("pnoSet", pnoSet);
			List<Map> citys =  projectService.searchCitys(); // 所有城市
			
			if(citys != null) log.debug("ProjectServlet  getUserProjectsInfos2  get  citys NOT NULL:"+citys.toString());
			
			Long totalProjectsNum = 0L; // @工地总数
			Long totalSType1Num = 0L; // @市政工程总数
			Long totalSType2Num = 0L; // @房屋建设总数
			Long totalBeginNum = 0L; // @今年完工总数
			Long totalEndNum = 0L; // @今年完工总数
			List<Map> infos1 = new ArrayList<Map>(); // 存储表一信息
			Map<String,Object> cityMap = new HashMap<String,Object>(); // 存储城市信息
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String nowYear = format.format(date); // 今年的年份
			int sType1 = (Integer) projectService.searchSTypeByContent("市政工程").get("bno"); // 市政工程的编号
			int sType2 = (Integer) projectService.searchSTypeByContent("房屋建设").get("bno"); // 房屋建设的编号
			for(Map m : citys){
//				Map m : citys
//				Map m = citys.get(i);
				int city = (Integer) m.get("city"); // 工程城市
				String cityContent = (String) projectService.searchDistrictContent(city).get("REGION_NAME"); // @工程地区的详细表示
				Long projectsNum = (Long) projectService.searchProjectNumByCity(city).get("a");; // @同城市工程总数
				Long sType1Num = (Long) projectService.searchProjectNumByCityAndSType(city, sType1).get("a") ;//  @该城市市政工程数目
				Long sType2Num = (Long) projectService.searchProjectNumByCityAndSType(city, sType2).get("a") ;//  @该地区房屋建设数目
				if(sType1Num == 0){
					sType1Num = projectsNum - sType2Num;
				}else{
					sType2Num = projectsNum - sType1Num;
				}
				Long beginNum ; // @今年开工数
				Long endNum ; // @今年完工数
				beginNum = (Long) projectService.searchBeginProjecstNumByCity(city, nowYear).get("a");
				endNum = (Long) projectService.searchEndProjecstNumByCity(city, nowYear).get("a");
				Map<String,Object> info1 = new HashMap<String,Object>();
				info1.put("district", city);
				info1.put("districtContent", cityContent);
				info1.put("projectsNum", projectsNum);
				info1.put("sType1Num", sType1Num);
				info1.put("sType2Num", sType2Num);
				info1.put("beginNum", beginNum);
				info1.put("endNum",endNum);
				infos1.add(info1);
				totalProjectsNum += projectsNum;
				totalSType1Num += sType1Num;
				totalSType2Num += sType2Num;
				totalBeginNum += beginNum;
				totalEndNum += endNum;
			}
			cityMap.put("cityContent","全国");
			cityMap.put("totalProjectsNum", totalProjectsNum);
			cityMap.put("totalSType1Num", totalSType1Num);
			cityMap.put("totalSType2Num", totalSType2Num);
			cityMap.put("totalBeginNum", totalBeginNum);
			cityMap.put("totalEndNum",totalEndNum);
			
			log.debug("ProjectServlet getUserProjectsInfos2: totalProjectsNum:"+totalProjectsNum+"  totalSType1Num:"+totalSType1Num+"  totalSType2Num:"+totalSType2Num+"  totalBeginNum:"+totalBeginNum+"  totalEndNum:"+totalEndNum);
			request.getSession().setAttribute("cityMap", cityMap);
			request.getSession().setAttribute("infos1", infos1);
		} catch (Exception e) {
			log.error("ProjectServlet: [e=" + e + "]");
		}
	}
	public void queryByCondition(HttpServletRequest request ,HttpServletResponse response , ProjectService  projectService){
		 try {
			
			 log.debug(projectService.getDto());
			 List<Map> results = projectService.queryByCondition();
			 if(null != results && results.size() > 0){
				 log.debug("results.size = "+results.size());
				 request.setAttribute("results", results);
				 request.setAttribute("sum",results.size());
			 }
			 request.getRequestDispatcher("/views/project/projectMain.jsp").forward(request, response);
		} catch (Exception e) {
			log.error(" ProjectServlet  queryByCondition  [e"+e+"]");
		}
    }
	/**
	 * 通过ID查找project
	 * @param request
	 * @param response
	 * @param projectService
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchProjectByID(HttpServletRequest request,
			HttpServletResponse response, ProjectService projectService)
			throws ServletException, IOException {
		try {
			
			int pno = Integer.valueOf(request.getParameter("pno"));
			Map project = projectService.searchProjectByID(pno);
			request.setAttribute("project", project);
			request.setAttribute("superBackURL","searchAllProjects");
			request.setAttribute("backURL", "searchProjectByID");
			User user =  (User)request.getSession().getAttribute("user");
			//游客
			if(UserType.TOURIST ==user.getuType()){
				request.getRequestDispatcher("/views/project/singleSiteTourist.jsp").forward(request, response); 
			}
			request.getRequestDispatcher("/views/project/singleSite.jsp").forward(request, response); // 将数据发送到临时 jsp
		} catch (Exception e) {
			log.debug("ProjectServlet searchProjectByID [e"+e+"]");
		}
	}
	 /* 通过ID查找project的位置
	 * @param request
	 * @param response
	 * @param projectService
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getProjectPositions(HttpServletRequest request,
			HttpServletResponse response, ProjectService projectService)
			throws ServletException, IOException {
		try {
			int pno = Integer.valueOf(request.getParameter("pno"));
			Map projectPosition = projectService.searchProjectPositionByID(pno);
			double x = (Double) projectPosition.get("x");
			double y = (Double) projectPosition.get("y");
			request.setAttribute("x_position", x);
			request.setAttribute("y_position", y);
			Map dto = Tools.createDto(request);
			projectService.setDto(dto);
			User  user = (User)request.getSession().getAttribute("user");
			String pnoSet = (String) request.getSession().getAttribute("pnoSet");
			projectService.getDto().put("user", user);
			projectService.getDto().put("pnoSet", pnoSet);
			List<Project> projects = projectService.queryAllProject();
			   if(null != projects && projects.size() > 0){
				   request.setAttribute("projects", projects);
			   }
			request.getRequestDispatcher("/views/project/map.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	/**
	 * 查询所有工地信息
	 * 
	 * @param request
	 * @param response
	 * @param projectService
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchAllProjects(HttpServletRequest request,
			HttpServletResponse response, ProjectService projectService)
			throws ServletException, IOException {
		try {
			request.setAttribute("isProject", "1");
			request.getRequestDispatcher("/views/index.jsp").forward(
					request, response);
		} catch (Exception e) {
			log.error("ProjectServlet: [e=" + e + "]");
		}
	}
	
	public void showProjectInfo(HttpServletRequest request ,HttpServletResponse response , ProjectService projectService ) throws Exception{
		try{
			log.debug(projectService.getDto());
			Project project = projectService.querySite() ;
			log.debug(project);
				
			if(null != project && !"".equals(project)){
				request.setAttribute("project", project);
			}
			String backURL = request.getParameter("backURL");
			String pno = request.getParameter("pno");
			if(null != backURL && !"".equals(backURL)){
				request.setAttribute("superBackURL",backURL);
			}
			else{
				request.setAttribute("backURL","showProjectInfo");
			}
			request.getRequestDispatcher("/views/project/projectInfo.jsp").forward(request,response);
			
		}catch(Exception e){
			log.error("ProjecgtServlet showProjectInfo  [ e = "+e+" ] ");
		}
	}
}
