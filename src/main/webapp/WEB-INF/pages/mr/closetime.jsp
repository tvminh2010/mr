<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="en_US" scope="session"/>
<div>

	<form:form role="form" method="post" id="MyFormID"  action="closetime" modelAttribute="closetime" >
					<table class="table small table-bordered table-striped rcorners1">
						
							<form:hidden  path="id" id="closetimeid" />
						
						<tbody>
						  <tr>
								<td colspan="2"> <h4>Đặt lịch</h4> </td>
								
							</tr>
						    <tr>
								<td> Tên: </td>
								<td><form:input path="name" type="text"  value="" /></td>
							</tr>
							   <tr>
								<td> Lịch đóng phiên: </td>
								<td><form:input id="closetime" path="closetime"  type="time"  /></td>
							</tr>
							<tr>
								
								
								
								
						
								<td colspan="2" align="right">
     		<button type="submit" class="btn btn-primary">Ok</button>
     	</td>
					</tr>
						
						</tbody>
					</table>		
		</form:form>
		 <button  onclick="deleteList('closetime/deletectall');" id="delete-all" class="btn btn-danger">Delete selected</button>
		
		<table id="tbl" class="table display table-bordered form-table border-collapse " border="1">
   		<thead>
			<tr>
			    <th width="5px"><input type="checkbox"  value="" id="isAgeSelected"/></th>
			    
				<th>Tên</th>
				<th>Lịch đóng phiên</th>
				
			
				
				<th width="5px"></th>
				<th width="5px"></th>
			</tr>
  		</thead>
  		<tbody>
  
  			 <c:forEach var="l" items="${cl}" varStatus="i">
		    <tr>
		    	<td width="5px"><input id="checkbo" type="checkbox" value="${l.id}"/></td>
		
		    	<td>${l.name}</td>
		    	
		    	<td>${l.closetime}</td>
		    
		    	
		    	  <td><a href="closetime?id=${l.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
		 	<%-- <td><a href="./proht?key=${l.id}"><span class="glyphicon glyphicon-edit"></span></a></td> --%>
		 		<td><a onclick="deleteOne('${l.id}','closetime/deletect');"><span class="glyphicon glyphicon-remove"></span></a></td>
		    </tr>
		 </c:forEach>
		 	
		
				
           </tbody>                    
          </table>	

</div>
<script>
 $(document).ready(function(){
 	$( "#MyFormID" ).submit(function( event ) {
 		
 		var ct = $("#closetime").val();
		if(ct.length==5){
		ct = ct+":00";
		$("#closetime").val(ct); 
		}
		}); 
});
 
</script>