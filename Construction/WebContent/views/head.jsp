<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>header</title>
<link href="<%=path%>/css/header.css" rel="stylesheet">
<link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/css/font-awesome.min.css">
</head>
<body>
	<div class="nav">
		<div class="role">
			<div class="logged-user">
				<div class="media-object">
					<c:if test="${user.uType == 7 }">
<%--              				<img src="<%=path%>/commons?method=getImage&fileName=A-01.jpg&filePath=builderPath" width="40"  height="40"/> --%>
					        <img src="<%=path%>/commons?method=getImage&fileName=${build.logo}&filePath=builderPath" width="40"  height="40"/>
			        </c:if>
					<c:if test="${user.uType != 7 }">
						<img alt="" src="<%=path%>/images/headerFrame/member.png" />
					</c:if>
				</div>
				<div class="media-body">
                  <h2>
                     <c:if test="${user.uType == 7 }">
                         <B>${user.unitName}大数据一体化云平台 </B>
                     </c:if>
                     <c:if test="${user.uType != 7}">
                         <B>智慧建管大数据一体化云平台</B>
                     </c:if>
                    <c:if test="${sessionScope.user != null}">
        			  <a style="color:white;float:right;"  href="<%=path%>/user?method=logout" target="_parent" onFocus="this.blur()"><i class="fa fa-power-off"></i><B>注销</B></a>
        	        </c:if>

                   </h2>
				</div>
			</div>
		</div>
	</div>
</body>
</html>