<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
	String mark = (String)request.getSession().getAttribute("mark");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>智慧建管大数据一体化云平台</title>
		<link href="<%=path%>/usermanage/show/AuthOpe_show/css/bootstrap.min.css" rel="stylesheet">
	   	<link href="<%=path%>/usermanage/show/AuthOpe_show/css/font-awesome.min.css" rel="stylesheet">
		<link rel="stylesheet" href="<%=path%>/usermanage/show/AuthOpe_show/administration.css">
<script type="text/javascript">
	function mess()
	{
		<%if("1".equals(mark)) {request.getSession().setAttribute("mark", "0"); %>
		alert("保存成功，单击确定返回");
		<%}%>
	}
</script>
</head>
<body class="main-body" onload="mess()">
	<div class="container img-rounded">
<div align="center"><a href="<%=path%>/usermanage/Main.jsp">返回</a></div>
<div class="top-box">
	<span>请选择权限类型：</span>
	</div>
	<div class="middle-box">
	<ul>
		<li><a href="<%=path%>/UserManage/AdminBuilderServlet?method=loadlimit&uType=1">公众</a></li>
		<li><a href="<%=path%>/UserManage/AdminBuilderServlet?method=loadlimit&uType=2">检测机构</a></li>
		<li><a href="<%=path%>/UserManage/AdminBuilderServlet?method=loadlimit&uType=3'">城乡建设委员会</a></li>
		<li><a href="<%=path%>/UserManage/AdminBuilderServlet?method=loadlimit&uType=4">队伍管理处</a></li>
		<li><a href="<%=path%>/UserManage/AdminBuilderServlet?method=loadlimit&uType=5">安全监督</a></li>
		<li><a href="<%=path%>/UserManage/AdminBuilderServlet?method=loadlimit&uType=6">质量监督</a></li>
		<li><a href="<%=path%>/UserManage/AdminBuilderServlet?method=loadlimit&uType=7">建设单位</a></li>
		<li><a href="<%=path%>/UserManage/AdminBuilderServlet?method=loadlimit&uType=8">施工总包单位</a></li>
		<li><a href="<%=path%>/UserManage/AdminBuilderServlet?method=loadlimit&uType=9">项目部</a></li>
		<li><a href="<%=path%>/UserManage/AdminBuilderServlet?method=loadlimit&uType=10">监理单位</a></li>
		<li><a href="<%=path%>/UserManage/AdminBuilderServlet?method=loadlimit&uType=11">建筑租赁公司</a></li>
	</ul>
</div>
</div>
<script src="<%=path%>/usermanage/js/jquery-3.1.1.min.js"></script>
</body>
</html>