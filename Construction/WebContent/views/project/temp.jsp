<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
   
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>智慧工地大数据云服务平台/首页</title>
<link rel="stylesheet" href="<%=basePath%>/css/initTable.css" />
<link rel="stylesheet" href="<%=basePath%>/css/01.css" />
<link rel="stylesheet" href="<%=basePath%>/css/02.css" />
</head>
<script>
  function solve(object){
	  alert(object.innerText);
}
 
</script>
<body>
    <Strong>我已将该project存放在requset中</Strong>
	<div class="rside">
		<table width="500" border="0">
			<tr class="th0">
				<th scope="col">项目ID</th>
			</tr>
			<c:if test="${project != null }">
				<tr class="td0">
					<td><a href = "<%=path%>/project?method=getDistrictProjects&district=-1">${project.projectName}</a></td>
				</tr>
			</c:if>
		</table>
	</div>
</body>
</html>
