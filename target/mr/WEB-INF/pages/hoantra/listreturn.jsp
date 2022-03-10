<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<fmt:setLocale value="en_US" scope="session" />
<script>
	 $(document).ready(function(){
		 
		 $('#isAgeSelected').click(function() {
	    	   
	    		  if (!this.checked) {
	    		    selectAll(false);
	    		  }else {
	    		    selectAll(true);
	    		  }
	    	});
	 });
    var YOUR_MESSAGE_STRING_CONST = "Bạn có chắc chắn muốn xóa không ?";
       function deleteOne(id,url,page){
    	  
    		confirmDialog(YOUR_MESSAGE_STRING_CONST, function(){
    			//alert("fdfdsfs");
    			window.open(url+"?id="+ id + "&page="+ page ,"_self");
    		});
    	}
       function returnprint(id){
     	  
   		if(id!=null){
	    	$.ajax({
 				type: "GET",
 				url: "/mr/api/returnprint",
 				data: {"id" : id},
 				success:function( data ) {
 					if(data.error ==1 ){
 						alert("Print thành công!");
 						
 					}else{
 						alert("Print không thành công!");
 					}
 					         
 				   }
 				});
   		}
   	}
       function deleteList(url){
    	   var lid=[];
    		 $("#tbl tbody input[type='checkbox']").each(function(){
    			 if ($(this).prop('checked')==true){ 
    				 lid.push(this.value);		 
    				  }
    			  });
   		confirmDialog(YOUR_MESSAGE_STRING_CONST, function(){
   			//alert("fdfdsfs");
   			window.open(url + "?lid="+ lid.toString() ,"_self");
   		});
   	}
       function deleteListString(url){
    	   var lid=[];
    		 $("#tbl tbody input[type='checkbox']").each(function(){
    			 if ($(this).prop('checked')==true){ 
    				 var sid = "'"+ this.value +"'";
    				 lid.push(sid);		 
    				  }
    			  });
   		confirmDialog(YOUR_MESSAGE_STRING_CONST, function(){
   			//alert("fdfdsfs");
   			window.open(url + "?lid="+ lid.toString() ,"_self");
   		});
   	}
       function selectAll (selected) {
    	   
    		$("#tbl tbody input[type='checkbox']").each(function(index, el) {
    			
    				 this.checked=selected;
    			
    		
    		});
    	}
        function confirmDialog(message, onConfirm){
    	    var fClose = function(){
    			modal.modal("hide");
    	    };
    	    var modal = $("#confirmModal");
    	    modal.modal("show");
    	    $("#confirmMessage").empty().append(message);
    	    $("#confirmOk").one('click', onConfirm);
    	    $("#confirmOk").one('click', fClose);
    	    $("#confirmCancel").one("click", fClose);
        }
	
  </script>

<div></div>
<div class="headerright">

	<p id="txt"></p>
	<p id="dat"></p>
	<script>
			var d = new Date();
			var options = {
				weekday : 'long',
				year : 'numeric',
				month : 'long',
				day : 'numeric'
			};
			document.getElementById("dat").innerHTML = d.toLocaleDateString(
					'vi-VN', options);
		</script>
</div>


<div>

	<div class="modal" id="confirmModal"
		style="display: none; z-index: 1050;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body" id="confirmMessage"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" id="confirmOk">Ok</button>
					<button type="button" class="btn btn-default" id="confirmCancel">Cancel</button>
				</div>
			</div>
		</div>
	</div>
	<h5>
		<strong>Danh sách NVL đã cân</strong>
	</h5>
	<h6 style="color: blue">
		<strong>Tìm kiếm</strong>
	</h6>
	<div id="demo">
		<form role="form" method="get" id="MyFormID" name="MyForm"
			action="listreturn">
			<table class="table small table-bordered table-striped rcorners1">
				<thead>
				</thead>
				<tbody>
					<tr>
						<td width="20%"><label for="SerialNo">WorkOrder</label></td>
						<td><input type="text" name="woname" value="${woname}"
							id="woname" autocomplete="off" /></td>
						<td width="20%"><label for="SerialNo">Mã code</label></td>
						<td><input name="item" id="item" value="${item}"
							autocomplete="off" /></td>
					</tr>
					<tr>
						<td><label for="NameSP">Serial No</label></td>
						<td>
							<%-- <form:input type="text" path="line.id" id="NameKHYC" name="fgcode" value="" class='rcorners1'/> --%>
							<input id="serialno" name="serialno" value="${serialno}"
							autocomplete="off" />

						</td>
					</tr>
					<tr>
						<td colspan=4 align="right">
							<button type="submit" class="btn btn-primary">Tìm</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<div style="font-style: italic; font-size: 12px; color: gray"></div>

	<table id="tbl"
		class="table display table-bordered form-table border-collapse "
		border="1">
		<thead>
			<tr>
				<th width="5px">STT</th>
				<th>WorkOrder</th>


				<th>Mã code</th>
				<th>Mã NVL</th>

				<th>Lotno</th>
				<th>Serial</th>
				<th>Qty</th>
				<th>Unit</th>
				<th width="5px"></th>
				<th width="5px"></th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="l" items="${lrw}" varStatus="i">
				<tr>
					<td>${((page-1) * pageSize) + i.index+1}</td>
					<td>${l.woname}</td>
					<td>${l.model}</td>
					<td>${l.ptdesc1}</td>

					<td>${l.lotno}</td>
					<td>${l.serialnew}</td>
					<td>${l.qty}</td>
					<td>${l.ptum}</td>
					<td><a onclick="returnprint(${l.id})"><span
							class="glyphicon glyphicon-print" title="In lại"></span></a></td>
					<td><c:if test="${l.status}">
							<a onclick="deleteOne('${l.id}','deletecoreweightlog',${page});"><span
								class="glyphicon glyphicon-remove" title="Xóa"></span></a>
						</c:if></td>
				</tr>
			</c:forEach>



		</tbody>
	</table>
	<div>

		<ul id="pagination" class="pagination-sm pagination"></ul>
		<script type="text/javascript">
				
				
				$( function() {
				    $( ".datepicker" ).datepicker();
				  } );

    $(function () {
   	var woname = $("#woname").val();
    	var item = $("#item").val();
    	var serialno = $("#serialno").val();
    	var currentPage = Number('${page}');
    	    var $pagination = $('#pagination');
            window.pagObj = $('#pagination').twbsPagination({
            totalPages: "${size}",
           visiblePages: 6,
           startPage:currentPage,
            onPageClick: function (event, page) {
                console.info(page + ' (from options)');
                
            }
        }).on('page', function (event, page) {
            console.info(page + ' (from event listening)');
            window.open("listreturn?page="+ page +"&item="+ item +"&woname="+ woname +"&serialno="+ serialno ,"_self");
        });
    });
</script>
	</div>
	<span style='color: red; font-size: 0.9em;' id="label1"> </span>


</div>

