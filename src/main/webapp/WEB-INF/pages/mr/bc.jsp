<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="en_US" scope="session"/>
<div>
	
		<h4>Báo cáo tổng hợp</h4>
	
<form action="bc" method="get">
<table class="table small">
<tr>

<td>Tìm kiếm<select id="selecttype" name="type">
						<option></option>
						<option value="${typesetup }">Setup</option>
							<option value="${typebs }">Bổ sung</option>
								<option value="${typeresponse }">Giao nhận</option>
									<option value="${typereturn }">Hoàn trả</option>
				</select>
				<button  type="submit" class="btn btn-primary">Thực hiện</button> </td>
				<td>
				<!-- <td>Tìm kiếm theo WorkOrder:<input class="qty"  class="inputtext" name="woname" type="text" width="100"> -->
				</td>
</tr>
</table>
</form>


	<div>
		
		<table class="table small ">
		<thead>
			<tr>
			
				<td><label for="MaTP">Line</label></td>
				<td><label for="LineSo">Mã NVL</label></td>
				
				<td><label for="MaTP"></label>
				<td><label for="MaTP"></label></td>
				
				<td><label for="MaTP">Số lượng Y/C </label></td>

			</tr>
			</thead>
			<tbody>
			
			<c:forEach items="${lrc}" var="rc" >
				<tr style="color:#0061ff">
		                        <td>${rc.wo.line.name } </td>
		                        <td>WorkOrder: ${rc.wo.name }</td>
		                        <td>Model: ${rc.wo.model.pt_desc1 } </td>
		                        <td><fmt:formatDate value="${rc.date }" type="both" 
      pattern="dd-MM-yyyy HH:mm" /></td>
		                        <td>${rc.type=="RequestBS"?"BS":rc.type=="RequestSetup"?"Setup":rc.type=="Return"?"Hoàn trả":rc.type=="Response"?"Giao nhận":"" }</td>
		                        
		                      	     <td align="center" width="5px">
		                      	        <c:if test="${ rc.type=='Return' || rc.type=='Response'  }">
       	    	                              <a href="${rc.type=='Return'?'hoantra':'giaonhan'}?id=${rc.id}">
		                      	               <span class="glyphicon glyphicon-edit"></span></a>
                                        </c:if>
		                      	    
		                      	     
		                      	     </td>
                  <td align="center" width="5px">
                      <c:if test="${ rc.type=='Return' || rc.type=='Response'  }">
       	    	                             <a onclick="deleteOne('${rc.id}','bc/deletect');"><span class="glyphicon glyphicon-remove"></span></a>
                                        </c:if>
                  	
                  </td>
		        </tr>
		        	<c:forEach items="${rc.lDetailComp}" var="dc" > 
		        	      <c:if test = "${dc.qty >0}">
                           
                       
				        	  <tr style="background-color:#F9F9F9">
		                        <td></td>
								<td >${dc.model.pt_desc1}</td>
								<td >${dc.model.pt_package} </td>
								<td >${dc.plan }</td>
								
							    <td > ${dc.qty }	</td>
						<td colspan="2"></td>
							</tr>
						</c:if>
		            </c:forEach>
			</c:forEach>
			</tbody>
		</table>
		  <ul id="pagination" class="pagination-sm pagination"></ul>	
	</div>
	


</div>

    <script type="text/javascript">
    $(document).ready(function(){
    $(function () {
    	
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
            window.open("bc?page="+ page ,"_self");
        });
    });
    });
</script>
