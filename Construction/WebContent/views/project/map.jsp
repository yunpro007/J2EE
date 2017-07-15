<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
   
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
#container {
	width: 100%;
	height: 100%;
	background: silver;
}

body {
	margin: 0px;
}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=pf49hCGIuN6vIhmUgyaGDLC6AB4ULYdd"></script>  
   <title>map</title>
  </head>
  <body>
  <c:if test="${projects != null }">
  	<c:forEach items="${projects}" var="ins">
	  	<input type="hidden" name="x" value="${ins.x }">
	  	<input type="hidden" name="y" value="${ins.y }">
	  	<input type="hidden" name="pno" value="${ins.pno }">
	  	<input type="hidden" name="projectName" value="${ins.projectName }">
	  	<input type="hidden" name="memo" value="${ins.memo }">
  	</c:forEach>
  </c:if>
  <c:if test="${x_position != null }">
  	<input type="hidden" id="x_position" value="${x_position }">
	<input type="hidden" id="y_position" value="${y_position }">
  </c:if>
  	<div id="container" align="center"></div>  
	  <script type="text/javascript"> 
		    var x = document.getElementsByName("x");
	  	    var y = document.getElementsByName("y");
	  	    var pno = document.getElementsByName("pno");
			var projectName = document.getElementsByName("projectName");
			var memo = document.getElementsByName("memo");
			var x_positon = document.getElementById("x_positon");
			var y_positon = document.getElementById("y_positon");
		    var len = pno.length;
			var marker;
			var point;
			var info_html;
			var label;
			var map = new BMap.Map("container");  // 创建地图实例  
		 	map.addControl(new BMap.NavigationControl()); //添加平移缩放控件  
 			map.addControl(new BMap.ScaleControl());  //添加放大、缩小控件  
 			map.enableScrollWheelZoom();//允许鼠标滑轮操作
 			if("undefined" != typeof(x_position)){
 				map.centerAndZoom(new BMap.Point(x_position.value,y_position.value), 15);
 	 		}else{
 	 			if(len > 0){
 	 				map.centerAndZoom(new BMap.Point(x[0].value, y[0].value), 15);
 	 			}else{
 	 				map.centerAndZoom(new BMap.Point(116.404, 39.915),15);
 	 			}
 	 	 	 } 
 			 // 复杂的自定义覆盖物
 		    function ComplexCustomOverlay(point, text, mouseoverText, pnotext){
 		      this._point = point;
 		      this._text = text;
 		      this._overText = mouseoverText;
 		      this._pno =  pnotext ;
 		    }
 		    ComplexCustomOverlay.prototype = new BMap.Overlay();
 		    ComplexCustomOverlay.prototype.initialize = function(map){
 		      this._map = map;
 		      var div = this._div = document.createElement("div");
 		      div.style.position = "absolute";
 		      div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
 		      div.style.backgroundColor = "#436EEE";
 		      div.style.border = "1px solid #292929";
 		      div.style.color = "white";
 		      div.style.height = "18px";
 		      div.style.padding = "2px";
 		      div.style.lineHeight = "18px";
 		      div.style.whiteSpace = "nowrap";
 		      div.style.MozUserSelect = "none";
 		      div.style.fontSize = "12px"
 		      var span = this._span = document.createElement("span");
 		      div.appendChild(span);
 		      span.appendChild(document.createTextNode(this._text));      
 		      var that = this;
 		      var arrow = this._arrow = document.createElement("div");
 		      arrow.style.background = "url(<%=path%>/images/content/arrow.ico) no-repeat";
 		      arrow.style.position = "absolute";
 		      arrow.style.width = "12px";
 		      arrow.style.height = "14px";
 		      arrow.style.top = "22px";
 		      arrow.style.left = "10px";
 		      arrow.style.overflow = "hidden";
 		      div.appendChild(arrow);
 		      
 		      div.onmouseover = function(){
 		        this.style.backgroundColor = "#48D1CC";
 		        this.style.borderColor = "#292929";
 		        this.getElementsByTagName("span")[0].innerHTML = that._overText;
 		        arrow.style.backgroundPosition = "0px -20px";
 		      }
 		 
 		      div.onmouseout = function(){
 		        this.style.backgroundColor = "#48D1CC";
 		        this.style.borderColor = "#292929";
 		        this.getElementsByTagName("span")[0].innerHTML = that._text;
 		        arrow.style.backgroundPosition = "0px 0px";
 		      };
 		     div.onclick = function(){
 		    	 var pnotem = that._pno ;
 		    	  window.parent.location.href ="<%=path%>/project?method=searchProjectByID&pno="+pnotem;
  		      };
 		      map.getPanes().labelPane.appendChild(div);
 		       
 		      return div;
 		    }
 		    ComplexCustomOverlay.prototype.draw = function(){
 		      var map = this._map;
 		      var pixel = map.pointToOverlayPixel(this._point);
 		      this._div.style.left = pixel.x - parseInt(this._arrow.style.left) + "px";
 		      this._div.style.top  = pixel.y - 30 + "px";
 		    }
	        for(var i = 0 ; i < len ; i++){
	        	var  mouseoverTxt = " "+projectName[i].value ;
	        	var  pnotext = ""+pno[i].value ;
	        	var myCompOverlay = new ComplexCustomOverlay(new BMap.Point(x[i].value, y[i].value), " "+projectName[i].value , mouseoverTxt , pnotext);
	        	map.addOverlay(myCompOverlay);
			}
</script>  
  </body>
</html>
	<%-- point = new BMap.Point(x[i].value, y[i].value);  // 创建点坐标  
		        var opts = {
		  		      position : point,    // 指定文本标注所在的地理位置
		  		      offset   : new BMap.Size(30, -30)    //设置文本偏移量
		  	    }
		        createMarker = function(point, info_html,pno) {  
		            var _marker = new BMap.Marker(point);  
		            _marker.addEventListener("click", function(e) {  
			              window.parent.location.href = "<%=path%>/project?method=searchProjectByID&pno=" + pno;
		                 /*  this.openInfoWindow(new BMap.InfoWindow(info_html));   */
		            });  
		            return _marker;  
		        };  
				marker = createMarker(point,info_html,pno[i].value);
		        map.addOverlay(marker); 
		        label = new BMap.Label(""+projectName[i].value, opts);  // 创建文本标注对象
		        label.setStyle({
		             color : "blue",
		             fontSize : "12px",
		             height : "20px",
		             lineHeight : "20px",
		             fontFamily:"微软雅黑"
		         });
		        map.addOverlay(label);  --%>
