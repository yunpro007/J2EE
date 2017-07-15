package com.ql.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.util.HSSFColor;

import com.ql.model.Door;
import com.ql.model.DoorForSum;
import com.ql.model.Progress;
import com.ql.model.Project;
import com.ql.model.User;
import com.ql.service.DetectionService;
import com.ql.service.DoorService;
import com.ql.service.ProgressService;
import com.ql.service.ProjectService;
import com.ql.util.ExcelUtil;
import com.ql.util.Tools;
import com.sun.jndi.url.dns.dnsURLContext;


@WebServlet("/door")
public class DoorServlet extends HttpServlet {
	private static final  Logger log = Logger.getLogger(DoorServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		Map dto = Tools.createDto(request);
		
		DoorService doorService = new DoorService() ;
		doorService.setDto(dto);
		
		try {
			Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class,
															 DoorService.class	);
			method.invoke(this, request,response,doorService);
		} catch (Exception e) {
			log.error(" DoorServlet doGet [e"+e+"]");
		}
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	public  void queryByCondition(HttpServletRequest request,HttpServletResponse response , DoorService doorService ) throws Exception{
		try {
		     User user =(User)request.getSession().getAttribute("user");
			 ProjectService projectService = new ProjectService() ;
			 projectService.setDto(doorService.getDto());
			 projectService.getDto().put("user", user);
			 log.debug(projectService.getDto());
			 
			 List<Map> results = projectService.queryByCondition();
			 if(null != results && results.size() > 0){
				 log.debug("results.size = "+results.size());
				 request.setAttribute("results", results);
				 request.setAttribute("sum",results.size());
 			 }
			 request.getRequestDispatcher("/views/door/project.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" EnvirDetection queryByCondition  [e"+e+"]");
		}
	}

	public void initMain(HttpServletRequest request, HttpServletResponse response, DoorService doorService)
			throws Exception {
		// 不可能有异常，因为如果Session中没有user时，该请求会被过滤器过滤阻止，而跳转到登录界面
		List<Project> results;
		User user = (User) request.getSession().getAttribute("user");
		ProjectService projectService = new ProjectService();
		projectService.setDto(new HashMap());
		try {
			projectService.getDto().put("user", user);
			log.debug(projectService.getDto());
			if (null != projectService) {
				results = projectService.queryAllProject();
				log.debug("results.size = " + results.size());
				if (null != results && results.size() > 0) {
					request.setAttribute("results", results);
					request.setAttribute("sum", results.size());
				}
			}
		} catch (Exception e) {
			log.error(" DoorServlet initMain [e" + e + "]");
		}
		request.getRequestDispatcher("/views/door/project.jsp").forward(request, response);
	}
	
	public void listDoorForProject(HttpServletRequest request,HttpServletResponse response, DoorService doorService){
		
		
		try{
			String projectNum = request.getParameter("projectNum");
			String projectName = request.getParameter("projectName");
			//解决URL中参数为中文时乱码的问题
			projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
			projectName = new String(projectName.getBytes("ISO-8859-1"),"UTF-8");
			doorService.getDto().put("projectNum", projectNum);
			doorService.getDto().put("projectName", projectName);
			//doorService.getDto().put("projectName", projectName);
			log.debug("DoorServlet  listDoorProject  dto = "+doorService.getDto());
			List<Door> doors =  doorService.listDoorForProject();
//			Object msgObj = request.getAttribute("msg");
			request.setAttribute("projectNum", projectNum);
			request.setAttribute("projectName", projectName);
			request.setAttribute("pno",request.getParameter("pno"));
//			if( null != msgObj ){
//				String msg = (String)msgObj ;
//				if(!"".equals(msg)){
//					request.setAttribute("msg", msg);
//				}
//			}
//			else{
//				request.setAttribute("msg","");
//			}
			if(null != doors && doors.size() > 0){
				DoorForSum doorForSum = doorService.getDoorForSum();
				request.setAttribute("doorForSum", doorForSum);
				request.setAttribute("doors", doors);
			}
			else{
				DoorForSum doorForSum = new DoorForSum();
				request.setAttribute("doors", doors);
				request.setAttribute("doorForSum", doorForSum);
			}
			/*if(null != doors && doors.size() > 0){
				request.setAttribute("sum",doors.size());
				doorForSum.setWorkersums(doors.size());
//				int nsums = 0;
//				int wsums = 0;
//				int msums = 0;
//				int dsums = 0;
//				int ssums = 0;
//				int hsums = 0;
//				int fsums = 0;
//				int jsums = 0;
//				int lsums = 0;
//				int tsums = 0;
//				int csums = 0;
				Door door = null;
				String wtype = "";
				for(int i=0; i<doors.size(); i++){
					door = doors.get(i);
					wtype = door.getwType();
					if(wtype != null && !"".equals(wtype)){
						if(wtype.equals("泥工"))
							doorForSum.setWsums(doorForSum.getWsums()+1);
						if(wtype.equals("1"))
							doorForSum.setWsums(doorForSum.getWsums()+1);
					}
				}
			}*/
			
			//从单个工程主界面跳转过来
			String backURL = request.getParameter("backURL") ;
			if(null != backURL && !"".equals(backURL)){
				request.setAttribute("superBackURL", backURL);
				request.removeAttribute("backURL");
			}
			else{
				request.setAttribute("backURL", "initMain");
				request.removeAttribute("superBackURL");
			}
			request.getRequestDispatcher("/views/door/doorMain.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void queryDoorByCondition(HttpServletRequest request,HttpServletResponse response, DoorService doorService){
		try {
			//解决URL中参数为中文时乱码的问题
			String projectName = request.getParameter("projectName");
			System.out.println(projectName);
			projectName = new String(projectName.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(projectName);
			doorService.getDto().put("projectName",projectName);
			
			String projectNum = request.getParameter("projectNum");
			System.out.println(projectNum);
			projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(projectNum);
			doorService.getDto().put("projectNum",projectNum);
			log.debug("DoorServlet  doorService  dto = "+doorService.getDto());
			
			List<Door> doors =  doorService.listDoorForProject();
			//List<Door> doors =  doorService.queryDoorByCondition();
			
			request.setAttribute("projectNum", projectNum);
			request.setAttribute("projectName", projectName);
			request.setAttribute("pno",request.getParameter("pno"));
            request.setAttribute("TimeForCondition",  (String) doorService.getDto().get("TimeForCondition"));   
			
			if(null != doors && doors.size() > 0){
				DoorForSum doorForSum = doorService.getDoorForSum();
				request.setAttribute("doorForSum", doorForSum);
				request.setAttribute("doors", doors);
			}
			else{
				DoorForSum doorForSum = new DoorForSum();
				request.setAttribute("doors", doors);
				request.setAttribute("doorForSum", doorForSum);
			}

			request.setAttribute("msg","");
			
			request.setAttribute("sum",doors.size());
			if(request.getParameter("CheckTime") != null)
				request.setAttribute("CheckTime", request.getParameter("CheckTime"));
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
			
			request.getRequestDispatcher("/views/door/doorMain.jsp").forward(request, response);
		} catch (Exception e) {
			log.error(" DoorServlet queryDoorByCondition ["+e+"]");
		}	
	}
	public void exportDoor(HttpServletRequest request, HttpServletResponse response, DoorService doorService) throws Exception{
		response.setContentType("octets/stream");
		
		String projectNum = request.getParameter("projectNum");
		System.out.println(projectNum);
		projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println(projectNum);
		doorService.getDto().put("projectNum",projectNum);
		
//		String excelName = new String(request.getParameter("projectNum").getBytes("ISO-8859-1"),"UTF-8");
		String projectName = request.getParameter("projectName");
		//解决URL中参数为中文时乱码的问题
		projectName = new String(projectName.getBytes("ISO-8859-1"),"UTF-8");
		String excelName = projectName + "门禁考勤";
		response.addHeader("Content-Disposition", "attachment;filename="+new String(excelName.getBytes("GBK"),"iso8859-1")+".xls");
		doorService.getDto().put("projectName", projectName);
		log.debug("DoorServlet  listDoorForProject  dto = "+doorService.getDto());
		String[] headers = new String[]{"编号","时间","姓名","性别","工种","部门","事件"}; 
		List<Door> doors =  doorService.listDoorForProject();
		log.debug("导出excel"+excelName);
		try {  
            OutputStream out = response.getOutputStream();
            HSSFWorkbook workbook =	ExcelUtil.getWorkBook(excelName, headers);
            HSSFSheet sheet = workbook.getSheetAt(0);
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            Map<String,Object> map = null;
            Door door = null;
            for(int i=0; i<doors.size(); i++){
            	door = doors.get(i);
            	map = new HashMap<String,Object>();
            	map.put("cno", door.getCno());
            	map.put("checkTime", door.getCheckTime());
            	map.put("wName", door.getwName());
            	map.put("wSex", door.getwSex());
            	map.put("wType", door.getwType());
            	map.put("wDept", door.getwDept());
            	map.put("cType", door.getcType());
            	list.add(map);
            }
            HSSFRow row = null;
            for(int i=0; i<list.size(); i++){
            	map = list.get(i);
            	row = sheet.createRow(i+1);
            	int j = 0;
            	row.createCell(j++).setCellValue(map.get("cno")!=null?map.get("cno").toString():"");
            	row.createCell(j++).setCellValue(map.get("checkTime")!=null?map.get("checkTime").toString():"");
            	row.createCell(j++).setCellValue(map.get("wName")!=null?map.get("wName").toString():"");
            	row.createCell(j++).setCellValue(map.get("wSex")!=null?map.get("wSex").toString():"");
            	row.createCell(j++).setCellValue(map.get("wDept")!=null?map.get("wDept").toString():"");
            	row.createCell(j++).setCellValue(map.get("wDept")!=null?map.get("wDept").toString():"");
            	row.createCell(j++).setCellValue(map.get("cType")!=null?map.get("cType").toString():"");
            	
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
