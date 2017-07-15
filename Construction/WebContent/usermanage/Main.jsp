<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html >
<html>
<head>
<style type="text/css">
.sp{float: right;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>智慧建管大数据一体化云平台</title>
		<link href="<%=path%>/usermanage/show/Operation_show/css/bootstrap.min.css" rel="stylesheet">
		<link href="<%=path%>/usermanage/show/Operation_show/css/font-awesome.min.css" rel="stylesheet">
		<link rel="stylesheet" href="<%=path%>/usermanage/show/Operation_show/operation.css">
</head>
<span class="sp"><a href="<%=path%>/UserManage/AdminServlet?method=quit">退出登录</a></span>
<body class="welcome-body">
		<div id="container" class="img-rounded">
		<form class="form" role="form" >
			<div class="form-body">
				<span class="login-load">登录成功，欢迎您！</span>
			</div>
			<div class="manage">
				<span class="typ">请选择权限类型</span>
				<div class="form-body ">
					<ul>
						<li class="user-manage"><a href="<%=path%>/UserManage/AdminUserServlet?method=loadUserMsg">用户信息管理</a></li>
						<li><a href="<%=path%>/UserManage/AdminBuilderServlet?method=loadBuilderMsg">总包公司信息管理</a></li>
						<li><a href="1.jsp">用户权限管理</a></li>
					</ul> 
				</div>
			</div>
		</form>
		</div>
		<script src="<%=path%>/usermanage/js/jquery-3.1.1.min.js"></script>
	</body>
</html>