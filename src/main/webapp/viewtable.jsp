<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="javax.servlet.http.HttpServlet,javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession, com.model.Item, java.util.List"%>              


<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">    
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Table</title>
</head>
<body>
		
	 
		<%
		if(session.getAttribute("username") ==null)
		{
			response.sendRedirect("errorresponse.jsp");
		}
		%>
		
	 	<c:set var="uname" value="${username}" />
		<c:if test = "${uname == 'pragya720'}">
		 
		 <h3  style="text-align:center; margin-top:2%" > You are in Viewing Mode. Your Update Requests will be submitted to Database Administrator</h3>
		 <div style="background-color:darkgrey; text-align:center"><h5>Click to Submit <a href="addtable.jsp">Add Request</a> </h5></div> 
		
	    </c:if>
	    
		<table class="table table-bordered">
				<thead>
					<tr>
						<th>Name</th>
						<th>Quantity</th>
						<th>Location</th>
						<th>Status</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="item" items="${itemresult}">

						<tr>
							<td><c:out value="${item.name}" /></td>
							<td><c:out value="${item.quantity}" /></td>
							<td><c:out value="${item.location}" /></td>
							<td><c:out value="${item.status}" /></td>
							<td> <a href="">Edit</a> / <a href="UserDelete?name=${item.name}&quantity=${item.quantity}&location=${item.location}&status=${item.status}">Delete</a> </td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
	
</body>
</html>