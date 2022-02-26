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
	<c:if test="${ error==1}">
		<div class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Lỗi!</strong> Không load được Driver - ${msg}
		</div>
	</c:if>
	<c:if test="${ error==2}">
		<div class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Lỗi!</strong> Lỗi connect database - ${msg}
		</div>
	</c:if>
	<c:if test="${ error==3}">
		<div class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Lỗi!</strong>Không tìm thấy file jrxml - ${msg}
		</div>
	</c:if>
	<c:if test="${ error==4}">
		<div class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Lỗi!</strong> Không tìm thấy máy in - ${msg}
		</div>
	</c:if>
	<c:if test="${ error==5}">
		<div class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Lỗi!</strong> Không tìm thấy dữ liệu - ${msg}
		</div>
	</c:if>
	<c:if test="${ error==6}">
		<div class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Lỗi!</strong> Không load được file jrxml - ${msg}
		</div>
	</c:if>
	<c:if test="${ error==7}">
		<div class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Print thành công !</strong>
		</div>
	</c:if>
	<c:if test="${ error==8}">
		<div class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Lỗi!</strong> Không tồn tại Murata - Part Name của - ${msg}.
			Không thực hiện lệnh in
		</div>
	</c:if>

</div>
<div id="socket">
<div id="main-content" class="container">
  

    
</div>
</div>
<div>
	<div style="float: left">
		<h4>Hoàn trả</h4>
	</div>

		

<!-- 	<form role="form" method="post" id="MyFormID" name="MyForm" action=""> -->
	<form:form id="fm1"  method="post" modelAttribute="rc" action="hoantra">
	

	
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
				<td> Trạng thái: <label id="status">  </label></td>
			</tr>
		</table>
	<%-- 	<div class="row">
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

	
	<span style='color: red; font-size: 0.9em;' id="label1"> </span>


</div>
<script>
$(document).ready(function() {
		


function displayPs() {

	 var table = document.getElementById('tbody')
	 $(table).empty();
	 $("#model").text('${rc.wo.model.pt_desc1}');
		$("#plan").text('${rc.wo.qty}');
		
	   $('#woid').val('${rc.wo.id}');
	   $("#selectline").val('${rc.wo.line.id}');
		$('#workorder').children().remove();
		$("#workorder").append(
				new Option('${rc.wo.name}',
						'${rc.wo.id}', true, true));
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