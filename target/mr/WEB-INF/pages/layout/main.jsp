<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title><tiles:insertAttribute name="title" ignore="true"/></title>
    


    <script type="text/javascript" src="res/js/jquery/jquery.min.js"></script> 
	<script type="text/javascript" src="res/bootstrap/js/bootstrap.min.js"></script>
 
    <script type="text/javascript" src="res/js/jquery/jquery.validate.min.js"></script>
   
  

    <script type="text/javascript" src="res/js/less.min.js"></script>
    
    <link rel="stylesheet" href="res/bootstrap/css/bootstrap.min.css"/>
     

<style>
body{
 		margin: 0px 0px 0px 0px;
 }
</style>
</head>
<body>
<!-- page -->
<div class="wrapper">
    <div class="container_fluid">
        <!-- header -->
        <header id="header">
            <tiles:insertAttribute name="header"/>
        </header>
        <!-- /header -->

        <!-- message -->
        <div id="message">
            <tiles:insertAttribute name="message"/>
        </div>
        <!-- /header -->

        <!-- content -->
        <div id="content">
            <tiles:insertAttribute name="content"/>
        </div>
        <!-- /content -->
    </div>
    <div class="push"><!--//--></div>
</div>
<!-- /page -->
<!-- footer -->
<footer>
    <div class="container">
        <tiles:insertAttribute name="footer"/>
    </div>
</footer>
<!-- /footer -->
</body>
</html>
