$(document).ready(
		
		function() {
			var list_ptpart = [];
			 var table = document.getElementById('tbody')
			function selectline(data) {
				$('#workorder').children().remove();
				
				$("#model").text('');
				$("#plan").text('');
				$("#status").text('');
				if (data.length >0){
					
					$("#model").val(data[0].model.pt_desc1);
					$("#plan").val(data[0].qty);
					 $("#status").text(data[0].status==1?"Mới tạo":"Đang sản xuất");
				
					for (i = 0; i < data.length; i++) {
						$("#workorder").append(
								new Option(data[i].name,
										data[i].id, true, true));
					}
					var type = $('input[name=type]:checked').val();
					$("#workorder").val(data[0].id);
					ajaxmodel(data[0].id);
				}else{
					 $(table).empty();
				}
			}
			function displaytype(data) {
				$('#workorder').children().remove();
				
				$("#model").text('');
				$("#plan").text('');
				 $("#status").text('');
				if (data.length == 1) {
					$("#workorder").append(
							new Option(data[0].name,
									data[0].id, true, true));

					$("#model").val(data[0].model.pt_desc1);
					$("#plan").val(data[0].qty);
				} else {
					for (i = 0; i < data.length; i++) {
						$("#workorder").append(
								new Option(data[i].name,
										data[i].id, true, true));
					}
					$('#workorder').focus();

				}
			}
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
			function displayPs(data) {
				// alert(data.wo.id);
				 var table = document.getElementById('tbody')
				 $(table).empty();
				 if($("#ptpart").length){
					// alert("fdsfs");
					 reset();
					 list_ptpart = [];
					  $('#ptpart').children().remove();
					  
				  }
				 
				 $("#rcid").val(data.id==null?'':data.id);
				 $("#woid").val(data.wo.id);
				 $("#model").text(data.wo.model.pt_desc1);
					$("#plan").text(data.wo.qty);
					
						 $("#status").text(data.wo.status==1?"Mới tạo":"Đang sản xuất");
				
					for (i = 0; i < data.lDetailComp.length; i++) {
						 
						    var row = table.insertRow(i);
						    var cell0 = row.insertCell(0);
						    var cell1 = row.insertCell(1);
						    var cell2 = row.insertCell(2);
						    var cell3 = row.insertCell(3);
						    var cell4 = row.insertCell(4);
						    var cell5 = row.insertCell(5);
						   var  j=i;
						   list_ptpart[j]= data.lDetailComp[j].model.pt_part;
						   var qtyper = Math.round(data.lDetailComp[j].plan*1000)/1000;
						   var total = Math.round((data.lDetailComp[j].totalResponse
									- data.lDetailComp[j].totalReturn)*100)/100;
						   var qty = data.lDetailComp[j].qty==null || data.lDetailComp[j].qty==0?'':data.lDetailComp[j].qty;
						   
						  // var pac =data.lDetailComp[j].model.pt_package==null?'':data.lDetailComp[j].model.pt_package;
						   var pac='';
						   var id = data.lDetailComp[j].id==null?'':data.lDetailComp[j].id;
						   if(data.lDetailComp[j].ps_qty_per>0){
						    cell0.innerHTML = '<input  value="'+ id  +'" name="lDetailComp['+ j +'].id" type="hidden"><p style="font-size:11px">'+data.lDetailComp[j].model.pt_desc2 +'</p>';
						    cell1.innerHTML = '<p style="font-size:11px">'+ data.lDetailComp[j].model.pt_desc1 +'</p>';
						    cell2.innerHTML ='<p style="font-size:11px">'+ pac +'</p>';
						   cell3.innerHTML =  '<p style="font-size:11px">'+ qtyper +'</p>';
						   cell4.innerHTML ='<p style="font-size:11px">'+ total +'</p>';
					    cell5.innerHTML = '<input  value="'+ data.lDetailComp[j].model.pt_part +'" name="lDetailComp['+ j +'].model.pt_part" type="hidden"><input class="qty" id="'+ data.lDetailComp[j].model.pt_part +'" class="inputtext" name="lDetailComp['+ j +'].qty" type="number" width="80" step="any" readonly value="'+ qty +'" >';
						   }else{
							   cell0.innerHTML = '<input  value="'+ id +'" name="lDetailComp['+ j +'].id" type="hidden"><p style="color: #b3b3b3;font-size:11px">'+data.lDetailComp[j].model.pt_desc2+'</p>';
							    cell1.innerHTML = '<p style="color: #b3b3b3;font-size:11px">'+ data.lDetailComp[j].model.pt_desc1+'</p>';
							    cell2.innerHTML = '<p style="color: #b3b3b3;font-size:11px">'+pac+'</p>';
							   cell3.innerHTML =  '<p style="color: #b3b3b3;font-size:11px">'+Math.round(data.lDetailComp[j].plan*1000)/1000 +'</p>';
							   cell4.innerHTML = '<p style="color: #b3b3b3;font-size:11px">'+ total +'</p>';
						    cell5.innerHTML = '<input  value="'+ data.lDetailComp[j].model.pt_part +'" name="lDetailComp['+ j +'].model.pt_part" type="hidden"><input class="qty"  id="'+ data.lDetailComp[j].model.pt_part +'" class="inputtext" name="lDetailComp['+ j +'].qty" type="number" width="80" step="any" readonly value="'+ qty +'">';
							   
						   }
					}
					
					 
					 if($("#ptpart").length){
						  $("#ptpart").append(new Option("","", true, true));
						  for (i = 0; i < list_ptpart.length; i++) {
						
								$("#ptpart").append(
										new Option(list_ptpart[i],
												list_ptpart[i], false, false));
							}
					  }
				
			}
			$("#selectline").change(function() {

				var lineid = $(this).val();
				var type = $('input[name=type]:checked').val();
				$.ajax({
					type : "GET",
					contentType : "application/json",// "application/json"text/html
					url : "/mr/api/selectline",
					data : {
						"lineid" : lineid,
						"type"  :type
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
			$("#workorder").change(function() {
				var woid = $(this).val();
				var lineid = $("#selectline").val();
				 $('#serial').focus();
				//var type = $('input[name=type]:checked').val();
				
				ajaxmodel(woid);
				
			});
				function ajaxmodel(woid){
				$.ajax({
					type : "GET",
					contentType : "application/json",// "application/json"text/html
					url : "/mr/api/selectrcid2",
					data : {
						"woid" :woid
					
					},
					dataType : 'json',
				     
					success : function(data) {
						console.log("SUCCESS: ", data);
						displayPs(data);
					},
					error : function(e) {
						console.log("ERROR: ", e);
						displayPs(e);
					},
					done : function(e) {
						console.log("DONE");
					}
				});
				}
			
			
		});
