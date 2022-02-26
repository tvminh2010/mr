<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="en_US" scope="session"/>
<div>

   
		
		<h4>Danh sách file Excel hoàn trả</h4>
	
	<div style="float: right">
		</div>
		  <table id="tbl" class="table display table-bordered form-table border-collapse " border="1">
   		<thead>
			<tr>
			   <th width="5px"><input type="checkbox"  value="" id="isAgeSelected"/></th>
			    
				<th>WorkOrder</th>
				<th>Model</th>
			    <th>Line</th>
			      <th>Date</th>
				<th>Download Excel</th>
			
			
			
			</tr>
  		</thead>
  		<tbody>
  
  			 <c:forEach var="l" items="${lrturn}" varStatus="i">
		    <tr>
		    	<td width="5px"><input id="checkbo" type="checkbox" value="${l[0]}"/></td>
		
		    	<td>${l[1]}</td>
		    	<td>  
		         ${l[2]}
                </td>
                <td>  
		     ${l[3]}
                </td>
                
		    	<td><fmt:formatDate value="${l[4] }" type="both" pattern="dd-MM-yyyy HH:mm" /></td>
		    	<td>  
		    	<a  href="downloadLogFile?filename=${l[5]}&type=2">Download</a> 
                </td>
		 		
		    </tr>
		 </c:forEach>
           </tbody>                    
          </table>	
           <ul id="pagination" class="pagination-sm pagination"></ul>
          <div>
          
         

</div>
				<span style='color: red; font-size: 0.9em;' id="label1">  </span>
			
	</div>	
    <script type="text/javascript">
    $(document).ready(function(){
    	function giaonhan(){
    		
    		alert("sua giao nhan");
    	}
    	
    	
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
            window.open("returnexcel?page="+ page ,"_self");
        });
    });
    });
</script>
