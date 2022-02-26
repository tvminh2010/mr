$(document).ready(
		
		function() {
			 var table = document.getElementById('tbody')
			function selectline(data) {
				$('#model').children().remove();
				$("#workorder").text('');
				$("#plan").text('');
				if (data.length >0) {
					
					$("#workorder").val(data[0].name);
					$("#plan").val(data[0].qty);
					
				
					for (i = 0; i < data.length; i++) {
						$("#model").append(
								new Option(data[i].model.pt_desc1,
										data[i].model.pt_part, true, true));
					}
					var type = $('#type').val();
					ajaxmodel(data[0].model.pt_part,data[0].line.id,type);
				}else{
					
					 $(table).empty();
				}
			}
			function displaytype(data) {
				$('#model').children().remove();
				$("#workorder").text('');
				$("#plan").text('');
				
				if (data.length == 1) {
					$("#model").append(
							new Option(data[0].model.pt_desc1,
									data[0].model.pt_part, true, true));

					$("#workorder").val(data[0].name);
					$("#plan").val(data[0].qty);
				} else {
					for (i = 0; i < data.length; i++) {
						$("#model").append(
								new Option(data[i].model.pt_desc1,
										data[i].model.pt_part, true, true));
					}
					$('#model').focus();

				}
			}
			function displayPs(data) {
				
				 var table = document.getElementById('tbody')
				 $(table).empty();
				 $("#rcid").val(data.id==null?'':data.id);
				 $("#woid").val(data.wo.id);
				 $("#workorder").text(data.wo.name);
					$("#plan").text(data.wo.qty);
					 $("#showline").text(data.wo.line.name);
						$("#showmodel").text(data.wo.model.pt_desc1);
				
					for (i = 0; i < data.lDetailComp.length; i++) {
						
						    var row = table.insertRow(i);
						    var cell0 = row.insertCell(0);
						    var cell1 = row.insertCell(1);
						    var cell2 = row.insertCell(2);
						    var cell3 = row.insertCell(3);
						    var cell4 = row.insertCell(4);
						    var cell5 = row.insertCell(5);
						   var  j=i;
						   var qtyper = Math.round(data.lDetailComp[j].plan*1000)/1000;
						   var total = data.lDetailComp[j].totalResponse-data.lDetailComp[j].totalReturn;
						   var qty = data.lDetailComp[j].qty==null?'':data.lDetailComp[j].qty;
						  // var pac =data.lDetailComp[j].model.pt_package==null?'':data.lDetailComp[j].model.pt_package;
						   var pac='';
						   var id = data.lDetailComp[j].id==null?'':data.lDetailComp[j].id;
						   if(data.lDetailComp[j].ps_qty_per>0){
						    cell0.innerHTML = '<input  value="'+ id  +'" name="lDetailComp['+ j +'].id" type="hidden"><p style="font-size:11px">'+data.lDetailComp[j].model.pt_desc2 +'</p>';
						    cell1.innerHTML = '<p style="font-size:11px">'+ data.lDetailComp[j].model.pt_desc1 +'</p>';
						    cell2.innerHTML ='<p style="font-size:11px">'+ pac +'</p>';
						   cell3.innerHTML =  '<p style="font-size:11px">'+ qtyper +'</p>';
						   cell4.innerHTML ='<p style="font-size:11px">'+ total +'</p>';
					    cell5.innerHTML = '<input  value="'+ data.lDetailComp[j].model.pt_part +'" name="lDetailComp['+ j +'].model.pt_part" type="hidden"><input class="qty" class="inputtext" name="lDetailComp['+ j +'].qty" type="number" width="50" >';
						   }else{
							   cell0.innerHTML = '<input  value="'+ id +'" name="lDetailComp['+ j +'].id" type="hidden"><p style="color: #b3b3b3;font-size:11px">'+data.lDetailComp[j].model.pt_desc2+'</p>';
							    cell1.innerHTML = '<p style="color: #b3b3b3;font-size:11px">'+ data.lDetailComp[j].model.pt_desc1+'</p>';
							    cell2.innerHTML = '<p style="color: #b3b3b3;font-size:11px">'+pac+'</p>';
							   cell3.innerHTML =  '<p style="color: #b3b3b3;font-size:11px">'+Math.round(data.lDetailComp[j].plan*1000)/1000 +'</p>';
							   cell4.innerHTML = '<p style="color: #b3b3b3;font-size:11px">'+ total +'</p>';
						    cell5.innerHTML = '<input  value="'+ data.lDetailComp[j].model.pt_part +'" name="lDetailComp['+ j +'].model.pt_part" type="hidden"><input class="qty" class="inputtext" name="lDetailComp['+ j +'].qty" type="number" width="50" >';
							   
						   }
					}
					

				
			}
			$("#selectline").change(function() {

				var lineid = $(this).val();
				var type = $('#type').val();
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
						
					},
					done : function(e) {
						console.log("DONE");
					}
				});
			});
			$("#model").change(function() {
				var productid = $(this).val();
				var lineid = $("#selectline").val();
				var type = $('#type').val();
				alert(type);
				ajaxmodel(productid,lineid,type);
			});
				function ajaxmodel(productid,lineid,type){
				$.ajax({
					type : "GET",
					contentType : "application/json",// "application/json"text/html
					url : "/mr/api/selectmodel",
					data : {
						"lineid" :lineid,
						"productid" : productid,
						"type" : type
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
			
			
		});
