package com.ql.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ql.model.Builder;
import com.ql.model.Limit;
import com.ql.model.User;
import com.ql.service.AdminBuilderService;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

@WebServlet("/UserManage/AdminBuilderServlet")
public class AdminBuilderServlet extends BaseServlet {
	AdminBuilderService adminBuilderService = new AdminBuilderService();
	/*
	 * 加载未删除用户信息
	 */
	public String loadBuilderMsg(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int flag = 1;
		List<Builder> list = adminBuilderService.loadBuilderMsg(flag);
		request.setAttribute("flag", flag);
		request.setAttribute("builderlist", list);
		return "f:/usermanage/Builder.jsp";
	}
	
	/*
	 * 加载已删除用户信息
	 */
	public String loadDelBuilderMsg(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int flag = 0;
		List<Builder> list = adminBuilderService.loaddelMsg(flag);
		request.setAttribute("flag", flag);
		request.setAttribute("builderlist", list);
		return "f:/usermanage/Builder.jsp";
	}
	
	/*
	 * 查询用户信息
	 */
	public String queryBuilder(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String sflag = request.getParameter("flag");
		int flag = 1;
		if(null != sflag)
			flag = Integer.parseInt(sflag);
		String bName = request.getParameter("bName");
		
		List<Builder> list;
		if("" == bName)
			list = adminBuilderService.loadBuilderMsg(flag);
		else
			list = adminBuilderService.queryBuilder(bName,flag);

		request.setAttribute("flag", flag);
		request.setAttribute("builderlist", list);
		return "f:/usermanage/Builder.jsp";
	}
	
	/*
	 * 删除、恢复
	 */
	public String del(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String sflag = request.getParameter("flag");
		request.setAttribute("flag", sflag);
		int flag = 1;
		if(null != sflag)
			flag = Integer.parseInt(sflag);
		int fl = flag==0 ? 1:0;
		String suno = request.getParameter("bno");
		int bno = Integer.parseInt(suno);

		adminBuilderService.del(bno,fl);
		if(flag == 1)
			return loadBuilderMsg(request,response);
		else
			return loadDelBuilderMsg(request,response);
	}
	
	/*
	 * 按id查询客户
	 */
	public String findBuilderById(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String suno = request.getParameter("bno");
		int bno = Integer.parseInt(suno);
		Builder builder = adminBuilderService.findById(bno);
		request.setAttribute("builder", builder);
		return "f:/usermanage/AddBuilder.jsp";
	}
	
	/*
	 * 添加客户
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Builder builders = CommonUtils.toBean(request.getParameterMap(), Builder.class);
		builders.setFlag(1);
		
		System.out.println(builders);
		
		adminBuilderService.add(builders);
		return loadBuilderMsg(request,response);
	}
	
	/*
	 * 编辑客户
	 */
	public String modify(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Builder builders = CommonUtils.toBean(request.getParameterMap(), Builder.class);
		builders.setFlag(1);
		String suno = request.getParameter("bno");
		int bno = Integer.parseInt(suno);
		builders.setBno(bno);
		
		adminBuilderService.modify(builders);
		return loadBuilderMsg(request,response);
	}
	
	/*
	 * 更改权限类型
	 */
	public String change(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String suType = request.getParameter("uType");
		int uType = 0;
		if("" != suType)
			uType = Integer.parseInt(suType);
		
		Map<String,String[]> tem=request.getParameterMap();
		Set<Map.Entry<String, String[]>> entrySet=tem.entrySet();
		
		String val[]=null;
		Map<String,Object> dto=new HashMap<>();
		for(Map.Entry<String, String[]>  entry : entrySet){
		     val=entry.getValue();
		     dto.put(entry.getKey(), val);
		     if(entry.getKey().equals("mno")){
		    	 adminBuilderService.dellimit(uType);
		    	 for(int i=0; i<val.length; i++){
		    		 adminBuilderService.change(uType,val[i]);
		    	 }
		     }
		}
		return "r:/usermanage/1.jsp";
	}
	
	/*
	 * 加载权限类型
	 */
	public String loadlimit(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String suType = request.getParameter("uType");
		request.setAttribute("uType", suType);
		int uType = 0;
		if("" != suType)
			uType = Integer.parseInt(suType);
		List<Limit> list = adminBuilderService.loadlimit(uType);
		int[] limitval = new int[list.size()];
		for(int i=0; i<list.size(); i++){
			limitval[i] = list.get(i).getMno();
		}
		request.setAttribute("limitval", limitval);
		return "f:/usermanage/AddAuthority.jsp";
	}
}
