<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String path = request.getContextPath() ; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工地视频</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/content-header.css"/>
<link rel="stylesheet" href="<%=path%>/css/main_list.css" />
<link rel="stylesheet" href="<%=path%>/css/Enironment_Monitor.css" />
<link href="<%=path %>/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link href="<%=path %>/css/red-datepicker.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/font-awesome.min.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="./T5Player/js/cyberplayer.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/jquery-1.8.1.js"  type="text/javascript"></script>
<script src="<%=path %>/js/jquery-ui.js" type="text/javascript"></script>
<script src="<%=path %>/js/dateinput-ch-ZN.js" type="text/javascript"></script>

</head>
<body>
	<nav id="content-header">
		    <B>位置：</B>工地视频>>>大门口
			<div class="return"> 
			    	<a href="<%=path %>/video?method=initMain" target="mainFrame"><i class="fa fa-minus-square-o fa-lg"></i>&nbsp;返回</a>	
			</div>
	</nav>
    <div id="playerContainer"></div>

   <script> 
    var player = cyberplayer("playerContainer").setup({
        flashplayer: "./T5Player/player/cyberplayer.flash.swf",
        width: 680,
        height: 400,
        backcolor: "#FFFFFF",
        stretching: "uniform",
        file: "http://open.ys7.com/openlive/4bd776dba5f94ff2a6fac397b1114781.m3u8",
        autoStart: true,
        repeat: "always",
        volume: 100,
        controlbar: "over"
    });
</script>
</body>

<script type="text/javascript">
$(document).ready(function() {
    $("#dateform").datepicker(
    {
    });
  });
</script>
</html>



