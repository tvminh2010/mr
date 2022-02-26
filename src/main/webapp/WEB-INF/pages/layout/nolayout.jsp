<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US" scope="session"/>

<!DOCTYPE html>
<html>
<head>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<meta name="viewport" content="width=device-width, initial-scale=0.8">
    <meta name="description" content="">
    <meta name="author" content="">
 <spring:url value="../res" var="lib" />
<link rel="stylesheet/less" href="<c:url value="${lib}/less/stype.less"/>" />
<link rel="stylesheet" href="${lib}/css/app.css" />
<link rel="stylesheet" href="${lib}/css/custom.css" />

<link rel="stylesheet"
	href="${lib}/js/pickadate.js-3.5.6/lib/compressed/themes/default.css" />
<link rel="stylesheet"
	href="${lib}/js/pickadate.js-3.5.6/lib/compressed/themes/default.date.css" />



<script type="text/javascript" src="${lib}/js/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="${lib}/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${lib}/js/jquery/additional-methods.min.js"></script>




<script type="text/javascript"
	src="${lib}/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${lib}/bootstrap/css/bootstrap.css" />
<script type="text/javascript" src="../res/js/yeucauNVL/001.js"></script>
<script type="text/javascript" src="../res/js/yeucauNVL/002.js"></script>

<script type="text/javascript" src="../res/js/sockjs.min.js"></script>
<script type="text/javascript" src="../res/js/stomp.min.js"></script>
<script type="text/javascript" src="../res/js/moment.js"></script>
<script type="text/javascript" src="../res/js/transition.min.js"></script>
<script type="text/javascript" src="../res/js/collapse.js"></script>

<script type="text/javascript" src="../res/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="../res/js/jquery.twbsPagination.min.js"></script>
<link rel="stylesheet" href="../res/js/bootstrap-datetimepicker.min.css" />
<script type="text/javascript" src="../res/js/hoantra/001.js"></script>
<script>
	$(".rcorners1").focus();
	function startTime() {
		var today = new Date();
		var h = today.getHours();
		var m = today.getMinutes();
		var s = today.getSeconds();
		m = checkTime(m);
		s = checkTime(s);
		document.getElementById('txt').innerHTML = h + ":" + m + ":" + s;
		var t = setTimeout(startTime, 500);
	}
	function checkTime(i) {
		if (i < 10) {
			i = "0" + i
		}
		; // add zero in front of numbers < 10
		return i;
	}
</script>
	<style>
			
</style>
</head>
<body onload="startTime()">
	<!-- page -->



		<div class="container_fluid">
			<div class="row">
				<!-- header -->
			
				<!-- /header -->

				<div class="col-sm-12">
	<div class="headerright">
		
		            <sec:authorize access="hasRole('ROLE_ADMIN')" >
                        <a href="<c:url value="/" />" ><span class="glyphicon glyphicon-home"></span>Home</a>
                        <a id="logout" href="./admin/controlPanel"><span class="glyphicon glyphicon-user"></span> ${pageContext.request.userPrincipal.name} </a>
					</sec:authorize>
		  
			 <sec:authorize access="hasRole('ROLE_LINE')" >
                        <a id="logout" href="../admin/changepass"><span class="glyphicon glyphicon-user"></span> ${pageContext.request.userPrincipal.name} </a>
					</sec:authorize>
		
			<a href="<c:url value="/logout" />" ><span class="glyphicon glyphicon-log-out"></span>Logout</a>
		
	
	
	
      
		<p id="txt"></p>
		<p id="dat"></p>
		<script>
			var d = new Date();
			var options = {
				weekday : 'long',
				year : 'numeric',
				month : 'long',
				day : 'numeric'
			};
			document.getElementById("dat").innerHTML = d.toLocaleDateString(
					'vi-VN', options);
		</script>
	</div>
					<!-- /header -->

					<!-- content -->
						<div style="clear: both;">
						<div id="error">
						<tiles:insertAttribute name="error" />
					</div>
					<div id="content">
						<tiles:insertAttribute name="content" />
					</div>
					</div>
					<!-- /content -->
				</div>
			
			</div>
		</div>
		<!-- /page -->
		<!-- footer -->
	
		<!-- /footer -->

</body>
</html>
