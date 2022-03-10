<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="en_US" scope="session"/>
<div> <h4>BOM</h4>
   
   <form role="form" method="post" id="MyFormID" name="import" action="bom/importBom" enctype="multipart/form-data">
					<table class="table small table-bordered table-striped rcorners1">
						<thead>
							<tr>
								<th colspan="4">  <strong> Nhập danh sách Bom(Excel)</strong> </th>
							</tr>
						</thead>
						<tbody>
							<tr>
								
								
								<td width="20%"><b>File to upload</b></td>
     	                         <td><input type="file"  name="excelfile" required="true" /></td>
								
						
								<td align="right">
     		<button type="submit" class="btn btn-primary">Import</button>
     	</td><td>Download file mẫu: <a href="downloadLogFileTemp?filename=BOM.xls">download</a></td>
					</tr>
						
						</tbody>
					</table>		
		</form>
		  <table id="tbl" class="table display table-bordered form-table border-collapse " border="1">
   		<thead>
			<tr>
			    
				
				<th>Model</th>
				<th>Component</th>
				
				<th>Quantity Per</th>
				
			
			</tr>
  		</thead>
  		<tbody>
  		<c:forEach var="l" items="${lps}" >
              <tr>
                  <td>${l.ps_par.pt_desc1}</td>
                  <td>${l.ps_comp.pt_desc1}</td>
                  <td><fmt:formatNumber value="${l.ps_qty_per}" maxFractionDigits="10"/></td>
                 
             
              </tr>
              </c:forEach>
  
   	
           </tbody>                    
          </table>		
		 
				
</div>
    <script type="text/javascript">
    $(document).ready(function(){
    	$('#tbl').DataTable( {
  	        "order": [[ 0, "asc" ]],
    	    "autoWidth": false,
    	    "dom": 'Bfrtip',
    	   
    	    "pageLength": 100,
  	      "fixedHeader": true,
    	    
    	            
    	    "buttons": [
    	                'pageLength',{ extend: 'copy', footer: true },
    	                { extend: 'csv', footer: true }, { extend: 'excel', footer: true }, 
    	                { extend: 'pdf', footer: true }, { extend: 'print', footer: true }
    	          ]
  	    });
    });
</script>
