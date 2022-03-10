<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
<!DOCTYPE html>

<html>
<head>
	<title>WMS | In tem bổ sung cho hàng xuất FG</title>
   
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/custom_style.css"/> ">
	<link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>">
	<script src="<c:url value="/resources/bootstrap/js/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/bootstrap/bootstrap.min.js" />"></script>  

   


	<style>
				.table-header {
				position:fixed;
				top:101px;
				height: 300px;
				background:#d1e0e0;
			}
			
			.table-tbody {
				overflow: scroll;
			}
			
			.rcorners1 {
				
				-moz-border-radius: 3px;
				-webkit-border-radius: 3px;
			}
			.rcorners2 {
				border-radius: 2px;
			}
</style>
<script>
$(".rcorners1").focus();
function sm2(id){
	
	

	$('#myModal1').modal("show");
	$('#myModal1 #ok1').click(function(){
		
		

	if(id != "" || id != null){
		window.open("./customercode?del="+id,"_self");

	}
	});
   }
   
function checkall(){
	//alert("dfs");
	
	 if (this.checked) {
			
		 this.checked=false;
	    selectAll(false);
  }else {
	   this.checked=true;
	    selectAll(true);
	   //$('#checkall').removeAttr('checked')
	 }
};
function selectAll (selected) {
	
	$("input[type='checkbox']").each(function(index, el) {
	  this.checked=selected;
	});
}

function delall(){
	$('#myModal1').modal("show");
	$('#myModal1 #ok1').click(function(){
		
		


	


	var lid=[];
	 $("#tbl tbody input[type='checkbox']").each(function(){
		 if ($(this).is(':checked')) {
			
			 lid.push(this.value);		 
					
			 
			 
			  }
		  });
	window.open("./customercode?delall="+ lid.toString() ,"_self");
});
};
function edit(id,vte,fgcode,customercode,customer){
	
	$('#myModal2').modal("show");
	
	$('#vtepartname1').val(vte);
	$('#fgcode1').val(fgcode);
	$('#id1').val(id);
	$('#customercode1').val(customercode);
	$('#customer1').val(customer);
   }

</script>
</head>
<body>

	<div class=".container-fluid">
		<div class="col-sm-2" > 
			<div class="panel-group">
				<div class="panel panel-default">
				  <div class="panel-heading">
					<h4 class="panel-title">
					  <a data-toggle="collapse" href="#collapse1" style="text-decoration: none;">Material Request</a>
					</h4>
				  </div>
				   <div id="collapse1" class="panel-collapse collapse">
				   </div>
	<div class="panel-body"><a href="yeucauNVL" style="font-size:14px; color: black; text-decoration: none;">Nhập kế hoạch SX</a></div>
				<div class="panel-body"><a href="index" style="font-size:14px; color: black; text-decoration: none;">Yêu cầu NVL</a></div>
					<div class="panel-body"><a href="giaonhan" style="font-size:14px; color: black; text-decoration: none;">Giao nhận NVL</a></div>
			        <div class="panel-body"><a href="hoantra" style="font-size:14px; color: black; text-decoration: none;">Hoàn trả NVL</a></div>
					<div class="panel-body"><a href="baocao" style="font-size:14px; color: black; text-decoration: none;">Xuất báo cáo</a></div>
					       <div class="panel-footer"><a href="setting" style="font-size:13px; color: gray; text-decoration: none; padding-left:10px">Báo cáo yêu cầu NVL</a></div>
					       <div class="panel-footer"><a href="setting" style="font-size:13px; color: gray; text-decoration: none;padding-left:10px">Báo cáo giao nhận NVL</a></div>
					       <div class="panel-footer"><a href="setting" style="font-size:13px; color: gray; text-decoration: none;padding-left:10px">Báo cáo hoàn trả NVL</a></div>
					<div class="panel-body"><a href="setting" style="font-size:14px; color: black; text-decoration: none;">Setting</a></div>
					      <div class="panel-footer"><a href="setting" style="font-size:13px; color: gray; text-decoration: none; padding-left:10px">Thời gian nhập xuất</a></div>
					       <div class="panel-footer"><a href="setting" style="font-size:13px; color: gray; text-decoration: none;padding-left:10px">Item master</a></div>
					       <div class="panel-footer"><a href="setting" style="font-size:13px; color: gray; text-decoration: none;padding-left:10px">Bom</a></div>
				</div>
			</div>
		</div>
	</div>
	
	
		<div class="col-sm-10">
		<form role="form" method="post" id="MyFormID" name="import" action="loginpn" >
					<table class="table small table-bordered table-striped rcorners1">
						
							
						
						<tbody>
						  <tr>
								<td colspan="2"> <h4>Login</h4> </td>
								
							</tr>
						    <tr>
								<td> User: </td>
								<td><input type="text"  name="userpn" value="" /></td>
							</tr>
							   <tr>
								<td> Password: </td>
								<td><input type="password" name="passpn" value="" /></td>
							</tr>
							<tr>
								
								
								
								
						
								<td colspan="2" align="right">
     		<button type="submit" class="btn btn-primary">Ok</button>
     	</td>
					</tr>
						
						</tbody>
					</table>		
		</form>
		
				<span style='color: red; font-size: 0.9em;' id="label1">  </span>
			
				
	</div>	
	
	
	

  </body>
  <script>
  $('#tbl').DataTable( {
    "order": [[ 0, "desc" ]],

   // "fixedHeader":true,
    "paging":true,
    "info":     true,
	     "autoWidth": false,
	     "dom": 'Bfrtip',
		   "pageLength": 500,
		    "lengthMenu": [
		                 [ 10, 25, 50, 100, 1000, -1 ],
		                 [ '10 rows', '25 rows', '50 rows', '100 rows', '1000 rows', 'Show all' ]
		             ], 
		    "buttons": [
		             
			             { extend: 'csv', footer: true }, { extend: 'excel', footer: true }, 
			             { extend: 'pdf', footer: true }
		          ]
});
</script>
</html>

