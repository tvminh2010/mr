<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url value="/res/images/logo.png" var="logo" />

<style>
	.table-header {
				position:fixed;
				top:101px;
				height: 300px;
				background:#d1e0e0;
			}
			
			.table-tbody {
				overflow: scroll;
			}
			
			.rcorners1 {
				
				-moz-border-radius: 3px;
				-webkit-border-radius: 3px;
			}
			.rcorners2 {
				border-radius: 2px;
			}
			.headerright{
			float:right;
			font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
			
			}
			.headerright p{
			display:inline;
			}
			.headerright a{
	margin-right:10px;
	text-decoration: none;
}
			.inputtext{
			width:100px;
			}
			.pagination{
			  float:right;
			   margin: -10px 0px 0px 0px !important;
			}
</style>
	<div class="headerright">
			<a id="logout" href="./admin/controlPanel"><span class="glyphicon glyphicon-user"></span> ${pageContext.request.userPrincipal.name} </a>
			<a href="<c:url value="/logout" />"><span class="glyphicon glyphicon-log-out"></span>Logout</a>
		<p id="txt"></p>
		<p id="dat"></p>
		<script>
			var d = new Date();
			var options = {
				weekday : 'long',
				year : 'numeric',
				month : 'long',
				day : 'numeric'
			};
			document.getElementById("dat").innerHTML = d.toLocaleDateString(
					'vi-VN', options);
		</script>
	</div>
  <script type="text/javascript">

  function sm2(url, id, m){
		var f = document.getElementById("dform");
	   	 f.action = url;
	   	 var y = document.getElementById("key");
	   	 y.name = "del";
	   	 y.value = id;
	   	$("#modal-title").text("Confirm delete "+m);
		$("#modal-delete").modal();	
	   }
 	
 	</script>

<style>
.content{
	position:absolute;
	padding-left:210px;
}

</style>

  <!-- Modal 2 -->
  <div class="modal fade" id="modal-delete" role="dialog">
    <div class="modal-dialog">    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" id="modal-title2"></h4>
        </div>
        <div class="modal-body">
          <p id="modal-text"><spring:message code="label.anwerdelete" text="This data will be permanently deleted and cannot be recovered. Are you sure?" /></p>
        </div>
        <div class="modal-footer">
       	  <form id="dform" method="get">
       	  	<input type="text" id="key" name="del" style="display:none">
	        <input type="submit" class="btn btn-danger" value="Delete">
	        <button type="button" class="btn btn-primary" data-dismiss="modal"><spring:message code="label.cancel" text="Cancel" /></button>
          </form>
        </div>
      </div>      
    </div>
  </div><!-- End Modal -->
  