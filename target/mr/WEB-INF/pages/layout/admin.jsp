<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US" scope="session"/>
<!DOCTYPE html>
<html>
<head>
    <title><tiles:insertAttribute name="title" ignore="true"/></title>
    
    <link rel="stylesheet/less" href="<c:url value="/res/less/stype.less"/>" />
  
    <link rel="stylesheet" href="../res/css/jquery.dataTables.min.css"/>
    <link rel="stylesheet" href="../res/js/pickadate.js-3.5.6/lib/compressed/themes/default.css"/>
    <link rel="stylesheet" href="../res/js/pickadate.js-3.5.6/lib/compressed/themes/default.date.css"/>
    <link rel="stylesheet" href="../res/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="../res/css/formValidation.min.css">
     
    <script type="text/javascript" src="../res/js/jquery/jquery.min.js"></script> 
    <script type="text/javascript" src="../res/js/less.min.js"></script>
	<script type="text/javascript" src="../res/bootstrap/js/bootstrap.min.js"></script>
    	
    <script type="text/javascript" src="../res/js/jquery/jquery.validate.min.js"></script>
    <script type="text/javascript" src="../res/js/jquery/additional-methods.min.js"></script>
    <script type="text/javascript" src="../res/js/less.min.js"></script>
	<script type="text/javascript" src="../res/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../res/js/jquery/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="../res/js/jquery/canvasjs.js"></script>
	<script type="text/javascript" src="../res/js/pickadate.js-3.5.6/lib/compressed/picker.js"></script>
	<script type="text/javascript" src="../res/js/pickadate.js-3.5.6/lib/compressed/legacy.js"></script>
	<script type="text/javascript" src="../res/js/pickadate.js-3.5.6/lib/compressed/picker.date.js"></script>
	
	<link rel="stylesheet/less" href="<c:url value="/res/less/stype.less"/>" />


	<script type="text/javascript" src="../res/js/formValidation.min.js"></script>
	<script type="text/javascript" src="../res/js/framework/bootstrap.min.js"></script>
 
<script type="text/javascript" src="../res/js/select2.min.js"></script>
<link rel="stylesheet" type="text/css" href="../res/css/select2.min.css"/>
<link rel="stylesheet" type="text/css" href="../res/js/select2-bootstrap.css"/>
</head>
<body>
<!-- page -->
<div class="wrapper">
    <div class="container_fluid">
        
			<div class="row">
				<!-- header -->
				<div class="col-sm-2">
				 <sec:authorize access="hasRole('ROLE_ADMIN')" >
					<h3>Admin Panel</h3>
					</sec:authorize>
					 <sec:authorize access="!hasRole('ROLE_ADMIN')" >
					<h3>User Panel</h3>
					</sec:authorize>
				</div>
				<!-- /header -->

				<div class="col-sm-10">
                    <header id="header">
						<tiles:insertAttribute name="header" />
					</header>
					</div>
        </div>
        <!-- /header -->

        <!-- left-sidebar -->
          <div id="error">
						<tiles:insertAttribute name="error" />
					</div>
        <div id="left-sidebar">
            <tiles:insertAttribute name="left-sidebar"/>
        </div>
        <!-- /left-sidebar -->

        <!-- content -->
        
        <div id="content">
        	     
            <tiles:insertAttribute name="content"/>
        </div>
        <!-- /content -->
    </div>
    <div class="push"><!--//--></div>

<!-- /page -->
<!-- footer -->
<footer>
    <div class="container">
        <tiles:insertAttribute name="footer"/>
    </div>
</footer>
<!-- /footer -->
</div>
</body>
</html>
