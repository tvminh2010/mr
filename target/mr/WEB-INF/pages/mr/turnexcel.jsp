<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="en_US" scope="session"/>
<div>

   
		
		<h4>Danh sách phiên</h4>
	
	<div style="float: right">
	<button  onclick="deleteList('turnexcel/deletectall');" id="delete-all" class="btn btn-danger">Delete selected</button>
		</div>
		  <table id="tbl" class="table display table-bordered form-table border-collapse " border="1">
   		<thead>
			<tr>
			   <th width="5px"><input type="checkbox"  value="" id="isAgeSelected"/></th>
			    
				<th>Phiên</th>
				<th>Date</th>
				<th>File Yêu cầu</th>
			    <th>File Giao nhận</th>
				<th width="100px"></th>
			</tr>
  		</thead>
  		<tbody>
  
  			 <c:forEach var="l" items="${lturn}" varStatus="i">
		    <tr>
		    	<td width="5px"><input id="checkbo" type="checkbox" value="${l.id}"/></td>
		
		    	<td><a href="byturn?name=${l.id}">${l.id}</a></td>
		    	<td><fmt:formatDate value="${l.d }" type="both" pattern="dd-MM-yyyy HH:mm" /></td>
		    	<td>  
		    	 <c:if test="${ l.downloadedbs}">
       	    	        <a style="color:red" href="downloadLogFile?filename=${l.linkdownloadbs}&type=1">${l.linkdownloadbs==null?"":"BS"}</a> 
                 </c:if>
                  <c:if test="${ !l.downloadedbs}">
       	    	        <a  href="downloadLogFile?filename=${l.linkdownloadbs}&type=0">${l.linkdownloadbs==null?"":"BS"}</a> 
                 </c:if>
                  <c:if test="${ l.downloadedsetup}">
		    	          <a style="color:red" href="downloadLogFile?filename=${l.linkdownloadsetup}&type=1">${l.linkdownloadsetup==null?"":"Setup"}</a>
                 </c:if>
                  <c:if test="${! l.downloadedsetup}">
		    	 <a  href="downloadLogFile?filename=${l.linkdownloadsetup}&type=0">${l.linkdownloadsetup==null?"":"Setup"}</a>
                 </c:if>
		 		<td>
		 		<a onclick="deleteOne('${l.id}','turnexcel/deletect');"><span class="glyphicon glyphicon-remove"></span></a>
		 		<a onclick="giaonhan('${l.id}');"><span class="glyphicon glyphicon-edit"></span></a>
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
            window.open("turnexcel?page="+ page ,"_self");
        });
    });
    });
</script>
