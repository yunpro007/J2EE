<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<% String path = request.getContextPath() ; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
	<meta http-equiv="X-UA-Compatible" content="IE=10"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>智慧建管大数据一体化云平台 </title>          
</head>
		<frameset  rows="70,*,40"  cols="*"  frameborder="no" border="0" framespacing="0" bordercolor="blue">
		 	  <frame  src = "<%=path%>/views/head.jsp" name = "headerFrame"  scrolling="no"/>
		 	  <frameset cols="200,10,*"  frameborder="no"  border="0" framespacing="0" id="attachucp">
		 	  	    <frame src="<%=path%>/views/left.jsp" name = "leftFrame" scrolling="yes"  id="leftFrame"/>
		 	  	    <frame src="<%=path%>/views/slider.jsp" name="sliderFrame" scrolling="no" id="sliderFrame"/>
		 	  	    <frame src="<%=path %>/views/project/main.jsp" name = "mainFrame" id="rightFrame"/>
		 	  </frameset>
		 	  <frame src="<%=path%>/views/footer.html" name ="footerFrame"  scrolling="no" frameborder="0" />
		</frameset>
</html>