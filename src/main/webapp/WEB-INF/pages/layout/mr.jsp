<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US" scope="session"/>


<!DOCTYPE html>
<html>
<head>
<title><tiles:insertAttribute name="title" ignore="true" /></title>

<link rel="stylesheet/less" href="<c:url value="/res/less/stype.less"/>" />
<link rel="stylesheet" href="res/css/app.css" />
<link rel="stylesheet" href="res/css/custom.css" />
<link rel="stylesheet" href="res/css/jquery-ui.min.css" />
<link rel="stylesheet"
	href="res/js/pickadate.js-3.5.6/lib/compressed/themes/default.css" />
<link rel="stylesheet"
	href="res/js/pickadate.js-3.5.6/lib/compressed/themes/default.date.css" />



<script type="text/javascript" src="res/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="res/js/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="res/js/jquery/datepicker-vi.js"></script>
<script type="text/javascript"
	src="res/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="res/js/jquery/additional-methods.min.js"></script>



<link rel="stylesheet" type="text/css"
	href="res/datatable/datatables.min.css" />
<script type="text/javascript"
	src="res/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="res/bootstrap/css/bootstrap.css" />





<script type="text/javascript" src="res/js/jquery/canvasjs.js"></script>
<!-- <script type="text/javascript"
	src="res/js/pickadate.js-3.5.6/lib/compressed/picker.js"></script>
<script type="text/javascript"
	src="res/js/pickadate.js-3.5.6/lib/compressed/legacy.js"></script>
<script type="text/javascript"
	src="res/js/pickadate.js-3.5.6/lib/compressed/picker.date.js"></script> -->


<script type="text/javascript" src="res/js/select2.min.js"></script>
<link rel="stylesheet" type="text/css" href="res/css/select2.min.css" />
<link rel="stylesheet" type="text/css"
	href="res/js/select2-bootstrap.css" />


<script type="text/javascript" src="res/js/moment.js"></script>
<script type="text/javascript" src="res/js/transition.min.js"></script>
<script type="text/javascript" src="res/js/collapse.js"></script>

<script type="text/javascript" src="res/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="res/js/jquery.twbsPagination.min.js"></script>
<link rel="stylesheet" href="res/js/bootstrap-datetimepicker.min.css" />
<script type="text/javascript" src="res/js/hoantra/001.js"></script>
<script src="res/readExcel/dist/cpexcel.js"></script>
<script src="res/readExcel/shim.js"></script>
<script src="res/readExcel/jszip.js"></script>
<script src="res/readExcel/xlsx.js"></script>
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
				<div class="col-sm-2">
					<header id="header">
						<tiles:insertAttribute name="header" />
					</header>
				</div>
				<!-- /header -->

				<div class="col-sm-10">
	<div class="headerright">
		
		
		  <sec:authorize access="hasRole('ROLE_ADMIN')" >
			<a id="" href="admin/controlPanel" class="btn btn-link"><span class="glyphicon glyphicon-cog"></span>Admin</a>
					</sec:authorize>
			<a id="logout" href="./admin/controlPanel"><span class="glyphicon glyphicon-user"></span> ${pageContext.request.userPrincipal.name} </a>
		
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
		<footer>
			<div class="footer">
				<tiles:insertAttribute name="footer" />
			</div>
		</footer>
		<!-- /footer -->

</body>
</html>
