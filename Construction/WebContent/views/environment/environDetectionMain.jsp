<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<%
    //该页面是环境监测显示页面。初始化时是实时查询，即当天信息，是根据设备号、和环境类别查询的当天信息。
    //若只改变下拉菜单，则显示的还是当天信息。
    //若要周查询、月查询，则除了选择下拉菜单外，还要点击后边的按钮。
    //周查询是查询当天开始过去7天，月查询是查询当天开始过去30天。
	String path = request.getContextPath() ;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主要内容区main</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/content-header.css"/>
<link rel="stylesheet" href="<%=path%>/css/Enironment_Monitor.css" />
<link href="<%=path%>/css/font-awesome.min.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/json-minified.js"></script> 
<script src="<%=path%>/js/Chart.js"></script>
</head>
<body>
	<nav id="content-header">
		    <B>位置：</B>环境监测>>>${projectName}
			<div class="return"> 
			    <c:if test="${backURL != null}">
				 	<a href="javascript:location.href=encodeURI('<%=path %>/environment?method=initMain')" target="mainFrame" ><i class="fa fa-minus-square-o fa-lg"></i>&nbsp;返回</a>
				</c:if>
				<c:if test="${superBackURL != null}">
			    	<a href="javascript:location.href=encodeURI('<%=path %>/project?method=${superBackURL}&pno=${pno}')" target="mainFrame"><i class="fa fa-minus-square-o fa-lg"></i>&nbsp;返回</a>	
				</c:if>
			</div>
	</nav>
	<div class="row header-content" style="margin:-3px 0px 0px 0px;">
		  <div class="col-md-4 col-xs-4">
		  	    <label for="equipment_class" class="header-label">设备号:</label>
			     <select name="aq" id="level" class="equipment_class" onchange="changeType()">
				　</select>
		  </div>
		  <div class="col-md-4 col-xs-4">
		  	     <label for="equipment_class" class="header-label">环境类别:</label>
			    <select name="type" id="type"  onchange="changeType()" class="equipment_class">
					<option value="1" selected="selected">温度</option>
					<option value="2">湿度</option>
					<option value="3">PM2.5</option>
					<option value="4">PM10</option>
					<option value="5">噪音</option>
			     　</select>
		  </div>
		  <div class="col-md-4 col-xs-4">
		  	      <button class="query"  onclick="changeModule('day')">实时</button>
				  <button class="query"  onclick="changeModule('week')">按周</button>
				  <button class="query"  onclick="changeModule('month')">按月</button>
		  </div>
	</div>
	<div>
	    <div>
	      <div id="name">温度</div>
	       <div>
			<canvas id="tem" width="800px" height="600px"></canvas>
	       </div>
	    </div>
	</div>
</body>
<script >
   //全局变量
   var  kind  = "Temperature";
   //设备下拉列表
  function onChange(id){
		jQuery.ajax({
			    url: "<%=path%>/dropDown",
				cache : false,
			
				data : {
					   pno : "${pno}",
					method : "getAq"
				},
				success : function(data) {
					setObj(data, id);
				}
			});
	}
    function  changeModule(moduleType){
    	if(moduleType == 'day'){ // 实时查询
    		changeType();
    	}else{ // 按周或按月查询
    		//对kind赋环境类型的值
        	updateType();
          var aqnum = $('#level option:selected').val();
    		jQuery.ajax({
        		url: "<%=path%>/environment",
    			 method:"GET",
    			 cache: false ,
    			 data :{
    				 method:"showEnvirDetectionByCondition",
    				 type : kind,
    				 pno : "${pno}",
    			 	 aq  : aqnum  ,
    			 	 module : moduleType
    			 } , 
    			 success: function (data){
    				 var  array =  eval('(' + data + ')');
    				 showChart(array);
    			 },
    			 error : function (jqXHR, textStatus, errorThrown) {
    			 }
        		
        	});    		
    	}
    }
	function setObj(data, id) {
		 var arr = jsonParse(data);
		if (arr != null && arr.length > 0) {
			var obj = document.getElementById(id);
				obj.innerHTML = "";
			for ( var o in arr) {
				var op = document.createElement("option");
				   op.setAttribute("value", arr[o].aq);
					op.appendChild(document.createTextNode(arr[o].aqV));
					obj.appendChild(op);
				}
			} 
		}
 function changeType(){
	 updateType();
	 //请求数据
	 var aqnum = $('#level option:selected').val();
	 getData(aqnum);
  }
 function  updateType(){
	 var secVal = $('#type  option:selected').val() ;
	 var secName = $('#type option:selected').text() ;
	  $('#name').text(secName);
	 if("2" == secVal){
		  kind = "Humidity";
	 }else if('3' == secVal){
		 kind = "PM25";
	 }else if('4' == secVal){
		 kind = "PM10";
	 }else if('5' == secVal){
		 kind = "Noise";
	 }else{
		kind = "Temperature";
	 }
  }
  function  getData(aqnum){
	  jQuery.ajax({
			 url: "<%=path%>/environment",
			 method:"GET",
			 cache: false ,
			 data :{
				 method:"showSingleEnvirDetection",
				 type : kind ,
				 pno : "${pno}",
			 	 aq  : aqnum
			 } , 
			 success: function (data){
				 var  array = eval('(' + data + ')');
				 showChart(array);
			 }
		 }); 
  }
  function showChart(data){
	  var  dataMin = data.dataMin;
	  var  dataStep = data.dataStep;
	  var  timestamp = data.timeStamp;
	  var  timeLabel = data.timeStamp;
	  var  yNum = data.yNumber ;
	  var  dataShow =  {
	    	    labels: timeLabel.split(",") ,
	    	    datasets: [
	    			        {
	    			            fillColor: "rgba(100,160,120,0)",                    //线下方的填充颜色
	    			            strokeColor: "rgba(100,160,120,1)",   //线的颜色
	    						pointColor : "rgba(220,220,220,1)",  // 点的填充颜色
	    			            pointStrokeColor : "#76AB88",          // 点边线颜色
	    			            label: "横坐标",
	    			            data: data.data.split(",") //与X轴对应的数据
	    			        }
	    	    		]
	    		};
	  var dataOptions = {
		        scaleOverlay : false, // 网格线是否在数据线的上面        
		        scaleOverride : true,	 // 是否用硬编码重写y轴网格线
		        scaleSteps : yNum, // y轴刻度的个数
		        scaleStepWidth : dataStep, // y轴每个刻度的宽度
		        scaleStartValue : dataMin-dataStep - 0.1,// y轴的起始值
		        scaleLineColor : "rgba(0,0,0,1)", // x轴y轴的颜色
		        scaleLineWidth : 1, // x轴y轴的线宽
		        scaleShowLabels : true,// 是否显示y轴的标签
		        scaleFontFamily : "'Arial'",        // 标签的字体
		        scaleFontSize : 12,// 标签字体的大小
		        scaleFontStyle : "normal",// 标签字体的样式
		        scaleFontColor : "#666",// 标签字体的颜色    
		        scaleShowGridLines : true,// 是否显示网格线
		        scaleGridLineColor : "rgba(0,0,0,.1)",// 网格线的颜色
		        scaleGridLineWidth : 1,// 网格线的线宽    
		        bezierCurve : true,// 是否是曲线
		        pointDot : true,// 是否显示点
		        pointDotRadius : 3,// 点的半径
		        pointDotStrokeWidth : 1,// 点的线宽
		        datasetStroke : true,//是否显示线条
		        datasetStrokeWidth : 3,// 数据线的线宽
		        datasetFill : true,// 数据线是否填充颜色
		        animation : true,// 是否有动画效果
		        animationSteps : 60,        // 动画的步数//Number
		        animationEasing : "easeOutQuart",// 动画的效果
		        onAnimationComplete : null        // 动画完成后调用
		   } ;
	  var ctxTem = $("#tem").get(0).getContext("2d");
	  var myChartTem = new Chart(ctxTem);
	      myChartTem.Line(dataShow,dataOptions);
  }
 $(function ()
   {
	 onChange("level");
	 getData("${aq}");
  }
 ); 
</script>
</html>