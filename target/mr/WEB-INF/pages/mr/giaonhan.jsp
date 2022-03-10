

	 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
      <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
       <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
   <fmt:setLocale value="en_US" scope="session"/>
<style>
#selectline{
width:50px;
}
.qty{
width:50px;
}

</style>
<div>


</div>
<div id="socket">
<div id="main-content" class="container">
  

    
</div>
</div>
<div>
	<div style="float: left">
		
		 <h4>  <strong>Giao nhận</strong></h4>
	</div>
	<form role="form" method="post" id="MyFormID" name="import" action="giaonhanexcel" enctype="multipart/form-data">
					<table class="table small table-bordered table-striped rcorners1">
						<thead>
							<tr>
								<th colspan="4"> <h5>Nhập từ Excel</h5> </th>
							</tr>
						</thead>
						<tbody>
							<tr>
								
								
								<td width="20%"><b>File to upload</b></td>
     	                         <td><input type="file"  id="inputfile" name="excelfile" required="true" /></td>
								
						
								<td align="right">
     		<button type="submit" id="btnimport"  class="btn btn-primary">Import</button>
     	</td>
					</tr>
						
						</tbody>
					</table>		
					</form>
		

<!-- 	<form role="form" method="post" id="MyFormID" name="MyForm" action=""> -->
     <c:if test = "${rc.id != null && rc.id != '' }">
     
     
	<form:form id="fm1"  method="post" modelAttribute="rc" action="giaonhan">

	
	
	<form:hidden id="rcid" path="id" class="form-control"/>
	<form:hidden id="woid" path="wo.id" class="form-control" />
		<table class="table small table-striped table-bordered rcorners1">
		
			<tr>
			<%-- 		<td>
			<div id="radiotype">
			<form:radiobuttons path="type" items="${typecomp}" />
  <label><input  type="radio" checked="checked" name="type" value="${typebs}">Bổ xung</label>

  <label><input type="radio" name="type" value="${typesetup}">Setup</label> 

</div>

			</td> --%>
				<td> Line SX: 
				
				<label id="selectline"> </label></td>
				
				<td> WorkOrder:<label id="workorder"> </label></td>
				<td> Model: <label id="model"> </label></td>
				<td>Kế hoạch: <label id="plan">  </label></td>
				<td> Trạng thái: <label id="status">  </label></td>
			</tr>
		</table>
<%-- 		<div class="row">
			<div class="col-sm-5">
				<p>
					Line {<strong style="color: blue" id="showline"></strong>} --
					Model {<strong style="color: blue" id="showmodel" ></strong>}:
				</p>
			</div>

			<div class="col-sm-5">
				<p>
					Thời gian đóng phiên:&nbsp;<strong style="color: blue"
						class="timeclose">${timeclose}</strong>
				</p>
			</div>
		</div> --%>
		<table class=" table small table-striped table-bordered rcorners1" id="pstable">
		        
			<thead>
					<tr>
					<th><label for="MaTP">Item Name</label></th>
					<th><label for="LineSo">Mã NVL </label></th>
					<th><label for="MaTP">Package</label></th>
					<th><label for="MaTP">Kế hoạch</label></th>
					<th><label for="MaTP">Đã nhận</label></th>
					<th><label for="MaTP">Số lượng<span
							style='color: red; font-size: 0.9em;'> * </span>
					</label></th>
				</tr>
			</thead>
			<tbody id="tbody">
			
				
				
				
			</tbody>
			
		</table>
		<div align="right">
		<button  type="submit" class="btn btn-primary">Gửi yêu cầu</button>
		</div>
		
	</form:form>
</c:if>
	
	
	


</div>
<script>
$(document).ready(function() {
 	 $("#btnimport").click(function(event)
			  {
			    // event.preventDefault(); // cancel default behavior
			    $("#btnimport").submit();
			    if($("#inputfile").get(0).files.length!= 0){
			    	
			    
			    $('#btnimport').prop('disabled', true);
			    }
			    //... rest of add logic
			  });		


function displayPs() {

	 var table = document.getElementById('tbody')
	 $(table).empty();
	 $("#model").text('${rc.wo.model.pt_desc1}');
		$("#plan").text('${rc.wo.qty}');
	
	   $('#workorder').text('${rc.wo.name}');
	   $("#selectline").text('${rc.wo.line.name}');
	
	
		   $("#status").text('${rc.wo.status}'==1?"Mới tạo":"Đang sản xuất");
	   var i=0;
	   <c:forEach items="${rc.lDetailComp}" var="ldc" >
		
			
			    var row = table.insertRow(i);
			    var cell0 = row.insertCell(0);
			    var cell1 = row.insertCell(1);
			    var cell2 = row.insertCell(2);
			    var cell3 = row.insertCell(3);
			    var cell4 = row.insertCell(4);
			    var cell5 = row.insertCell(5);
			   var  j=i;
			   var plan = Number('${ldc.plan}');
			   var qtyper = Math.round(plan*1000)/1000;
			   var total = Math.round(Number('${ldc.totalResponse-ldc.totalReturn}')*100)/100;
			   var pac ='${ldc.model.pt_package}'==0.0?'':Number('${ldc.model.pt_package}');
			   var qtya = '${ldc.qty}'==0.0?'':Number('${ldc.qty}');
			   var qty = Number('${ldc.ps_qty_per}');
			   if(qty>0){
			    cell0.innerHTML = '<input  value="'+ '${ldc.id}' +'" name="lDetailComp['+ j +'].id" type="hidden"><p style="font-size:11px">'+'${ldc.model.pt_desc2}' +'</p>';
			    cell1.innerHTML = '<p style="font-size:11px">'+ '${ldc.model.pt_desc1}' +'</p>';
			    cell2.innerHTML ='<p style="font-size:11px">'+ pac +'</p>';
			   cell3.innerHTML =  '<p style="font-size:11px">'+ qtyper +'</p>';
			   cell4.innerHTML ='<p style="font-size:11px">'+ total +'</p>';
		    cell5.innerHTML = '<input  value="'+ '${ldc.model.pt_part}' +'" name="lDetailComp['+ j +'].model.pt_part" type="hidden"><input class="qty" value="'+ qtya +'" class="inputtext" name="lDetailComp['+ j +'].qty" type="number" width="50" step="any">';
			   }else{
				   cell0.innerHTML = '<input  value="'+ '${ldc.id}' +'" name="lDetailComp['+ j +'].id" type="hidden"><p style="color: #b3b3b3;font-size:11px">'+'${ldc.model.pt_desc2}'+'</p>';
				    cell1.innerHTML = '<p style="color: #b3b3b3;font-size:11px">'+ '${ldc.model.pt_desc1}'+'</p>';
				    cell2.innerHTML = '<p style="color: #b3b3b3;font-size:11px">'+pac+'</p>';
				   cell3.innerHTML =  '<p style="color: #b3b3b3;font-size:11px">'+ qtyper +'</p>';
				   cell4.innerHTML = '<p style="color: #b3b3b3;font-size:11px">'+ total +'</p>';
			    cell5.innerHTML = '<input  value="'+ '${ldc.model.pt_part}' +'" name="lDetailComp['+ j +'].model.pt_part" type="hidden"><input class="qty" value="'+ qtya +'" class="inputtext" name="lDetailComp['+ j +'].qty" type="number" width="50" step="any">';
				   
			   }  
		
		i++;
	   </c:forEach>  
	
}
 if('${rc.id}' != null){
displayPs();
} 
});
</script>

