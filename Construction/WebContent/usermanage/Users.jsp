<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<script src = "<%=path%>/usermanage/js/js.js" charset = "UTF-8"></script>
<script src = "<%=path%>/usermanage/js/city.js" charset = "UTF-8"></script>
<script src = "<%=path%>/usermanage/js/getcity.js" charset = "UTF-8"></script>

<title>智慧建管大数据一体化云平台</title>
		<link href="<%=path%>/usermanage/show/Users_show/css/bootstrap.min.css" rel="stylesheet">
		<link href="<%=path%>/usermanage/show/Users_show/css/font-awesome.min.css" rel="stylesheet">
		<link rel="stylesheet" href="<%=path%>/usermanage/show/Users_show/new 1.css">
</head>

<script type="text/javascript">

</script>

<body class="user_body">
	<div class="left-box"><a  href="<%=path%>/usermanage/Main.jsp" ><返回></a>	</div> 
	           
	<div align="center" >
	
		<form action="<%=path%>/UserManage/AdminUserServlet?method=queryUser&flag=${flag }" method="post" name="form1">
		<div class="right-box">
		
			姓名：<input type="text" class="user" name="uName">
			所在城市：<select id="cmbProvince" name="cmbProvince" class="city"></select>
				<select id="cmbCity" name="cmbCity" class="city"></select>
		
		<script type="text/javascript">
			addressInit('cmbProvince', 'cmbCity');
		</script>
		
			<button class="user">查询</button>
			<input type="button" class="user" value="添加新用户" onclick="javascript:location.href='<%=path%>/usermanage/AddUsers.jsp'">
			
			</div>
		</form>
	</div>
	
	
	<div class="main-body">
		<form action="" method="post" name="form2">
		<br>
		<div class="check ">
		<a class="btn  btn-sm btn_left" href=" <%=path%>/UserManage/AdminUserServlet?method=loadUserMsg">查看未删除用户</a>
		<a class="btn  btn-sm  btn_right" href="<%=path%>/UserManage/AdminUserServlet?method=loadDelUserMsg">查看已删除用户</a>
		</div>
		
		
		<div class="table_body">
			<table class="table table-striped table-bordered" >
			<thead>
			  	<tr>
			  		<th width="80px">序号</th>
			  		<th width="120px">账号</th>
			  		<th width="120px">密码</th>
			  		<th width="100px">用户类型</th>
			  		<th width="100px">姓名</th>
			  		<th width="150px">身份证号</th>
			  		<th width="120px">联系电话</th>
			  		<th width="100px">所在城市</th>
			  		<th width="120px">单位名称</th>
			  		<th width="100px">施工总包单位编号</th>
			  		<th width="150px">备注</th>
			  		<th width="100px">编辑</th>
			  	</tr>
			  	</thead>
				<c:forEach items="${list }" var="user">
					
			  	<tr>
			  		<td align="center">${user.uno }</td>
			  		<td align="center">${user.uid }</td>
			  		<td align="center">${user.password }</td>
			  		<td align="center">${user.uType }</td>
			  		<td align="center">${user.uName }</td>
			  		<td align="center">${user.idCard }</td>
			  		<td align="center">${user.tel }</td>
			  		<td align="center">
			  			<script>
			  				document.write(rcity("${user.city}"))
			  			</script>
			  		</td>
			  		<td align="center">${user.unitName }</td>
			  		<td align="center">${user.bno }</td>
			  		<td align="center">${user.memo }</td>
			  		<td width="120" align="center">
			  			<input type="hidden" name="Id" value="uno">
			  			<input type="hidden" name="url" value="Users.jsp">
			  			<input type="hidden" name="id" id="uno">
			  			<input type="hidden" name="tableName" value="users" >
			  			<input type="hidden" name="num" id="num">
			  			<c:choose>
			  				<c:when test="${user.flag eq 1 }">
					  			<a href="<%=path%>/UserManage/AdminUserServlet?method=findUserById&uno=${user.uno }" class="change">修改</a>
					  			<span class="gray"> | </span>
					  			<a href="<%=path%>/UserManage/AdminUserServlet?method=del&flag=${flag }&uno=${user.uno }" class="change">删除</a>
			  				</c:when>
			  				<c:when test="${user.flag eq 0 }">
			  					<a href="<%=path%>/UserManage/AdminUserServlet?method=del&flag=${flag }&uno=${user.uno }" class="change">恢复</a>
			  				</c:when>
			  			</c:choose>
			  		</td>
			  	</tr>
				</c:forEach>

			  	<tr>
			  		<td colspan="12" align="center" height="40px">
			  		<div class="base">
			  			<a class="link" href="javascript:first()">首页</a>
			  			<a class="link" href="javascript:forward()">上一页</a>
			  			<a class="link" href="javascript:next()">下一页</a>
			  			<a class="link" href="javascript:end()">尾页</a>
			  			</div>
			  		</td>
			  	</tr>
			</table>
			</div>
		</form>
	</div>
</body>
</html>