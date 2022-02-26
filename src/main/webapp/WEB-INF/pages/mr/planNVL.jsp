<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <fmt:setLocale value="en_US" scope="session"/>
<div>


	
		
	</div>
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
	
			<form role="form" method="post" id="MyFormID" name="import" action="importPlan" enctype="multipart/form-data">
					<table class="table small table-bordered table-striped rcorners1">
						<thead>
							<tr>
								<th colspan="4"> <h4>  <strong> Kế hoạch SX</strong></h4> </th>
							</tr>
						</thead>
						<tbody>
							<tr>
								
								
								<td width="20%"><b>File to upload</b></td>
     	                         <td><input type="file"  name="excelfile" required="true" /></td>
								
						
								<td align="right">
     		<button type="submit" class="btn btn-primary">Import</button>
     	</td>
     	<td>Download file mẫu: <a href="downloadLogFileTemp?filename=Plan.xls">download</a></td>
					</tr>
						
						</tbody>
					</table>		
		</form>
		<h6 style="color:blue">  <strong>Nhập dữ liệu</strong></h6>
		<form:form role="form" method="post" id="MyFormID" name="MyForm" action="planNVL" modelAttribute="wo">
		<form:hidden id="woid" path="id" class="form-control"/>
					<table class="table small table-bordered table-striped rcorners1">
						<thead>
							
						</thead>
						<tbody>
							<tr>
								<td  width="20%"> <label for="SerialNo">WorkOrder&nbsp;&nbsp;&nbsp;</label><span style='color: red; font-size: 0.9em;'> * </span></td>
								<td> 
									<form:input type="text" path="name" id="SerialNo"  required="true" />
								</td>
								<%-- <td  width="20%"> <label for="SerialNo">Ngày&nbsp;&nbsp;&nbsp;</label></td>
								<td> 
									<form:input type="date" path="createdate" id="SerialNo"  />
								</td> --%>
								
					</tr>
								<tr>
								<td> <label for="NameSP">Line</label><span style='color: red; font-size: 0.9em;'> * </span> </td>
								<td> 
									<%-- <form:input type="text" path="line.id" id="NameKHYC" name="fgcode" value="" class='rcorners1'/> --%>
									
			
								
														
			                     <form:input path="line.name" id="loing" list="browsers1" autocomplete="off" required="true"/>
     	                            <datalist id="browsers1" >
     	                         	<option></option>
     	                        
														<c:forEach var="ln" items="${listline}">
															<option>${ln.name}</option>
														</c:forEach>
															
								  </datalist>
								</td>
								<td  width="20%"> <label for="SerialNo">Model &nbsp;&nbsp;&nbsp;</label><span style='color: red; font-size: 0.9em;'> * </span></td>
								<td> 
									
								   <form:input path="model.pt_desc1" id="loing" list="browsers" autocomplete="off" required="true"/>
     	                            <datalist id="browsers" >
     	                         	<option></option>
     	                        
														<c:forEach var="p" items="${lp}">
															<option>${p.pt_desc1}</option>
														</c:forEach>
															
								  </datalist>
						<%-- 			<form:select  path="model.pt_part"
															 required="true">
															<option></option>

															<c:forEach var="l" items="${lp}">
																<c:choose>
																	<c:when
																		test="${l.pt_part==wo.model.pt_part}">

																		<option selected value="${l.pt_part}">${l.pt_desc1}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${l.pt_part}">${l.pt_desc1}</option>
																	</c:otherwise>

																</c:choose>
															</c:forEach>

														</form:select> --%>
								</td>
								
						
							</tr>
							<tr>
							<td> <label for="NameSP">Số lượng <span style='color: red; font-size: 0.9em;'> * </span></label> </td>
								<td> 
									<form:input type="text" path="qty" id="NameKHYC"  value=""  required="true" />
								
								
								</td>
								<td> <label for="NameSP">Trạng thái</label> </td>
								<td> 
									<form:input type="number" path="status"  readonly="true"  />
								
								
								</td>
							</tr>
								
								<tr>
							
							
								<td colspan=4 align="right"> 
									<button type="submit" class="btn btn-primary">Save</button>
								  
								
								</td>
							</tr>
						</tbody>
					</table>		
		</form:form>
				<span style='color: red; font-size: 0.9em;' id="label1">  </span>
			
				
	</div>	
	
	<div>
	
			
	
   <h6 style="color:blue">  <strong>Tìm kiếm</strong></h6>
  <div id="demo" >
     <form role="form" method="get" id="MyFormID" name="MyForm" action="planNVL">
					<table class="table small table-bordered table-striped rcorners1">
						<thead>
						</thead>
						<tbody>
						<tr>
						<td  width="20%"> <label>Từ </label></td>
					
								<td> 
									<input type="text" id="startdate" name="startdate" value="<fmt:formatDate value="${startdate }"  
      pattern="dd/MM/yyyy" />" class="datepicker"   required="true" autocomplete="off"/>
								</td>
								<td  width="20%"> <label>Đến</label></td>
						
								<td> 
									<input type="text" id="enddate" name="enddate" value="<fmt:formatDate value="${enddate }"  
      pattern="dd/MM/yyyy" />" class="datepicker" autocomplete="off" />
								</td>
						</tr>
						
							<tr>
								<td  width="20%"> <label for="SerialNo">WorkOrder</label></td>
								<td> 
									<input type="text" name="woname" value="${woname}" id="woname" autocomplete="off" />
								</td>
									<td  width="20%"> <label for="SerialNo">Model &nbsp;&nbsp;&nbsp;</label></td>
								<td> 
									
								   <input name="item" id="item" value="${item}" list="browsers" autocomplete="off"/>
     	                            <datalist id="browsers" >
     	                         	<option></option>
														<c:forEach var="p" items="${lp}">
															<option>${p.pt_desc1}</option>
														</c:forEach>
								  </datalist>
								</td>
					</tr>
								<tr>
								<td> <label for="NameSP">Line</label></td>
								<td> 
									<%-- <form:input type="text" path="line.id" id="NameKHYC" name="fgcode" value="" class='rcorners1'/> --%>
			                        <input  id="line" name="line"  value="${line}" list="browsers1" autocomplete="off"/>
     	                            <datalist id="browsers1" >
     	                         	<option></option>
														<c:forEach var="ln" items="${listline}">
															<option>${ln.name}</option>
														</c:forEach>
								  </datalist>
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
   	
   <button  onclick="deleteListString('deleteplanall');" id="delete-all" class="btn btn-primary">Delete selected</button>
   <div style="font-style: italic; font-size:12px;color:gray">
      Trạng thái: 1 - mới tạo, 2 - đang sản xuất,  3 - kết thúc
   </div>
   
		  <table id="tbl" class="table display table-bordered form-table border-collapse " border="1">
   		<thead>
			<tr>
			    <th width="5px"><input type="checkbox"  value="" id="isAgeSelected"/></th>
			    	<th>WorkOrder</th>
				<th>Ngày</th>
				<th>Line</th>
				<th>Model</th>
				<th>Số lượng</th>
				<th>Trạng thái</th>
			  
				<th width="5px"></th>
				<th width="5px"></th>
			</tr>
  		</thead>
  		<tbody>
  
  			 <c:forEach var="l" items="${lwo}" varStatus="i">
		    <tr>
		    	<td width="5px"><input id="checkbo" type="checkbox" value="${l.id}"/></td>
		    	<td>
		    	<c:if test = "${l.status >= 2}">
                         <a href="bywo?woname=${l.name}">${l.name}</a>
                </c:if>
		    	
		    	<c:if test = "${l.status== 1}">
                         ${l.name}
                </c:if>
		    	</td>
		    	<td><fmt:formatDate value="${l.createdate }" type="both" pattern="MM/dd/yyyy" /></td>
		    	<td>${l.line.name}</td>
		    	<td>${l.model.pt_desc1}</td>
		    	<td align="right"><fmt:formatNumber value="${l.qty}" maxFractionDigits="3"/></td>
		    	 <td>${l.status}</td>
		    	
		 	<td><a href="planNVL?id=${l.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
		 		<td><a  onclick="deleteOne('${l.id}','deleteplan');"><span class="glyphicon glyphicon-remove"></span></a></td>
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
    	var startdate = $("#startdate").val();
    	var enddate = $("#enddate").val();
    	var item = $("#item").val();
    	var line = $("#line").val();
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
            window.open("planNVL?page="+ page +"&item="+ item +"&woname="+ woname +"&line="+ line +"&startdate="+ startdate +"&enddate="+enddate ,"_self");
        });
    });
</script>
</div>
				<span style='color: red; font-size: 0.9em;' id="label1">  </span>
			
				
	</div>	

