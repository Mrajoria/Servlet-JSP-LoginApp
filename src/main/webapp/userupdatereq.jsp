<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Requests by Users</title>
</head>
<body>
	<%
	if (session.getAttribute("username") == null) {
		response.sendRedirect("errorresponse.jsp");
	}
	%>

	<c:set var="uname" value="${username}" />
	<c:if test="${uname == 'pragya720'}">

		<h3 style="text-align: center; margin-top: 2%">Your requst is
			sent to Database Admin.</h3>
		<div style="background-color: darkgrey; text-align: center">
			<h5>Pending Add Requests shown below</h5>
		</div>

		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Name</th>
					<th>Quantity</th>
					<th>Location</th>
					<th>Status</th>
					<th>User Action</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="item" items="${usertableitems}">

					<c:if test="${item.actioncode=='pending'}">
						<tr>
							<td><c:out value="${item.name}" /></td>
							<td><c:out value="${item.quantity}" /></td>
							<td><c:out value="${item.location}" /></td>
							<td><c:out value="${item.status}" /></td>
							<td><a href="">Delete</a></td>
						</tr>
					</c:if>
				</c:forEach>

			</tbody>

		</table>
		
		<div style="background-color: orange; text-align: center">
			<h5>Delete Requests from User are shown below</h5>
		</div>

		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Name</th>
					<th>Quantity</th>
					<th>Location</th>
					<th>Status</th>
					<th>User Action</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="item" items="${usertableitems}">

					<c:if test="${item.actioncode=='userdelete'}">
						<tr>
							<td><c:out value="${item.name}" /></td>
							<td><c:out value="${item.quantity}" /></td>
							<td><c:out value="${item.location}" /></td>
							<td><c:out value="${item.status}" /></td>
							<td><a href="">Delete</a></td>
						</tr>
					</c:if>
				</c:forEach>

			</tbody>

		</table>
		
		
		
		
		
		<div style="background-color: darkgrey; text-align: center">
			<h5>Invalidated Data by Admin is shown below</h5>
		</div>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Name</th>
					<th>Quantity</th>
					<th>Location</th>
					<th>Status</th>
					<th>User Action</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="item" items="${usertableitems}">
					<c:if test="${item.actioncode=='invalid'}">
						<tr>
							<td><c:out value="${item.name}" /></td>
							<td><c:out value="${item.quantity}" /></td>
							<td><c:out value="${item.location}" /></td>
							<td><c:out value="${item.status}" /></td>
							<td><a href="">Delete</a></td>
						</tr>
					</c:if>
				</c:forEach>

			</tbody>

		</table>

		<div style="background-color: green; text-align: center">
			<h5>Approved Data by Admin is shown below</h5>
		</div>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Name</th>
					<th>Quantity</th>
					<th>Location</th>
					<th>Status</th>
			
				</tr>
			</thead>
			<tbody>

				<c:forEach var="item" items="${usertableitems}">
					<c:if test="${item.actioncode=='approved'}">
						<tr>
							<td><c:out value="${item.name}" /></td>
							<td><c:out value="${item.quantity}" /></td>
							<td><c:out value="${item.location}" /></td>
							<td><c:out value="${item.status}" /></td>
						
						</tr>
					</c:if>
				</c:forEach>

			</tbody>

		</table>

	</c:if>
	
	

	<c:if test="${uname == 'admin'}">


		<h3 style="text-align: center; margin-top: 2%">Update Request
			Page.</h3>
		<div style="background-color: darkgrey; text-align: center">
			<h5>Pending Data is shown below</h5>
		</div>

		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Name</th>
					<th>Quantity</th>
					<th>Location</th>
					<th>Status</th>
					<th>Admin Action</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="item" items="${usertableitems}">
					<c:if test="${item.actioncode=='pending'}">
						<tr>
							<td><c:out value="${item.name}" /></td>
							<td><c:out value="${item.quantity}" /></td>
							<td><c:out value="${item.location}" /></td>
							<td><c:out value="${item.status}" /></td>
							<td><a href="approvereq?id=<c:out value='${item.name}' />">Approve</a>  &nbsp;&nbsp; <a href="discardreq?id=<c:out value='${item.name}' />">Discard</a> </td>
						</tr>

					</c:if>
				</c:forEach>

			</tbody>

		</table>

		<div
			style="display: flex; flex-direction: row; justify-content: center">
			<c:set var="varA" value="all" />
			<button onclick="window.location='approvereq?id=<c:out value='${varA}' />'" >Merge All</button>
			<button onclick="window.location='discardreq?id=<c:out value='${varA}' />'" >Discard All</button>
		</div>
		
		<br>
		<br>
		
		<div style="background-color: orange; text-align: center">
			<h5>Delete Requests are shown below</h5>
		</div>

		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Name</th>
					<th>Quantity</th>
					<th>Location</th>
					<th>Status</th>
					<th>Admin Action</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="item" items="${usertableitems}">
					<c:if test="${item.actioncode=='userdelete'}">
						<tr>
							<td><c:out value="${item.name}" /></td>
							<td><c:out value="${item.quantity}" /></td>
							<td><c:out value="${item.location}" /></td>
							<td><c:out value="${item.status}" /></td>
							<td><a href="UserDeleteToAdmin?uitemname=${item.name}">Delete Data</a> </td>
						</tr>

					</c:if>
				</c:forEach>

			</tbody>

		</table>
		
		<div
			style="display: flex; flex-direction: row; justify-content: center">
			
			<button onclick="window.location='UserDeleteToAdmin?&uitemname=all'" >Delete All</button>
		</div>
		

	</c:if>






</body>
</html>