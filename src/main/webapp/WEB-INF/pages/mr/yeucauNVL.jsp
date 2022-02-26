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
#pstable tbody tr{

}
</style>


<div>
	<div style="float: left">
		<h4>Yêu cầu NVL</h4>
	</div>


<!-- 	<form role="form" method="post" id="MyFormID" name="MyForm" action=""> -->
	<form:form id="fm1"  method="post" modelAttribute="rc" action="yeucauNVL">
	
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
				<select id="selectline">
						<option></option>
						<c:forEach var="line" items="${listline}">
							<option value="${line.id}">${line.name }</option>

						</c:forEach>
				</select></td>
				
				<td> WorkOrder: <select id="workorder">
				
				</select></td>
				<td> Model: <label id="model"> </label></td>
				<td>Kế hoạch: <label id="plan">  </label></td>
				<td> Trạng thái: <select id="selectstatus">
						<option value="2">Bổ xung</option>
						
							<option value="1">Setup</option>

						
				</select> </label>
<!-- 				<div class="radio">
  <label><input type="radio" name="optradio">Setup</label>
</div>
<div class="radio">
  <label><input type="radio" name="optradio">Bổ xung</label>
</div> -->
</td>
			</tr>
		</table>
				<div style="float:both;height:20px">
				<div style="float:left">
				           Các phiên đang y/c:&nbsp;
				</div>
				<div id="dangyc">
					<c:forEach var="rcc" items="${lrc}">
							<strong>${rcc.closetime.closetime} - ${rcc.closetime.name}, </strong>
						</c:forEach>
			</div>
				
			</div>
    <div class="panel panel-default">
     <div class="panel-body">			
     <div>
			<div style="float:left">
			     <p>
					T/g đóng phiên:&nbsp;
				</p>
			</div>
				<div style="float:left">
				<form:select path="closetime.id" id="selectclosetime" required="true" style="color: blue"> 
						
						<c:forEach var="cl" items="${lclosetime}">
							<option value="${cl.id}">${cl.closetime} - ${cl.name}</option>

						</c:forEach>
				</form:select>
				</div>
			<!-- 	 <p style="float:left">
				&nbsp;&nbsp;TT:	&nbsp;
				</p>
				 <p style="float:left;color: blue;font-style: italic;" id="rcstatus">
					
				</p> -->
				<div style="float:right;margin-bottom:10px" >
				 <button  type="button" id="guiyeucau" class=" nhaplai btn btn-primary">Nhập lại</button>
			       <button  type="submit" id="guiyeucau" class=" guiyeucau btn btn-primary">Gửi yêu cầu</button>
			    </div>
			</div>
		
		<table class=" table small table-striped table-bordered rcorners1" id="pstable">
		        
			<thead>
					<tr>
					<th><label for="MaTP">Item Name</label></th>
					<th><label for="LineSo">Mã NVL </label></th>
					<th><label for="MaTP">Package</label></th>
					<th><label for="MaTP">Kế hoạch</label></th>
					<th><label for="MaTP">Đã nhận</label></th>
					<th><label for="MaTP">Số lượng<span
							style='color: red; font-size: 0.9em;'>*</span>
					</label></th>
				</tr>
			</thead>
			<tbody id="tbody">
			
				
				
				
			</tbody>
			
		</table>
		<div align="right">
		<button  type="button" id="nhaplai" class=" nhaplai btn btn-primary">Nhập lại</button>
		<button  type="submit" id="guiyeucau" class=" guiyeucau btn btn-primary">Gửi yêu cầu</button>
		</div>
		</div>
		</div>
	</form:form>
	

	
	<span style='color: red; font-size: 0.9em;' id="label1"> </span>


</div>
 <div class="modal" id="confirmModal" style="display: none; z-index: 1050;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body" id="confirmMessage">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" id="confirmOk">Ok</button>
		        	<button type="button" class="btn btn-default" id="confirmCancel">Cancel</button>
		        </div>
			</div>
		</div>
	</div>
<script>

$(document).ready(function() {
	
		$("#selectclosetime").val('${timeclose.id}');
		
function displayPs() {

	 var table = document.getElementById('tbody')
	 $(table).empty();
	    $("#model").text('${rc.wo.model.pt_desc1}');
		$("#plan").text('${rc.wo.qty}');
		$("#selectline").val('${rc.wo.line.id}');
		$('#workorder').children().remove();
		$("#workorder").append(
				new Option('${rc.wo.name}',
						'${rc.wo.id}', true, true));

	  // $("#status").text('${rc.wo.status}'==1?"Mới tạo":"Đang sản xuất");
	   $('#woid').val('${rc.wo.id}');
	   

	   $("#selectclosetime").val('${rc.closetime.id}');
	  
	   //$("#rcstatus").text('${rc.id}' != null && '${rc.id}' != ""? 'đang yêu cầu':'thêm mới');
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
			   var pac ='${ldc.model.pt_package}'==0.0  ?'':Number('${ldc.model.pt_package}');
			   var qtya = '${ldc.qty}'==0.0?'':Number('${ldc.qty}');
			   var qty = Number('${ldc.ps_qty_per}');
			   var qtyEarly = '${ldc.qtyEarly}'==null || '${ldc.qtyEarly}' ==0.0?'':'('+'${ldc.qtyEarly}'+')';

			   if(qty>0){
			    cell0.innerHTML = '<input  value="'+ '${ldc.id}' +'" name="lDetailComp['+ j +'].id" type="hidden"><p style="font-size:11px">'+'${ldc.model.pt_desc2}' +'</p>';
			    cell1.innerHTML = '<p style="font-size:11px">'+ '${ldc.model.pt_desc1}' +'</p>';
			    cell2.innerHTML ='<p style="font-size:11px">'+ pac +'</p>';
			   cell3.innerHTML =  '<p style="font-size:11px">'+ qtyper +'</p>';
			   cell4.innerHTML ='<p style="font-size:11px">'+ total +  qtyEarly +'</p>';
		    cell5.innerHTML = '<input  value="'+ '${ldc.model.pt_part}' +'" name="lDetailComp['+ j +'].model.pt_part" type="hidden"><input class="qty" value="'+ qtya +'" class="inputtext" name="lDetailComp['+ j +'].qty" type="number" width="50" step="any" >';
			   }else{
				   cell0.innerHTML = '<input  value="'+ '${ldc.id}' +'" name="lDetailComp['+ j +'].id" type="hidden"><p style="color: #b3b3b3;font-size:11px">'+'${ldc.model.pt_desc2}'+'</p>';
				    cell1.innerHTML = '<p style="color: #b3b3b3;font-size:11px">'+ '${ldc.model.pt_desc1}'+'</p>';
				    cell2.innerHTML = '<p style="color: #b3b3b3;font-size:11px">'+pac+'</p>';
				   cell3.innerHTML =  '<p style="color: #b3b3b3;font-size:11px">'+ qtyper +'</p>';
				   cell4.innerHTML = '<p style="color: #b3b3b3;font-size:11px">'+ total + qtyEarly + '</p>';
			    cell5.innerHTML = '<input  value="'+ '${ldc.model.pt_part}' +'" name="lDetailComp['+ j +'].model.pt_part" type="hidden"><input class="qty" value="'+ qtya +'" class="inputtext" name="lDetailComp['+ j +'].qty" type="number" width="50" step="any" >';
				   
			   }  
		
		i++;
	   </c:forEach>  
	
}
 if('${rc.wo}' != null && '${rc.wo.id}' != '' ){
displayPs();

}/* else{
	
		$("#btndel").attr("disabled", true);
		$("#guiyeucau").attr("disabled", true);
		$("#btnnew").attr("disabled", true);
} */

 
});
</script>
