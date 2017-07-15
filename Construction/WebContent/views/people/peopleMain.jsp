<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工程主界面</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/content-header.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css">


</head>
<body>
	<nav id="content-header"> <B>位置：</B>人证相符>>>${projectName}
	<div class="return">
		<c:if test="${backURL != null}">
			<a href="<%=path%>/people?method=initMain" target="mainFrame"><i
				class="fa fa-minus-square-o fa-lg"></i>&nbsp;返回</a>
		</c:if>
		<c:if test="${superBackURL != null}">
			<a href="<%=path %>/project?method=${superBackURL}&pno=${pno}"
				target="mainFrame"><i class="fa fa-minus-square-o fa-lg"></i>&nbsp;返回</a>
		</c:if>
	</div>
	</nav>
	<!--main_top-->
	<table width="99%" border="0" cellspacing="0" cellpadding="0"
		id="searchmain">
		<tr>
			<td><span> <img src="<%=path%>/images/people/people.png">
			</span></td>

		</tr>
	</table>
</body>

</html>