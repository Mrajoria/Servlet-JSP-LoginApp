<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	if(session.getAttribute("username") ==null)
	{
		response.sendRedirect("errorresponse.jsp");
	}
	%>
<h1>This is a secure page. Click to see videos</h1>

</body>
</html>