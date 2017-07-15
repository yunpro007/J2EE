<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>slider</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/slider.css"/>
</head>
<body onLoad="load()">
			<div class="slider_left" id="leftbar"><a onClick="switchSysBar()" href="javascript:void(0);"><img src="<%=path %>/images/slider/pic23.jpg" /></a></div>
			<div class="slider_right" id="rightbar"><a onClick="switchSysBar()" href="javascript:void(0);"><img src="<%=path %>/images/slider/pic24.jpg"/></a></div>
	</body>
	<script type="text/javascript">
	function switchSysBar() {
		if (parent.document.getElementById('attachucp').cols == "200,10,*") {
			document.getElementById('leftbar').style.display = "";
			parent.document.getElementById('attachucp').cols = "0,10,*";
		} else {
			parent.document.getElementById('attachucp').cols = "200,10,*";
			document.getElementById('leftbar').style.display = "none"
		}
	}
	function load() {
		if (parent.document.getElementById('attachucp').cols == "0,10,*") {
			document.getElementById('leftbar').style.display = "";
		}
	}
</script>
</html>
