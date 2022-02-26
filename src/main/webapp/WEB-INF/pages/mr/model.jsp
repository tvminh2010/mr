<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
      
    <h4>Item Master</h4>
   
    
    	
    	
    
          
	<div>
		<form role="form" method="post" id="MyFormID" name="import" action="model/importModel" enctype="multipart/form-data">
					<table class="table small table-bordered table-striped rcorners1">
						<thead>
							<tr>
								<th colspan="4">  <strong> Nhập danh sách Model(Excel)</strong> </th>
							</tr>
						</thead>
						<tbody>
							<tr>
								
								
								<td width="20%"><b>File to upload</b></td>
     	                         <td><input type="file"  name="excelfile" required="true"/></td>
								
						
								<td align="right">
     		<button type="submit" class="btn btn-primary">Import</button>
     	</td><td>Download file mẫu: <a href="downloadLogFileTemp?filename=Itemmaster.xls">download</a></td>
					</tr>
						
						</tbody>
					</table>		
		</form>
		<form role="form" method="post" id="MyFormID" name="import" action="model/importPackage" enctype="multipart/form-data">
					<table class="table small table-bordered table-striped rcorners1">
						<thead>
							<tr>
								<th colspan="4">  <strong> Nhập danh sách Package(Excel)</strong></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								
								
								<td width="20%"><b>File to upload</b></td>
     	                         <td><input type="file"  name="excelfile" required="true" /></td>
								
						
								<td align="right">
     		<button type="submit" class="btn btn-primary">Import</button>
     	</td>
     	<td>Download file mẫu: <a href="downloadLogFileTemp?filename=Packing.xls">download</a>
     		
     	</td>
					</tr>
						
						</tbody>
					</table>		
		</form>
		<p style="color:#006EBE">Kết quả</p>
     <table id="tbl" class="table display table-bordered form-table border-collapse " border="1">
          <thead>
			<tr>
				<th>Item code</th>
				<th>Item number</th>
				<th>Item name</th>
				<th>Prod Line</th>
				<th>UM</th>
				<th>Package</th>
			
			</tr>
		  </thead>
             <c:forEach var="l" items="${lp}" >
              <tr>
                  <td>${l.pt_part}</td>
                  <td>${l.pt_desc1}</td>
                  <td>${l.pt_desc2}</td>
                  <td>${l.pt_prod_line}</td>
                  <td>${l.pt_um}</td>
                  <td><%-- ${l.pt_package} --%></td>
             
              </tr>
              </c:forEach>
          </table>
        
    </div>

   
    <script>
    
    </script>
   
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