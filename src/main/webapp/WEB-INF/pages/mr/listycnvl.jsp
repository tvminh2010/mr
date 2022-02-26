<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
       <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%--    <fmt:setLocale value="en_US" scope="session"/> --%>
    <fmt:setLocale value="vi_VN" scope="session"/>
       <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="res/js/yeucauNVL/001.js"></script>
<script type="text/javascript" src="res/js/yeucauNVL/002.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<div>


</div>

<div>
	<div style="float: left">
		<h4>Yêu cầu NVL</h4>
	</div>




	<div>
		<button style="float: right"  class="btn btn-primary"  onclick="myFunction()">Reload</button>
		<table class="table small ">
		<thead>
			<tr>
				<td><label for="MaTP">Line</label></td>
				<td><label for="LineSo">Mã NVL</label></td>
				
				<td><label for="MaTP">Package</label>
				<td><label for="MaTP">Kế hoạch</label></td>
				<td><label for="MaTP">Đã nhận</label></td>
				<td><label for="MaTP">Số lượng Y/C </label></td>

			</tr>
			</thead>
			<tbody>
			
			<c:forEach items="${lrc}" var="lrc" >
				<tr style="color:#0061ff">
		                        <td>${lrc.wo.line.name } </td>
		                        <td>WorkOrder: ${lrc.wo.name }</td>
		                        <td>Model: ${lrc.wo.model.pt_desc1 } </td>
		                        <td>Kế hoạch: ${lrc.wo.qty }</td>
		                      
		                        <td>T/g đóng phiên: ${lrc.closetime.closetime } </td>
		                        <td></td>
		        </tr>
		        	<c:forEach items="${lrc.lDetailComp}" var="dc" > 
		        	      <c:if test = "${dc.qty >0}">
                           
                       
				        	  <tr style="background-color:#F9F9F9">
		                        <td></td>
								<td >${dc.model.pt_desc1}</td>
								<td >${dc.model.pt_package} </td>
								<td ><fmt:formatNumber value="${dc.plan }" maxFractionDigits="3"/></td>
								<td > ${dc.totalResponse - dc.totalReturn } </td>
							    <td > ${dc.qty }	</td>
							</tr>
						</c:if>
		            </c:forEach>
			
			</c:forEach>
			<tr>
				<td colspan="7" align="right">
						
						<a href="closeturn" class="btn btn-primary" role="button">Đóng phiên hiện tại</a>
						</td>
						
			</tr>
			</tbody>

           





			
		</table>
	</div>
	<span style='color: red; font-size: 0.9em;' id="label1"> </span>


</div>
<script>
function myFunction() {
    location.reload();
}
</script>
