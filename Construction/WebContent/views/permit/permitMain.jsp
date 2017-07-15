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
	<nav id="content-header"> <B>位置：</B>施工许可>>>${projectName}
	<div class="return">
		<a href="#" id="back"><i class="fa fa-minus-square-o fa-lg"></i>&nbsp;返回</a>
	</div>
	</nav>
	<!--main_top-->
	<table width="99%" border="0" cellspacing="0" cellpadding="0"
		id="searchmain">
		<tr>
		   <td>
		     <span>
		      <img alt="" class="image"  src="<%=path%>/commons?method=getImage&filePath=projectPath&fileName=${permission}">
		     </span>
		   </td>
			
		</tr>
	</table>
</body>
<script src="<%=path %>/js/jquery-1.8.1.js"  type="text/javascript"></script>
<script type="text/javascript">

$("#back").click(function(){
	   history.go(-1);
});
</script>
</html>