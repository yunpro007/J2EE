<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String path = request.getContextPath() ; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>门禁考勤</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/content-header.css"/>
<link rel="stylesheet" href="<%=path%>/css/main_list.css" />
<link rel="stylesheet" href="<%=path%>/css/Enironment_Monitor.css" />

<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/jquery-1.8.1.js"  type="text/javascript"></script>
<script src="<%=path %>/js/jquery-ui.js" type="text/javascript"></script>
<script src="<%=path %>/js/dateinput-ch-ZN.js" type="text/javascript"></script>
<link href="<%=path %>/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link href="<%=path %>/css/red-datepicker.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/font-awesome.min.css" type="text/css" rel="stylesheet"/>
</head>
<body>
	<nav id="content-header">
		    <B>位置：</B>门禁考勤>>>${projectName}
			<div class="return"> 
			       <a href="#" id="back"><i class="fa fa-minus-square-o fa-lg"></i>&nbsp;返回</a>
<%-- 			    <c:if test="${backURL != null}"> --%>
<%-- 				 	<a href="<%=path %>/door?method=initMain" target="mainFrame" ><i class="fa fa-minus-square-o fa-lg"></i>&nbsp;返回</a> --%>
<%-- 				</c:if> --%>
<%-- 				<c:if test="${superBackURL != null}"> --%>
<%-- 			    	<a href="<%=path %>/project?method=${superBackURL}&pno=${pno}" target="mainFrame"><i class="fa fa-minus-square-o fa-lg"></i>&nbsp;返回</a>	 --%>
<%-- 				</c:if> --%>
			</div>
	</nav>
	<div class="row header-content" style="margin:-3px 0px 0px 0px;">
		<form method="post"
			action="<%=path%>/door?method=queryDoorByCondition&projectNum=${projectNum}&projectName=${projectName}">
		  <div class="col-md-4 col-xs-4">
		  	    <label for="equipment_class" class="header-label">考勤时间:</label>
		  	    <input id="dateform" type="text" name="TimeForCondition" class="equipment_class"  >
		  </div>
		  <div class="col-md-4 col-xs-4">
		  	     <button type="submit" class="query">
						<i class="fa fa-search"></i>&nbsp;查询
				 </button>
				 
<%-- 		 <c:if test="${sessionScope.user.uType == 8}">&cno=${cno}&checkTime=${checkTime}&wName=${wName}&wSex=${wSex}&wType=${wType}&wDept=${wDept}&cType=${cType} --%>
				<a href="javascript:location.href=encodeURI('<%=path%>/door?method=exportDoor&projectNum=${projectNum}&projectName=${projectName}&TimeForCondition=${TimeForCondition}')"
				   class="add"><i class="fa fa-share-square-o fa-lg"></i>导出&nbsp;</a>
<%-- 		 </c:if> --%>
		  </div></form>
	</div>
	<div class="row header-content" style="margin:-3px 0px 0px 0px;">
		<div class="row">
			  <div class="col-md-2">工人总数 : ${doorForSum.workersums}</div>
			  <div class="col-md-2">水泥工 : ${doorForSum.nsums}</div>
			  <div class="col-md-2">瓦工 : ${doorForSum.wsums}</div>
			  <div class="col-md-2">木工 : ${doorForSum.msums}</div>
			  <div class="col-md-2">电工 : ${doorForSum.dsums}</div>
			  <div class="col-md-2">施工员 : ${doorForSum.ssums}</div>
		 </div>
		 <div class="row">
		 	  <div class="col-md-2">焊工 : ${doorForSum.hsums}</div>
			  <div class="col-md-2">放线员 : ${doorForSum.fsums}</div>
			  <div class="col-md-2">监理 : ${doorForSum.jsums}</div>
			  <div class="col-md-2">力工 : ${doorForSum.lsums}</div>
			  <div class="col-md-2">塔吊驾驶员 : ${doorForSum.tsums}</div>
			  <div class="col-md-2">铲车驾驶员 : ${doorForSum.csums}</div>
		 </div>
	</div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		id="searchmain">
		<tr>
			<td align="left" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					id="main-tab">
					<tr>
						<th align="center" valign="middle" width="50" class="borderright">编号</th>
						<th align="center" valign="middle" width="50" class="borderright">时间</th>
						<th align="center" valign="middle" width="120" class="borderright">姓名</th>
					 	<th align="center" valign="middle" width="120" class="borderright">性别</th>
						<th align="center" valign="middle" width="120" class="borderright">工种</th>
						<th align="center" valign="middle" width="120" class="borderright">部门</th>
						<th align="center" valign="middle" width="120" class="borderright">事件</th>
						<c:if test="${ sessionScope.user.uType == 8 }">
						<!-- 项目部 -->
						   <th align="center" valign="middle" width="100" class="borderright">操作</th>
						</c:if>
					</tr>
					<c:if test="${requestScope.doors != null }">
						<c:forEach items="${doors}" var="ins" varStatus="vs" begin="0">
							<tr class="bggray"
								onMouseOut="this.style.backgroundColor='#f9f9f9'"
								onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="center" valign="middle" width="50"
									class="borderright borderbottom">${vs.index+1}</td>
								<td align="center" valign="middle" width="100"
									class="borderright borderbottom">${ins.checkTime}</td>
								<td align="center" valign="middle" width="120"
									class="borderright borderbottom">${ins.wName}</td>
								<td align="center" valign="middle" width="120"
									class="borderright borderbottom">${ins.wSex }</td>
								<td align="center" valign="middle"  width="120"
									class="borderright borderbottom">${ins.wType }</td>
								<td align="center" valign="middle"  width="120"
									class="borderright borderbottom">${ins.wDept}</td>
								<td align="center" valign="middle"  width="120"
									class="borderright borderbottom">${ins.cType}</td>
								<c:if test="${sessionScope.user.uType == 8}">
									<td align="center" valign="middle" class="borderbottom"
										width="80">
									   <a	href="#" class="edit">编辑</a> <span class="gray">&nbsp;|&nbsp;</span>
										<a	href="#" class="edit">删除</a></td>
								</c:if>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</td>
		</tr>
		<tr>
			<td align="left" valign="top" class="fenye">${sum}条数据
			<!-- 			1/1 -->
<!-- 				页&nbsp;&nbsp;<a href="#" target="mainFrame" onFocus="this.blur()">首页</a>&nbsp;&nbsp;<a -->
<!-- 				href="#" target="mainFrame" onFocus="this.blur()">上一页</a>&nbsp;&nbsp;<a -->
<!-- 				href="#" target="mainFrame" onFocus="this.blur()">下一页</a>&nbsp;&nbsp;<a -->
<!-- 				href="#" target="mainFrame" onFocus="this.blur()">尾页</a> -->
			</td>

		</tr>
	</table>
</body>
<script type="text/javascript">
$(document).ready(function() {
    $("#dateform").datepicker(
    {
    });
  });
$("#back").click(function(){
	   history.go(-1);
});
</script>
</html>