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
        <form:form id="userData" action="user" modelAttribute="us" method="post" >
              <form:hidden  path="id" id="id" />
          	<table class="table table-hover table-condensed">
          	  <thead>
          	    <tr>
          	      <td width="20%"><b><spring:message code="label.property" text="Property" /></b></td>
          	      <td width="80%"><b><spring:message code="label.value" text="Value" /></b></td>
          	    </tr>
          	  </thead>

              <tr>
                <td><spring:message code="label.staffcode" text="Staff Code" />:</td>
                <td>
                 <%--  <form:select id="staffCode" class="to-clear form-control" path="staff.code" value="${user.staff.code}" required="required" >
		              <option></option>
		              <c:forEach var="l" items="${listStaff}" >
		                <c:choose>
		                  <c:when test="${l.code==user.staff.code}"> 
		                    <option selected firstName="${l.firstName}" lastName="${l.lastName}" part="${l.part}" position="${l.position}" value="${l.code}">${l.code}</option>
		                  </c:when>
		                  <c:otherwise>
		                    <option firstName="${l.firstName}" lastName="${l.lastName}" part="${l.part}" position="${l.position}" value="${l.code}">${l.code}</option>
		                  </c:otherwise>
		                </c:choose>
		              </c:forEach>
		          </form:select> --%>
		          																	<c:choose>
														<c:when
															test="${us.staff.id!=null}">
															<form:input id="inputStates1" path="staff.code" list="browsers1"
																class="form-control"   autocomplete="off"
																value="${us.staff.code }"
																 required="required"/>
															

														</c:when>
														<c:otherwise>
															<form:input width="40%" id="inputStates1" path="staff.code"  list="browsers1"
																class="form-control" name="browser1"  autocomplete="off" required="required"/>
															
														</c:otherwise>
														
													</c:choose>
													

													<datalist id="browsers1">
														<c:forEach var="staff" items="${listStaff}">
															<option firstName="${staff.firstName}" lastName="${staff.lastName}" part="${staff.part}" position="${staff.position}" id="${staff.id}" class="${staff.code}" value="${staff.code}">${staff.code}-${staff.firstName} ${staff.lastName} </option>
														</c:forEach>
													</datalist>	

												
                </td>
              </tr>
              <tr>
                <td><spring:message code="label.password" text="Password" />:</td>
                
                <td><%-- <input id="password"  class="to-clear form-control" autocomplete="off" value="${user.pass}" placeholder="Password"> --%>
                <form:input  id="password" width="40%" path="pass" class="to-clear form-control"  type="password"  value="${us.pass}" onfocus="this.removeAttribute('readonly');" required="required"/></td>
              </tr>
              <tr>
                <td><spring:message code="label.retype" text="Retype" /> <spring:message code="label.password" text="Password" />:</td>
                <td><input id="retype-password" value="${us.pass}"  type="password" class="to-clear form-control" placeholder="Retype password" required="required"/> <span id='message'></span></td>
              </tr>
              <tr>
              <tr>
                <td><spring:message code="label.fname" text="Firstname" />:</td>
                <td><input id="firstName" placeholder="Firstname" value="${us.staff.firstName}" readonly class="to-clear form-control" required="required"/></td>
              </tr>
              <tr>
                <td><spring:message code="label.lname" text="Lastname" />:</td>
                <td><input id="lastName" placeholder="Lastname" value="${us.staff.lastName}" readonly class="to-clear form-control"/></td>
              </tr>
              <tr>
                <td><spring:message code="label.part" text="Part" />:</td>
                <td><input id="part" placeholder="Part" value="${us.staff.part}" readonly class="to-clear form-control" /></td>
              </tr>
              <tr>
                <td><spring:message code="label.pos" text="Position" />:</td>
                <td><input id="position" placeholder="Position" value="${us.staff.position}" readonly class="to-clear form-control"/></td>
              </tr>
              <tr>
                <td><spring:message code="label.partrole" text="Role" />:</td>
                <td>
                <c:forEach var="lpr" items="${lpr}" varStatus="r" > 
                        <br>
             		    <c:if test="${user.role.id==lpr.id }">
             		         <form:radiobutton checked="checked" path="role.id" value="${lpr.id}"  />${lpr.name} 
             		    </c:if>
             		    <c:if test="${us.role.id!=lpr.id }">
             		         <form:radiobutton path="role.id" value="${lpr.id}" required="required"  />${lpr.name} 
             		    </c:if>
                    </c:forEach>   
                </td>
                </tr>
               
          
                 
            
              <tr align="right">
                <td colspan="2">
                <c:if test="${us.staff.code==null}">
                	<button type="submit" class="btn btn-primary"><spring:message code="label.add" text="Add" /></button>
                </c:if>
                <c:if test="${us.staff.code!=null}">
                	<a href="javascript: clearAll()" class="btn btn-primary"><spring:message code="label.reset" text="Reset" /></a>
                	<button id="btn-save" type="submit" class="btn btn-primary"><spring:message code="label.save" text="Save" /></button>
                </c:if>
                </td>
              </tr>
         
            </table>
               </form:form>
          </div>
          
	
	<div>
	 <p style="color:#006EBE"><spring:message code="label.userlist" text="User List" /></p>   
     <table id="tbl" class="table display table-bordered form-table border-collapse table-condensed " border="1">
   		<thead>
			<tr>
				<td><b><spring:message code="label.username" text="User name" /></b></td>
				<td><b><spring:message code="label.fname" text="Firstname" /></b></td>
				<td><b><spring:message code="label.lname" text="Lastname" /></b></td>
				<td><b><spring:message code="label.part" text="Part" /></b></td>
				<td><b><spring:message code="label.pos" text="Position" /></b></td>
				<td><b><spring:message code="label.role" text="Role" /></b></td>
					
				<td width="5px"></td>
				<td width="5px"></td>
			</tr>
  		</thead>
  		<tbody>
            <c:forEach var="l" items="${listUser}" >
               <tr>
                <td>${l.staff.code}</td>
                  <td>${l.staff.firstName}</td>
                  <td>${l.staff.lastName}</td>
                 <td>${l.staff.part}</td>
                          <td>${l.staff.position}</td>
                  
                        <td>${l.role.name}</td>
          
                
      
                    
                  <td align="center"><a href="?key=${l.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
                  <td align="center">
                  	<a href="javascript: sm2('','${l.id}','');"><span class="glyphicon glyphicon-remove"></span></a>
                  </td>
               </tr>
             </c:forEach>
           </tbody>                    
          </table>
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
 $('#tbl').DataTable( {
     "order": [[ 0, "desc" ]],

   
     "paging":true,
     "info":     true,
	     "autoWidth": false,
	     "pageLength": 100
 });
	     
	 function getStaffByName(name){
			$.post( "/mr/api/getStaffByName", {pname:name}, function( data ) {
				if(data.result=='ok'){
				  $('#statesHidden').attr('value',data.id);
				  $("#firstName").attr("value",data.firstName);
					 $("#lastName").attr("value",data.lastName);
					 $("#part").attr("value",data.part);
					 $("#position").attr("value",data.position);
					 
				}
			}).fail(function(err) {
				console.log( "connection error: "+err );
			});
		}
	
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