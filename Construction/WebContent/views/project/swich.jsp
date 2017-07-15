<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=path %>/css/mapMain.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
	function switchSysBar() {
		if (parent.document.getElementById('attachucp').cols == "*,12,510") {
			document.getElementById('leftbar').style.display = "";
			parent.document.getElementById('attachucp').cols = "*,12,0";
		} else {
			parent.document.getElementById('attachucp').cols = "*,12,510";
			document.getElementById('rightbar').style.display = ""
		}
	}
	function load() {
		if (parent.document.getElementById('attachucp').cols == "*,12,510") {
			document.getElementById('leftbar').style.display = "none";
		}
	}
</script>
</head>
<body marginwidth="0" marginheight="0" onLoad="load()" topmargin="0"
	leftmargin="0" 
	style="overflow-x: hidden;">
	<center>
		<table height="100%" cellspacing="0" cellpadding="0" border="0"
			width="100%">
			<tbody>
				<tr>
					<td bgcolor="#ededb1" width="1"></td>
					<td id="rightbar"
						style="background: url(<%=path %>/images/slider/pic23.jpg) repeat-y #f2f0f5 0px 0">
						<a onClick="switchSysBar()" href="javascript:void(0);"> <img
							src="<%=path %>/images/slider/pic24.jpg" width="12" height="72" border="0"
							alt="隐藏右侧表格">
					</a>
					</td>
					<td id="leftbar"
						style="display: none; background: url(<%=path %>/images/slider/pic24.jpg) repeat-y #d2d2d0 0px 0">
						<a onClick="switchSysBar()" href="javascript:void(0);"> <img
							src="<%=path %>/images/slider/pic24.jpg" width="12" height="72" border="0"
							alt="隐藏右侧表格">
					</a>
					</td>
					
				</tr>
			</tbody>
		</table>
	</center>
</body>
</html>