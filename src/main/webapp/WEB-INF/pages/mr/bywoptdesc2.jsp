<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="en_US" scope="session"/>
	
		<h4>Báo cáo theo WorkOrder</h4>
	



 <h6 style="color:blue">  <strong>Tìm kiếm</strong></h6>
  <div id="demo" >
     <form role="form" method="get" id="MyFormID" name="MyForm" action="bywoptdesc2">
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
	<div>
	<div>
	 <form role="form" method="get" id="MyFormID" name="MyForm" action="bywoptdesc2export">
					
<input type="hidden" name="startdate" value="<fmt:formatDate value="${startdate }"  
      pattern="dd/MM/yyyy" />"  />
	<input type="hidden" name="enddate" value="<fmt:formatDate value="${enddate }"  
      pattern="dd/MM/yyyy" />"   />
						<input type="hidden" name="woname" value="${woname}" id="SerialNo"  />
								   <input  type="hidden" name="item" id="loing" value="${item}" list="browsers" autocomplete="off"/>
     	                            <datalist id="browsers" >
     	                         	<option></option>
														<c:forEach var="p" items="${lp}">
															<option>${p.pt_desc1}</option>
														</c:forEach>
								  </datalist>
									<%-- <form:input type="text" path="line.id" id="NameKHYC" name="fgcode" value="" class='rcorners1'/> --%>
			                        <input   type="hidden" id="loing" name="line"  value="${line}" list="browsers1" autocomplete="off"/>
     	                            <datalist id="browsers1" >
     	                         	<option></option>
														<c:forEach var="ln" items="${listline}">
															<option>${ln.name}</option>
														</c:forEach>
								  </datalist>
									<button type="submit" class="btn btn-primary btn-sm">Download Exel</button>
		</form>
		</div>
		<table class="table small table-bordered">
		<thead>
			<tr>
		<!-- 	<td><label>STT</label></td>  -->
				 <td><label>WorkOrder</label></td> 
				<td><label>Date</label></td>
				<td><label>Line</label></td>
				<td><label>Model</label></td>
				<td><label>Plan</label></td>
				<td><label>Status</label></td>
				
			<c:forEach items="${listPt_desc2}" var="desc2"  varStatus="i">
		                        <td><label>${desc2 }</label></td>
			</c:forEach>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${lrc}" var="rc"  varStatus="i">
			
				<tr>
		                        <td>${rc[0]} </td>
		                       <td>${rc[1]} </td>
		                        <td>${rc[2]} </td>
		                         <td>${rc[3]} </td>
		                          <td>${rc[4]} </td>
		                            <td>${rc[5]} </td>
			<c:forEach items="${listPt_desc2}" var="desc2"  varStatus="idesc2">
		                        <td>${rc[idesc2.index+6] >0?rc[idesc2.index+6] :null}</td>
			</c:forEach>
		        </tr>
			</c:forEach>
			</tbody>

           





			
		</table>
		<ul id="pagination" class="pagination-sm pagination"></ul>
	</div>
	<span style='color: red; font-size: 0.9em;' id="label1"> </span>


</div>
<script type="text/javascript">

$(document).ready(function(){
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
            totalPages: '${size}',
           visiblePages:6,
           startPage:currentPage,
            onPageClick: function (event, page) {
                console.info(page + ' (from options)');
            }
        }).on('page', function (event, page) {
            console.info(page + ' (from event listening)');
            window.open("bywoptdesc2?page="+ page +"&item="+ item +"&woname="+ woname +"&line="+ line +"&startdate="+ startdate +"&enddate="+enddate ,"_self");
        });
    });
    });
</script>
