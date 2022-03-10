<%@ page language="java" contentType="text/html; charset=UTF-8"%>
      <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
      
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
		<title>Material Request</title>
	</head>
	 <link rel="stylesheet" href="res/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="res/css/formValidation.min.css">
		
    <script type="text/javascript" src="res/js/jquery/jquery-1.11.3.min.js"></script> 
	<script type="text/javascript" src="res/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="res/js/formValidation.min.js"></script>
	<script type="text/javascript" src="res/js/framework/bootstrap.min.js"></script>
	<style type="text/css" media="screen">
		  .login-form-container{
			margin: 100px 30px 100px 30px!important;
		}	 
		body{
			background-color: #ccc!important;
		}
  </style>
<body>

<div class="row">
<div  class="col-sm-3 col-md-4">

</div>
<div  class="col-sm-7 col-md-4">
<div class="panel panel-primary login-form-container">
	  <div class="panel-heading">
			<h3 class="panel-title">LOGIN</h3>
	  </div>
	  <div class="panel-body">			
			<form id="login-form" action="<c:url value='/login' />" method="POST" class="form-horizontal" role="form" data-fv-framework="bootstrap" data-fv-icon-valid="glyphicon glyphicon-ok" data-fv-icon-invalid="glyphicon glyphicon-remove" data-fv-icon-validating="glyphicon glyphicon-refresh">
			    <div class="form-group">
			      <label class="control-label col-xs-2" for="user"><span class="glyphicon glyphicon-user"></span></label>
			      <div class="col-xs-9">          
			        <input type="text" class="form-control" id="user" name="username" placeholder="Username" required data-fv-notempty-message="The username is required">
			      </div>
			    </div>

			    <div class="form-group">
			      <label class="control-label col-xs-2" for="pwd"><span class="glyphicon glyphicon-lock"></span></label>
			      <div class="col-xs-9">          
			        <input type="password" class="form-control" id="pwd" name="password" placeholder="Password" required data-fv-notempty-message="The password is required">
			      </div>
			    </div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<div class="form-group">
						<div class="col-xs-10 col-sm-offset-1" align="right">
							<button type="submit" class="btn btn-primary">Login</button>
						</div>
					</div>
			</form>
			<c:if test="${not empty error}">
			  <div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				<strong>ERROR! </strong>${error}
			  </div>
			</c:if>			
			<c:if test="${not empty msg}">
			  <div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				<strong>MSG! </strong>${msg}
			  </div>
			</c:if>
	  </div>
	</div>
</div>
</div>
	
	
	
	<script type="text/javascript">
	$(document).ready(function() {
	    $('#login-form').formValidation();
	});
	</script>
  </body>
</html>