<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="res/js/yeucauNVL/001.js"></script>
<script type="text/javascript" src="res/js/yeucauNVL/002.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<div>
	<c:if test="${ error==1}">
		<div class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Lỗi!</strong> Không load được Driver - ${msg}
		</div>
	</c:if>
	<c:if test="${ error==2}">
		<div class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Lỗi!</strong> Lỗi connect database - ${msg}
		</div>
	</c:if>
	<c:if test="${ error==3}">
		<div class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Lỗi!</strong>Không tìm thấy file jrxml - ${msg}
		</div>
	</c:if>
	<c:if test="${ error==4}">
		<div class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Lỗi!</strong> Không tìm thấy máy in - ${msg}
		</div>
	</c:if>
	<c:if test="${ error==5}">
		<div class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Lỗi!</strong> Không tìm thấy dữ liệu - ${msg}
		</div>
	</c:if>
	<c:if test="${ error==6}">
		<div class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Lỗi!</strong> Không load được file jrxml - ${msg}
		</div>
	</c:if>
	<c:if test="${ error==7}">
		<div class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Print thành công !</strong>
		</div>
	</c:if>
	<c:if test="${ error==8}">
		<div class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Lỗi!</strong> Không tồn tại Murata - Part Name của - ${msg}.
			Không thực hiện lệnh in
		</div>
	</c:if>

</div>

<div>
	
		<h4>Báo cáo theo phiên</h4>
	

<form action="byturn" method="get">
<table class="table small">
<tr>

<td>Tìm kiếm theo phiên: <input class="qty"  class="inputtext" name="name" type="text" width="100">
<button  type="submit" class="btn btn-primary">Thực hiện</button> </td>
				<td> </td>
</tr>
</table>
</form>


	<div>
			<h5>Phiên: ${name }</h5>
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
			
			<c:forEach items="${lrc}" var="lrc" >
				<tr style="color:#0061ff">
		                        <td>${lrc.wo.line.name } </td>
		                        <td>WorkOrder: ${lrc.wo.name }</td>
		                        <td>Model: ${lrc.wo.model.pt_desc1 } </td>
		                        <td>Kế hoạch: ${lrc.wo.qty }</td>
		                        <td>${lrc.type=="RequestBS"?"BS":lrc.type=="RequestSetup"?"Setup":lrc.type=="Response"?"Giao nhận":"Hoàn trả" }</td>
		                        <td></td>
		                        <td></td>
		        </tr>
		        	<c:forEach items="${lrc.lDetailComp}" var="dc" > 
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

