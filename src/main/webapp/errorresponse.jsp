<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="orange">

 <%
 	if(session.getAttribute("username") != null){
 		response.sendRedirect("welcome.jsp");
 	}
 %>
 <h2 style="text-align:center">Your session is logged out. Please Login Again</h2>
 <h3 style="text-align:center">Click to <a href="login.jsp"> Login here</a></h3>
 
 
 

</body>
</html>