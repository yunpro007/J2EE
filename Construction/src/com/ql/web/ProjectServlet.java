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
	 * ��ʾ���̹����������
	 * @param request
	 * @param response
	 * @param projectService
	 * @throws Exception
	 */
	public void showMain(HttpServletRequest request, HttpServletResponse response,ProjectService projectService) throws Exception{
		List<Project> results = null ;
		//���������쳣����Ϊ���Session��û��userʱ��������ᱻ������������ֹ������ת����¼����
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
	 * ��ʼ���޸Ľ��������
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
				request.setAttribute("msg", "�޸�ʧ��");
				log.debug("�޸�ʧ��");
			}
			else{
				request.setAttribute("msg", "�޸ĳɹ�");
				log.debug("�޸ĳɹ�");
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
				request.setAttribute("msg", "ɾ���ɹ�");
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
				request.setAttribute("msg", "���ʧ��");
				log.debug("���ʧ��");
			}
			else{
				request.setAttribute("msg", "��ӳɹ�");
				log.debug("��ӳɹ�");
			}
		} catch (Exception e) {
			log.error(" ProjectServlet addProject  [e"+e+"]");
		}
		request.getRequestDispatcher("/project?method=showMain").forward(request, response);
	}
	/**
	 * ��ʼ����ͼ
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
		//�ο�
		if(UserType.TOURIST == user.getuType()){
			request.getRequestDispatcher("/views/project/singleSiteTourist.jsp").forward(request, response);
		}	
		request.getRequestDispatcher("/views/project/singleSite.jsp").forward(request, response);
	}
	/**
	 * ��ȡ���л��ߵ����µĹ���
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
	 * ��ȡ�û����̵���Ϣ
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
	 * ��ȡ�ó��������й���
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
			List<Map> citys = new ArrayList<Map>(); // ���г���
			int city = Integer.valueOf(request.getParameter("district"));
			if(city == -1){
				citys = projectService.searchCitys(); // ���г���
			}else{
				Map<String,Object> m = new HashMap<String,Object>();
				m.put("city", city);
				citys.add(m);
			}
			List<Map> infos2 = new ArrayList<Map>(); // �洢������Ϣ
			for(Map m : citys){
				city = (Integer) m.get("city"); // ���̳���
				int sType1 = (Integer) projectService.searchSTypeByContent("��������").get("bno"); // �������̵ı��
				int sType2 = (Integer) projectService.searchSTypeByContent("���ݽ���").get("bno"); // ���ݽ���ı��
				String districtContent;
				List<Map> projects = projectService.searchProjectsByCity(city);
				for(Map s : projects){
					int pno = (Integer) s.get("pno"); // @��ĿID
					
					log.debug("ProjectServlet getCityProjects pno:"+pno);
					districtContent = (String) projectService.searchDistrictContentByID(pno).get("REGION_NAME");
					String projectName = (String) s.get("projectName"); // @��������
					String isOver = String.valueOf(s.get("over")); // @�Ƿ��깤
					if("1".equals(isOver)){
						isOver = "�깤";
					}else{
						isOver = "δ�깤";
					}
					String isOnline = String.valueOf(s.get("isOnline")); // @�Ƿ�����
					if("1".equals(isOnline)){
						isOnline = "����";
					}else{
						isOnline = "δ����";
					}
					Long sType1Num = (Long) projectService.searchProjectNumByCityAndSType(city, sType1).get("a") ;//  �õ�������������Ŀ
					Long sType2Num = (Long) projectService.searchProjectNumByCityAndSType(city, sType2).get("a") ;//  �õ������ݽ�����Ŀ
					String projectType = String.valueOf(s.get("sType")); // @��������
					if(sType1Num != 0){
						if(String.valueOf(sType1).equals(projectType)){
							projectType = "��������";
						}else{
							projectType = "���ݽ���";
						}
					}else{
						if(String.valueOf(sType2).equals(projectType)){
							projectType = "���ݽ���";
						}else{
							projectType = "��������";
						}
					}
					String belong = districtContent; // @����
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡ�õ��������й���
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
			int city = user.getCity(); // �û����б��
			List<Map> districts = new ArrayList<Map>(); // ���������е���
			int district = Integer.valueOf(request.getParameter("district"));
			if(district == -1){
				districts = projectService.searchDistrictsByCity(city); // ���������е���
			}else{
				Map<String,Object> m = new HashMap<String,Object>();
				m.put("district", district);
				districts.add(m);
			}
			List<Map> infos2 = new ArrayList<Map>(); // �洢������Ϣ
			for(Map m : districts){
				district = (Integer) m.get("district"); // ���̵���
				int sType1 = (Integer) projectService.searchSTypeByContent("��������").get("bno"); // �������̵ı��
				int sType2 = (Integer) projectService.searchSTypeByContent("���ݽ���").get("bno"); // ���ݽ���ı��
				String districtContent = (String) projectService.searchDistrictContent(district).get("content"); // @���̵�������ϸ��ʾ
				List<Map> projects = projectService.searchProjectsByDistrict(district);
				for(Map s : projects){
					int pno = (Integer) s.get("pno"); // @��ĿID
					String projectName = (String) s.get("projectName"); // @��������
					String isOver = String.valueOf(s.get("over")); // @�Ƿ��깤
					if("1".equals(isOver)){
						isOver = "�깤";
					}else{
						isOver = "δ�깤";
					}
					String isOnline = String.valueOf(s.get("isOnline")); // @�Ƿ�����
					if("1".equals(isOnline)){
						isOnline = "����";
					}else{
						isOnline = "δ����";
					}
					Long sType1Num = (Long) projectService.searchProjectNumByDistrictAndSType(district, sType1).get("a") ;//  �õ�������������Ŀ
					Long sType2Num = (Long) projectService.searchProjectNumByDistrictAndSType(district, sType2).get("a") ;//  �õ������ݽ�����Ŀ
					String projectType = String.valueOf(s.get("sType")); // @��������
					if(sType1Num != 0){
						if(String.valueOf(sType1).equals(projectType)){
							projectType = "��������";
						}else{
							projectType = "���ݽ���";
						}
					}else{
						if(String.valueOf(sType2).equals(projectType)){
							projectType = "���ݽ���";
						}else{
							projectType = "��������";
						}
					}
					String belong = districtContent; // @����
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡ����������Ŀ��Ϣ
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
			int city = user.getCity(); // �û����б��
			String cityContent = (String) projectService.searchCityContent(city).get("REGION_NAME"); // @�û����е���ϸ����
			Long totalProjectsNum = 0L; // @��������
			Long totalSType1Num = 0L; // @������������
			Long totalSType2Num = 0L; // @���ݽ�������
			Long totalBeginNum = 0L; // @�����깤����
			Long totalEndNum = 0L; // @�����깤����
			List<Map> districts = projectService.searchDistrictsByCity(city); // ���������е���
			List<Map> infos1 = new ArrayList<Map>(); // �洢��һ��Ϣ
			Map<String,Object> cityMap = new HashMap<String,Object>(); // �洢������Ϣ
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String nowYear = format.format(date); // ��������
			int sType1 = (Integer) projectService.searchSTypeByContent("��������").get("bno"); // �������̵ı��
			int sType2 = (Integer) projectService.searchSTypeByContent("���ݽ���").get("bno"); // ���ݽ���ı��
			for(Map m : districts){
				int district = (Integer) m.get("district"); // ���̵���
				String districtContent = (String) projectService.searchDistrictContent(district).get("REGION_NAME"); // @���̵�������ϸ��ʾ
				Long projectsNum; // @ͬ������������
				Long sType1Num;//  @�õ�������������Ŀ
				Long sType2Num = (Long) projectService.searchProjectNumByDistrictAndSType(district, sType2).get("a") ;//  @�õ������ݽ�����Ŀ
				projectsNum = (Long) projectService.searchProjectNumByDistrict(district).get("a");
				sType1Num = (Long) projectService.searchProjectNumByDistrictAndSType(district, sType1).get("a");
				sType2Num = (Long) projectService.searchProjectNumByDistrictAndSType(district, sType2).get("a");
				if(sType1Num == 0){
					sType1Num = projectsNum - sType2Num;
				}else{
					sType2Num = projectsNum - sType1Num;
				}
				Long beginNum ; // @���꿪����
				Long endNum ; // @�����깤��
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
	 * ��ȡ�������Ļ�����Ŀ��Ϣ
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
			List<Map> citys =  projectService.searchCitys(); // ���г���
			
			if(citys != null) log.debug("ProjectServlet  getUserProjectsInfos2  get  citys NOT NULL:"+citys.toString());
			
			Long totalProjectsNum = 0L; // @��������
			Long totalSType1Num = 0L; // @������������
			Long totalSType2Num = 0L; // @���ݽ�������
			Long totalBeginNum = 0L; // @�����깤����
			Long totalEndNum = 0L; // @�����깤����
			List<Map> infos1 = new ArrayList<Map>(); // �洢��һ��Ϣ
			Map<String,Object> cityMap = new HashMap<String,Object>(); // �洢������Ϣ
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String nowYear = format.format(date); // ��������
			int sType1 = (Integer) projectService.searchSTypeByContent("��������").get("bno"); // �������̵ı��
			int sType2 = (Integer) projectService.searchSTypeByContent("���ݽ���").get("bno"); // ���ݽ���ı��
			for(Map m : citys){
//				Map m : citys
//				Map m = citys.get(i);
				int city = (Integer) m.get("city"); // ���̳���
				String cityContent = (String) projectService.searchDistrictContent(city).get("REGION_NAME"); // @���̵�������ϸ��ʾ
				Long projectsNum = (Long) projectService.searchProjectNumByCity(city).get("a");; // @ͬ���й�������
				Long sType1Num = (Long) projectService.searchProjectNumByCityAndSType(city, sType1).get("a") ;//  @�ó�������������Ŀ
				Long sType2Num = (Long) projectService.searchProjectNumByCityAndSType(city, sType2).get("a") ;//  @�õ������ݽ�����Ŀ
				if(sType1Num == 0){
					sType1Num = projectsNum - sType2Num;
				}else{
					sType2Num = projectsNum - sType1Num;
				}
				Long beginNum ; // @���꿪����
				Long endNum ; // @�����깤��
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
			cityMap.put("cityContent","ȫ��");
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
	 * ͨ��ID����project
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
			//�ο�
			if(UserType.TOURIST ==user.getuType()){
				request.getRequestDispatcher("/views/project/singleSiteTourist.jsp").forward(request, response); 
			}
			request.getRequestDispatcher("/views/project/singleSite.jsp").forward(request, response); // �����ݷ��͵���ʱ jsp
		} catch (Exception e) {
			log.debug("ProjectServlet searchProjectByID [e"+e+"]");
		}
	}
	 /* ͨ��ID����project��λ��
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	/**
	 * ��ѯ���й�����Ϣ
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
