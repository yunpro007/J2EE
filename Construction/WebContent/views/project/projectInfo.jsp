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
<title>工程详细信息</title>
<link href="<%=path %>/css/css.css" type="text/css" rel="stylesheet" />
<link href="<%=path %>/main.css" type="text/css" rel="stylesheet" />
<link href="<%=path %>/css/font-awesome.min.css" type="text/css" rel="stylesheet">

<link rel="shortcut icon" href="<%=path %>/images/main/favicon.ico" />
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.min.js"></script>
<style>
body {
	overflow-x: hidden;
	background: #f2f0f5;
	padding: 0px 0px 10px 0px;
}
#main-tab {
	border: 1px solid #eaeaea;
	background: #FFF;
	font-size: 12px;
}
#main-tab th {
	font-size: 12px;
	background: url(<%=path %>/images/main/list_bg.jpg) repeat-x;
	height: 32px;
	line-height: 32px;
}
#main-tab td {
	font-size: 12px;
	line-height: 40px;
}
#main-tab td a {
	font-size: 12px;
	color: #548fc9;
}
#main-tab td a:hover {
	color: #565656;
	text-decoration: underline;
}

.bordertop {
	border-top: 1px solid #ebebeb
}
.borderright {
	border-right: 1px solid #ebebeb
}
.borderbottom {
	border-bottom: 1px solid #ebebeb
}
.borderleft {
	border-left: 1px solid #ebebeb
}
.gray {
	color: #dbdbdb;
}
.bggray {
	background: #f9f9f9;
	font-size: 14px;
	font-weight: bold;
	padding: 10px 10px 10px 0;
	width: 120px;
}
.main-for {
	padding: 10px;
}
.main-for input.text-word {
	width: 240px;
	height: 36px;
	line-height: 36px;
	border: #ebebeb 1px solid;
	background: #FFF;
	font-family: "Microsoft YaHei", "Tahoma", "Arial", '宋体';
	padding: 0 10px;
}
</style>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/content-header.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css">
</head>
<body>

    <nav id="content-header"> <B>位置：</B>工程详细信息>>>${project.projectName}
    <div class="return">
		<c:if test="${superBackURL != null }">
				<a href="<%=path%>/project?method=${superBackURL}&pno=${project.pno}" target="mainFrame"><i
													class="fa fa-minus-square-o fa-lg"></i>返回&nbsp;</a>
			</c:if>
			<c:if test="${backURL != null }">
				<a href="<%=path%>/project?method=${backURL}" target="mainFrame"><i
													class="fa fa-minus-square-o fa-lg"></i>返回&nbsp;</a>
			</c:if>
	</div>
	
	<!--main_top-->
	<table width="99%" border="0" cellspacing="0" cellpadding="0"
		id="searchmain">
<!-- 		<tr> -->
<!-- 			<td width="80%" align="left" valign="top">您的位置：工程概况&nbsp;&nbsp;&gt;&nbsp;&nbsp;工程信息</td> -->
<!-- 			<td width="20%" align="right" valign="top"> -->
<%-- 				<c:if test="${superBackURL != null }"> --%>
<%-- 				<a href="<%=path%>/project?method=${superBackURL}&pno=${project.pno}" target="mainFrame"><i --%>
<!-- 													class="fa fa-sign-out fa-lg"></i>返回&nbsp;</a> -->
<%-- 			</c:if> --%>
<%-- 			<c:if test="${backURL != null }"> --%>
<%-- 				<a href="<%=path%>/project?method=${backURL}" target="mainFrame"><i --%>
<!-- 													class="fa fa-sign-out fa-lg"></i>返回&nbsp;</a> -->
<%-- 			</c:if> --%>
<!-- 			</td> -->
<!-- 		</tr> -->
		
		<tr>
			<td align="left" valign="top">
				<form method="post" action="">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						id="main-tab">
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">工程名称 ：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><span>${project.projectName }</span> </td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">工程地址:</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for">
								<span>${project.location }</span>
							</td>
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">施工许可证：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for">
							    <span><a href="<%=path%>/permit?method=showSiteMain&permission=${project.permission}&backURL=${backURL}&pno=${project.pno}&projectName=${project.projectName}">查看</a></span>
							</td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">建设单位：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for">
								<span>${project.investor }</span>
							</td>
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">勘察单位：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for">
							</td>	
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">设计单位：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for">
							</td>							
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">施工单位：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for">
								<span >${project.bName } </span>
							</td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">监理单位：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for">
								<span>${project.supervisor }</span>
							</td>
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">质量监督单位：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for">
								<span>${project.supervisor }</span>
							</td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">安全监督单位：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for">
								<span>${project.managementS }</span>
							</td>
							
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'" >
						    <td align="right" valign="middle"
								class="borderright borderbottom bggray">初步设计审查&nbsp;</td>
							<td align="left" valign="middle"  colspan="3"
								class="borderright borderbottom main-for">
								<a href="#">查看</a>
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>