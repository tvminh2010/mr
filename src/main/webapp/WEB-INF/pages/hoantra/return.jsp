
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<fmt:setLocale value="en_US" scope="session" />
<style>
#selectline {
	width: 50px;
}

.qty {
	width: 50px;
}
</style>
<div>
<%-- 
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
 --%>
</div>
<div id="socket">
	<div id="main-content" class="container"></div>
</div>
<div>




	<!-- 	<form role="form" method="post" id="MyFormID" name="MyForm" action=""> -->
	<form:form id="fm1" method="post" modelAttribute="rc" action="return">



		<form:hidden id="rcid" path="id" class="form-control" />
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
				<td>Line SX</td>
				<td><select id="selectline">
						<option></option>
						<c:forEach var="line" items="${listline}">
							<option value="${line.id}">${line.name }</option>

						</c:forEach>
				</select></td>
				<td rowspan="2">Weight</td>
				<td rowspan="2"><input type="number" id="weight" name="weight"
					style="text-align: right; height: 40px; font-size: 30px; color: red; width: 150px" />
					kg</td>
			</tr>
			<tr>
				<td>WorkOrder</td>
				<td><select id="workorder">

				</select></td>
			<tr>
				<td>Model</td>
				<td><label id="model"> </label></td>
				<td rowspan="2">Trọng lượng lõi</td>
				<td rowspan="2">
			
				<input type="number" id="coreweight"
					name="coreweight"
					style="text-align: right; height: 30px; font-size: 20px; width: 150px" />
					kg
				
				<div id="coresize" style="margin-top:5px">
				Số lõi: 	
				<input type="number" id="coreweightsize"
					name="coreweightsize"
					style="text-align: right; height: 30px; font-size: 15px; width: 115px" />
               </div></td>
			</tr>
			<tr>

				<td>Trạng thái</td>
				<td><label id="status"> </label></td>

			</tr>
			<tr>
				<td>Mã vạch</td>
				<td><input type="text" id="serial" name="serial" /></td>
				<td rowspan="2">Weight NET</td>
				<td rowspan="2"><input type="number" id="weightnet"
					name="weightnet"
					style="text-align: right; height: 30px; font-size: 20px; width: 150px" />
					kg</td>

			</tr>
			<tr>
				<td>Item Code</td>
				<td><!-- <input type="text" id="ptpart" name="ptpart" /> -->
					<select id="ptpart">
						
						
				</select>
				</td>

			</tr>
			<tr>
				<td>Item Number</td>
				<td><input type="text" id="ptdesc1" name="ptdesc1" /></td>
				<td rowspan="2">Tỉ lệ</td>
				<td rowspan="2"><input type="number" id="rate" name="rate"
					style="text-align: right; height: 30px; font-size: 20px; width: 150px" />
					<strong>kg/</strong><strong class="unit1"></strong></td>
			</tr>
			<tr>
				<td>Item Name</td>
				<td><input type="text" id="ptdesc2" name="ptdesc2" /></td>

			</tr>


			<tr>
				<td>Lot No</td>
				<td><input type="text" id="lotno" name="lotno" /></td>
				<td rowspan="2">Quantity</td>
				<td rowspan="2"><input type="number" id="unitnet" name="unitnet"
					style="text-align: right; height: 40px; font-size: 30px; color: blue; width: 150px" />
					<strong class="unit1"></strong></td>
			</tr>

			<tr>
				<td>Vendor</td>
				<td><input type="text" id="vendor" name="vendor" /></td>

			</tr>
			<tr>
				<td>Unit</td>
				<td><input type="text" id="unit" /></td>
						<td>Remark</td>
				<td><input type="text" id="remark" name="remark" /></td>
			</tr>
			<tr>

			</tr>
			<tr>
				<td>Kiểu lõi</td>
				<td>
					<!-- <input type="text" id="typecore"  name="typecore"  /> --> 
					<select id="typecore">
						<option></option>
						<c:forEach var="t" items="${tt}">
							<option value="${t[1]}">${t[0] }(${t[1]})</option>

						</c:forEach>
				</select>

				</td>
				<td>Copies</td>
				<td><input type="number" id="copies" name="copies" /></td>
			</tr>
			<tr>
				<td colspan="4">
					<div align="right">
						<button id="add1" class="btn btn-primary">Add and Print</button>
					</div>
			</tr>
		</table>

		<table class=" table small table-striped table-bordered rcorners1"
			id="pstable">

			<thead>
				<tr>
					<th><label for="MaTP">Item Name</label></th>
					<th><label for="LineSo">Item Number</label></th>
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
			<button type="submit" id="guiyeucau" class="btn btn-primary">Gửi yêu cầu</button>
		</div>

	</form:form>


	<span style='color: red; font-size: 0.9em;' id="label1"> </span>


</div>
<script>
$(document).ready(function() {
		
var interval;
$("#coresize").hide();

 $("#fm1").submit(function (e) {
    	 $('#guiyeucau').prop('disabled', true);
    });

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
		   $("#status").text('${rc.wo.status}'==1?"Mới tạo":'${rc.wo.status}'==0?"Đang sản xuất":"");
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
		    cell5.innerHTML = '<input  value="'+ '${ldc.model.pt_part}' +'" name="lDetailComp['+ j +'].model.pt_part" type="hidden"><input class="qty" id="'+ '${ldc.model.pt_part}' +'" value="'+ qtya +'" class="inputtext" name="lDetailComp['+ j +'].qty" type="number" width="80" step="any">';
			   }else{
				   cell0.innerHTML = '<input  value="'+ '${ldc.id}' +'" name="lDetailComp['+ j +'].id" type="hidden"><p style="color: #b3b3b3;font-size:11px">'+'${ldc.model.pt_desc2}'+'</p>';
				    cell1.innerHTML = '<p style="color: #b3b3b3;font-size:11px">'+ '${ldc.model.pt_desc1}'+'</p>';
				    cell2.innerHTML = '<p style="color: #b3b3b3;font-size:11px">'+pac+'</p>';
				   cell3.innerHTML =  '<p style="color: #b3b3b3;font-size:11px">'+ qtyper +'</p>';
				   cell4.innerHTML = '<p style="color: #b3b3b3;font-size:11px">'+ total +'</p>';
			    cell5.innerHTML = '<input  value="'+ '${ldc.model.pt_part}' +'" name="lDetailComp['+ j +'].model.pt_part" type="hidden"><input class="qty" id="'+ '${ldc.model.pt_part}' +'" value="'+ qtya +'" class="inputtext" name="lDetailComp['+ j +'].qty" type="number" width="80" step="any">';
				   
			   }  
		
		i++;
	   </c:forEach>  
	
}
 if('${rc.id}' != null){
displayPs();
} 

$("#serial").change(function(){
	var serial = $('#serial').val();
	  clearInterval(interval);
   // alert("The text has been changed.");
    $.ajax({
		url : '/mr/api/getSerial',
		type : 'GET',
		cache : false,
		data : {
			"serial" : serial
		},
		success : function(data1) {
			showdata(data1);
			
		},
		error : function(e) {
			console.log("ERROR: ", e);
			
		},
		done : function(e) {
			alert("SUCCESS1: "+ data1.p.pt_part);
		}
	});
});
$("#ptpart").change(function(){
	var ptpart = $('#ptpart').val();
	  clearInterval(interval);
   // alert("The text has been changed.");
    $.ajax({
		url : '/mr/api/getptpart',
		type : 'GET',
		cache : false,
		data : {
			"ptpart" : ptpart
		},
		success : function(data1) {
			reset();
			showdata(data1);
			
		},
		error : function(e) {
			console.log("ERROR: ", e);
			
		},
		done : function(e) {
			alert("SUCCESS1: "+ data1.p.pt_part);
		}
	});
});
$("#coreweightsize").change(function(){

	changeall();

});
  function showdata(data5){
	  var valold =    $('#'+data5.pt_part).val();
	  if(isNaN(valold)){
		  alert("Không tìm thấy NVL " + data5.pt_part);
		  reset();
		  $('#serial').val("");
	  }else{
		  if(data5.pt_desc2 == "TAPE" || data5.pt_desc2 == "Tape" ){
			  $("#coresize").show();
		  }else{
			  $("#coresize").hide();  
		  }
		
	  interval = setInterval(getWeight, 1000);  
	  $('#ptpart').val(data5.pt_part);
	 
	  $('#ptdesc1').val(data5.pt_desc1);
	  $('#ptdesc2').val(data5.pt_desc2);
	
	  
	//  $('#typecore').val(data5.typecore);
	  $('#coreweight').val(data5.coreweight);
	  $('#rate').val(data5.rate);
	  $('#unit').val(data5.pt_um);
	  $('.unit1').text(data5.pt_um);
	  $('#lotno').val(data5.lotno);
	  $('#vendor').val(data5.vendor);
	//  alert(data5.coreweight);
	  $("#typecore").val(data5.coreweight);
	  }
	  
  }
  function getWeight(){
	
		   
		   
 	    	$.ajax({
 				type: "GET",
 				url: "/mr/api/getSerialWeight",
 				
 				success:function( data2 ) {
 					if(data2.result != null && data2.result == 1){
 						 $("#weight").val(data2.weight);
 						changeall();
 				         
 					}
 				  
 				
 				   }
 				});
 	    
 	
  }
  function mathround(unit,unitnet){
	    if(unit == 'P' || unit == 'Psc'){
	        	$('#unitnet').val(Math.round(unitnet));
	         }else if ( unit == 'Kg' || unit == 'KG' ){
	        	$('#unitnet').val(Math.round(unitnet*100)/100);

	        }else if ( unit == 'SK' ){
	        	
	        	$('#unitnet').val(Math.round(unitnet*10)/10);
            }else if ( unit == 'M' ){
	        	
	        	$('#unitnet').val(Math.round(unitnet));
	         }else{
	        	 $('#unitnet').val(Math.round(unitnet));
	         }
	  
  };
  
  function reset(){
           $('#vendor').val("");
	
			$('#serial').val("");
			$('#ptpart').val("");
			 $('#ptdesc1').val("");
			   $('#ptdesc2').val("");
				  $('#lotno').val("");
			  $('#remark').val("");
			  $('#unit').val("");
				  $('#coreweight').val("");
				  $('#weight').val("");
				  $('#weightnet').val("");
				  $('#rate').val("");
				  $('#unitnet').val("");
				  $('#copies').val("");
				  $('#typecore').val("");
				  
				
  }
  
	$("#typecore").change(function() {
		var typecore = $("#typecore").val();
		 $('#coreweight').val(typecore);
		changeall();
		
	});
	 $('#weight').change(function () {
		 changeall();
	
	 });
	 function changeall(){
		 
		 var weight =   $('#weight').val();
		 var coreweight =  $('#coreweight').val();
		
		 var  coreweightsize = 1;
			 if(!$("#coreweightsize").is(':hidden')){
				 coreweightsize = $('#coreweightsize').val();
					  
				  }
			 

		 var weightnet = weight - (coreweight*coreweightsize);
		 var rate =  $('#rate').val();
         $('#weightnet').val(weightnet);
         var unitnet = weightnet/rate;
         var unit =  $('#unit').val();
         mathround(unit,unitnet);
	 };
	$("#coreweight").change(function() {
	
		changeall();
		
	});


	
  $('#add1').click(function(event){
	  event.preventDefault();
	 // $('#add1').prop('disabled', true);
	 // alert("fsfsd");
	  //alert("add");
	    var weightnet = Number($('#unitnet').val());
	  if(weightnet != null && weightnet > 0){
		  $('#add1').prop('disabled', true);
	  
	 clearInterval(interval);
	 var ptpart =    $('#ptpart').val();
	  var valold =   $('#'+ptpart).val();
	  var qtynew = Number(valold) + weightnet;
	 
	  //print
	  var weight  = Number($('#weight').val());
	   var serial = $('#serial').val();
	 var woid = $('#woid').val();
	  var vendor =$('#vendor').val();
	 /*  var partcode = $('#ptpart').val();
	  var partname =  $('#ptpart').val();
	  var partnumber =  $('#ptpart').val();
		var   lotno =  $('#lotno').val(); */
		 var woname = $('#workorder').find('option:selected').text();
		
		 var partcode =  $('#ptpart').val();
		  var partnumber =   $('#ptdesc1').val();
		  var partname =  $('#ptdesc2').val();
			var   lotno =   $('#lotno').val();
			var   remark =   $('#remark').val();
			var   unit =   $('#unit').val();
	var   copies =   $('#copies').val();
			var   qty =   $('#unitnet').val();
			
   	$.ajax({
			type: "GET",
			url: "/mr/api/excutePrint",
			data : {
				 "serialold" : serial,
				 "vendor" : vendor,
				"partcode" : partcode,
				"partname" : partname,
				 "partnumber" : partnumber,
				"lotno"     : lotno,
				"unit"     : unit,
				"remark"   :remark,
				"weight" : weight,
				"weightnet" : weightnet,
				"qty" : qty,
				"woid" : woid,
				"copies" : copies,
				"woname" : woname
			},
			success:function( data2 ) {
				if(data2.error == 1){
					alert("Lỗi! Không load được Driver");
				}else if (data2.error == 2){
					alert("Lỗi! Lỗi connect database");
				}else if (data2.error == 3){
					alert("Lỗi! Không tìm thấy file jrxml");
				}else if (data2.error == 4){
					alert("Lỗi! Không tìm thấy máy in");
	
			   
			    }else if (data2.error ==5){
				alert("Lỗi! Không tìm thấy dữ liệu ");

		   
	            }else if (data2.error == 6){
	             	alert("Lỗi! Không load được file jrxml");

   
	             }else if (data2.error == 7){
	            	 
	            	 
	            	 // alert(qty);
	            	 $('#'+ptpart).val(qtynew);
	            	  reset();
	            	  $('#add1').prop('disabled', false);

	            	alert("Print thành công !");
	            	 
                 }else{
                      reset();
                	  $('#add1').prop('disabled', false);
                 }
				
				 
			}
			});
	  }
	  
  });
  
});
</script>
 <div style="display: none;">
   <form id="my-form" action="downloadLogFile" action="get">
   <input id="fn" name="filename" value="${filename}" />
   <input  name="type" value="2" />
   <input class="button-link" type="submit" value="Click me" />
</form>
</div>
    <script type="text/javascript">
    var fn = $("#fn").val();
    if(fn != null && fn != ''){
        $("form#my-form").submit();
    }

       </script>