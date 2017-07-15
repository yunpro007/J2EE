<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e"%>
<%
	String path = request.getContextPath();
	String projectName = request.getParameter("projectName");
	projectName = new String(projectName.getBytes("ISO-8859-1"), "UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主要内容区main</title>
<link rel="stylesheet" href="<%=path%>/css/main_list.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/content-header.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/font-awesome.min.css" />
<link href="<%=path%>/css/bootstrap.min.css" type="text/css"
	rel="stylesheet" />
<link href="<%=path%>/css/font-awesome.min.css" type="text/css"
	rel="stylesheet" />
<link rel="shortcut icon" href="<%=path%>/images/main/favicon.ico" />
<script type="text/javascript" src="<%=path%>/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/Chart.js"></script>
<style type="text/css">
#searchmain {
	font-size: 12px;
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

#container {
	padding: -2px;
	margin: 0px;
	width: 99.6%;
	height: 100%;
	display: block;
	background-color:white;
}

.title {
	background-color: #3A6C83;
	font-family: 微软雅黑;
	display: block;
	height: 40px;
	color: white;
	vertical-align: middle;
	font-size: 24px;
}

#button {
	background: url() no-repeat;
	width: 100px;
	height: 50px;
	border: 0px;
	cursor: pointer;
}

.section {
	padding: 12px 10px;
	background-color: #36A3FF;
	font-family: 微软雅黑;
	display: block;
	height: 170px;
	color: white;
	vertical-align: middle;
	font-size: 16px;
	margin-right:-5px
}

.section1 {
	padding: 12px 10px;
	background-color: #FFFFFF;
	font-family: 微软雅黑;
	display: block;
	height: 400px;
	color: white;
	vertical-align: middle;
	font-size: 16px;
}
</style>

</head>
<body>
	<nav id="content-header"> <B>位置：</B>坑基监测>>>${ projectName}
	<div class="return">
		<c:if test="${backURL != null}">
			<a href="<%=path%>/pitbase?method=initMain" target="mainFrame"><i
				class="fa fa-minus-square-o fa-lg"></i>&nbsp;返回</a>
		</c:if>
		<c:if test="${superBackURL != null}">
			<a href="javascript:location.href=encodeURI('<%=path %>/project?method=${superBackURL}&pno=${pno}')"
				target="mainFrame"><i class="fa fa-minus-square-o fa-lg"></i>&nbsp;返回</a>
		</c:if>
	</div>
	</nav>
	<!--main_top-->
	<div id="container">
<!-- 		<div class="row title"> -->
<%-- 			<div class="col-md-11"><%=projectName%></div> --%>
<!-- 			<!-- <div class="col-md-1" style="padding-top: 5px;"> -->
<!-- 				<button type="button" id="close" class="btn btn-danger"> -->
<!-- 					<i class="fa fa-close fa-lg"></i> -->
<!-- 				</button> -->
<!-- 			</div> --> 
<!-- 		</div> -->
		<div class="section">
			<div class="row" style="width:90%;">
				<div class="col-xs-5" style="padding-left: 36px;">
					<div class="btn-group">
						<button type="button"
							class="btn btn-primary dropdown-toggle btn-lg"
							data-toggle="dropdown">
							<i class="fa fa-cogs fa-lg"></i>&nbsp;&nbsp;设备名称 <span
								class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">设备000001</a></li>
							<li><a href="#">设备000001</a></li>
							<li><a href="#">设备000001</a></li>
							<li><a href="#">设备000001</a></li>
							<li><a href="#">设备000001</a></li>
						</ul>
					</div>
				</div>
				<div class="col-xs-5">
					<h4 style="padding-top: 5px;">监控部位：</h4>
				</div>
			</div>
			<div class="row" style="margin-top: 10px; margin-left: 0px;width:90%;">
				<div class="col-xs-12" style="padding-left: 0px;">
					<label class="checkbox-inline" style="margin-left: 0px;"> <input
						type="radio" name="optionsRadios" id="Radios1" value="option1"
						checked>监控点水平位移
					</label> <label class="checkbox-inline"> <input type="radio"
						name="optionsRadios" id="Radios2" value="option2">监控点垂直位移
					</label> <label class="checkbox-inline"> <input type="radio"
						name="optionsRadios" id="Radios3" value="option3">监控点水平位移
					</label> <label class="checkbox-inline"> <input type="radio"
						name="optionsRadios" id="Radios4" value="option4">监控点垂直位移
					</label> <label class="checkbox-inline"> <input type="radio"
						name="optionsRadios" id="Radios5" value="option5">监控点水平位移
					</label>
				</div>
			</div>
			<div class="row" style="width:90%;">
				<div class="col-xs-3"></div>
				<div class="col-xs-6">
					<div class="btn-group" style="margin-top: 25px">
						<button type="button" class="btn btn-primary btn-lg">
							<i class="fa fa-clock-o fa-lg"></i>&nbsp;&nbsp;实时
						</button>
						<button type="button" class="btn btn-primary btn-lg">
							<i class="fa fa-adjust fa-lg"></i>&nbsp;&nbsp;按天
						</button>
						<button type="button" class="btn btn-primary btn-lg">
							<i class="fa fa-history fa-lg"></i>&nbsp;&nbsp;按日
						</button>
						<button type="button" class="btn btn-primary btn-lg">
							<i class="fa fa-calendar fa-lg"></i>&nbsp;&nbsp;按月
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="section1">
			<div class="col-xs-11" style="width: 80%;">
				<p style="color: black; font-size: 14px;">位移 (mm)</p>
				<canvas id="myChart1" width="800" height="300"></canvas>
				<p
					style="text-align: right; color: black; margin-top: -40px; margin-left: -80px;">时间</p>
			</div>
			<div class="col-xs-1" style="padding: 10 0;"></div>
		</div>
	</div>
	<script>
		var data = {
			labels : [ "16:35:10", "17:35:10", "18:35:10", "19:35:10",
					"19:35:10", "20:35:10", "21:35:10", "22:35:10", "23:35:10" ],
			datasets : [

			{
				fillColor : "rgba(100,160,120,0)", //线下方的填充颜色
				strokeColor : "rgba(100,160,120,1)", //线的颜色
				pointColor : "rgba(220,220,220,1)", // 点的填充颜色
				pointStrokeColor : "#76AB88", // 点边线颜色
				label : "2010年",
				data : [ 2.20, 2.21, 2.21, 2.23, 2.23, 2.24, 2.25 ]
			}

			]
		}
		var options = {

			// 网格线是否在数据线的上面        
			scaleOverlay : false,

			// 是否用硬编码重写y轴网格线
			scaleOverride : true,

			// y轴刻度的个数
			scaleSteps : 6,

			// y轴每个刻度的宽度
			scaleStepWidth : 0.01,

			// y轴的起始值
			scaleStartValue : 2.20,

			// x轴y轴的颜色
			scaleLineColor : "rgba(0,0,0,1)",

			// x轴y轴的线宽    
			scaleLineWidth : 3,

			// 是否显示y轴的标签
			scaleShowLabels : true,

			// y轴标签显示值

			// 标签的字体
			scaleFontFamily : "'Arial'",

			// 标签字体的大小
			scaleFontSize : 12,

			// 标签字体的样式
			scaleFontStyle : "normal",

			// 标签字体的颜色
			scaleFontColor : "#666",

			// 是否显示网格线
			scaleShowGridLines : true,

			// 网格线的颜色
			scaleGridLineColor : "rgba(0,0,0,.1)",

			// 网格线的线宽
			scaleGridLineWidth : 1,

			// 是否是曲线
			bezierCurve : true,

			// 是否显示点
			pointDot : true,

			// 点的半径
			pointDotRadius : 3,

			// 点的线宽
			pointDotStrokeWidth : 1,

			//是否显示线条
			datasetStroke : true,

			// 数据线的线宽
			datasetStrokeWidth : 3,

			// 数据线是否填充颜色
			datasetFill : true,

			// 是否有动画效果
			animation : true,

			//Number 
			// 动画的步数
			animationSteps : 60,

			// 动画的效果
			animationEasing : "easeOutQuart",

			// 动画完成后调用
			onAnimationComplete : null
		}
		$(function() {
			var ctx = $("#myChart1").get(0).getContext("2d");
			var myNewChart = new Chart(ctx);
			myNewChart.Line(data, options);

		});
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#close").click(function() {
				$("#container").hide();
			});
		});
	</script>
</body>

</html>