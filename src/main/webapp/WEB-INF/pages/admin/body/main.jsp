<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="container content" >
<!-- User profile -->
	<form role="form" action="profile" method="post" data-fv-framework="bootstrap">
	
	  <table class="table table-hover">
	    <thead>
	      <tr>
	        <th colspan="2"><h4><spring:message code="label.profile" text="Profile" /></h4></th>
	      </tr>
	    </thead>
	    <tbody>
		  <tr>
		   <td width="25%"><b><spring:message code="label.username" text="User name" /></b></td>
		   <td width="75%">${user.staff.code}</td>
		  </tr>
		  <tr>
		   <td><b><spring:message code="label.password" text="Password" /></b></td>
		   <td>
		     <input class="nonborder" type="password" value="${user.pass}">
		     <a class="fn-btn" href="#"><span class="glyphicon glyphicon-edit"></span></a>
		     <div  >
		     	
		     			<table class="p">
		     			<tr>
		     			 	<td>
		     			 	<spring:message code="label.password" text="Password" />
		     			 	</td>
		     			 	<td> <input  id="pass" name="pass" type="password" value="${user.pass}"/></td>
		     			</tr>
		     			<tr>
		     			<td>
		     			 	  <spring:message code="label.retype" text="Retype" /> <spring:message code="label.password" text="Password" />
		     			 	</td>
		     			 	<td>
		     			 	
		     			 	 <div class="repass">
		     		   <input type="password" name="confirm_password" id="confirm_password" /><p id="messag"></p>
		     		      </div></td>
		     			</tr>
		     			<tr>
		     			  <td>
		     			  </td>
		     			  <td align="right"><input type="submit"  class="btn btn-primary" value="Save"></td>
		     			</tr>
		     			</table>
		     		 
		     		     <div class="repass">
		     		      </div>
		     
		     </div>
		   </td>
		  </tr>
		  <tr id="rpass-container" class="hide">
		   <td><b><spring:message code="label.retype" text="Retype" /> <spring:message code="label.password" text="Password" /></b></td>
		   <td>
		     <input class="nonborder" readonly="readonly"  type="password" value="${user.pass}">
		     <button class="fn-btn" href=""><span class="glyphicon glyphicon-floppy-disk"></span></button>
		   </td>
		  </tr>
		  <tr>
		   <td><b><spring:message code="label.fname" text="Firstname" /></b></td>
		   <td>${user.staff.firstName}</td>
		  </tr>
		  <tr>
		   <td><b><spring:message code="label.lname" text="Lastname" /></b></td>
		   <td>${user.staff.lastName}</td>
		  </tr>
		  
		  <tr>
		   <td><b><spring:message code="label.part" text="Part" /></b></td>
		   <td>${user.staff.part}</td>
		  </tr>
		  <tr>
		   <td><b><spring:message code="label.pos" text="Position" /></b></td>
		   <td>${user.staff.position}</td>
		  </tr>
		  <tr>
		   <td><b><spring:message code="label.partrole" text="Role" /></b></td>
		   <td> 
		   	
				${user.role.name}	
		 
		   </td> 
		   </tr>
		
		</tbody>
	  </table>
	</form>


</div>

<script>


$(document).ready(function(){
	$(".p").hide();
	$('.fn-btn').click( function () {
	   $('.p').toggle();
	   $('.nonborder').toggle();
	});
	$('#confirm_password').on('keyup', function () {
	    if ($(this).val() == $('#pass').val()) {
	        $('#messag').html('<span class="glyphicon glyphicon-ok"></span>').css('color', 'green');
	    } else $('#messag').html('<span class="glyphicon glyphicon-remove"></span>').css('color', 'red');
	});
});
</script>

<style>
.content{
	position:absolute;
	padding-left:210px;
}
.fn-btn{
	float:right;
	background:transparent;
	border:none;
	color:#006EBE;
}
.nonborder{
	border: 0px none;
	background: transparent;
}
.repass p{
    display: inline;
}
.p tr td{

padding:5px;
}
</style>