<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>智慧建管大数据一体化云平台</title>
		<link href="<%=path%>/usermanage/show/Login_show/css/bootstrap.min.css" rel="stylesheet">
		<link href="<%=path%>/usermanage/show/Login_show/css/font-awesome.min.css" rel="stylesheet">
	   <link rel="stylesheet" href="<%=path%>/usermanage/show/Login_show/load.css">
</head>
<body class="login-body">
		<div class="container">
			<div class="container_left">
			</div>
			<div class="container_right">
				<div id="login" class="img-rounded">
					<form action="<%=path%>/UserManage/AdminServlet" method="post" class="form-horizontal" role="form">
						<input type="hidden" name="method" value="login"/>
						<div style="color: red; font-weight: 900"><br/>${msg }</div><br>
						<div class="form-group">
							<span for="username" class="col-sm-4 control-label">用户名</i></span>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="username" name="username" value=${u }></input>
							</div>
						</div>
						<div class="form-group">
							<span for="password" class="col-sm-4 control-label">密码</span>
							<div class="col-sm-8">
								<input type="password" class="form-control" id="username" name="password" value=${p }></input>
							</div>
						</div>
						<button class="btn btn-lg btn-login btn-block m_btn" type="submit">
							登录
						</button>
						
					</form>
				</div>
			</div>
		</div>
		<script src="<%=path%>/usermanage/js/jquery-3.1.1.min.js"></script>
	</body>
</html>
