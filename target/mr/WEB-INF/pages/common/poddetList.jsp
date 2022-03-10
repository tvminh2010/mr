<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

   <div align="center">
          <h1>Department List</h1>
          <table border="1">
              <th>No</th>
              <th>Dept No</th>
              <th>Dept Name</th>
              <th>Location</th>
              
              <c:forEach var="dept" items="${poddets}" varStatus="status">
              <tr>
                
                  
                  <td>${dept.id}</td>
                  <td>${dept.name}</td>                            
              </tr>
              </c:forEach>                
          </table>
      </div>
