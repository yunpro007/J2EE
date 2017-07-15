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
<link rel="stylesheet" href="<%=path%>/css/main_list.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/content-header.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/font-awesome.min.css" />

<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/bootstrap.min.css">
</head>
<body>
	<nav id="content-header"> <B>位置：</B>样板工地
	<div class="return">
		<!-- 		<a href="#"><i class="fa fa-minus-square-o fa-lg"></i>&nbsp;返回</a> -->
	</div>
	</nav>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		id="searchmain">
		<tr>
			<td align="left" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					id="search">
					<tr>
						<td width="90%" align="left" valign="middle">
							<form method="post" action="#">
								<table width="100%">
									<tr>
										<td><span>工程名称：</span></td>
										<td><input type="text" name="qoprojectName" value=""
											class="text-word"></td>
										<td><span>工程地址：</span></td>
										<td><input type="text" name="qolocation" value=""
											class="text-word"></td>
									</tr>
									<tr>
										<td><span>建设单位：</span></td>
										<td><input type="text" name="qoinvestor" value=""
											class="text-word"></td>
										<td><span>施工单位：</span></td>
										<td><input type="text" name="qobuilder" value=""
											class="text-word"></td>
										<td><button type="submit" class="query">
												<i class="fa fa-search"></i>&nbsp;查询
											</button></td>
										<c:if test="${sessionScope.user.uType!=11}">
											<td><button type="button" class="query">
													<i class="fa  fa-share-square-o"></i>&nbsp;导出
												</button></td>
										</c:if>
									</tr>
								</table>
							</form>
						</td>
						<!-- <td width="25%" align="right" valign="bottom" ><a href="#" target="mainFrame" onFocus="this.blur()" class="add"><i class="fa fa-plus"></i>&nbsp;新增管理员</a></td> -->
					</tr>
				</table>
			</td>
		</tr>
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
									class="borderright borderbottom"><a href="#">${ins.projectName}</a>
								</td>
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
