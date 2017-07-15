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
<script src = "<%=path%>/usermanage/js/city.js" charset = "UTF-8"></script>
<script src = "<%=path%>/usermanage/js/putcity.js" charset = "UTF-8"></script>
<script src = "<%=path%>/usermanage/js/js.js" charset = "UTF-8"></script>
<script src = "<%=path%>/usermanage/js/getcity.js" charset = "UTF-8"></script>

<script type="text/javascript">
function dosub()
{
	var th = document.form;
	if('${user.uno}' == '')
	{
		th.action="<%=path%>/UserManage/AdminUserServlet?method=add";
		th.submit();
	}
	else
	{
		th.action="<%=path%>/UserManage/AdminUserServlet?method=modify&uno=${user.uno}";
		th.submit();
	}
}
</script>
		<title>智慧建管大数据一体化云平台</title>
		<link href="<%=path%>/usermanage/show/Add_show/css/bootstrap.min.css" rel="stylesheet">
		<link href="<%=path%>/usermanage/show/Add_show/css/font-awesome.min.css" rel="stylesheet">
		<link rel="stylesheet" href="<%=path%>/usermanage/show/Add_show/add_staff.css">
</head>
<body class="body-container">
<div class="container img-rounded">
	<span><a href="<%=path%>/usermanage/Users.jsp">返回</a></span>
	<form action="" method="post" name="form" class="form-horizontal" role="form">
			<div class="form-group">
				<span for="counter" class="col-sm-3 control-label" >账号：</span>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="counter" name="uid" autofocus="true" value=${user.uid }>
				</div>
			</div>
			<div class="form-group">
				<span for="password" class="col-sm-3 control-label">密码：</span>
				<div class="col-sm-9">
					<input type="password" class="form-control" id="password" name="password" value=${user.password }>
				</div>
			</div>	
			<div for="user-type" class="form-group">
				<span class="col-sm-3 control-label">用户类型：</span>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="user-type" name="uType" value=${user.uType }>
				</div>
			</div>
			<div class="form-group">
				<span  for="name" class="col-sm-3 control-label">姓名：</span>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="name" name="uName" value=${user.uName }>
				</div>
			</div>
			<div class="form-group">
				<span for="ID" class="col-sm-3 control-label">身份证号：</span>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="ID" name="idCard" value=${user.idCard }>
				</div>
			</div>
			<div class="form-group">
				<span for="telephone" class="col-sm-3 control-label">联系电话：</span>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="telephone" name="tel" value=${user.tel }>
				</div>
			</div>
			<div class="form-group">
			
				<span for="city" class="col-sm-3 control-label">所在城市：</span>
				<div class="col-sm-9">
					<select id="cmbProvince" name="cmbProvince" class="city"></select>
					<select id="cmbCity" name="cmbCity" class="city"></select>
					<c:choose>
						<c:when test="${user.city eq '' }">
							<script type="text/javascript">
								addressInit('cmbProvince', 'cmbCity');
							</script>
						</c:when>
						<c:otherwise>
							<script type="text/javascript">
								putaddressInit('cmbProvince', 'cmbCity','${user.city}');
							</script>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="form-group">
				<span for="name-work" class="col-sm-3 control-label">单位名称：</span>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="name-work" name="unitName" value=${user.unitName }>
				</div>
			</div>
			<div class="form-group">
				<span for="ID-work" class="col-sm-3 control-label">施工总包单位编号：</span>
				<div class="col-sm-9">
					<input type="text" class="form-control unit_ID" id="ID-work" name="bno" value=${user.bno }>
				</div>
			</div>
			<div class="form-group ">
				<span for="column" class="col-sm-3 control-label remark">备注：</span>
				<div class="col-sm-9">
					<textarea type="text" class="form-control " name="memo" id="column" style="width:100%;height:20%;" >${user.memo } </textarea>
				</div>
			</div>	
			<div class="form-group ">
				<button class="btn  btn-login  m_btn1" onclick="dosub()">提交</button>
				<input class="btn  btn-login  m_btn2" type="reset" value="重置">
			</div>
	</form>
</div>
<script src="<%=path%>/usermanage/show/Add_show/js/jquery-3.1.1.min.js"></script>
</body>
</html>