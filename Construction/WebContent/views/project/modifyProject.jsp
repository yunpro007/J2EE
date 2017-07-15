<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改工程</title>
<link href="<%=path%>/css/css.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>/main.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/content-header.css" />
<link rel="shortcut icon" href="<%=path%>/images/main/favicon.ico" />
<script type="text/javascript" src="<%=path%>/js/jquery-1.7.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/json-minified.js"></script>
<!-- 时间插件datepicker -->
<link href="<%=path%>/css/jquery-ui.css" rel="stylesheet"
	type="text/css" />
<link href="<%=path%>/css/red-datepicker.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/bootstrap.min.css">
<script src="<%=path%>/js/jquery-1.8.1.js" type="text/javascript"></script>
<script src="<%=path%>/js/jquery-ui.js" type="text/javascript"></script>
<script src="<%=path%>/js/dateinput-ch-ZN.js" type="text/javascript"></script>
<style>
body {
	overflow-x: hidden;
	background: #f2f0f5;
	padding: 15px 0px 10px 5px;
}

#searchmain {
	font-size: 12px;
}

#search {
	font-size: 12px;
	background: #548fc9;
	margin: 10px 10px 0 0;
	display: inline;
	width: 100%;
	color: #FFF
}

#search form span {
	height: 40px;
	line-height: 40px;
	padding: 0 0px 0 10px;
	float: left;
}

#search form input.text-word {
	height: 24px;
	line-height: 24px;
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
	background: url(<%=path%>/images/main/list_input.jpg) no-repeat left
		top;
	border: none;
	cursor: pointer;
	font-family: "Microsoft YaHei", "Tahoma", "Arial", '宋体';
	color: #666;
	float: left;
	margin: 8px 0 0 6px;
	display: inline;
}

#search a.add {
	background: url(<%=path%>/images/main/add.jpg) no-repeat 0px 6px;
	padding: 0 10px 0 26px;
	height: 40px;
	line-height: 40px;
	font-size: 14px;
	font-weight: bold;
	color: #FFF
}

#search a:hover.add {
	text-decoration: underline;
	color: #d2e9ff;
}

#main-tab {
	border: 1px solid #eaeaea;
	background: #FFF;
	font-size: 12px;
}

#main-tab th {
	font-size: 12px;
	background: url(<%=path%>/images/main/list_bg.jpg) repeat-x;
	height: 32px;
	line-height: 32px;
}

#main-tab td {
	font-size: 12px;
	line-height: 40px;
}

#main-tab td a {
	font-size: 12px;
	color: #548fc9;
}

#main-tab td a:hover {
	color: #565656;
	text-decoration: underline;
}

.bordertop {
	border-top: 1px solid #ebebeb
}

.borderright {
	border-right: 1px solid #ebebeb
}

.borderbottom {
	border-bottom: 1px solid #ebebeb
}

.borderleft {
	border-left: 1px solid #ebebeb
}

.gray {
	color: #dbdbdb;
}

td.fenye {
	padding: 10px 0 0 0;
	text-align: right;
}

.bggray {
	background: #f9f9f9;
	font-size: 14px;
	font-weight: bold;
	padding: 10px 10px 10px 0;
	width: 140px;
}

.main-for {
	padding: 10px;
}

.main-for input.text-word {
	width: 200px;
	height: 36px;
	line-height: 36px;
	border: #ebebeb 1px solid;
	background: #FFF;
	font-family: "Microsoft YaHei", "Tahoma", "Arial", '宋体';
	padding: 0 10px;
}

.main-for select.text-word {
	width: 220px;
	height: 36px;
	line-height: 36px;
	border: #ebebeb 1px solid;
	background: #FFF;
	font-family: "Microsoft YaHei", "Tahoma", "Arial", '宋体';
	padding: 0 10px;
}

.main-for input.text-but {
	width: 100px;
	height: 40px;
	line-height: 30px;
	border: 1px solid #cdcdcd;
	background: #e6e6e6;
	font-family: "Microsoft YaHei", "Tahoma", "Arial", '宋体';
	color: #969696;
	float: left;
	margin: 0 10px 0 0;
	display: inline;
	cursor: pointer;
	font-size: 14px;
	font-weight: bold;
}

.main-for input.text-add {
	width: 55px;
	height: 20px;
	line-height: 20px;
	border: 1px solid #cdcdcd;
	background: #e6e6e6;
	font-family: "Microsoft YaHei", "Tahoma", "Arial", '宋体';
	color: #969696;
	float: left;
	margin: 0 10px 0 0;
	display: inline;
	cursor: pointer;
	font-size: 12px;
	font-weight: bold;
}

.main-for img {
	width: 40px;
	height: 30px;
}
</style>
<script type="text/javascript">
  $(function() {
     $("#sTimeform").datepicker(
    {
    });
     $("#beginTimeform").datepicker(
    {
    });
     $("#endTimeform").datepicker(
    {
    });
  });
  	function addFlie(name,divName)
  	{
  		var fdiv = document.getElementById(divName);
  		fdiv.innerHTML +='<div> <input type="file" name ="'+name+'"+ > <input type="button"  class="text-add" value="删除..." onclick="delone(this)"><br/></div>';
  	}
  	function delone(obj)
  	{
  		obj.parentNode.parentNode.removeChild(obj.parentNode);
  	}
  	function onBack(){
  		with(document.forms[0]){
  			action="<%=path%>/project?method=showMain";
  		}
  	}
  </script>
</head>
<body>
	<!--main_top-->
	<nav id="content-header"> <B>位置：</B>工程概况>>>修改工程 </nav>
	<table width="99%" border="0" cellspacing="0" cellpadding="0"
		id="searchmain">
		<tr>
			<td align="left" valign="top">
				<form method="post"
					action="<%=path%>/project?method=modifyProject&pno=${project.pno}"
					enctype="multipart/form-data">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						id="main-tab">
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">工程编码：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								type="text" name="projectNum" value="${project.projectNum }"
								class="text-word"></td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">工程名称：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								type="text" name="projectName" value="${project.projectName }"
								class="text-word"></td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">工程地址：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								type="text" name="location" value="${project.location }"
								class="text-word"></td>
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">建设单位：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								type="text" name="investor" value="${project.investor }"
								class="text-word"></td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">施工总包单位：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><select
								name="bno" id="bno" class="text-word">
<%-- 									<option value="1">${build.bName}</option> --%>
							</select>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">监理单位：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								type="text" name="supervisor" value="${project.supervisor }"
								class="text-word"></td>
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">工程造价：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								type="text" name="price" value="${project.price }"
								class="text-word"></td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">建筑规模：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								type="text" name="scale" value="${project.scale }"
								class="text-word"></td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">质量监督单位：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								type="text" name="managementQ" value="${project.managementQ }"
								class="text-word"></td>
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">安全监督单位：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								type="text" name="managementS" value="${project.managementS }"
								class="text-word"></td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">报监时间：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								id="sTimeform" name="sTime" defval="${project.sTime }"
								class="text-word" />
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">工程简介：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								type="text" name="brief" value="${project.brief }"
								class="text-word"></td>
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">工地所在省：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><select
								name="province" id="province" class="text-word"
								onchange="onSelectChange(this,'city');"></select></td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">工地所在市：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><select
								name="city" id="city" class="text-word"
								onchange="onSelectChange(this,'district');">
							</select></td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">工地所在区：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><select
								name="district" id="district" class="text-word">
							</select></td>
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">经度：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><e:number
									name="x" step="0.01" defval="${project.x }"
									styleClass="text-word" /></td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">维度：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><e:number
									name="y" step="0.01" defval="${project.y }"
									styleClass="text-word" /></td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">工地类型：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><select
								name="sType" class="text-word">
									<option value="15">房屋建设</option>
									<option value="50">市政工程</option>
							</select></td>
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">开工日期：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								id="beginTimeform" name="beginTime"
								defval="${project.beginTime }" class="text-word" /></td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">结束日期：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								id="endTimeform" name="endTime" defval="${project.endTime }"
								class="text-word" /></td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">在线工地：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><select
								name="isOnline" class="text-word">
									<option value="0">未在线</option>
									<option value="1">在线</option>
							</select></td>
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">是否完工：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><select
								name="over" class="text-word">
									<option value="0">未完工</option>
									<option value="1">完工</option>
							</select></td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">是否隐藏：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><select
								name="hide" class="text-word">
									<option value="0">未隐藏</option>
									<option value="1">隐藏</option>
							</select></td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">备注：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								type="text" name="memo" value="${project.memo }"
								class="text-word"></td>
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">施工许可证图片：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><img alt=""
								class="image"
								src="<%=path%>/commons?method=getImage&filePath=projectPath&fileName=${project.permission}">
								<div id="fdiv"></div> <input type="button" value="添加"
								onclick="addFlie('permission','fdiv')" class="text-add">
							</td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">工地简介图片：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><img alt=""
								class="image"
								src="<%=path%>/commons?method=getImage&filePath=projectPath&fileName=${project.image}">
								<div id="idiv"></div> <input type="button" value="添加"
								onclick="addFlie('image','idiv')" class="text-add"></td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">工地影像：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><img alt=""
								class="image"
								src="<%=path%>/commons?method=getImage&filePath=projectPath&fileName=${project.video}">
								<div id="vdiv"></div> <input type="button" value="添加"
								onclick="addFlie('video','vdiv')" class="text-add"></td>
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">&nbsp;</td>
							<td align="left" valign="middle" colspan="3"
								class="borderright borderbottom main-for"><input name=""
								type="submit" value="修改" class="text-but"> <input
								name="" type="submit" onclick="onBack()" value="返回"
								class="text-but"></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">

 function onChange(id){
	jQuery.ajax({
		  url: "<%=path%>/dropDown",
		  cache: false,
		  data: "method=getBuilder",
		  success: function(data){
		    setObj(data,id);
		  }
	});
}
function setObj(data,id){
	var arr = jsonParse(data);
	if(arr != null && arr.length > 0){
		var obj = document.getElementById(id);
		obj.innerHTML="";
		for(var o in arr){
			var op = document.createElement("option");
			op.setAttribute("value",arr[o].bno);
			op.appendChild(document.createTextNode(arr[o].bName));
			obj.appendChild(op);
		}
	}
}

function onSelectChange(obj,toSelId){
	setSelect(obj.value,toSelId);
}
function setSelect(fromSelVal,toSelId){
	//alert(document.getElementById("province").selectedIndex);
	document.getElementById(toSelId).innerHTML="";
	jQuery.ajax({
	        url: "<%=path%>/dropDown",
			cache : false,
			data : {
				parentId : fromSelVal,
				method : "getPalces"
			},
			success : function(data) {
				createSelectObj(data, toSelId);
			}
		});
	}
	function createSelectObj(data, toSelId) {
		var arr = jsonParse(data);
		if (arr != null && arr.length > 0) {
			var obj = document.getElementById(toSelId);
			obj.innerHTML = "";
			for ( var o in arr) {
				var op = document.createElement("option");
				op.setAttribute("value", arr[o].region_id);
				op.appendChild(document.createTextNode(arr[o].region_name));
				obj.appendChild(op);
			}

		}
	}
    function Selected(){
    	if('15' == '${project.sType}'){
    		document.getElementsByName('sType')[0].options[0].selected=true;
    	}else{
    		document.getElementsByName('sType')[0].options[1].selected=true;
    	}
    	document.getElementsByName('isOnline')[0].options[parseInt("${project.isOnline}")].selected=true;
    	document.getElementsByName('over')[0].options[parseInt("${project.over}")].selected=true;
	    document.getElementsByName('hide')[0].options[parseInt("${project.hide}")].selected=true;	
	}
	setSelect('1', 'province');
	onChange("bno") ;
	Selected() ;
</script>
</html>