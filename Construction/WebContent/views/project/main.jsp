<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath() ;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Insert title here</title>
<link href="<%=path %>/css/mapMain.css" type="text/css" rel="stylesheet" />
</head>
	<frameset id="attachucp" framespacing="0" border="0" frameborder="no" cols="*,20,0" rows="*">
		<frame scrolling="auto" noresize="noresize" frameborder="0" name="mapFrame" src="<%=path %>/views/project/map.jsp"></frame>
		<frame id="leftbar"     scrolling="no" noresize="noresize" name="switchFrame" src="<%=path %>/views/project/swich.jsp"></frame>
		<frame scrolling="auto" noresize="noresize" frameborder="1" name="tableFrame" src="<%=path %>/views/project/mapTable.jsp"></frame>
	</frameset>
</html>