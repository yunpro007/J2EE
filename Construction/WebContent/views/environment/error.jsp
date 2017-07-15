<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<%
	String path = request.getContextPath() ;
%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主要内容区main</title>
<link href="<%=path %>/css/css.css" type="text/css" rel="stylesheet" />
<link href="<%=path %>/css/main.css" type="text/css" rel="stylesheet" />
<link rel="shortcut icon" href="<%=path %>/images/main/favicon.ico" />
<link href="<%=path %>/css/font-awesome.min.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="<%=path %>/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/json-minified.js"></script> 
<script src="<%=path %>/js/Chart.js"></script>
<style>
body {
	overflow-x: hidden;
	background: #f2f0f5;
	padding: 15px 0px 10px 5px;
}

#searchmain {
	font-size: 12px;
	width:100% ;
	padding-top:  0px ;
}

#search {
	font-size: 12px;
	background: #548fc9;
	margin: 10px 10px 0 0;
	display: inline;
	width: 100%;
	color: #FFF;
	float: left
}

#search form span {
	height: 40px;
	line-height: 40px;
	padding: 0 0px 0 10px;
	float: left;
}

#search form input.text-word {
	height: 18px;
	line-height: 18px;
	width: 180px;
	margin: 8px 0 6px 0;
	padding: 0 0px 0 10px;
	float: left;
	border: 1px solid #FFF;
}

#search form input.text-but {
	height: 24px;
	line-height: 24px;
	width: 55px;
	background: url(<%=path%>/images/main/list_input.jpg) no-repeat left top;
	border: none;
	cursor: pointer;
	font-family: "Microsoft YaHei", "Tahoma", "Arial", '宋体';
	color: #666;
	float: left;
	margin: 8px 0 0 6px;
	display: inline;
}

#search form td.serarch_pa {
	padding-left: 5%;
	padding-bottom: 2%;
}
search form td a{
	background: white;
	color: white;
}
#search a.back {
	background: url(<%=path%>/images/main/replayblue.jpg) no-repeat -3px 7px
		#548fc9;
	padding: 0 10px 0 26px;
	height: 40px;
	line-height: 40px;
	font-size: 14px;
	font-weight: bold;
	color: #FFF;
	float: right
}
#search a:hover.add {
	text-decoration: underline;
	color: #d2e9ff;
}
#search a:hover.back {
	text-decoration: underline;
	color: #d2e9ff;
}
.main-for select {
	width: 192px;
	height: 21px;
	line-height: 21px;
	border: 1px solid #FFF;
	background: #FFF;
	font-family: "Microsoft YaHei", "Tahoma", "Arial", '宋体';
	color: #666;
}
.right_side
{
   vertical-align:bottom;
   margin-left:40px;
   margin-right:0px;
   margin-bottom:5px;
   padding:10px;
   text-align:right;
   padding-right:5px;
}
.right_side a.search-a {
	background: white;
	font-size: 16px;
	font-weight: bold;
	margin-right:10px;
	margin-left: 10px ;
}
.search-a{
	background: white;
}
</style>

</head>
<body>
	<!--main_top-->
	<table 
		id="searchmain">
		<tr>
			<td width="99%" align="left" valign="top">您的位置：环境检测<b>&#40;${projectName}&#41;</b></td>
		</tr>
		<tr>
			<td align="left" valign="top">
				<table 
					id="search">
					<tr>
						<td width="90%" align="left" valign="middle">
							<form method="post" action="#" name="form1" id="form1">
								<table>
									<tr>
										<td>&nbsp;&nbsp;设备号&nbsp;:&nbsp;&nbsp;</td>
										<td align="left" valign="middle" class="main-for">
											<select name="aq" id="level"  onchange="changeAq()">
											</select>
										</td>
										<td>&nbsp;&nbsp;环境类别&nbsp;:&nbsp;&nbsp;</td>
										<td align="left" valign="middle" class="main-for">
											<select name="type" id="type"  onchange="changeType()" >
												<option value="1" selected="selected">温度</option>
												<option value="2">湿度</option>
												<option value="3">PM2.5</option>
												<option value="4">PM10</option>
												<option value="5">噪音</option>
											</select>
										</td>
										<td>&nbsp;&nbsp;</td>
										<td>
											<div  class="right_size" >
												<c:if test="${backURL != null}">
													<a href="javascript:location.href=encodeURI('<%=path%>/environment?method=showEnvirDetection&aq=${aq}&pno=${pno}&backURL=${backURL}')" class="search-a"><i class="fa fa-clock-o fa-lg"></i>&nbsp;&nbsp;实时</a>										  
														&nbsp;&nbsp;
												  	<a href="javascript:location.href=encodeURI('<%=path%>/environment?method=showEnvirDetectionByCondition&aq=${aq}&pno=${pno}&type=week&backURL=${backURL}')" class="search-a" ><i class="fa fa-adjust fa-lg"></i>&nbsp;&nbsp;按周</a>
					   								  &nbsp;&nbsp;
						   						  	<a href="javascript:location.href=encodeURI('<%=path%>/environment?method=showEnvirDetectionByCondition&aq=${aq}&pno=${pno}&type=month&backURL=${backURL}')" class="search-a" ><i class="fa fa-calendar fa-lg"></i>&nbsp;&nbsp;按月</a>
												</c:if>				 
											    <c:if test="${superBackURL != null}">
											    	<a href="javascript:location.href=encodeURI('<%=path%>/environment?method=showEnvirDetection&aq=${aq}&pno=${pno}&backURL=${superBackURL}')" class="search-a"><i class="fa fa-clock-o fa-lg"></i>&nbsp;&nbsp;实时</a>										  
														&nbsp;&nbsp;
												  	<a href="javascript:location.href=encodeURI('<%=path%>/environment?method=showEnvirDetectionByCondition&aq=${aq}&pno=${pno}&type=week&backURL=${superBackURL}')" class="search-a" ><i class="fa fa-adjust fa-lg"></i>&nbsp;&nbsp;按周</a>
					   								  &nbsp;&nbsp;
						   						  	<a href="javascript:location.href=encodeURI('<%=path%>/environment?method=showEnvirDetectionByCondition&aq=${aq}&pno=${pno}&type=month&backURL=${superBackURL}')" class="search-a" ><i class="fa fa-calendar fa-lg"></i>&nbsp;&nbsp;按月</a>
											    </c:if>
											</div>
										</td>
									</tr>
								</table>
							</form>
						</td>
						<td width="10%" align="center" valign="middle"
							style="text-align: right; width: 100px;">
							<c:if test="${backURL != null}">
							 	<a href="javascript:location.href=encodeURI('<%=path %>/environment?method=initMain')" target="mainFrame" onFocus="this.blur()" class="back">返回</a>
							</c:if>
							<c:if test="${superBackURL != null}">
						    	<a href="javascript:location.href=encodeURI('<%=path %>/project?method=${superBackURL}&pno=${pno}')" target="mainFrame" onFocus="this.blur()" class="back">返回</a>	
							</c:if>
					   </td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="left" valign="top">
				 <div id = "name" >没有该时间段的环境检测数据或者工程的设备环境没有环境设备</div>
				  <div id = "name" >请查看其它工程环境数据</div>
                 <canvas id="tem" width="800px" height="600"></canvas>
			</td>
		</tr>
	</table>
</body>
</html>