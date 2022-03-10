<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
<div class="logo">
	<img width="60%" alt="" src="res/images/logo1.png">
</div>
<div id="top-bar" align="center">
		
		<div id="logout-container">
			<a id="" href="admin/controlPanel"><span class="glyphicon glyphicon-cog"></span> <spring:message code="label.admin" text="Admin" /></a>
		</div>
		<div id="logout-container">
			<a id="logout" href="./admin/controlPanel"><span class="glyphicon glyphicon-user"></span> ${pageContext.request.userPrincipal.name} </a>
		</div>
		<div id="logout-container">
			<a href="<c:url value="/logout" />" ><span class="glyphicon glyphicon-log-out"></span> <spring:message code="label.logout" text="Logout" /></a>
		</div>
	
		 <div id="logout-container">
				<spring:message code="label.lang" text="Language" />:<a href="?language=vn"><spring:message code="label.langvn" text="Ti\u1EBFng Vi\u1EC7t" /></a> | <a href="?language=en"><spring:message code="label.langen" text="English" /></a>
		</div> 
		
	</div>
	</div>
	<div class="a">
	<div align="center">

	    <h2><spring:message code="label.apptitle" text="MES TOTAL SOLUTION" /></h2>
	</div>
	
	<div class="row"  id="box-container" align="center">
	    
		<div class="col-md-2 col-sm-2 col-md-offset-3" align="center">
		     <a href="qms/" class="btn btn-info" role="button" ><spring:message code="label.qmsshort" text="QMS" /></a>
		 </div>
		<div class="col-md-2 col-sm-2" align="center">
		     <a href="iqc/" class="btn btn-info" role="button" ><spring:message code="label.iqcshort" text="IQC" /></a>
		 </div>
		<div class="col-md-2 col-sm-2" align="center">
		     <a href="production/" class="btn btn-info" role="button" ><spring:message code="label.production" text="PRODUCTION" /></a>
		 </div>
	
	</div>
</div>
<div class="footer-widget" align="center">
	 <div class="row">
	 <div class="col-md-1 col-sm-1" id="cl">  
	 </div>
	 	<div class="col-md-3 col-sm-3" id="cl">    
	 		<ul><h4><spring:message code="label.qmsshort" text="QMS" /></h4><h6><spring:message code="label.qms" text="QUALITY MANAGEMENT SYSTEM" /></h6>
	 		<li><a href="qms/input"><span class="caret"></span> <spring:message code="label.inputdata" text="Input Data" /></a></li>
		    <li><a href="qms/analysisFrom"><span class="caret"></span><spring:message code="label.dailyreport" text="Daily Report" /></a></li>
		    <li><a href="qms/totalbyshift"><span class="caret"></span><spring:message code="label.totalbyshift" text="Total by Shift" /></a></li>
		    <li><a href="qms/detailproducts"><span class="caret"></span><spring:message code="label.detailproducts" text="Detail Products" /></a></li>
            <li><a href="qms/summary"><span class="caret"></span><spring:message code="label.summarybyeachmodel" text="Summary by each Model, Date" /></a></li>
            <li><a href="qms/detaillotno"><span class="caret"></span><spring:message code="label.detailproductandlot" text="Detail Product and Lot Number" /></a></li>
		    </ul>
		</div>
		
		<div class="col-md-3 col-sm-3" id="cl">
	 		<ul><h4><spring:message code="label.iqcshort" text="IQC" /></h4><h6><spring:message code="label.iqc" text="INCOMING QUANTITY CONTROL" /></h6>
	 			<li><a href="iqc/input"><span class="caret"></span> <spring:message code="label.inputdata" text="Input Data" /></a></li>
		     	
		  
		     	
		    
		     	<li><a href="iqc/bypart"><span class="caret"></span><spring:message code="label.obparts" text="OutPut By Parts" /></a></li>
		     	<li><a href="iqc/byvendor"><span class="caret"></span><spring:message code="label.obvendor" text="OutPut By Vendor" /></a></li>
		     	<li><a href="iqc/bymonth"><span class="caret"></span><spring:message code="label.obmonth" text="OutPut By Month" /></a></li>
		     	   <li><a href="iqc/spqr"><span class="caret"></span><spring:message code="label.spqr" text="Supplier Part Quality Report" /></a></li>
		    </ul>
		</div>
		
		<div class="col-md-3 col-sm-3" id="cl">
	 		<ul><h4><spring:message code="label.production" text="PRODUCTION" /></h4>
	 		  <li><a href="production/qd"><span class="caret"></span>GIO CONG QD</a></li>
            <li><a href="production/totalwinding"><span class="caret"></span><spring:message code="label.totalwinding" text="TOTAL WINDING" /></a></li>
            <li><a href="prodution/kt"><span class="caret"></span>GIO CONG KT</a></li>
              <li><a href="production/totaltestting"><span class="caret"></span><spring:message code="label.totaltesting" text="TOTAL TESTTING" /></a></li>
            <li><a href="production/totalngay"><span class="caret"></span><spring:message code="label.totaldaialy" text="Total Daily" /></a></li>
            <li><a href="production/hangton"><span class="caret"></span>HÀNG TỒN</a></li>
            <li><a href="production/fgwh"><span class="caret"></span><spring:message code="label.fgwh" text="FGWH" /></a></li>
		    </ul>
		</div>
		<div class="col-md-1 col-sm-1" id="cl">  
	 </div>
	 </div>
</div>


<style type="text/css">	

	*{font-family: arial;}
	
	a{
		text-decoration: none;
		color: #000 ;
	}
	ul>h4{
		font-weight: bold;
	}
	.footer-widget ul,li{
		/*padding-left: 15px;*/
		/*padding-right: 15px;*/
		text-align: left;
		list-style-type: none;
	}
	.footer-widget li{
		padding-top: 5px;
		
		font-size: 12px;
		padding-left: 20px;

	}
	.footer-widget a{
	    font-family: "Open Sans",Arial,sans-serif;
		color: #000;
		display: block;
	}
	.footer-widget{
	padding-top:20px;
	padding-left:70px;
		margin-top: 60px;
		background-color: #F1F9FF;
		bottom: 0px;
		height:290px;
	}

	body{
/*     background-image: url("res/images/main-bg.jpg"); */
       background-color:#FFFFFF;
      
    }
	a:hover{
		color: #39f;
		text-decoration: underline;
	}
	.logo{
	    margin-left:40px;
		margin-top:10px;
	 float:left;
	
	}
	.a{
		padding-top:70px;
	}
	#top-bar{		
	float:right;
		color: #000;
		margin-right:30px;
		margin-top:10px;
		text-align: right;
		vertical-align: middle;
		/*background: #39f;*/
		height: 40px;	
		text-transform: capitalize;
	}
	#top-bar>div{
		padding-top: 10px;
		padding-right: 10px;
		display: inline-block;
	}
	#box-container{
		text-align: center;
		margin-top:40px;
	}
	.row {
    margin-right: 0px !important;
    margin-left: 0px !important;
}
	
	.box{
		font-size: 26;
		width: 20%;
		height: 100px;
		
		display: inline-block;
		/*padding-left: 3%;*/
		padding-top: 30px;
		color:#FFFFFF;
	}
	.col-md-2{
	    
	}
	.btn:hover{
	    background: #006EBE;
		box-shadow:  0px 0px 10px black;
	}
	.btn{
	    background: #006EBE;
		width:160px;
		height:150px;
		padding-top:60px;
		font-size:21px;
		box-shadow:  0px 0px 3px grey;
		
		
	}
	#cl{
	    padding-right:20px;
	    padding-left:20px;
	}
	 .caret{
	 transform: rotate(270deg);
	}

</style>