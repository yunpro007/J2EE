<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>智慧建管大数据一体化云平台</title>
		<link href="<%=path%>/usermanage/show/Users_show/css/bootstrap.min.css" rel="stylesheet">
		<link href="<%=path%>/usermanage/show/Users_show/css/font-awesome.min.css" rel="stylesheet">
		<link rel="stylesheet" href="<%=path%>/usermanage/show/Users_show/new 1.css">
</head>
<body class="user_body">
	<div class="left-box"><a  href="<%=path%>/usermanage/Main.jsp" ><返回></a>	</div>
	<div align="center" >
		<form action="<%=path%>/UserManage/AdminBuilderServlet?method=queryBuilder&flag=${flag }" method="post" name="form1">
		
		<div class="right-box">
			总包单位名称：<input type="text" class="user" name="bName">
			<button >查询</button>
			<input type="button" value="添加新用户" onclick="javascript:location.href='<%=path%>/usermanage/AddBuilder.jsp'">
			</div>
		</form>
	</div>
	
	<div class="main-body">
		<form action="" method="post" name="form2">
		<br>
		<div class="check ">
		<a class="btn  btn-sm btn_left" href="<%=path%>/UserManage/AdminBuilderServlet?method=loadBuilderMsg">查看未删除用户</a>
		<a class="btn  btn-sm  btn_right" href="<%=path%>/UserManage/AdminBuilderServlet?method=loadDelBuilderMsg">查看已删除用户</a>
		</div>
		<div class="table_body">
			<table class="table table-striped table-bordered">
		    	<tr>
		    		<th width="80px">序号</th>
		    		<th width="160px">施工总包单位</th>
		    		<th width="220px">企业logo图片路径</th>
		    		<th width="200px">企业简介</th>
		    		<th width="280px">备注</th>
		    		<th width="100px">编辑</th>
		    	</tr>
		    	<c:forEach items="${builderlist }" var="builder">
			  	<tr>
			  		<td align="center">${builder.bno }</td>
			  		<td align="center">${builder.bName }</td>
			  		<td align="center">${builder.logo }</td>
			  		<td align="center">${builder.brief }</td>
			  		<td align="center">${builder.memo }</td>
			  		
			  		<td width="120" align="center">
			  			<input type="hidden" name="Id" value="uno">
			  			<input type="hidden" name="url" value="Users.jsp">
			  			<input type="hidden" name="id" id="uno">
			  			<input type="hidden" name="tableName" value="users" >
			  			<input type="hidden" name="num" id="num">
			  			<c:choose>
			  				<c:when test="${builder.flag eq 1 }">
					  			<a href="<%=path%>/UserManage/AdminBuilderServlet?method=findBuilderById&bno=${builder.bno }" class="change">修改</a>
					  			<span class="gray"> | </span>
					  			<a href="<%=path%>/UserManage/AdminBuilderServlet?method=del&flag=${flag }&bno=${builder.bno }" class="change">删除</a>
			  				</c:when>
			  				<c:when test="${builder.flag eq 0 }">
			  					<a href="<%=path%>/UserManage/AdminBuilderServlet?method=del&flag=${flag }&bno=${builder.bno }" class="change">恢复</a>
			  				</c:when>
			  			</c:choose>
			  		</td>
			  	</tr>
				</c:forEach>
		    	<tr>
			  		<td colspan="6" align="center" height="40px">
			  		<div class="base">
			  			<a class="link" href="javascript:first()">首页</a>
			  			<a class="link" href="javascript:forward()">上一页</a>
			  			<a class="link" href="javascript:next()">下一页</a>
			  			<a class="link" href="javascript:end()">尾页</a>
			  		</div>
			  		</td>
			  	</tr>
		    </table>
	    </form>
	</div>
</body>
</html>