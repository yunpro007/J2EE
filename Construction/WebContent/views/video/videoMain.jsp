<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String path = request.getContextPath() ; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工地视频</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/content-header.css"/>
<link rel="stylesheet" href="<%=path%>/css/main_list.css" />
<link rel="stylesheet" href="<%=path%>/css/Enironment_Monitor.css" />
<link href="<%=path %>/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link href="<%=path %>/css/red-datepicker.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/font-awesome.min.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/jquery-1.8.1.js"  type="text/javascript"></script>
<script src="<%=path %>/js/jquery-ui.js" type="text/javascript"></script>
<script src="<%=path %>/js/dateinput-ch-ZN.js" type="text/javascript"></script>
</head>
<body>
	<nav id="content-header">
		    <B>位置：</B>工地视频>>>${projectName}
			<div class="return"> 
			    <c:if test="${backURL != null}">
				 	<a href="<%=path %>/video?method=initMain" target="mainFrame" ><i class="fa fa-minus-square-o fa-lg"></i>&nbsp;返回</a>
				</c:if>
				<c:if test="${superBackURL != null}">
			    	<a href="javascript:location.href=encodeURI('<%=path %>/project?method=${superBackURL}&pno=${pno}')" target="mainFrame"><i class="fa fa-minus-square-o fa-lg"></i>&nbsp;返回</a>	
				</c:if>
			</div>
	</nav>
	<!-- <div class="row header-content" style="margin:-3px 0px 0px 0px;">
		  <div class="col-md-4 col-xs-4">
		  	    <label for="equipment_class" class="header-label">摄像头:</label>
		  	    <input id="dateform" type="text" name="TimeForCondition" class="equipment_class"  >
		  </div>
		  <div class="col-md-4 col-xs-4">
		  	       <button type="submit" class="query">
						<i class="fa fa-search"></i>&nbsp;查询
				 </button>
		  </div>
	</div> -->
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		id="searchmain">
		<tr>
			<td align="left" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					id="main-tab">
					<tr>
						<th align="center" valign="middle" width="50" class="borderright">编号</th>
						<th align="center" valign="middle" width="100" class="borderright">工程编号</th>
						<th align="center" valign="middle" width="50" class="borderright">摄像头序列号</th>
						<th align="center" valign="middle" width="120" class="borderright">摄像头通道号</th>
					 	<th align="center" valign="middle" width="120" class="borderright">摄像头名称</th>
						<th align="center" valign="middle" width="120" class="borderright">操作</th>
		
					</tr>
					<c:if test="${requestScope.videos != null }">
						<c:forEach items="${videos}" var="ins" varStatus="vs">
							<tr class="bggray"
								onMouseOut="this.style.backgroundColor='#f9f9f9'"
								onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="center" valign="middle" width="50"
									class="borderright borderbottom">${ins.ano}</td>
								<td align="center" valign="middle" width="50"
									class="borderright borderbottom">${ins.projectNum}</td>
								<td align="center" valign="middle" width="120"
									class="borderright borderbottom">${ins.caNo}</td>
								<td align="center" valign="middle" width="120"
									class="borderright borderbottom">${ins.caCh }</td>
								<td align="center" valign="middle"  width="120"
									class="borderright borderbottom">${ins.caName }</td>
								
									<td align="center" valign="middle" class="borderbottom"
										width="80"><a
										href="${ins.caAd } " 
										target="mainFrame" class="edit">预览地址</a> <span class="gray">&nbsp;|&nbsp;</span>
										
								
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</td>
		</tr>
		<tr>
			<td align="left" valign="top" class="fenye">${sum}条数据1/1
				页&nbsp;&nbsp;<a href="#" target="mainFrame" onFocus="this.blur()">首页</a>&nbsp;&nbsp;<a
				href="#" target="mainFrame" onFocus="this.blur()">上一页</a>&nbsp;&nbsp;<a
				href="#" target="mainFrame" onFocus="this.blur()">下一页</a>&nbsp;&nbsp;<a
				href="#" target="mainFrame" onFocus="this.blur()">尾页</a>
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
</script>
</html>