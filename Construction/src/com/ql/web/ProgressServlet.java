package com.ql.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringBufferInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jspsmart.upload.SmartUpload;
import com.ql.common.ProgressType;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import com.ql.model.Progress;
import com.ql.model.Project;
import com.ql.model.User;
import com.ql.service.ProgressService;
import com.ql.service.ProjectService;
import com.ql.util.ExcelUtil;
import com.ql.util.FileTools;
import com.ql.util.NetworkUtil;
import com.ql.util.Tools;

@SuppressWarnings("serial")
@WebServlet("/progress")
public class ProgressServlet extends HttpServlet {

	private final static Logger log = Logger.getLogger(ProgressServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProgressService progressService = new ProgressService();
		String methodName = request.getParameter("method");
		log.debug("PregressServlet : method = "+methodName);
		Map dto = Tools.createDto(request);
		log.debug(dto);
	
		progressService.setDto(dto);
		User user = (User) request.getSession().getAttribute("user");
		progressService.getDto().put("user",user);
		try {
			Method method = this.getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class,ProgressService.class);
			method.invoke(this, request, response, progressService);
		} catch (Exception e) {
			log.error("ProgressServlet: [e="+e+"]");
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	/**
	 * 初始化形象进度主界面
	 * @param progressService
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void initProgress(HttpServletRequest request,HttpServletResponse response,ProgressService progressService) throws Exception{
		List<Project> results = null ;
		//不可能有异常，因为如果Session中没有user时，该请求会被过滤器过滤阻止，而跳转到登录界面
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
		request.getRequestDispatcher("/views/progress/project.jsp").forward(request, response);
	}

	
	/**
	 * 添加形象进度
	 * @param progressService
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addProgress(HttpServletRequest request,HttpServletResponse response,ProgressService progressService) throws Exception{
		String path = request.getContextPath();
		String projectNum = null  ;
		String msg = null;
		try {
			projectNum = request.getParameter("projectNum");
			projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
		    String projectName = request.getParameter("projectName");
			projectName = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
			progressService.getDto().put("projectNum", projectNum);
			log.debug(progressService.getDto());
			boolean flag = progressService.addProgress();
			request.removeAttribute("method");
			log.debug(projectName);
			request.setAttribute("projectName", projectName);
			request.setAttribute("projectNum", projectNum);
			if(flag){
				request.setAttribute("msg", "添加成功");
			}
			else{
				request.setAttribute("msg", "添加失败");
			}
			//维持返回链接的状态
			String backURL = request.getParameter("backURL") ;
			if(null != backURL && !"".equals(backURL)){
				String Ppno = request.getParameter("Ppno");
				request.setAttribute("superBackURL", backURL);
				request.setAttribute("pno", Ppno);
			}
			else{
				request.setAttribute("backURL", "initProgress");
			}
			request.getRequestDispatcher("/progress?method=listProgressForProject").forward(request, response);
		} catch (Exception e) {
			log.error("ProgressServlet : e = ["+e+"]");
		}
	}
	/**
	 * 删除一个形象进度
	 * @param progressService
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void deleteProgress(HttpServletRequest request,HttpServletResponse response,ProgressService progressService) throws Exception{
		String projectNum = null ;
		try {
			projectNum = request.getParameter("projectNum");
			projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
			String projectName = request.getParameter("projectName");
			projectName = new String(projectName.getBytes("ISO-8859-1"),"UTF-8");
			boolean flag = progressService.deleteOneProgress();
			request.removeAttribute("method");
			request.setAttribute("projectNum", projectNum);
			request.setAttribute("projectName", projectName);
			if(!flag){
				request.setAttribute("msg", "删除失败");
			}
			else{
				request.setAttribute("msg", "删除成功");
			}
			//维持返回链接的状态
			String backURL = request.getParameter("backURL") ;
			if(null != backURL && !"".equals(backURL)){
				String Ppno = request.getParameter("Ppno");
				request.setAttribute("superBackURL", backURL);
				request.setAttribute("pno", Ppno);
			}
			else{
				request.setAttribute("backURL", "initProgress");
			}
			
		} catch (Exception e) {
			log.error("ProgressSerlvet deleteProgress  [e = "+e+"]");
		}
		request.getRequestDispatcher("/progress?method=listProgressForProject").forward(request, response);
	}
	
	/**
	 * 更新形象进度
	 * @param request
	 * @param response
	 * @param progressService
	 * @throws Exception
	 */
	public void updateProgress(HttpServletRequest request,HttpServletResponse response,ProgressService progressService) throws Exception{
		String path = request.getContextPath();
		boolean flag = false ;
		String projectNum = null;
		try {
			progressService.getDto().remove("projectNum");
			projectNum = request.getParameter("projectNum");
			projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
			String projectName = request.getParameter("projectName");
			projectName = new String(projectName.getBytes("ISO-8859-1"),"UTF-8");
			progressService.getDto().put("projectNum", projectNum);
			flag = progressService.update();
			request.removeAttribute("method");
			request.setAttribute("projectName",projectName);
			request.setAttribute("projectNum",projectNum);
			log.debug(" 修改成功 :" +flag);
			if(!flag){
				request.setAttribute("msg", "修改失败");
			}else{
				request.setAttribute("msg", "修改成功");
			}
			
			//维持返回链接的状态
			String backURL = request.getParameter("backURL") ;
			if(null != backURL && !"".equals(backURL)){
				String Ppno = request.getParameter("Ppno");
				request.setAttribute("superBackURL", backURL);
				request.setAttribute("pno", Ppno);
			}
			else{
				request.setAttribute("backURL", "initProgress");
			}
			request.getRequestDispatcher("/progress?method=listProgressForProject").forward(request, response);
		} catch (Exception e) {
			log.error("ProgressServlet : e = [" + e + "]");
		}
		
	}
	/**
	 * 查询单条形象进度
	 * @param progressService
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void initUpdate(HttpServletRequest request,HttpServletResponse response,ProgressService progressService) throws Exception{
		try {
			String projectNum = request.getParameter("projectNum");
			//解决URL中参数为中文时乱码的问题
			projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
			String projectName = request.getParameter("projectName");
			projectName = new String(projectName.getBytes("ISO-8859-1"),"UTF-8");
			Progress progress = progressService.queryOne();
			request.setAttribute("progress", progress);
			request.setAttribute("projectNum", projectNum);
			request.setAttribute("projectName", projectName);
			//维持返回链接的状态
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
		
		
	}
	
	/**
	 * 列出工程的所有形象进度
	 * @param request
	 * @param response
	 * @param progressService
	 * @throws Exception
	 */
	public void listProgressForProject( HttpServletRequest request, HttpServletResponse response,ProgressService progressService) throws Exception{
		try {
			String projectNum = request.getParameter("projectNum");
			//解决URL中参数为中文时乱码的问题
			projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
			progressService.getDto().put("projectNum", projectNum);
			String projectName = request.getParameter("projectName");
		    projectName =  new String(projectName.getBytes("ISO-8859-1"),"UTF-8");
			log.debug("ProgressServlet  listProgressProject  dto = "+progressService.getDto());
			List<Progress> progresses =  progressService.listProgressForProject();
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
			
			if(null != progresses && progresses.size() >= 0){
				request.setAttribute("progresses", progresses);
				request.setAttribute("sum",progresses.size());
			}
			//从单个工程主界面跳转过来
			String backURL = request.getParameter("backURL") ;
			if(null != backURL && !"".equals(backURL)){
				Object Ppno = request.getAttribute("pno");
				String pno = request.getParameter("pno");
				request.setAttribute("superBackURL", backURL);
				if(null != Ppno){
					//处理更新和删除时的工程的pno
					request.setAttribute("pno", Ppno);
				}
				else{
					request.setAttribute("pno", pno);
				}
			}
			else{
				request.setAttribute("backURL", "initProgress");
			}
			
			request.getRequestDispatcher("/views/progress/progressMain.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(" ProgressServlet  listProgressForProject  [e"+e+"]");
		}
	}
	
	/**
	 * 加载本地图片
	 * @param request
	 * @param response
	 * @param progressService
	 * @throws Exception
	 */
	public void getImage(HttpServletRequest request,HttpServletResponse response,ProgressService progressService) throws Exception{
		log.debug("getImage : "+progressService.getDto());
		FileTools.getImage(request, response, FileTools.getPath("progressPath"),(String)progressService.getDto().get("fileName"));
	}
	
	/**
	 * 按条件查询工程
	 * @param request
	 * @param response
	 * @param progressService
	 * @throws Exception
	 */
	public void queryByCondition(HttpServletRequest request , HttpServletResponse response , ProgressService progressService) throws Exception{
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

	/**
	 * 按条件查询某个工程的形象进度
	 * @param request
	 * @param response
	 * @param progressService
	 * @throws Exception
	 */
	public void queryProgerssByCondition(HttpServletRequest request ,HttpServletResponse response , ProgressService progressService) throws Exception{
		try {
			String projectNum = request.getParameter("projectNum");
			//解决URL中参数为中文时乱码的问题
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
			//维持返回链接的状态
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
	
	/**
	 * 导出excel
	 * @param request
	 * @param response
	 * @param progressService
	 * @throws Exception 
	 */
	public void exportProgerss(HttpServletRequest request, HttpServletResponse response, ProgressService progressService) throws Exception{
		response.setContentType("octets/stream");
	    //String excelName = new String(request.getParameter("projectNum").getBytes("ISO-8859-1"),"UTF-8");
		String projectNum = request.getParameter("projectNum");
		//解决URL中参数为中文时乱码的问题
		projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
		String excelName = projectNum+"-形象进度";
		//获取当前系统的编码方式
	    String encoding = System.getProperty("file.encoding"); 
	    log.debug("ProgressServlet exportProgerss()：当前编码方式是"+encoding);
		response.addHeader("Content-Disposition", "attachment;filename="+new String(excelName.getBytes("GBK"),"iso8859-1")+".xls");
		//response.addHeader("Content-Disposition", "attachment;filename="+excelName.toString()+".xls");
		response.setContentType("application/msexcel;charset=utf-8");
		progressService.getDto().put("projectNum", projectNum);
		log.debug("ProgressServlet  listProgressProject  dto = "+progressService.getDto());
		String[] headers = new String[]{"工程编号","形象进度","施工部位","时间信息","详细"}; 
		List<Progress> progresses =  progressService.queryProgerssByCondition();
		log.debug("导出excel"+excelName);
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
            System.out.println("excel导出成功！");  
        } catch (FileNotFoundException e) {  
                e.printStackTrace();  
        } catch (IOException e) {  
                e.printStackTrace();  
        } 
	}

}
