<%@ page contentType="text/html;charset=UTF-8" language="java" %>

			<div class="panel-group">
				<div class="panel panel-default">
				  <div class="panel-heading">
					<h4 class="panel-title">
					  <a data-toggle="" href="" style="text-decoration: none;">Material Request</a>
					</h4>
				  </div>
					<div id="collapse1" class="">
					<div class="panel-body"><strong>Nghiệp vụ</strong></div>
				    <div class="panel-footer"><a href="planNVL?page=1" style="font-size:13px; color: gray; text-decoration: none;padding-left:10px">Nhập kế hoạch SX</a></div>
				    <div class="panel-footer"><a href="listycnvl" style="font-size:13px; color: gray; text-decoration: none;padding-left:10px">Yêu cầu NVL</a></div>
					<div class="panel-footer"><a href="giaonhan" style="font-size:13px; color: gray; text-decoration: none;padding-left:10px">Giao nhận NVL</a></div>
					<div class="panel-footer"><a href="hoantra" style="font-size:13px; color: gray; text-decoration: none;padding-left:10px">Hoàn trả NVL</a></div>
					<div class="panel-footer"><a href="cowo" style="font-size:13px; color: gray; text-decoration: none;padding-left:10px">Đóng/Mở WorkOrder</a></div>

			        
					<div class="panel-body"><strong>Xuất báo cáo</strong></div>
								        <div class="panel-footer"><a  style="font-size:13px; color: gray; text-decoration: none;padding-left:10px" href="turnexcel?page=1" style="font-size:14px; color: black; text-decoration: none;">Danh sách phiên</a></div>
 					       <div class="panel-footer"><a href="bywo" style="font-size:13px; color: gray; text-decoration: none; padding-left:10px">Báo cáo theo WorkOrder</a></div> 
					       					       <div class="panel-footer"><a href="bywo1" style="font-size:13px; color: gray; text-decoration: none; padding-left:10px">Báo cáo chi tiết WorkOrder</a></div>
					       
					       					       <div class="panel-footer"><a href="byturn" style="font-size:13px; color: gray; text-decoration: none; padding-left:10px">Báo cáo theo phiên</a></div>
					       
					       <div class="panel-footer"><a href="bc?page=1" style="font-size:13px; color: gray; text-decoration: none;padding-left:10px">Báo cáo tổng hợp</a></div>
<!-- 					       <div class="panel-footer"><a href="bywoptdesc2?page=1" style="font-size:13px; color: gray; text-decoration: none;padding-left:10px">Báo cáo WorkOrder theo Item Number</a></div> -->
					        <div class="panel-footer"><a href="bywoptdesc21?page=1" style="font-size:13px; color: gray; text-decoration: none;padding-left:10px">Báo cáo tổng hợp WorkOrder</a></div>
					        					        <div class="panel-footer"><a href="returnexcel?page=1" style="font-size:13px; color: gray; text-decoration: none;padding-left:10px">Báo cáo hoàn trả</a></div>
					        
					<div class="panel-body"><strong>Setting</strong></div>
					      <div class="panel-footer"><a href="closetime" style="font-size:13px; color: gray; text-decoration: none; padding-left:10px">Đặt lịch</a></div>
					       <div class="panel-footer"><a href="model" style="font-size:13px; color: gray; text-decoration: none;padding-left:10px">Item master</a></div>
					       <div class="panel-footer"><a href="bom" style="font-size:13px; color: gray; text-decoration: none;padding-left:10px">Bom</a></div>
					       					       <div class="panel-footer"><a href="returnbom" style="font-size:13px; color: gray; text-decoration: none;padding-left:10px">Core Weight</a></div>
					       
					</div>
				</div>
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
       function deleteOne(id,url){
    		confirmDialog(YOUR_MESSAGE_STRING_CONST, function(){
    			//alert("fdfdsfs");
    			window.open(url+"?id="+ id ,"_self");
    		});
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
</html>