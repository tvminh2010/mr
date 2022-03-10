<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



	<div style="float: left">
		<h4>Đóng/Mở WorkOrder</h4>
	</div>

	                           

<form role="form" method="post" id="MyFormID" name="import" action="cwo" >
					<table class="table small table-bordered table-striped rcorners1">
						<thead>
							<tr>
								<th colspan="4"> <h4>  <strong>Đóng WorkOrder</strong></h4> </th>
							</tr>
						</thead>
						<tbody>
							<tr>
								
								
								<td width="20%"><b>Nhập WorkOrder:</b></td>
     	                         <td> <input name="namewo" id="loing" list="browsers1" autocomplete="off"/>
     	                            <datalist id="browsers1" >
     	                         	<option></option>
     	                         	
														<c:forEach var="p" items="${loing}">
															<option>${p }</option>
														</c:forEach>
															
								  </datalist>
								  	<button type="submit" class="btn btn-primary">Đóng</button>
								  </td>
								
						
								<td align="right">
 
     	</td>
					</tr>
						
						</tbody>
					</table>		
		</form>
		<form role="form" method="post" id="MyFormID" name="import" action="owo">
					<table class="table small table-bordered table-striped rcorners1">
						<thead>
							<tr>
								<th colspan="4"> <h4>  <strong> Mở WordOrder</strong></h4> </th>
							</tr>
						</thead>
						<tbody>
							<tr>
								
								
								<td width="20%"><b>Nhập WorkOrder:</b></td>
     	                         <td>
     	                         <input name="namewo" id="loing" list="browsers" autocomplete="off"/>
     	                            <datalist id="browsers" >
     	                         	<option></option>
     	                         	
														<c:forEach var="p1" items="${lcing}">
															<option>${p1 }</option>
														</c:forEach>
															
								  </datalist>
     	 		<button type="submit" class="btn btn-primary">Mở</button>
     	                         </td>
								
						
								<td align="right">
     	
     	</td>
     
     
					</tr>
						
						</tbody>
					</table>		
		</form>
