<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<%
	String path = request.getContextPath();
	String projectNum = request.getParameter("projectNum");
	projectNum = new String(projectNum.getBytes("ISO-8859-1"),"UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加形象进度</title>
<link href="<%=path %>/css/Acceptance.css" type="text/css" rel="stylesheet" />
<link href="<%=path %>/css/css.css" type="text/css" rel="stylesheet" />
<link href="<%=path %>/css/main.css" type="text/css" rel="stylesheet" />
<link href="<%=path %>/css/content-header.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=path %>/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/json-minified.js"></script>
<!-- 时间控件datepicker -->
<script src="<%=path %>/js/jquery-1.8.1.js"  type="text/javascript"></script>
<script src="<%=path %>/js/jquery-ui.js" type="text/javascript"></script>
<script src="<%=path %>/js/dateinput-ch-ZN.js" type="text/javascript"></script>
<link href="<%=path %>/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link href="<%=path %>/css/red-datepicker.css" rel="stylesheet" type="text/css"/>
   <script type="text/javascript">
     $(document).ready(function() {
    $("#aTimeform").datepicker(
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
  			action="<%=path%>/acceptance?method=listAcceptanceForProject&projectNum=<%=projectNum%>&backURL=${superBackURL}&pno=${pno}&projectName=${projectName}";
  		}
  	}
  </script>
</head>
<body>
	<!--main_top-->
	<nav id="content-header">
		    <B>位置：</B><b>分部验收&gt;&gt;&gt;修改分布验收&gt;&gt;&gt;${projectName}</b>
	    </nav>
	<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
		<tr>
			<td align="left" valign="top">
				<form method="post" action="<%=path%>/acceptance?method=updateAcceptance&projectNum=<%=projectNum%>&ano=${acceptance.ano}&backURL=${superBackURL}&pno=${pno}&projectName=${projectName}"  enctype="multipart/form-data" >
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						id="main-tab">
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">工程编号：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for"><input
								type="text" name="projectNum" disabled="disabled"   value="${acceptance.projectNum }" class="text-word"></td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">验收名称：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for">
							<select name="aType" id="level"  onchange="onSelectChange(this,'subLevel');" >
							</select></td>
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">验收描述：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for">
							<select name="detail" id="subLevel" > </select>	
							</td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">确认时间：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for">
								<input id="aTimeform" name="aTime" defval="${acceptance.aTime }" class="text-word"/>
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">验收结论：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for">
							  <input type="text" name="result" value="${acceptance.result }" class="text-word">
							</td>
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">验收图片：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for">
								<img alt="" class="image"  src="<%=path%>/commons?method=getImage&filePath=acceptancePath&fileName=${acceptance.image }">
								<div id="fdiv"></div> 
								  <input type="button"  value="添加" onclick="addFlie('image','fdiv')" class="text-add">
								</td>
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="right" valign="middle"
								class="borderright borderbottom bggray">详情：</td>
							<td align="left" valign="middle"
								class="borderright borderbottom main-for">
							  <input type="text" name="memo" value="${acceptance.memo}" class="text-word">
							</td>
						</tr>
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'" >
						    <td align="right" valign="middle"
								class="borderright borderbottom bggray">&nbsp;</td>
							<td align="left" valign="middle"  colspan="3"
								class="borderright borderbottom main-for">
								 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								 <input name="" type="submit" value="修改" class="text-but">
								 <input name="" type="submit" value="返回"  onclick="onBack()" class="text-but">
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
   function onSelectChange(obj,toSelId){
	   setSelect(obj.value,toSelId);
   }
	function setSelect(fromSelVal,toSelId){
		document.getElementById(toSelId).innerHTML="";
		jQuery.ajax({
		        url: "<%=path%>/dropDown",
				cache : false,
				data : {
					pno : fromSelVal,
					method : "getAcceptanceType"
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
					op.setAttribute("value", arr[o].bno);
					op.appendChild(document.createTextNode(arr[o].aType));
					obj.appendChild(op);
				}

			}
		}
		setSelect('0', 'level');
		setSelect('14','subLevel');
</script>
</html>