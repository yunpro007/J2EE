<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>智慧工地大数据云服务平台/首页</title>
<link rel="stylesheet" href="<%=path%>/css/initTable.css" />
<link rel="stylesheet" href="<%=path%>/css/01.css" />
<link rel="stylesheet" href="<%=path%>/css/02.css" />
<style type="text/css">

.td1{
	font-size: 12px;
	line-height: 28px;
}
.td2{
	
}
.th0>th
{
   font-size:12px;
   height: 30px;
   line-height: 30px;
   font-family: "Microsoft YaHei", "Tahoma", "Arial", '宋体';
   color:white;
}
td
{
    font-size: 12px;
    color: #656565;
    font-weight: normal;
    text-decoration: none;
    font-family: "Microsoft YaHei", "Tahoma", "Arial", '宋体';
}
td>a
{
    font-size: 12px;
    color: #656565;
    font-weight: normal;
    text-decoration: none;
    font-family: "Microsoft YaHei", "Tahoma", "Arial", '宋体';
}
.td0
{
   font-size: 12px;
    color: #656565;
    font-weight: normal;
    text-decoration: none;
    font-family: "Microsoft YaHei", "Tahoma", "Arial", '宋体';
}
</style>
</head>
<body>
	<div class="rside" style="padding-right:10px; padding-left:20px;margin-left:10px;">
		<table width="500px" border="0px" >
			<tr class="th0">
				<th scope="col">地区</th>
				<th scope="col">工地总数</th>
				<th scope="col">房屋建设</th>
				<th scope="col">市政工程</th>
				<th scope="col">今年开工</th>
				<th scope="col">今年完工</th>
			</tr>
			<c:if test="${cityMap != null }">
				<tr class="td0">
					<td><a href = "<%=path%>/project?method=getCityOrDistrictProjects&district=-1">${cityMap.cityContent}</a></td>
					<td>${cityMap.totalProjectsNum}</td>
					<td>${cityMap.totalSType1Num}</td>
					<td>${cityMap.totalSType2Num}</td>
					<td>${cityMap.totalBeginNum}</td>
					<td>${cityMap.totalEndNum}</td>
				</tr>
			</c:if>
			<c:if test="${infos1 != null }">
				<c:forEach items="${infos1}" var="ins" varStatus="vs">
					<tr class="td1">
						<td><a href = "<%=path%>/project?method=getCityOrDistrictProjects&district=${ins.district}">${ins.districtContent}</a></td>
						<td>${ins.projectsNum}</td>
						<td>${ins.sType1Num}</td>
						<td>${ins.sType2Num}</td>
						<td>${ins.beginNum}</td>
						<td>${ins.endNum}</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<c:if test = "${infos2 != null and mark != null and mark == true}">
		  <table width="500px" border="1" >
			<tr class="th0">
				<th width="250" scope="col">工地名称</th>
				<th width="81" scope="col">工地状态</th>
				<th width="81" scope="col">是否在线</th>
				<th width="72" scope="col">工地类型</th>
				<th width="61" scope="col">区域</th>
			</tr>
			<c:forEach items="${infos2}" var="ins" varStatus="vs">
					<tr class="td1">
						<td ><a href = "<%=path%>/project?method=searchProjectByID&pno=${ins.pno}" target = "mainFrame" onMouseOver = "window.parent.frames['mapFrame'].location.href = &quot;<%=path%>/project?method=getProjectPositions&pno=${ins.pno }&quot;">${ins.projectName}</a></td>
						<td>${ins.isOver}</td>
						<td>${ins.isOnline}</td>
						<td>${ins.projectType}</td>
						<td>${ins.belong}</td>
					</tr>
			</c:forEach>
		</table>
		</c:if>
	</div>
</body>
</html>
