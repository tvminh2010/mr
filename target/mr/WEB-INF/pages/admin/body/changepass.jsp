<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container content" >
<c:if test = "${error==1 }">
   
					    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong><spring:message code="label.error" text="Error" />!</strong> Mã nhân viên ko tồn tại
				  	</div>
    	</c:if>
    			<c:if test = "${errordel==1 }">
   
					    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> Tồn tại dữ liệu liên quan
				  	</div>
    	</c:if>
<!-- User profile -->
	  <div  class="panel panel-default ">
    	<!-- <div class="panel-heading">List of User</div> -->
    	<div class="panel-body ">    
          <div id="form-insert" class="">
        <form id="userData" action="changepass"  method="post" >
           
          	<table class="table table-hover table-condensed">
          	

         
              <tr>
                <td><spring:message code="label.password" text="Password" />:</td>
                
                <td><%-- <input id="password"  class="to-clear form-control" autocomplete="off" value="${user.pass}" placeholder="Password"> --%>
                <input  id="password" width="40%" name="pass" class="to-clear form-control"  type="password"  value="${us.pass}" onfocus="this.removeAttribute('readonly');" required="required"/></td>
              </tr>
              <tr>
                <td><spring:message code="label.retype" text="Retype" /> <spring:message code="label.password" text="Password" />:</td>
                <td><input id="retype-password" value="${us.pass}"  type="password" class="to-clear form-control" placeholder="Retype password" required="required"/> <span id='message'></span></td>
              </tr>
              <tr>
   <tr>
      <td></td>
      <td>                	<button id="btn-save" type="submit" class="btn btn-primary"><spring:message code="label.save" text="Save" /></button></td>
   </tr>
            </table>
               </form>
          </div>
          
	
	
    </div>
    </div>


</div>

<script>

var selectedValues = new Array();



$(document).ready(function(){
	  $(".select2").select2({dropdownAutoWidth : false }); 
	 
 $("#inputStates1").change(function(){
	
		 getStaffByName($(this).val());
		 
	 });

	     

	
	 $('#password, #retype-password').on('keyup', function () {
		  if ($('#password').val() == $('#retype-password').val()) {
		    $('#message').html('Matching').css('color', 'green');
		  } else 
		    $('#message').html('Not Matching').css('color', 'red');
		});
  
});
</script>

<style>


</style>