<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US" scope="session"/>
<ul class="nav nav-left-sidebar">
<sec:authorize access="hasRole('ROLE_ADMIN')" >
	<li>
	  <a href="../">
	    <i class="fa fa-gear"></i>
	    <span class="glyphicon glyphicon-home"></span> <spring:message code="label.menuhome" text="HOME" />
	  </a>
	  </li>
	  </sec:authorize>
	  <sec:authorize access="!hasRole('ROLE_ADMIN')" >
	  <li>
	  <a href="../yeucauNVL/">
	    <i class="fa fa-gear"></i>
	    <span class="glyphicon glyphicon-home"></span> <spring:message code="label.menuhome" text="HOME" />
	  </a>
	  </li>
	  </sec:authorize>
	  <li>
	  <a href="changepass">
	    <i class="fa fa-gear"></i>
	    <span class="glyphicon glyphicon-lock"></span> Thay đổi password
	  </a>
	  </li>
	  <sec:authorize access="hasRole('ROLE_ADMIN')" >
    <li><a href="controlPanel">
      <i class="fa fa-university"></i>
      <span class="glyphicon glyphicon-file"></span> <spring:message code="label.profile" text="Profile" />
      </a>
    </li>
    <li><a href="user">
      <i class="fa fa-university"></i>
      <span class="glyphicon glyphicon-user"></span> <spring:message code="label.user" text="User" />
      </a>
    </li>
    <li><a href="staff">
      <i class="fa fa-university"></i>
      <span class="glyphicon glyphicon-user"></span> <spring:message code="label.staff" text="Staff" />
      </a>
    </li>
    </sec:authorize>
</ul>


<style>
#left-sidebar{
	width:200px;
	display:block;
	position:absolute;
	left:1px;
	z-index:999;
	border-right: 1px solid #969696;
}

</style>