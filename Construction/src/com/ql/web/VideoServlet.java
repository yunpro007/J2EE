package com.ql.web;


import java.io.IOException;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.ql.model.Video;
import com.ql.model.Project;
import com.ql.model.User;
import com.ql.service.VideoService;
import com.ql.service.ProjectService;
import com.ql.util.Tools;

@SuppressWarnings("serial")
@WebServlet("/video")
public class VideoServlet extends HttpServlet {

	private final static Logger log = Logger.getLogger(VideoServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VideoService videoService = new VideoService();
		String methodName = request.getParameter("method");
		log.debug("VideoServlet : method = "+methodName);
		Map dto = Tools.createDto(request);
		log.debug(dto);
	
		videoService.setDto(dto);
		User user = (User) request.getSession().getAttribute("user");
		videoService.getDto().put("user",user);
		try {
			Method method = this.getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class,VideoService.class);
			method.invoke(this, request, response, videoService);
		} catch (Exception e) {
			log.error("VideoServlet: [e="+e+"]");
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	/**
	 * ��ʼ���������������
	 * @param progressService
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void initMain(HttpServletRequest request,HttpServletResponse response,VideoService videoService) throws Exception{
		List<Project> results = null ;
		//���������쳣����Ϊ���Session��û��userʱ��������ᱻ������������ֹ������ת����¼����
		User  user =(User)request.getSession().getAttribute("user");
		ProjectService projectService = new ProjectService();
		projectService.setDto(new HashMap());
		try {
			projectService.getDto().put("user", user);
			log.debug(projectService.getDto());
			if(null != projectService){
				results = projectService.queryAllProject();
				log.debug("results.size = "+results.size());
				if(null != results && results.size() > 0){
					request.setAttribute("results", results);
					request.setAttribute("sum",results.size());
				}
			}
		} catch (Exception e) {
			log.error(" ProjectServlet showMain [e"+e+"]");
		}
		request.getRequestDispatcher("/views/video/project.jsp").forward(request, response);
	}

	
	
	/**
	 * ��ѯ�����������
	 * @param progressService
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	/*public void initUpdate(HttpServletRequest request,HttpServletResponse response,ProgressService progressService) throws Exception{
		try {
			String projectNum = request.getParameter("projectNum");
			//���URL�в���Ϊ����ʱ���������
			projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
			String projectName = request.getParameter("projectName");
			projectName = new String(projectName.getBytes("ISO-8859-1"),"UTF-8");
			Progress progress = progressService.queryOne();
			request.setAttribute("progress", progress);
			request.setAttribute("projectNum", projectNum);
			request.setAttribute("projectName", projectName);
			//ά�ַ������ӵ�״̬
			String backURL = request.getParameter("backURL") ;
			if(null != backURL && !"".equals(backURL)){
				String Ppno = request.getParameter("Ppno");
				request.setAttribute("superBackURL", backURL);
				request.setAttribute("Ppno", Ppno);
			}
			else{
				request.setAttribute("backURL", "initProgress");
			}
			request.getRequestDispatcher("/views/progress/updateProgress.jsp").forward(request, response);
		} catch (Exception e) {
			log.debug("ProgressServlet initUpdate [e"+e+"]");
		}
		
		
	}*/
	
	/**
	 * �г����̵������������
	 * @param request
	 * @param response
	 * @param progressService
	 * @throws Exception
	 */
	public void listVideoForProject( HttpServletRequest request, HttpServletResponse response,VideoService videoService) throws Exception{
		try {
			String projectNum = request.getParameter("projectNum");
			//���URL�в���Ϊ����ʱ���������
			projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
			videoService.getDto().put("projectNum", projectNum);
			String projectName = request.getParameter("projectName");
		    projectName =  new String(projectName.getBytes("ISO-8859-1"),"UTF-8");
			log.debug("VideoServlet  listVideoProject  dto = "+videoService.getDto());
			List<Video> videos =  videoService.listVideoForProject();
			Object msgObj = request.getAttribute("msg");
			request.setAttribute("projectNum", projectNum);
			request.setAttribute("projectName",projectName);
			if( null != msgObj ){
				String msg = (String)msgObj ;
				if(!"".equals(msg)){
					request.setAttribute("msg", msg);
				}
			}
			else{
				request.setAttribute("msg","");
			}
			
			if(null != videos && videos.size() >= 0){
				request.setAttribute("videos", videos);
				request.setAttribute("sum",videos.size());
			}
			//�ӵ���������������ת����
			String backURL = request.getParameter("backURL") ;
			if(null != backURL && !"".equals(backURL)){
				Object Pano = request.getAttribute("ano");
				String ano = request.getParameter("ano");
				request.setAttribute("superBackURL", backURL);
				if(null != Pano){
					//������º�ɾ��ʱ�Ĺ��̵�ano
					request.setAttribute("ano", Pano);
				}
				else{
					request.setAttribute("ano", ano);
				}
			}
			else{
				request.setAttribute("backURL", "initMain");
			}
			
			request.getRequestDispatcher("/views/video/videoMain.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(" VideoServlet  listVideoForProject  [e"+e+"]");
		}
	}
}
	
	/*public void queryByCondition(HttpServletRequest request , HttpServletResponse response , ProgressService progressService) throws Exception{
		try {
			
			 ProjectService projectService = new ProjectService() ;
			 projectService.setDto(progressService.getDto());
			 List<Map> results = projectService.queryByCondition();
			 if(null != results && results.size() > 0){
				 log.debug("results.size = "+results.size());
				 request.setAttribute("results", results);
				 request.setAttribute("sum",results.size());
			 }
			 request.getRequestDispatcher("/views/progress/project.jsp").forward(request, response);
		} catch (Exception e) {
			log.error(" ProgressServlet queryByCondition  [e"+e+"]");
		}
	}

	*//**
	 * ��������ѯĳ�����̵��������
	 * @param request
	 * @param response
	 * @param progressService
	 * @throws Exception
	 *//*
	public void queryProgerssByCondition(HttpServletRequest request ,HttpServletResponse response , ProgressService progressService) throws Exception{
		try {
			String projectNum = request.getParameter("projectNum");
			//���URL�в���Ϊ����ʱ���������
			projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
			String projectName = request.getParameter("projectName");
			System.out.println("projectName");
			projectName = new String(projectName.getBytes("ISO-8859-1"),"UTF-8");
			progressService.getDto().put("projectNum", projectNum);
			log.debug("ProgressServlet  listProgressProject  dto = "+progressService.getDto());
			List<Progress> progresses =  progressService.queryProgerssByCondition();
			request.setAttribute("msg","");
			request.setAttribute("projectName", projectName);
			if(null != progresses && progresses.size() > 0){
				request.setAttribute("progresses", progresses);
			}
			request.setAttribute("sum",progresses.size());
		    if(request.getParameter("qopart") != null)
				request.setAttribute("qopart", request.getParameter("qopart"));
			if(request.getParameter("qopType") != null)
				request.setAttribute("qopType", request.getParameter("qopType"));
			if(request.getParameter("qopTime") != null)
				request.setAttribute("qopTime", request.getParameter("qopTime"));
			request.setAttribute("projectNum", projectNum);
			log.debug(" projectNum =  "+projectNum);
			//ά�ַ������ӵ�״̬
			String backURL = request.getParameter("backURL") ;
			if(null != backURL && !"".equals(backURL)){
				String pno = request.getParameter("pno");
				request.setAttribute("superBackURL", backURL);
				request.setAttribute("pno", pno);
			}
			else{
				request.setAttribute("backURL", "initProgress");
			}
			
			request.getRequestDispatcher("/views/progress/progressMain.jsp").forward(request, response);
		} catch (Exception e) {
			log.error(" ProgressServlet queryProgressByCondition ["+e+"]");
		}
		
		
	}
	
	*//**
	 * ����excel
	 * @param request
	 * @param response
	 * @param progressService
	 * @throws Exception 
	 *//*
	public void exportProgerss(HttpServletRequest request, HttpServletResponse response, ProgressService progressService) throws Exception{
		response.setContentType("octets/stream");
//		String excelName = new String(request.getParameter("projectNum").getBytes("ISO-8859-1"),"UTF-8");
		String projectNum = request.getParameter("projectNum");
		//���URL�в���Ϊ����ʱ���������
		projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
		String excelName = projectNum + "�������";
		response.addHeader("Content-Disposition", "attachment;filename="+new String(excelName.getBytes("utf-8"),"iso8859-1")+".xls");
		progressService.getDto().put("projectNum", projectNum);
		log.debug("ProgressServlet  listProgressProject  dto = "+progressService.getDto());
		String[] headers = new String[]{"���̱��","�������","ʩ����λ","ʱ����Ϣ","��ϸ"}; 
		List<Progress> progresses =  progressService.queryProgerssByCondition();
		log.debug("����excel"+excelName);
		try {  
            OutputStream out = response.getOutputStream();
            HSSFWorkbook workbook =	ExcelUtil.getWorkBook(excelName, headers);
            HSSFSheet sheet = workbook.getSheetAt(0);
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            Map<String,Object> map = null;
            Progress progress = null;
            for(int i=0; i<progresses.size(); i++){
            	progress = progresses.get(i);
            	map = new HashMap<String,Object>();
            	map.put("pno", progress.getPno());
            	map.put("projectNum", progress.getProjectNum());
            	map.put("pTime", progress.getpTime());
            	map.put("pType", progress.getpType());
            	map.put("part", progress.getPart());
            	map.put("memo", progress.getMemo());
            	list.add(map);
            }
            HSSFRow row = null;
            for(int i=0; i<list.size(); i++){
            	map = list.get(i);
            	row = sheet.createRow(i+1);
            	int j = 0;
            	row.createCell(j++).setCellValue(map.get("pno")!=null?map.get("pno").toString():"");
            	row.createCell(j++).setCellValue(map.get("projectNum")!=null?map.get("projectNum").toString():"");
            	row.createCell(j++).setCellValue(map.get("part")!=null?map.get("part").toString():"");
            	row.createCell(j++).setCellValue(map.get("pTime")!=null?map.get("pTime").toString():"");
            	row.createCell(j++).setCellValue(map.get("memo")!=null?map.get("memo").toString():"");
            	
            }
            ExcelUtil.exportExcel(workbook, out);
            out.close();  
            System.out.println("excel�����ɹ���");  
        } catch (FileNotFoundException e) {  
                e.printStackTrace();  
        } catch (IOException e) {  
                e.printStackTrace();  
        } 
	}

}
*/