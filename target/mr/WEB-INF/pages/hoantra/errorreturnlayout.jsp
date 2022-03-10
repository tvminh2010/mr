<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <fmt:setLocale value="en_US" scope="session"/>
<div>

   <c:if test="${ error==1}">
       	    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> Không được phép trùng tên WorkOrder  ${mg}
				  	</div>
   </c:if>
      <c:if test="${ error==2}">
       	    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> Không được phép xóa do có dữ liệu liên kết ${mg}
				  	</div>
   </c:if>
      <c:if test="${ error==3}">
       	    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong>Đóng phiên lỗi 
				  	</div>
   </c:if>
      <c:if test="${ error==4}">
       	    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> Không tìm thấy máy in - ${mg}
				  	</div>
   </c:if>
       <c:if test="${ error==5}">
       	    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> Không tìm thấy dữ liệu - ${mg}
				  	</div>
   </c:if>
     <c:if test="${ error==6}">
       	    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> Không load được file jrxml - ${mg}
				  	</div>
   </c:if>
    <c:if test="${ error==8}">
       	    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> Chưa nhập dữ liệu
				  	</div>
   </c:if>
    <c:if test="${ error==9}">
       	    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> Lưu không thành công
				  	</div>
   </c:if>
  <c:if test="${ error==7}">
       	    
				  	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> WorkOrder này đã được nhập dữ liệu !
				  	</div>
   </c:if>
   <c:if test="${ msg==1}">
   
     
       	    	<div class="alert alert-success">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lưu thành công !</strong>
					  
				  	</div>
				  	
	 
   </c:if>
	  <c:if test="${ msg==2}">
	 
       	    	<div class="alert alert-success">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Sửa thành công !</strong>
				  	</div>
   </c:if>
		  <c:if test="${ msg==3}">
       	    	<div class="alert alert-success">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Xóa thành công !</strong>
				  	</div>
   </c:if>
	</div>
		