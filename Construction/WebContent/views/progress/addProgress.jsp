<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<%
	String path = request.getContextPath();
	String projectNum = request.getParameter("projectNum");
 	projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
    String projectName = request.getParameter("projectName");
    projectName =  new String(projectName.getBytes("ISO-8859-1"),"UTF-8") ;
	String backURL = request.getParameter("backURL");
	String pno = request.getParameter("pno");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加形象进度</title>
<link href="<%=path %>/css/css.css" type="text/css" rel="stylesheet" />
<link href="<%=path %>/css/main.css" type="text/css" rel="stylesheet" />

<link href="<%=path %>/css/Progress.css" rel="stylesheet" type="text/css"/>
<!-- 时间控件datepicker -->
<link href="<%=path %>/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link href="<%=path %>/css/red-datepicker.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/content-header.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css">

 <script type="text/javascript">
   $(document).ready(function() {
    $("#pTimeform").datepicker(
    {
    });
  });
  	function onBack(){
  		with(document.forms[0]){
  			action="<%=path%>/progress?method=listProgressForProject&projectNum=<%=projectNum%>&backURL=<%=backURL%>&pno=<%=pno%>&projectName=<%=projectName%>";
  		}
  	}
  </script>
</head>
<body>
	<nav id="content-header">
		    <B>位置：</B>形象进度&gt;&gt;&gt;添加形象进度&gt;&gt;&gt;<%=projectName %>
		    <div class="return">
		<a href="#" id="back"><i class="fa fa-minus-square-o fa-lg"></i>&nbsp;返回</a>
	</div>
	 </nav>
	<!--main_top-->
	<table width="99%" border="0" cellspacing="0" cellpadding="0"
		id="searchmain">
		<tr>
			<td align="left" valign="top">
				<form method="post" action="<%=path%>/progress?method=addProgress&projectNum=<%=projectNum%>&backURL=<%=backURL%>&pno=<%=pno%>&projectName=<%=projectName%>">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						id="main-tab">
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">工程编号：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								type="text" name="projectNum" disabled="disabled"   value="<%=projectNum%>" class="text-word"></td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">形象进度：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for">
							<select name="pType" id="level" >
							</select></td>
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">施工部位：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								type="text" name="part" value="" class="text-word"></td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">时间信息：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for">
								<input id="pTimeform" type="text" name="pTime" class="text-word"/>
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">详情：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for">
								<textarea rows="3" cols="30" class="text-word"  name="memo"></textarea>
								 
							
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'" >
						    <td align="right" valign="middle"
								class="borderright borderbottom bggray">&nbsp;</td>
							<td align="left" valign="middle"  colspan="3"
								class="borderright borderbottom main-for">
								 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								 <input name="" type="submit" value="添加" class="text-but">
								 <input name="" type="submit" value="返回"  onclick="onBack()" class="text-but">
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript" src="<%=path %>/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/json-minified.js"></script>
<script src="<%=path %>/js/jquery-1.8.1.js"  type="text/javascript"></script>
<script src="<%=path %>/js/jquery-ui.js" type="text/javascript"></script>
<script src="<%=path %>/js/dateinput-ch-ZN.js" type="text/javascript"></script>
<script type="text/javascript">
$("#back").click(function(){
	   history.go(-1);
});
 function onChange(id){
	jQuery.ajax({
		    url: "<%=path%>/dropDown",
			cache : false,
			data : "method=getProgressType",
			success : function(data) {
				setObj(data, id);
			}
		});
	}
	function setObj(data, id) {
		var arr = jsonParse(data);
		if (arr != null && arr.length > 0) {
			var obj = document.getElementById(id);
			obj.innerHTML = "";
			for ( var o in arr) {
				var op = document.createElement("option");
				op.setAttribute("value", arr[o].bno);
				op.appendChild(document.createTextNode(arr[o].pType));
				obj.appendChild(op);
			}
		}
	}
	onChange("level")
</script>
</html>