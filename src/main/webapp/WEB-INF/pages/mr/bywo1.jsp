<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="en_US" scope="session"/>
<div>
		<h4>Báo cáo chi tiết WorkOrder</h4>
<form action="bywo1" method="get">
<table class="table small">
<tr>
<td>Tìm kiếm theo WorkOrder:<input class="qty"  class="inputtext" name="woname" type="text" width="100"  value="${woname }">
<button  type="submit" class="btn btn-primary">Thực hiện</button></td>
				<td> </td>
</tr>
</table>
</form>
 <form role="form" method="get" id="MyFormID" name="MyForm" action="bywo1excel">
					
                 <input class="qty"  class="inputtext" name="woname" type="hidden" width="100" value="${woname }">
									<button type="submit" class="btn btn-primary btn-sm">Download Exel</button>
		</form>
		<table  class="table small table-bordered table-striped rcorners1"> 
	<tr>
	            <td><label>WorkOrder</label>: ${wo.name }</td>
	             <td><label>Date</label>:      <fmt:formatDate value="${wo.createdate}" type="both" pattern="dd/MM/yyyy" /> </td>
	      
	            <td><label>Line</label>: ${wo.line.name }</td>
				<td><label>Model</label>: ${wo.model.pt_desc1 }</td>
				<td><label>Số lượng</label>: ${wo.qty }</td>
	</tr>
	</table>
<ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#home">BC1</a></li>
    <li><a data-toggle="tab" href="#menu1">BC2</a></li>
    <li><a data-toggle="tab" href="#bc3">BC3</a></li>
  </ul>
  <div class="tab-content">
    <div id="home" class="tab-pane fade in active">
     	
		<table class="table small table-bordered table-striped rcorners1">
		<thead>
			<tr>
			<td><label>STT</label></td>
				<td><label>Item Code</label></td>
				<td><label>Item Name</label></td>
				
				<td><label>Item Number</label>
				<td><label>SL Nhận</label></td>
				<td><label>SL Hoàn trả</label></td>
				<td><label>Thực nhận</label></td>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${lwo1}" var="obj1"  varStatus="i">
			
				<tr>
				 <td>${i.index+1 } </td>
		                        <td>${obj1[0] } </td>
		                        <td>${obj1[1] } </td>
		                        <td>${obj1[2] } </td>
		                        <td>${obj1[3] } </td>
		                        <td>${obj1[4] } </td>
		                        <td><fmt:formatNumber value="${obj1[3] - obj1[4] }"  maxFractionDigits="2"  /> </td>
		        </tr>
			</c:forEach>
			
			</tbody>

           





			
		</table>
			
    </div>
    <div id="menu1" class="tab-pane fade">
		<table class="table small table-bordered table-striped rcorners1 ">
		<thead>
			<tr>
				<td><label>STT</label></td>
				<td><label>Item Code</label></td>
				<td><label>Item Name</label></td>
				
				<td><label>Item Number</label>
				<td><label>SL Nhận</label></td>
				<td><label>Trạng thái</label></td>
				
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${ls21}" var="s21"  varStatus="ix">
				<tr>
				<td>${ix.index+1} </td>
		                        <td>${s21[0]} </td>
		                       <td>${s21[1]} </td>
		                        <td>${s21[2]} </td>
		                        <td>${s21[3]} </td>
		                       <td>Setup</td>
		        </tr>
			</c:forEach>
			 <c:set var = "ls21size" value = "${fn:length(ls21)}"/>
			<c:forEach items="${lb22}" var="b22"  varStatus="i2">
				<tr>
				  <td>${ls21size+i2.index+1} </td>
		                        <td>${b22[0]} </td>
		                       <td>${b22[1]} </td>
		                        <td>${b22[2]} </td>
		                        <td>${b22[3]} </td>
		                         <td>BS</td>
		        </tr>
			</c:forEach>
			</tbody>
		</table>
    </div>
       <div id="bc3" class="tab-pane fade">
     	
		<table class="table small table-bordered table-striped rcorners1">
		<thead>
			<tr>
			<td><label>STT</label></td>
			<td><label>Tên phiên</label></td>
			<td><label>Trạng thái</label></td>
			<td><label>Giờ yêu cầu</label></td>
			<td><label>Giờ giao</label></td>
				<td><label>Item Code</label></td>
				<td><label>Item Name</label></td>
				<td><label>Item Number</label>
				<td><label>Số Lượng YC</label></td>
				<td><label>Số Lượng Giao</label></td>
				
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${l2}" var="ol2"  varStatus="i">
			
				<tr>
				 <td>${i.index+1 } </td>
		                        <td>${ol2[1].turn.id } </td>
		                        <td>${ol2[1].type=="RequestBS"?"BS":ol2[1].type=="RequestSetup"?"Setup":ol2[1].type=="Return"?"Hoàn trả":ol2[1].type=="Response"?"Giao nhận":"" }</td>
		                        <td><fmt:formatDate value="${ol2[1].closetime.closetime}" type="both" pattern="HH:mm" />  </td>
		                        <td><fmt:formatDate value="${ol2[3].date}" type="both" pattern="HH:mm" /> </td>
		                         <td>${ol2[0].model.pt_part} </td>
		                           <td>${ol2[0].model.pt_desc1} </td>
		                             <td>${ol2[0].model.pt_desc2} </td>
		                              <td>${ol2[0].qty} </td>
		                               <td>${ol2[2].qty} </td>
		        </tr>
			</c:forEach>
			
			</tbody>

           





			
		</table>
			
    </div>
  </div>
	<div>

	</div>
	<span style='color: red; font-size: 0.9em;' id="label1"> </span>
</div>
<script type="text/javascript">
$('.datepicker').pickadate()
</script>
