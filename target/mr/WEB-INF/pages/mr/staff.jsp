<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 


<div class="container content" >
<!-- staff profile -->
	<c:if test = "${error==1 }">
   
					    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> Mã nhân viên đã tồn tại 
				  	</div>
    	</c:if>
    			<c:if test = "${errordel==1 }">
   
					    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> Tồn tại dữ liệu liên quan
				  	</div>
    	</c:if>
	<form:form id="staffData" modelAttribute="staff" method="post">
	<form:hidden id="id" class="form-control" path="id" value="${staff.id}"/>
	  <table class="table table-hover">
	    <thead>
	      <tr>
	        <th colspan="2"><h4>Staff Profile</h4></th>
	      </tr>
	    </thead>
	    <tbody>
	      <tr>
		   <td><b>Code</b></td>
		   <td><form:input id="code" class="form-control" path="code" value="${staff.code}" required="required"/></td>
		  </tr>
		  <tr>
		   <td><b>Firstname</b></td>
		   <td><form:input id="firstName" class="form-control" path="firstName" value="${staff.firstName}"  required="required"/></td>
		  </tr>
		  <tr>
		   <td><b>lastname</b></td>
		   <td><form:input id="lastName" class="form-control" path="lastName" value="${staff.lastName}"  required="required"/></td>
		  </tr>
		  <tr>
		   <td><b>Part</b></td>
		   <td><form:input id="part" class="form-control" path="part" value="${staff.part}"  required="required" /></td>
		  </tr>
		  <tr>
		   <td><b>Position</b></td>
		   <td><form:input id="position" class="form-control" path="position" value="${staff.position}"/></td>
		  </tr>	
		  <tr>
		   <td colspan="2" align="right">
		     <a onclick="clearAll();" class="btn btn-primary">Reset</a>
		     <c:if test="${staff.id==null}">
		       <button type="submit" class="btn btn-primary">Add</button>
		     </c:if>
		     <c:if test="${staff.id!=null}">
		       <button type="submit" class="btn btn-primary">Save</button>
		     </c:if>
		   </td>
		  </tr>		
		</tbody>
	  </table>
	</form:form>
	  <hr>
	  <table id="tbl" class="table display table-bordered form-table border-collapse " border="1">
   		<thead>
			<tr>
				<td><b>Code</b></td>
				<td><b>Firstname</b></td>
				<td><b>Lastname</b></td>
				<td><b>Part</b></td>
				<td><b>Position</b></td>
				<td width="5px"></td>
				<td width="5px"></td>
			</tr>
  		</thead>
  		<tbody>
            <c:forEach var="l" items="${listStaff}" >
               <tr>
                  <td>${l.code}</td>
                  <td>${l.firstName}</td>
                  <td>${l.lastName}</td>
                  <td>${l.part}</td>
                  <td>${l.position}</td>
                  <td align="center"><a href="?key=${l.code}"><span class="glyphicon glyphicon-edit"></span></a></td>
                  <td align="center">
                  	<a href="javascript: sm2('','${l.id}','${l.code}');"><span class="glyphicon glyphicon-remove"></span></a>
                  </td>
               </tr>
             </c:forEach>
           </tbody>                    
          </table>


</div>

<style>

</style>
<script>
function clearAll(){
	document.getElementById("id").value="0";
	document.getElementById("code").value="";
	document.getElementById("firstName").value="";
	document.getElementById("lastName").value="";
	document.getElementById("part").value="";
	document.getElementById("position").value="";
}
$(document).ready(function(){
  $('#tbl').DataTable( {
    "order": [[ 0, "desc" ]],
   
     "ordering": true,
     "info": true,
	 "autoWidth": false,
	 "pageLength": 100,
     "fixedHeader": true
  });
  
  
});
</script>