<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div>
	
		<h4>Báo cáo theo WorkOrder</h4>
	

<form action="bywo" method="get">
<table class="table small">
<tr>

<td>Tìm kiếm theo WorkOrder:<input class="qty"  class="inputtext" name="woname" type="text" width="100">
<button  type="submit" class="btn btn-primary">Thực hiện</button></td>
				<td> </td>
</tr>
</table>
</form>

 <h6 style="color:blue">  <strong>Tìm kiếm</strong></h6>
  <div id="demo" >
     
	<div>
	
		<table class="table small ">
		<thead>
			<tr>
				<td><label for="MaTP">Line</label></td>
				<td><label for="LineSo">Mã NVL</label></td>
				
				<td><label for="MaTP">Package</label>
				<td><label for="MaTP">Kế hoạch</label></td>
				<td><label for="MaTP"></label></td>
				<td><label for="MaTP">Số lượng</label></td>

			</tr>
			</thead>
			<tbody>
			
			
			<c:forEach items="${lrc}" var="rc"  varStatus="i">
			<c:if test="${i.index==0 }">
				<tr style="color:#0061ff">
		                        <td>${rc.wo.line.name } </td>
		                        <td>WorkOrder: ${rc.wo.name }</td>
		                        <td>Model: ${rc.wo.model.pt_desc1 } </td>
		                        <td>Kế hoạch: ${rc.wo.qty }</td>
		                   
		                        <td></td>
		                        <td></td>
		        </tr>
			</c:if>
				<tr style="color:#0061ff">
		                      
		                     
		                     
		                    
		                        <td>${rc.type=="RequestBS"?"BS":rc.type=="RequestSetup"?"Setup":rc.type=="Return"?"Hoàn trả":"Giao nhận" }</td>
		                        <td>${rc.date }</td>
		                        <td>${rc.us.staff.code }</td>
		                             <td></td>
		                                  <td></td>
		        </tr>
		        	<c:forEach items="${rc.lDetailComp}" var="dc" > 
		        	      <c:if test = "${dc.qty >0}">
                           
                       
				        	  <tr style="background-color:#F9F9F9">
		                        <td></td>
								<td >${dc.model.pt_desc1}</td>
								<td >${dc.model.pt_package} </td>
								<td >${dc.plan }</td>
								<td > </td>
							    <td > ${dc.qty }	</td>
							</tr>
						</c:if>
		            </c:forEach>
			
			</c:forEach>
			
			</tbody>

           





			
		</table>
	</div>
	<span style='color: red; font-size: 0.9em;' id="label1"> </span>


</div>
<script type="text/javascript">
$('.datepicker').pickadate()

</script>
