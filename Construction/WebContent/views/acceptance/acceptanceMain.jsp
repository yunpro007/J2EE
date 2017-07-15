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
<title>主要内容区main</title>
<link rel="stylesheet" href="<%=path%>/css/Enironment_Monitor.css" />
<link rel="stylesheet" href="<%=path%>/css/main_list.css" />
<link rel="stylesheet" href="<%=path%>/css/content-header.css" />
<link href="<%=path%>/css/jquery-ui.css" type="text/css"
	rel="stylesheet" />
<link href="<%=path%>/css/red-datepicker.css" type="text/css"
	rel="stylesheet" />
<link href="<%=path%>/css/font-awesome.min.css" type="text/css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/bootstrap.min.css">

<script type="text/javascript" src="<%=path%>/js/jquery-1.10.2.min.js"></script>
<script src="<%=path%>/js/jquery-1.8.1.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/json-minified.js"></script>
<script src="<%=path%>/js/jquery-ui.js" type="text/javascript"></script>
<script src="<%=path%>/js/dateinput-ch-ZN.js" type="text/javascript"></script>
</head>
<body>
	<!--main_top-->
	<nav id="content-header"> <B>位置：</B>分布验收>>>${projectName} <c:if
		test="${backURL != null }">
		<div class="return">
			<a href="<%=path%>/progress?method=initProgress" target="mainFrame"
				onFocus="this.blur()"> <i class="fa fa-minus-square-o fa-lg"></i>返回&nbsp;
			</a>
		</div>
	</c:if> <c:if test="${superBackURL != null }">
		<div class="return">
			<a
				href="javascript:location.href=encodeURI('<%=path %>/project?method=${superBackURL}&pno=${pno}&projectName=${projectName}')"
				target="mainFrame" onFocus="this.blur()"> <i
				class="fa fa-minus-square-o fa-lg"></i>返回&nbsp;
			</a>
		</div>
	</c:if> </nav>
	<div class="header-query">
		<div class="row" style="margin: 0px; width: 98%;">
			<div class="col-md-12">
				<form method="post"
					action="<%=path%>/acceptance?method=queryAcceptanceByCondition&projectNum=${projectNum}&projectName=${projectName}">
					<div class="row">
						<div class="col-sm-4">
							<label class="form_query">验收项目&nbsp;:&nbsp;</label> <select
								name="qoaType" id="level" class="equipment_class"
								onchange="onSelectChange(this,'subLevel');"></select>
						</div>
						<div class="col-sm-4">
							<label class="form_query">类别&nbsp;:&nbsp;</label> <select
								name="qodetail" id="subLevel" class="equipment_class"></select>
						</div>
						<div class="col-sm-4"></div>
					</div>
					<div class="row" style="width: 98%;">
						<div class="col-sm-4">
							<label class="form_query">验收时间&nbsp;:&nbsp;</label> <input
								id="dateform" type="text" name="qopTime" class="equipment_class">
						</div>
						<div class="col-sm-4"></div>
						<div class="col-sm-4">
							<button type="submit" class="query">
								<i class="fa fa-search"></i>&nbsp;查询
							</button>
							
							    <a href="javascript:location.href=encodeURI('<%=path%>/acceptance?method=exportAcceptance&projectNum=${projectNum}&qoaType=${qoaType}&qodetail=${qodetail}&qopTime=${qodetail}&projectName=${projectName}')"
								   class="add"><i class="fa fa-share-square-o fa-lg"></i>导出&nbsp;</a>
						    <c:if test="${sessionScope.user.uType == 8 }">
								<a
									href="javascript:location.href=encodeURI('<%=path %>/views/acceptance/addAcceptance.jsp?projectNum=${projectNum}&backURL=${superBackURL}&pno=${pno}&projectName=${projectName}')"
									class="add"><i class="fa fa-plus"></i>&nbsp;新增</a>
							</c:if>
						</div>
					</div>
					<div>
						<!-- 维持返回链接的URL -->
						<input type="hidden" name="backURL" value="${superBackURL}">
						<input type="hidden" name="pno" value="${pno}">
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="row" style="width: 101%;">
		<div class="col-md-6" align="center">
			<span><a
				href="javascript:location.href=encodeURI('<%=path%>/progress?method=listProgressForProject&projectNum=${projectNum}&backURL=${superBackURL}&pno=${pno}&projectName=${projectName}')">形象进度</a></span>
		</div>
		<div class="col-md-6" align="center"
			style="background-color: #acd6ff;">
			<span><a
				href="javascript:location.href=encodeURI('<%=path%>/acceptance?method=listAcceptanceForProject&projectNum=${projectNum}&backURL=${superBackURL}&pno=${pno}&projectName=${projectName}')">分部验收</a></span>
		</div>
	</div>


	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		id="searchmain">
		<tr>
			<td align="left" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					id="main-tab">
					<tr>
						<th align="center" valign="middle" width="50" class="borderright">序号</th>
						<th align="center" valign="middle" width="100" class="borderright">验收项目</th>
						<th align="center" valign="middle" width="100" class="borderright">类别</th>
						<th align="center" valign="middle" width="100" class="borderright">验收时间</th>
						<th align="center" valign="middle" width="100" class="borderright">详情</th>
						<c:if test="${ sessionScope.user.uType == 8 }">
							<!-- 项目部 -->
							<th align="center" valign="middle" width="100"
								class="borderright">操作</th>
						</c:if>
					</tr>
					<c:if test="${requestScope.acceptances != null }">
						<c:forEach items="${acceptances}" var="ins" varStatus="vs">
							<tr class="bggray"
								onMouseOut="this.style.backgroundColor='#f9f9f9'"
								onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="center" valign="middle" width="50"
									class="borderright borderbottom">${vs.index+1}</td>
								<td align="center" valign="middle" width="120"
									class="borderright borderbottom">${ins.aType}</td>
								<td align="center" valign="middle" width="120"
									class="borderright borderbottom">${ins.detail }</td>
								<td align="center" valign="middle" width="120"
									class="borderright borderbottom">${ins.aTime }</td>
								<td align="center" valign="middle" width="120"
									class="borderright borderbottom">${ins.memo}</td>
								<c:if test="${sessionScope.user.uType == 8}">
									<td align="center" valign="middle" class="borderbottom"
										width="100"><a
										href="javascript:location.href=encodeURI('<%=path%>/acceptance?method=initUpdate&projectNum=${projectNum}&ano=${ins.ano}&backURL=${superBackURL}&pno=${pno}&projectName=${projectName}')"
										target="mainFrame" onFocus="this.blur()" class="edit">编辑</a> <span
										class="gray">&nbsp;|&nbsp;</span> <a
										href="javascript:location.href=encodeURI('<%=path%>/acceptance?method=deleteAcceptance&projectNum=${projectNum}&ano=${ins.ano}&backURL=${superBackURL}&pno=${pno}&projectName=${projectName}')"
										target="mainFrame" onFocus="this.blur()" class="edit">删除</a></td>
								</c:if>
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
<script type="text/javascript">
$(function(){ 
	setSelect('0', 'level');
	});
	var msg = "${msg}";
	if('' != msg){
		alert(msg);
	}
    function onSelectChange(obj,toSelId){
	   setSelect(obj.value,toSelId);
 }
	function setSelect(fromSelVal,toSelId){
		document.getElementById(toSelId).innerHTML="";
		jQuery.ajax({
		        url: "<%=path%>/dropDown",
			cache : false,
			data : {
				pno : fromSelVal,
				method : "getAcceptanceType"
			},
			success : function(data) {
				createSelectObj(data, toSelId);
			}
		});
	}
	function createSelectObj(data, toSelId) {
		var arr = jsonParse(data);
		if (arr != null && arr.length > 0) {
			var obj = document.getElementById(toSelId);
			obj.innerHTML = "";
			var nullOp = document.createElement("option");
			nullOp.setAttribute("value", "");
			nullOp.appendChild(document.createTextNode("请选择"));
			obj.appendChild(nullOp);
			for ( var o in arr) {
				var op = document.createElement("option");
				op.setAttribute("value", arr[o].bno);
				op.appendChild(document.createTextNode(arr[o].aType));
				obj.appendChild(op);
			}

		}
	}
	
</script>
</html>