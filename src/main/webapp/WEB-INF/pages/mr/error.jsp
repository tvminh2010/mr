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
					  <strong>Lỗi!</strong> 
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
					  <strong>Lỗi!</strong>
				  	</div>
   </c:if>
       <c:if test="${ error==5}">
       	    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> Không tìm thấy dữ liệu 
				  	</div>
   </c:if>
     <c:if test="${ error==7}">
       	    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> Đã được giao
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
					  <strong>Lỗi!</strong> WorkOrder ${msg}  đã đóng !
				  	</div>
   </c:if>
     <c:if test="${ error==11}">
       	    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> Không tồn tại Model vừa nhập
				  	</div>
   </c:if>
     <c:if test="${ error==12}">
       	    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> Không tồn tại Line p nhập
				  	</div>
   </c:if>
        <c:if test="${ error==13}">
       	    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> Trùng tên hoặc trùng thời gian
				  	</div>
   </c:if>
      <c:if test="${ error==14}">
       	    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> WorkOrder này đang mở!
				  	</div>
   </c:if>
       <c:if test="${ error==15}">
       	    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> WorkOrder này đang đóng hoặc mới tạo!
				  	</div>
   </c:if>
     <c:if test="${ error==16}">
       	    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> WorkOrder này mới tạo!
				  	</div>
   </c:if>
       <c:if test="${ error==17}">
       	    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> WorkOrder này không tồn tại!
				  	</div>
   </c:if>
     <c:if test="${ error==18}">
       	    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong> ở dòng có tên WorkOrder: ${ms}  
				  	</div>
   </c:if>
     <c:if test="${ error==19}">
       	    	<div class="alert alert-danger">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Lỗi!</strong>Tên file Excel không đúng định dạng - [Tênphiên_RequestBS hoặc Tênphiên_RequestSetup]
				  	</div>
   </c:if>
   <c:if test="${ msg==1}">
       	    	<div class="alert alert-success">
					    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Thành công !</strong>
				  	</div>
   </c:if>
	
		
	</div>
		