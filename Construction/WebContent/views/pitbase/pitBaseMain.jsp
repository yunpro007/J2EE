<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主要内容区main</title>
<link rel="stylesheet" href="<%=path%>/css/main_list.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/content-header.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/font-awesome.min.css" />
<link href="<%=path%>/css/css.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>/css/main.css" type="text/css" rel="stylesheet" />
<link rel="shortcut icon" href="<%=path%>/images/main/favicon.ico" />
<style>
body {
	overflow-x: hidden;
	background: #f2f0f5;
	padding: 15px 0px 10px 5px;
}

#searchmain {
	font-size: 12px;
}

#search {
	font-size: 12px;
	background: #548fc9;
	margin: 10px 10px 0 0;
	display: inline;
	width: 100%;
	color: #FFF;
	float: left
}
</style>

</head>
<body>
<nav id="content-header"> <B>位置：</B>坑基检测
	<div class="return">
		<a href="#"><i class="fa fa-minus-square-o fa-lg"></i>&nbsp;返回</a>
	</div>
	</nav>
	<!--main_top-->
	<table width="99%" border="0" cellspacing="0" cellpadding="0"
		id="searchmain">
		<tr>
			<td><span> <img src="<%=path%>/images/foundation.jpg">
			</span></td>

		</tr>
	</table>
</body>

</html>