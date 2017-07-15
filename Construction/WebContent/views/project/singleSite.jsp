<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String path = request.getContextPath() ;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工程概况</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/content-header.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/css/singleSite.css"/>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/font-awesome.min.css" />
</head>
<body>
		<nav id="content-header">
		    <B>位置：</B>工程概况>>>${project.projectName}
			<div class="return"> 
					<c:if test="${superBackURL == 'searchAllProjects' }">
						<a href="<%=path%>/project?method=${superBackURL}" target="_top"><i
															class="fa fa-minus-square-o fa-lg"></i>返回&nbsp;</a>
					</c:if>
					<c:if test="${superBackURL == 'showMain' }">
						<a href="<%=path%>/project?method=${superBackURL}" target="mainFrame"><i
															class="fa fa-minus-square-o fa-lg"></i>返回&nbsp;</a>
					</c:if>
			</div>
		</nav>
		<div class="wrapper">
			 <div class="container">
	             <div class="row clearfix">
		               <div class="col-md-4 column">
			                <div class="panel panel-primary" style="width:250px;">
			                  	<div class="panel-heading">
					                   <h2 class="panel-title">
						                    <center><B>工地简介</B></center>
					                   </h2>
				                </div>
				                <div class="panel-body">
					                    <img src="<%=path%>/images/content/singSite1.PNG" class="img-responsive center-block" style="height:180px;"/>
				                </div>
			                </div>
		                </div>
		                <div class="col-md-4 column">
			                <div class="panel panel-primary">
			                  	<div class="panel-heading">
					                   <h3 class="panel-title">
						                    <center><B>现场图片</B></center>
					                   </h3>
				                </div>
				                <div class="panel-body">
					                   <img src="<%=path%>/images/content/singSite2.PNG" class="img-responsive center-block" style="height:180px;"/>
				                </div>
			                </div>
		                </div>
		                <div class="col-md-4 column">
			                <div class="panel panel-primary">
			                  	<div class="panel-heading">
					                   <h3 class="panel-title">
						                    <center><B>BIM</B></center>
					                   </h3>
				                </div>
				                <div class="panel-body">
					                   <img src="<%=path%>/images/content/singSite3.PNG" class="img-responsive center-block" style="height:180px;"/>
				                </div>
			                </div>
		                </div>
	            </div>
                 <div class="row clearfix" style="margin-top: 45px;">
                       <c:if test="${sessionScope.modules != null}">
                        	<c:forEach items="${modules}" var="item" varStatus="status">
                        		 <c:choose>
                        		 	<c:when test="${item.mname =='工程概况' }">
                        		 		 <div class="col-md-2 column">
                 	    					<a href="javascript:location.href=encodeURI('<%=path %>/project?method=showProjectInfo&projectName=${project.projectName}&projectNum=${project.projectNum}&backURL=${backURL}&pno=${project.pno}')"><button type="button" class="btn btn-primary btn-space">工程概况</button></a>
                 	  					 </div>
                        		 	</c:when>
                        		 	<c:when test="${item.mname =='形象进度' }">
                        		 		 <div class="col-md-2 column">
                 	    					<a href="javascript:location.href=encodeURI('<%=path%>/progress?method=listProgressForProject&projectName=${project.projectName}&projectNum=${project.projectNum}&backURL=${backURL}&pno=${project.pno}')"><button type="button" class="btn btn-primary btn-space">形象进度</button></a>
                 	  					 </div>
                        		 	</c:when>
                        		 	<c:when test="${item.mname =='工程检测' }">
                        		 		 <div class="col-md-2 column">
                 	    					<a href="${item.hyperlink}" target="_blank"><button type="button" class="btn btn-primary btn-space">工程检测</button></a>
                 	  					 </div>
                        		 	</c:when>
                        		 	<c:when test="${item.mname =='夜间施工' }">
                        		 		 <div class="col-md-2 column">
                 	    					<a href="#"><button type="button" class="btn btn-primary btn-space">夜间施工</button></a>
                 	  					 </div>
                        		 	</c:when>
                        		 	<c:when test="${item.mname =='工地视频' }">
                        		 		 <div class="col-md-2 column">
                 	    					<a href="<%=path%>/${item.hyperlink}&pno=${project.pno}&backURL=${backURL}&projectName=${project.projectName}" target="mainFrame"><button type="button" class="btn btn-primary btn-space">工地视频</button></a>
                 	  					 </div>
                        		 	</c:when>
                        		 	<c:when test="${item.mname =='基坑监测' }">
                        		 		 <div class="col-md-2 column">
                 	    					<a href="javascript:location.href=encodeURI('<%=path%>/pitbase?method=showSiteMain&pno=${project.pno}&backURL=${backURL}&projectName=${project.projectName}')"><button type="button" class="btn btn-primary btn-space">基坑监测</button></a>
                 	  					 </div>
                        		 	</c:when>
                        		 	
                        		 </c:choose>	
                        	</c:forEach>
                        </c:if>
                 </div>
                  <div class="row clearfix" style="margin-top: 10px;">
                 	     <c:if test="${sessionScope.modules != null}">
                        	<c:forEach items="${modules}" var="item" varStatus="status">
                        		 <c:choose>
                        		 	<c:when test="${item.mname =='起重设备' }">
                        		 		 <div class="col-md-2 column">
                 	    					<a href="${item.hyperlink}" target="mainFrame"><button type="button" class="btn btn-primary btn-space">起重设备</button></a>
                 	  					 </div>
                        		 	</c:when>
                        		 	<c:when test="${item.mname =='人证相符' }">
                        		 		 <div class="col-md-2 column">
                 	    					<a href="javascript:location.href=encodeURI('<%=path%>/people?method=showSiteMain&backURL=${backURL}&pno=${project.pno}&projectName=${project.projectName}')"><button type="button" class="btn btn-primary btn-space">人证相符</button></a>
                 	  					 </div>
                        		 	</c:when>
                        		 	<c:when test="${item.mname =='人员培训' }">
                        		 		 <div class="col-md-2 column">
                 	    					<a href="${item.hyperlink}"><button type="button" class="btn btn-primary btn-space">人员培训</button></a>
                 	  					 </div>
                        		 	</c:when>
                        		 	<c:when test="${item.mname =='样板工地' }">
                        		 		 <div class="col-md-2 column">
                 	    					<a href="#"><button type="button" class="btn btn-primary btn-space">样板工地</button></a>
                 	  					 </div>
                        		 	</c:when>
                        		 	<c:when test="${item.mname =='执法情况' }">
                        		 		 <div class="col-md-2 column">
                 	    					<a href="#"><button type="button" class="btn btn-primary btn-space">执法情况</button></a>
                 	  					 </div>
                        		 	</c:when>
                        		 	<c:when test="${item.mname =='环境监测' }">
                        		 		 <div class="col-md-2 column">
                 	    					<a href="javascript:location.href=encodeURI('<%=path%>/environment?method=showEnvirDetection&aq=${project.aq1}&backURL=${backURL}&pno=${project.pno}&projectName=${project.projectName}')"><button type="button" class="btn btn-primary btn-space">环境监测</button></a>
                 	  					 </div>
                        		 	</c:when>
                        		 	
                        		 </c:choose>
                         	</c:forEach>
                         </c:if>
                 </div>
                  <div class="row clearfix" style="margin-top: 10px;margin-bottom:43px;">
                  		   <c:if test="${sessionScope.modules != null}">
                        	<c:forEach items="${modules}" var="item" varStatus="status">
                        		 <c:choose>
                        		 	<c:when test="${item.mname =='门禁考勤' }">
                        		 		 <div class="col-md-2 column">
                 	    					<a href="javascript:location.href=encodeURI('<%=path%>/door?method=listDoorForProject&projectNum=${project.projectNum}&projectName=${project.projectName}')"><button type="button" class="btn btn-primary btn-space">门禁考勤</button></a>
                 	  					 </div>
                        		 	</c:when>
                        		 	<c:when test="${item.mname =='奖惩情况' }">
                        		 		 <div class="col-md-2 column">
                 	    					<a href="#"><button type="button" class="btn btn-primary btn-space">奖惩情况</button></a>
                 	  					 </div>
                        		 	</c:when>
                        		 	<c:when test="${item.mname =='施工许可' }">
                        		 		 <div class="col-md-2 column">
                 	    					<a href="javascript:location.href=encodeURI('<%=path%>/permit?method=showSiteMain&permission=${project.permission}&backURL=${backURL}&pno=${project.pno}&projectName=${project.projectName}')"><button type="button" class="btn btn-primary btn-space">施工许可</button></a>
                 	  					 </div>
                        		 	</c:when>
                        		 	<c:when test="${item.mname =='诚信评价' }">
                        		 		 <div class="col-md-2 column">
                 	    					<a href="${item.hyperlink}"><button type="button" class="btn btn-primary btn-space">诚信评价</button></a>
                 	  					 </div>
                        		 	</c:when>
                        		 	
                        		 </c:choose>
                         	</c:forEach>
                         </c:if>
                 </div>
			 </div>
		</div>
	</body>
</html>