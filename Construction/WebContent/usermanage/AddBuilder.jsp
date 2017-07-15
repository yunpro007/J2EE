<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<script type="text/javascript">
function dosub()
{
	var th = document.form;
	if('${builder.bno}' == '')
	{
		th.action="<%=path%>/UserManage/AdminBuilderServlet?method=add";
		th.submit();
	}
	else
	{
		th.action="<%=path%>/UserManage/AdminBuilderServlet?method=modify&bno=${builder.bno}";
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
	<span><a href="<%=path%>/usermanage/Builder.jsp">返回</a></span>
	<form action="" method="post" name="form" class="form-horizontal" role="form">
		
		<div class="form-group">
			<span for="counter" class="col-sm-3 control-label" >施工总包单位：</span>
			<div class="col-sm-9">
				<input type="text" class="form-control" name="bName" width="80px" value=${builder.bName }>
			</div>
		</div>
		
		<div class="form-group">
			<span for="counter" class="col-sm-3 control-label" >企业logo图片路径</span>
			<div class="col-sm-9">
				<input type="file" class="form-control" name="logo" id = "logo">
			</div>
		</div>
		
		<div class="form-group ">
			<span for="column" class="col-sm-3 control-label remark">企业简介：</span>
			<div class="col-sm-9">
				<textarea type="text" class="form-control " id="column" style="width:100%;height:20%;" name="brief">${builder.brief }</textarea>
			</div>
		</div>	
		
		<div class="form-group ">
			<span for="column" class="col-sm-3 control-label remark">备注：</span>
			<div class="col-sm-9">
				<textarea type="text" class="form-control " id="column" style="width:100%;height:20%;" name="memo">${builder.memo } </textarea>
			</div>
		</div>	
		<div class="form-group ">
					<button class="btn  btn-login  m_btn1" onclick="dosub()">提交</button>
					<input class="btn  btn-login  m_btn2" type="reset" value="重置">
					<input type="hidden" name="url" value="Builder.jsp">
					<input type="hidden" name="tableName" value="builder">
					<input type="hidden" name="no" value=${builder.bno }>
		</div>		
	</form>
	</div>
	<script src="<%=path%>/usermanage/show/Add_show/js/jquery-3.1.1.min.js"></script>
</body>
</html>