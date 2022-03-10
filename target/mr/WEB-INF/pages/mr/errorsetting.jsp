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
					  <strong>Lỗi!</strong> Không Import được !
				  	</div>
   </c:if>
      <c:if test="${ error==2}">
       	    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> Không được phép xóa do có dữ liệu liên kết ${msg}
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
					  <strong>Lỗi!</strong> Trùng tên hoặc trùng thời gian
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
     <c:if test="${ error==8}">
       	    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> Không tồn tại CanonThai - Part Name của  - ${msg}. Không thực hiện lệnh in
				  	</div>
   </c:if>
  <c:if test="${ error==7}">
       	    	<div class="alert alert-success">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Print thành công !</strong>
				  	</div>
   </c:if>
   <c:if test="${ error==10}">
       	    <div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> Không tồn tại Item code - ${mg}
				  	</div>
   </c:if>
   <c:if test="${ msg==1}">
       	    	<div class="alert alert-success">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Successful !</strong>
				  	</div>
   </c:if>
	
		
	</div>
		