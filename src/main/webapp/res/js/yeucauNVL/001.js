$(document)
		.ready(
                 
			
				function() {
					var listline = [];
					var table = document.getElementById('tbody')
					function selectline(data) {
						$( "#dangyc" ).children().remove();
					
						/*$("#btndel").attr("disabled", false);
						$("#guiyeucau").attr("disabled", false);
						$("#btnnew").attr("disabled", false);
						listline = [];
						$("#selectedclosetimehidden").children().remove();*/
						$('#workorder').children().remove();
						/*$("#selectclosetime option").each(function()
								{
							$(this).attr("disabled", false);
								});
						$("#selectclosetime")
								.val(
										data.closetime == null ? ''
												: data.closetime.id);*/
						if (data.length > 0) {
							
							$("#model").text(data[0].model.pt_desc1);
							 
							$("#plan").text(data[0].qty);
							//$("#status").text(
									//data[0].status == 1 ? "Mới tạo"
										//	: "Đang sản xuất");

							for (i = 0; i < data.length; i++) {
								$("#workorder").append(
										new Option(data[i].name, data[i].id,
												true, true));
							}
							//var type = $('input[name=type]:checked').val();
							$("#workorder").val(data[0].id);
							$("#selectstatus").val(data[0].status);
							ajaxwoid(data[0].id);
						} else {
							$(table).empty();
                            $("#model").text('');
                            $("#selectclosetime").val('');
							$("#plan").text('');
							
						//	$("#status").text('');
							
						}
						
					}
					/*$( "#btnnew" ).click(function() {
						var woid = 	$("#workorder").val();
						listline = [];
						$("#selectedclosetimehidden option").each(function()
								{
							listline.push($(this).val() );
								});
						fLen = listline.length;
						
						for (i = 0; i < fLen; i++) {
						
							$("#selectclosetime option[value='"+ listline[i]+"']").attr("disabled", true);
							
						}
						$('#selectedclosetime').val('');
						ajaxrcid(null,woid);
						});*/
					/*var YOUR_MESSAGE_STRING_CONST = "Bạn có chắc chắn muốn xóa không ?";
					$( "#btndel" ).click(function() {
						confirmDialog(YOUR_MESSAGE_STRING_CONST, function(){
							var rcid = $('#selectedclosetime').val();
							ajaxdelrcid(rcid);
				   		});
					
						
						});*/
					$( ".nhaplai" ).click(function() {
						
						$(".qty").val('');
					});
						
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
					function selectwoid(data) {
						
						$( "#dangyc" ).children().remove();
						if (data!=null && data.length >0) {
						$("#selectclosetime").val(data[0].closetime.id);
						  for(i=0;i<data.length;i++){
							  if(i==data.length-1){
								  $( "#dangyc" ).append( "<strong>"+data[i].closetime.closetime+"-"+data[i].closetime.name+"</strong>" );
							  }else{
							  $( "#dangyc" ).append( "<strong>"+data[i].closetime.closetime+"-"+data[i].closetime.name+",  </strong>" );
						       }
						  }
						  

						}else{
							
							$("#selectclosetime").val('');
						}
						
						
						var closetimeid = $("#selectclosetime").val();
						var woid = 	$("#workorder").val();
						ajaxclosetimeid(closetimeid,woid);
					}
					
					function displayPs(data) {

						var table = document.getElementById('tbody')
						$(table).empty();
						$("#rcid").val(data.id == null ? '' : data.id);
						$("#woid").val(data.wo.id);
						$("#model").text(data.wo.model.pt_desc1);
						$("#plan").text(data.wo.qty);
					/*	$("#selectedclosetime").val(data.id);*/
						//$("#status").text(
					//		data.wo.status == 1 ? "Mới tạo"
						//				: "Đang sản xuất");
					
						$("#selectclosetime").val(data.closetime.id);
					/*	$("#rcstatus").text(
								data.isWait == 1 ? "đang yêu cầu"
										: data.isPending == 1?"đang chờ giao":"thêm mới");*/
						
					/*	if(data.isPending == 1){
							$("#btndel").attr("disabled", true);
							$("#guiyeucau").attr("disabled", true);
						}else
						if(data.isWait == 1){
							$("#btndel").attr("disabled", false);
							$("#guiyeucau").attr("disabled", false);
						}else{
							$("#btndel").attr("disabled", false);
							$("#guiyeucau").attr("disabled", false);
						}
	                   listline = [];
						
						$("#selectedclosetimehidden option").each(function()
								{
							listline.push($(this).val() );
								});
						fLen = listline.length;
						var b = false;
						var selectedclosetime = 	$("#selectedclosetime").val();
						
					    if(fLen >0){
					    
						for (i = 0; i < fLen; i++) {
							if(data.closetime.id == listline[i]){
								b=true;
							}
							$("#selectclosetime option[value='"+ listline[i]+"']").attr("disabled", true);
							
						}
					
					    }
					  
					    if(b && selectedclosetime==null){
					    	$("#selectclosetime").val('');
					    	
					    }else{
					    	$("#selectclosetime option[value='"+ data.closetime.id+"']").attr("disabled", false);
						$("#selectclosetime")
								.val(
										data.closetime == null ? ''
												: data.closetime.id);
					    }*/
						
						for (i = 0; i < data.lDetailComp.length; i++) {

							var row = table.insertRow(i);
							var cell0 = row.insertCell(0);
							var cell1 = row.insertCell(1);
							var cell2 = row.insertCell(2);
							var cell3 = row.insertCell(3);
							var cell4 = row.insertCell(4);
							var cell5 = row.insertCell(5);
							var j = i;
							var qtyper = Math
									.round(data.lDetailComp[j].plan * 1000) / 1000;
							var total = Math.round((data.lDetailComp[j].totalResponse
									- data.lDetailComp[j].totalReturn)*100)/100;
							var qty = data.lDetailComp[j].qty == null
									|| data.lDetailComp[j].qty == 0 ? ''
									: data.lDetailComp[j].qty;
							//var pac = data.lDetailComp[j].model.pt_package == null ? ''
								//	: data.lDetailComp[j].model.pt_package;
							var pac = '';
							var id = data.lDetailComp[j].id == null ? ''
									: data.lDetailComp[j].id;
							var qtyEarly = data.lDetailComp[j].qtyEarly == null
									|| data.lDetailComp[j].qtyEarly == 0 ? ''
									: '(' + data.lDetailComp[j].qtyEarly + ')';
							if (data.lDetailComp[j].ps_qty_per > 0) {
								cell0.innerHTML = '<input  value="'
										+ id
										+ '" name="lDetailComp['
										+ j
										+ '].id" type="hidden"><p style="font-size:11px">'
										+ data.lDetailComp[j].model.pt_desc2
										+ '</p>';
								cell1.innerHTML = '<p style="font-size:11px">'
										+ data.lDetailComp[j].model.pt_desc1
										+ '</p>';
								cell2.innerHTML = '<p style="font-size:11px">'
										+ pac + '</p>';
								cell3.innerHTML = '<p style="font-size:11px">'
										+ qtyper + '</p>';
								cell4.innerHTML = '<p style="font-size:11px">'
										+ total + qtyEarly + '</p>';
								cell5.innerHTML = '<input  value="'
										+ data.lDetailComp[j].model.pt_part
										+ '" name="lDetailComp['
										+ j
										+ '].model.pt_part" type="hidden"><input class="qty" class="inputtext" name="lDetailComp['
										+ j
										+ '].qty" type="number" width="50" value = "'
										+ qty + '" step="any">';
							} else {
								cell0.innerHTML = '<input  value="'
										+ id
										+ '" name="lDetailComp['
										+ j
										+ '].id" type="hidden"><p style="color: #b3b3b3;font-size:11px">'
										+ data.lDetailComp[j].model.pt_desc2
										+ '</p>';
								cell1.innerHTML = '<p style="color: #b3b3b3;font-size:11px">'
										+ data.lDetailComp[j].model.pt_desc1
										+ '</p>';
								cell2.innerHTML = '<p style="color: #b3b3b3;font-size:11px">'
										+ pac + '</p>';
								cell3.innerHTML = '<p style="color: #b3b3b3;font-size:11px">'
										+ Math
												.round(data.lDetailComp[j].plan * 1000)
										/ 1000 + '</p>';
								cell4.innerHTML = '<p style="color: #b3b3b3;font-size:11px">' + total 
										+ qtyEarly + '</p> ';
								cell5.innerHTML = '<input  value="'
										+ data.lDetailComp[j].model.pt_part
										+ '" name="lDetailComp['
										+ j
										+ '].model.pt_part" type="hidden"><input class="qty" class="inputtext" name="lDetailComp['
										+ j
										+ '].qty" type="number" width="50" value = "'
										+ qty + '" step="any">';

							}
						}
					}

					$("#selectline").change(function() {

						var lineid = $(this).val();
						//var type = $('input[name=type]:checked').val();
						$.ajax({
							type : "GET",
							contentType : "application/json",// "application/json"text/html
							url : "/mr/api/selectbx",
							data : {
								"lineid" : lineid,
								"type" : 2
							},
							dataType : 'json',
							timeout : 100000,
							success : function(data) {
								console.log("SUCCESS: ", data);
								selectline(data);
							},
							error : function(e) {
								console.log("ERROR: ", e);
								selectline(e);
							
							},
							done : function(e) {
								console.log("DONE");
							}
						});
					});
					$("#selectstatus").change(function() {
						var lineid = $('#selectline').val();
						var status = $(this).val();
					if(status==1){
						$.ajax({
							type : "GET",
							contentType : "application/json",// "application/json"text/html
							url : "/mr/api/selectsetup",
							data : {
								"lineid" : lineid,
								"type"  :1
							},
							dataType : 'json',
							timeout : 100000,
							success : function(data) {
								console.log("SUCCESS: ", data);
								selectline(data);
							},
							error : function(e) {
								console.log("ERROR: ", e);
								selectline(2);
								$("#selectstatus").val(1);
							},
							done : function(e) {
								console.log("DONE");
							}
						});
					}else{
						
						$.ajax({
							type : "GET",
							contentType : "application/json",// "application/json"text/html
							url : "/mr/api/selectbx",
							data : {
								"lineid" : lineid,
								"type"  :2
							},
							dataType : 'json',
							timeout : 100000,
							success : function(data) {
								console.log("SUCCESS: ", data);
								selectline(data);
							},
							error : function(e) {
								console.log("ERROR: ", e);
								selectline(e);
								$("#selectstatus").val(2);
							},
							done : function(e) {
								console.log("DONE");
							}
						});
					}
					
				
					});
					function ajaxwoid(woid) {
						$.ajax({
							type : "GET",
							contentType : "application/json",// "application/json"text/html
							url : "/mr/api/selectwoid",
							data : {
								"woid" : woid
							
							},
							dataType : 'json',
							timeout : 100000,
							success : function(data) {
								console.log("SUCCESS: ", data);
								selectwoid(data);
							},
							error : function(e) {
								console.log("ERROR: ", e);

							},
							done : function(e) {
								console.log("DONE");
							}
						});
					}
					function ajaxdelrcid(rcid) {
						var woid = 	$("#workorder").val();
						$.ajax({
							type : "GET",
							contentType : "application/json",// "application/json"text/html
							url : "/mr/api/delrcid",
							data : {
								"rcid" : rcid
							
							},
							dataType : 'json',
							timeout : 100000,
							success : function(data) {
								console.log("SUCCESS: ",data );
								alert(data==true?"Xóa thành công":"Lỗi, không xóa được")
								ajaxwoid(woid);
							},
							error : function(e) {
								console.log("ERROR: ", e);

							},
							done : function(e) {
								console.log("DONE");
							}
						});
					}
					$("#workorder").change(function() {
						var woid = $(this).val();
						
						ajaxwoid(woid);
					});
					/*$("#selectedclosetime").change(function() {
						var rcid = $(this).val();
						ajaxrcid(rcid,null);
					});*/
					$("#selectclosetime").change(function() {
						var closetimeid = $(this).val();
						var woid = 	$("#workorder").val();
						if(woid!=null && woid!=''){
						ajaxclosetimeid(closetimeid,woid);
						}
					});
					function ajaxclosetimeid(closetimeid,woid) {
						$.ajax({
							type : "GET",
							contentType : "application/json",// "application/json"text/html
							url : "/mr/api/selectclosetimeid",
							data : {
								"closetimeid" : closetimeid,
								"woid" : woid
								

							},
							dataType : 'json',
							timeout : 100000,
							success : function(data) {
								console.log("SUCCESS: ", data);
								displayPs(data);
							},
							error : function(e) {
								console.log("ERROR: ", e);

							},
							done : function(e) {
								console.log("DONE");
							}
						});
					}
				/*	function ajaxrcid(rcid,woid) {
						$.ajax({
							type : "GET",
							contentType : "application/json",// "application/json"text/html
							url : "/mr/api/selectrcid",
							data : {
								"rcid" : rcid,
								"woid" : woid

							},
							dataType : 'json',
							timeout : 100000,
							success : function(data) {
								console.log("SUCCESS: ", data);
								displayPs(data);
							},
							error : function(e) {
								console.log("ERROR: ", e);

							},
							done : function(e) {
								console.log("DONE");
							}
						});
					}*/

				});
