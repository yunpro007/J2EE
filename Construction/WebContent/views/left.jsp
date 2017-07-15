<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>left</title>
	<link href="<%=path%>/css/left-nav.css" rel="stylesheet" type="text/css" />
	<link href="<%=path%>/css/font-awesome.min.css" rel="stylesheet" type="text/css" />  
</head>
<body style="height:auto;">
		<div id="jquery-accordion-menu" class="jquery-accordion-menu blue">
		<div class="jquery-accordion-menu-header">智慧建管 <i class="fa fa-angle-double-down"></i></div>
		<ul id="demo-list">
			<c:if test="${sessionScope.user != null }">
		     <c:if test="${modules != null}">
		     	<c:forEach  items="${modules}" var="item" varStatus="status">
		     		<c:choose>
		     			<c:when test="${item.mname == '工程分布'}">
		    				<li class="active"><a href="<%=path%>/${item.hyperlink}" target="_top"><i class="fa fa-cubes"></i>工程分布 </a></li>
		     			</c:when>
		     			<c:when test="${item.mname == '工程概况'}">
							<li><a href="<%=path%>/${item.hyperlink}" target="mainFrame"><i class="fa fa-glass"></i>工程概况 </a></li>
		     			</c:when>
		     			<c:when test="${item.mname == '形象进度' }">
							<li><a href="<%=path%>/${item.hyperlink}" target="mainFrame"><i class="fa fa-file-image-o"></i>形象进度</a></li>
		     			</c:when>
		     			<c:when test="${item.mname == '工程检测' }">
							<li><a href="${item.hyperlink}" target="_blank" ><i class="fa fa-sitemap"></i>工程检测</a></li>
		     			</c:when>
		     			<c:when test="${item.mname == '夜间施工' }">
							<li><a href="<%=path%>/${item.hyperlink}" target="mainFrame"><i class="fa fa-lightbulb-o"></i>夜间施工</a></li>
		     			</c:when>
		     			<c:when test="${item.mname == '工地视频' }">
							<li><a href="<%=path%>/${item.hyperlink}" target="mainFrame"><i class="fa  fa-video-camera"></i>工地视频</a></li>
		     			</c:when>
		     			<c:when test="${item.mname == '基坑监测' }">
							<li><a href="<%=path%>/${item.hyperlink}" target="mainFrame"><i class="fa  fa-sitemap"></i>基坑检测</a></li>
		     			</c:when>
		     			<c:when test="${item.mname == '起重设备' }">
							<li><a href="${item.hyperlink}" target="mainFrame"><i class="fa  fa-tachometer"></i>起重设备 </a></li>
		     			</c:when>
		     				<c:when test="${item.mname == '人证相符' }">
						    <li><a href="<%=path%>/${item.hyperlink}" target="mainFrame"><i class="fa fa-graduation-cap"></i>人证相符 </a></li>
		     			</c:when>
		     			<c:when test="${item.mname == '人员培训' }">
							<li><a href="${item.hyperlink}" target="mainFrame" ><i class="fa fa-group"></i>人员培训 </a></li>
		     			</c:when>
		     			<c:when test="${item.mname == '样板工地' }">
							<li><a href="<%=path%>/${item.hyperlink}" target="mainFrame"><i class="fa fa-cube"></i>样板工地 </a></li>
		     			</c:when>
		     			<c:when test="${item.mname == '执法情况' }">
						    <li><a href="<%=path%>/${item.hyperlink}" target="mainFrame"><i class="fa fa-bullhorn"></i>执法情况 </a></li>
		     			</c:when>
						<c:when test="${item.mname == '环境监测' }">
							<li><a href="<%=path%>/${item.hyperlink}" target="mainFrame"><i class="fa fa-recycle"></i>环境监测 </a></li>
		     			</c:when>
						<c:when test="${item.mname == '门禁考勤' }">
							<li><a href="<%=path%>/${item.hyperlink}" target="mainFrame"><i class="fa f fa-road"></i>门禁考勤</a></li>
		     			</c:when>
		     			<c:when test="${item.mname == '奖惩情况' }">
							<li><a href="<%=path%>/${item.hyperlink}" target="mainFrame"><i class="fa fa-institution"></i>奖惩情况</a></li>
		     			</c:when>
		     			<c:when test="${item.mname == '施工许可' }">
							<li><a href="<%=path%>/${item.hyperlink}" target="mainFrame"><i class="fa  fa-anchor"></i>施工许可</a></li>
		     			</c:when>
		     			<c:when test="${item.mname == '诚信评价' }">
							<li><a href="${item.hyperlink}" target="mainFrame"><i class="fa fa-joomla"></i>诚信评价</a></li>
		     			</c:when>
		     			<c:otherwise>
		     				<li><a href="${item.hyperlink}" target="mainFrame"><i class="fa fa-envelope"></i>${item.mname}</a></li>
		     			</c:otherwise>	
		     		</c:choose>
		     	</c:forEach>
		     </c:if>
		  </c:if> 
		</ul>
	</div>
	
	<!--
    	描述：默认时第一列选中，鼠标点击时切换
    -->
	<script type="text/javascript" src="<%=path%>/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript">
	$(function(){	
	$("#demo-list li").click(function(){
		$("#demo-list li.active").removeClass("active")
		$(this).addClass("active");
	})	
})	
</script>
	</body>
</html>