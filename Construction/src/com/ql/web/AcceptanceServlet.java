package com.ql.web;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
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

import com.ql.model.Acceptance;
import com.ql.model.Progress;
import com.ql.service.AcceptanceService;
import com.ql.service.ProjectService;
import com.ql.util.ExcelUtil;
import com.ql.util.FileTools;
import com.ql.util.Tools;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

@WebServlet("/acceptance")
public class AcceptanceServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger(AcceptanceServlet.class);
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		AcceptanceService acceptanceService = new AcceptanceService();
		String methodName = request.getParameter("method");
		Map dto = Tools.createDto(request);
		log.debug("AcceptanceSerclet: method = "+ methodName);
		log.debug("  dto = "+dto);
		acceptanceService.setDto(dto);
		try {
			Method method = this.getClass().getDeclaredMethod(methodName,HttpServletRequest.class,
                    HttpServletResponse.class,AcceptanceService.class);
			method.invoke(this, request, response, acceptanceService);
		} catch (Exception e) {
			log.error("AcceptanceServlet:[e="+e+"]");
		} 
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	public void initAcceptance(HttpServletRequest request,HttpServletResponse response,AcceptanceService acceptanceService) throws Exception{
		try {
			
			List<Acceptance> acceptances = acceptanceService.querylist();
			log.debug(acceptances);
			if(null != acceptances && acceptances.size() > 0 ){
				request.setAttribute("acceptances", acceptances);
				request.setAttribute("sum", acceptances.size());
			}
			request.getRequestDispatcher("/views/acceptance/acceptanceMain.jsp").forward(request, response);
		} catch (Exception e) {
			log.error("AcceptanceServlet  initAcceptance  [e"+e+"]");
		}
	}
	
	/**
	 * 查询某个工程的所有验收
	 * @param request
	 * @param response
	 * @param acceptanceService
	 * @throws Exception
	 */
	public void listAcceptanceForProject(HttpServletRequest request,HttpServletResponse response,AcceptanceService acceptanceService) throws Exception{
		  List<Acceptance> acceptances = null ;
		  String msg  = "";
		  try {
			  String projectNum = request.getParameter("projectNum");
				//解决URL中参数为中文时乱码的问题
			  projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
			  acceptanceService.getDto().put("projectNum",projectNum);
			  String projectName = request.getParameter("projectName");
		      projectName = new String(projectName.getBytes("ISO-8859-1"),"UTF-8");
			  log.debug(" AcceptanceServlet listAcceptanceForProject dot = "+acceptanceService.getDto());
			  acceptances = acceptanceService.listAcceptanceForProject() ;
				Object msgObj = request.getAttribute("msg");
				request.setAttribute("projectNum",projectNum);
				request.setAttribute("projectName",projectName);
				if( null != msgObj ){
					 msg = (String)msgObj ;
					if(!"".equals(msg)){
						request.setAttribute("msg", msg);
					}
				}
				else{
					request.setAttribute("msg","");
				}
			  if(null != acceptances && acceptances.size() > 0 ){
				  request.setAttribute("acceptances", acceptances);
				  request.setAttribute("sum", acceptances.size());
				  
			  }
			  String backURL = request.getParameter("backURL") ;
			if(null != backURL && !"".equals(backURL)){
					String pno = request.getParameter("pno");
					request.setAttribute("superBackURL", backURL);
					request.setAttribute("pno", pno);
				}
				else{
					request.setAttribute("backURL", "initProgress");
				}
			  request.getRequestDispatcher("/views/acceptance/acceptanceMain.jsp").forward(request, response);
		  } catch (Exception e) {
			  log.debug(" AcceptanceServlet listAcceptanceForProject  [e"+e+"]");
		  }
		  
	}
	
	public void  addAcceptance(HttpServletRequest request,HttpServletResponse response,AcceptanceService acceptanceService) throws Exception{
		try {
			String projectNum = request.getParameter("projectNum");
			String projectName = request.getParameter("projectName");
				//解决URL中参数为中文时乱码的问题
			 projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
			 projectName = new String(projectName.getBytes("ISO-8859-1"),"UTF-8");
			 Map dto = new HashMap<String,Object>() ;
			String desFileTem = FileTools.getPath("tem");
			String desFileDic = FileTools.getPath("acceptancePath");
			FileTools.upload(request, response, dto, desFileDic, desFileTem);
			dto.put("projectNum", projectNum);
			log.debug("ProgressServlet  addProject: dto = "+dto);
			acceptanceService.setDto(dto);
			boolean flag = acceptanceService.addAcceptance();
			request.setAttribute("projectNum", projectNum);
			request.setAttribute("projectName", projectName);
			request.removeAttribute("method");
			if(!flag){
				request.setAttribute("msg", "添加失败");
				log.debug("添加失败");
			}
			else{
				request.setAttribute("msg", "添加成功");
				log.debug("添加成功");
			}
			String backURL = request.getParameter("backURL") ;
			if(null != backURL && !"".equals(backURL)){
				String pno = request.getParameter("pno");
				request.setAttribute("superBackURL", backURL);
				request.setAttribute("pno", pno);
			}
			else{
				request.setAttribute("backURL", "initProgress");
			}
			request.getRequestDispatcher("/acceptance?method=listAcceptanceForProject").forward(request, response);
			
		} catch (Exception e) {
			log.error(" AcceptanceServlet addAcceptance  [e"+e+"]");
		}
	}
	
	public void deleteAcceptance(HttpServletRequest request , HttpServletResponse response , AcceptanceService acceptanceService) throws Exception{
		try {
			String projectNum = request.getParameter("projectNum");
			String projectName = request.getParameter("projectName");
			projectName = new String(projectName.getBytes("ISO-8859-1"),"UTF-8");
			//解决URL中参数为中文时乱码的问题
		   projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
		   
		   boolean sign = acceptanceService.deleteAcceptance();
		   request.removeAttribute("method");
		   if(sign){
			   request.setAttribute("msg","删除成功");
		   }else{
			   request.setAttribute("msg", "删除失败");
		   }
		   request.setAttribute("projectNum", projectNum);
		   request.setAttribute("projectName", projectName);
		   //维持返回链接状态
			String backURL = request.getParameter("backURL") ;
				if(null != backURL && !"".equals(backURL)){
					String pno = request.getParameter("pno");
					request.setAttribute("superBackURL", backURL);
					request.setAttribute("pno", pno);
				}
				else{
					request.setAttribute("backURL", "initProgress");
				}
		   request.getRequestDispatcher("/acceptance?method=listAcceptanceForProject").forward(request, response);
		} catch (Exception e) {
			log.error(" AcceptanceServlet deleteAcceptance  [e"+e+"]");
		}
	}
	
	public  void initUpdate(HttpServletRequest request , HttpServletResponse response , AcceptanceService acceptanceService ) throws Exception{
		try {
			String projectNum = request.getParameter("projectNum");
			System.out.println(projectNum);
			projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
			
			//解决URL中参数为中文时乱码的问题
		   String projectName = request.getParameter("projectName");
		   projectName = new String(projectName.getBytes("ISO-8859-1"),"UTF-8") ;
		   Acceptance acceptance = acceptanceService.querySingle();
		   request.setAttribute("projectNum",projectNum);
		   request.setAttribute("projectName", projectName);
		   if(null != acceptance){
			   request.setAttribute("acceptance", acceptance);
		   }
		   //维持返回链接状态
			String backURL = request.getParameter("backURL") ;
			if(null != backURL && !"".equals(backURL)){
					String pno = request.getParameter("pno");
					request.setAttribute("superBackURL", backURL);
					request.setAttribute("pno", pno);
			}
			else{
				request.setAttribute("backURL", "initProgress");
			}
		   request.getRequestDispatcher("/views/acceptance/updateAcceptance.jsp").forward(request, response);
		} catch (Exception e) {
			log.error(" AcceptanceServlet initUpdate  [e"+e+"]");
		}
		
		
	}
	public void updateAcceptance(HttpServletRequest request , HttpServletResponse response,AcceptanceService acceptanceService) throws Exception{
		try {
			String projectNum = request.getParameter("projectNum");
			String  ano  = request.getParameter("ano");
			//解决URL中参数为中文时乱码的问题
		    projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
			Map dto = new HashMap<String,Object>() ;
			String desFileTem = FileTools.getPath("tem");
			String desFileDic = FileTools.getPath("acceptancePath");
			FileTools.upload(request, response, dto, desFileDic, desFileTem);
			dto.put("projectNum", projectNum);
			dto.put("ano",ano) ;
			log.debug("ProgressServlet  addProject: dto = "+dto);
			acceptanceService.setDto(dto);
			boolean flag = acceptanceService.updateAcceptance();
			request.removeAttribute("method");
			if(flag){
				request.setAttribute("msg", "更新成功");
				log.debug("更新成功");
			}
			else{
				request.setAttribute("msg", "更新失败");
				log.debug("更新失败");
				
			}
			 //维持返回链接状态
			String backURL = request.getParameter("backURL") ;
				if(null != backURL && !"".equals(backURL)){
					String pno = request.getParameter("pno");
					request.setAttribute("superBackURL", backURL);
					request.setAttribute("pno", pno);
				}
				else{
					request.setAttribute("backURL", "initProgress");
				}
			request.getRequestDispatcher("/acceptance?method=listAcceptanceForProject").forward(request, response);
		} catch (Exception e) {
			log.error(" AcceptanceServlet updateAcceptance  [e"+e+"]");
		}
	}

	/**
	 * 按条件查询分部验收
	 * @param request
	 * @param response
	 * @param acceptanceService
	 * @throws Exception
	 */
	public void queryAcceptanceByCondition(HttpServletRequest request,HttpServletResponse response,AcceptanceService acceptanceService) throws Exception{
		List<Acceptance>  acceptances = null;
		try {
			String projectNum = request.getParameter("projectNum");
			//解决URL中参数为中文时乱码的问题
		  projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
		  String projectName = request.getParameter("projectName");
		  projectName = new String(projectName.getBytes("ISO-8859-1"),"UTF-8");
		  acceptanceService.getDto().put("projectNum",projectNum);
		  log.debug(" AcceptanceServlet queryAcceptanceByCondition dot = "+acceptanceService.getDto());
		  acceptances = acceptanceService.queryAcceptanceByCondition() ;
		  request.setAttribute("projectNum",projectNum);
		  request.setAttribute("projectName", projectName);
		  if(null != acceptances && acceptances.size() > 0 ){
			  request.setAttribute("acceptances", acceptances);
		  }	
		  request.setAttribute("sum", acceptances.size());
		  //维持返回链接状态
		  String backURL = request.getParameter("backURL") ;
			if(null != backURL && !"".equals(backURL)){
				String pno = request.getParameter("pno");
				request.setAttribute("superBackURL", backURL);
				request.setAttribute("pno", pno);
			}
			else{
				request.setAttribute("backURL", "initProgress");
			}
		  request.getRequestDispatcher("/views/acceptance/acceptanceMain.jsp").forward(request, response);
		} catch (Exception e) {
			log.error(" AcceptanceServlet queryAcceptanceByCondition  [e:"+e+"]");
		}
	}
	
	/**
	 * 导出
	 * @param request
	 * @param response
	 * @param acceptanceService
	 */
	public void exportAcceptance(HttpServletRequest request,HttpServletResponse response,AcceptanceService acceptanceService){
		String projectNum = request.getParameter("projectNum");
		List<Acceptance>  acceptances = null;
		try{
			//解决URL中参数为中文时乱码的问题
		    projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
		    acceptanceService.getDto().put("projectNum",projectNum);
		    log.debug(" AcceptanceServlet queryAcceptanceByCondition dot = "+acceptanceService.getDto());
		    acceptances = acceptanceService.queryAcceptanceByCondition() ;
		    request.setAttribute("projectNum",projectNum);
		    String excelName = projectNum+"-分布验收";
		    response.setContentType("octets/stream");
		   
			response.addHeader("Content-Disposition", "attachment;filename="+new String(excelName.getBytes("gbk"),"iso8859-1")+".xls");
			//response.addHeader("Content-Disposition", "attachment;filename="+excelName+".xls");
			String[] headers = new String[]{"编号","验收项目","类别","验收时间","详情"}; 
			OutputStream out = response.getOutputStream();
	        HSSFWorkbook workbook =	ExcelUtil.getWorkBook(excelName, headers);
	        HSSFSheet sheet = workbook.getSheetAt(0);
	        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	        Map<String,Object> map = null;
	        Acceptance acceptance = null;
	        for(int i=0; i<acceptances.size(); i++){
	        	acceptance = acceptances.get(i);
	        	map = new HashMap<String,Object>();
            	map.put("no", i+1);
            	map.put("aType", acceptance.getaType());
            	map.put("detail", acceptance.getDetail());
            	map.put("pType", acceptance.getaTime());
            	map.put("memo", acceptance.getMemo());
            	list.add(map);
	        }
	        HSSFRow row = null;
            for(int i=0; i<list.size(); i++){
            	map = list.get(i);
            	row = sheet.createRow(i+1);
            	int j = 0;
            	row.createCell(j++).setCellValue(map.get("no").toString());
            	row.createCell(j++).setCellValue(map.get("aType")!=null?map.get("aType").toString():"");
            	row.createCell(j++).setCellValue(map.get("detail")!=null?map.get("detail").toString():"");
            	row.createCell(j++).setCellValue(map.get("pType")!=null?map.get("pType").toString():"");
            	row.createCell(j++).setCellValue(map.get("memo")!=null?map.get("memo").toString():"");
            }
            ExcelUtil.exportExcel(workbook, out);
            out.close();  
            System.out.println("excel导出成功！");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
	
