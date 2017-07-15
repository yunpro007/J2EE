<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工程主界面</title>
<link rel="stylesheet" href="<%=path%>/css/main_list.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/content-header.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css">
</head>
<body>
	<nav id="content-header"> <B>位置：</B>施工许可
	<div class="return">
		<!-- 	<a href="#"><i class="fa fa-minus-square-o fa-lg"></i>&nbsp;返回</a> -->
	</div>
	</nav>
	<div class="header-query">
		<div class="row" style="margin: 0px;">
			<div class="col-md-12">
				<form method="post"
					action="#">
					<div class="row">
						<div class="col-sm-4">
							<label class="form_query">工程名称&nbsp;:&nbsp;</label><input type="text"
								name="qoprojectName" />
						</div>
						<div class="col-sm-4">
							<label class="form_query">工程地址&nbsp;:&nbsp;</label><input type="text"
								name="qolocation" />
						</div>
						<div class="col-sm-4"></div>
					</div>
					<div class="row">
						<div class="col-sm-4">
							<label class="form_query">建设单位&nbsp;:&nbsp;</label><input type="text"
								name="qoinvestor" />
						</div>
						<div class="col-sm-4">
							<label class="form_query">施工单位&nbsp;:&nbsp;</label><input type="text"
								name="qobuilder" />
						</div>
						<div class="col-sm-4">
							<button type="submit" class="query">
								<i class="fa fa-search"></i>&nbsp;查询
							</button>
						</div>
					</div>
				</form>
			</div>
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
						<th align="center" valign="middle" width="100" class="borderright">工程名称</th>
						<th align="center" valign="middle" width="100" class="borderright">工程地址</th>
						<th align="center" valign="middle" width="100" class="borderright">建设单位</th>
						<th align="center" valign="middle" width="100" class="borderright">施工单位</th>
						<th align="center" valign="middle" width="100" class="borderright">详情</th>
						<!-- <th align="center" valign="middle">操作</th> -->
					</tr>
					<c:if test="${requestScope.results != null }">
						<c:forEach var="ins" items="${results}" varStatus="vs">
							<tr class="bggray"
								onMouseOut="this.style.backgroundColor='#f9f9f9'"
								onMouseOver="this.style.backgroundColor='#edf5ff'">

								<td align="center" valign="middle" width="50"
									class="borderright borderbottom">${vs.index +1 }</td>
								<td align="center" valign="middle" width="100"
									class="borderright borderbottom">
										<a href="javascript:location.href=encodeURI('<%=path%>/permit?method=showSiteMain&pno=${ins.pno}&permission=${ins.permission}&projectName=${ins.projectName}')" target="mainFrame">${ins.projectName}</a>	
								<td align="center" valign="middle" width="100"
									class="borderright borderbottom">${ins.location}</td>
								<td align="center" valign="middle" width="100"
									class="borderright borderbottom">${ins.investor }</td>
								<td align="center" valign="middle" width="100"
									class="borderright borderbottom">${ins.bName }</td>
								<td align="center" valign="middle" width="100"
									class="borderright borderbottom">${ins.memo }</td>
								<!-- <td align="center" valign="middle" class="borderbottom"><a href="add.html" target="mainFrame" onFocus="this.blur()" class="add">编辑</a><span class="gray">&nbsp;|&nbsp;</span><a href="add.html" target="mainFrame" onFocus="this.blur()" class="add">删除</a></td> -->
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
</html>
