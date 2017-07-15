<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
 <meta http-equiv="X-UA-Compatible" content="IE=9"/>
 <meta http-equiv="X-UA-Compatible" content="IE=10"/> 
<title>智慧建管大数据一体化云平台</title>
<link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=path%>/css/style.css" rel="stylesheet">
<link href="<%=path%>/css/font-awesome.min.css" rel="stylesheet">
</head>
<body class="login-body">
  <div class="container">
    <div ><a href="#" class="role">管理登录&nbsp;<i class="fa fa-angle-double-right fa-lg  flicker"></i></a></div>
    <form class="form-signin" action="<%=path%>/user?method=checkLogin"  method="post">
        <div class="login-wrap">
		    <div class="form-group">
                <div class="input-group" >
                  <span class="input-group-addon"><span class="fa fa-user fa-lg"></span></span>
                  <input type="text" class="form-control" name="username" >
                </div>
            </div>
			<div class="form-group">
                <div class="input-group" >
                  <span class="input-group-addon"><span class="fa fa-unlock-alt fa-lg" style="width:15px;"></span></span>
                  	<input type="password" class="form-control" name="password" >
                </div>
            </div>
            <!-- 
            <div class="error">登录失败！！</div>-->
            <button class="btn btn-lg btn-login btn-block" type="submit">
               	 登录
            </button>
			<div class="link">
                <a class="link" href="<%=path%>/user?method=touristLogin">
                 	   游客登录
                </a>
            </div>
        </div>
    </form>
  </div>
</body>
</html>