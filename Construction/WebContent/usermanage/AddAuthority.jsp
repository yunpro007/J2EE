<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>智慧建管大数据一体化云平台</title>
		<link href="<%=path%>/usermanage/show/Authority_show/css/bootstrap.min.css" rel="stylesheet">
	  	<link href="<%=path%>/usermanage/show/Authority_show/css/font-awesome.min.css" rel="stylesheet">
		<link rel="stylesheet" href="<%=path%>/usermanage/show/Authority_show/limit_operation.css">
<script type="text/javascript">
	function mess()
	{
		var mno = document.getElementsByName("mno");
		<c:forEach items="${limitval }" var="ele" varStatus="l">
			mno['${ele}'-1].checked = true;
		</c:forEach>
	}
</script>
</head>
<body onload="mess()" class="main-body">
<div class="container">
<span><a href="<%=path%>/usermanage/1.jsp">返回</a></span>
	<form action="<%=path%>/UserManage/AdminBuilderServlet?method=change" method="post">
		<div class=" top-box">
			<span  width="50px">权限类型：</span>
			<input type="text" name="uType" value=${uType }>
		</div>
		<div class="middle-box">
			<span >模块序号：</span>
				<div class=" modle-select">
					<input type="checkbox" name="mno" value="1" id="1"><label for="1">工程分布</label>&nbsp;&nbsp;
					<input type="checkbox" name="mno" value="2" id="2"><label for="2">工程概况</label>&nbsp;&nbsp;
					<input type="checkbox" name="mno" value="3" id="3"><label for="3">形象进度</label><br>
					<input type="checkbox" name="mno" value="4" id="4"><label for="4">工程检测</label>&nbsp;&nbsp;
					<input type="checkbox" name="mno" value="5" id="5"><label for="5">夜间施工</label>&nbsp;&nbsp;
					<input type="checkbox" name="mno" value="6" id="6"><label for="6">工地视频</label><br>
					<input type="checkbox" name="mno" value="7" id="7"><label for="7">基坑监测</label>&nbsp;&nbsp;
					<input type="checkbox" name="mno" value="8" id="8"><label for="8">高支模</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" name="mno" value="9" id="9"><label for="9">起重设备</label><br>
					<input type="checkbox" name="mno" value="10" id="10"><label for="10">人证相符</label>&nbsp;&nbsp;
					<input type="checkbox" name="mno" value="11" id="11"><label for="11">人员培训</label>&nbsp;&nbsp;
					<input type="checkbox" name="mno" value="12" id="12"><label for="12">混凝土检测</label><br>
					<input type="checkbox" name="mno" value="13" id="13"><label for="13">样板工地</label>&nbsp;&nbsp;
					<input type="checkbox" name="mno" value="14" id="14"><label for="14">执法情况</label>&nbsp;&nbsp;
					<input type="checkbox" name="mno" value="15" id="15"><label for="15">扬尘噪声</label><br>
					<input type="checkbox" name="mno" value="16" id="16"><label for="16">门禁考勤</label>&nbsp;&nbsp;
					<input type="checkbox" name="mno" value="17" id="17"><label for="17">奖惩情况</label>&nbsp;&nbsp;
					<input type="checkbox" name="mno" value="18" id="18"><label for="18">施工许可</label><br>
					<input type="checkbox" name="mno" value="19" id="19"><label for="19">诚信评价</label>&nbsp;&nbsp;
				<div class="bottom-box">
					<button class="btn  btn-sm  m_btn1" type="submit">提交</button>
					<button class="btn btn-sm  m_btn2" type="reset">重置</button>
				</div>
			</div>
		</div>
	</form>
	</div>
</body>
</html>