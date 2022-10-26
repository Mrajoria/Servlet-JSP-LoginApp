<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/welcomepage.css" >
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
    	 <div class="main-container" >
    	 
    	<div id="header-div"><h2 stlye="text-align:center" >Welcome to UnAuthorized Food Smuggling System</h2></div> 
    	<div id="header-div"><h2 stlye="text-align:center" >अनधिकृत खाद्यान्न वितरण  प्रणाली में आपका स्वागत हे</h2></div> 
    	 <h3 style="text-align:center" > Welcome ${username}. You are signed in as Registered User</h3> 
		 <br>
		 
		 <div class="nav-bar">
		    <button onclick="window.location='viewtable'">Access Table</button>
		    <button onclick="window.location='viewusertable'">Update Status</button>
		    <form action="Logout">
		    <input type="submit" value ="logout">
		    </form>
		 </div>
		   
         </div>
    </c:if>
    	
    	<c:if test = "${uname == 'admin'}">
    	 <div class="main-container" >
    	 
    	<div id="header-div"><h2 stlye="text-align:center" >Welcome to UnAuthorized Food Smuggling System</h2></div> 
    	<div id="header-div"><h2 stlye="text-align:center" >अनधिकृत खाद्यान्न वितरण  प्रणाली में आपका स्वागत हे</h2></div> 
    	 <h3 style="text-align:center" > Welcome ${username}. You are signed in as Administrator</h3> 
		 <br>
		 
		 <div class="nav-bar">
		    <button onclick="window.location='viewtable'" >View Table</button>
		    <button onclick="window.location='addtable.jsp'">Add Entry</button>
		    <button onclick="window.location='viewusertable'">Update Requests</button>
		    <form action="Logout">
		    <input type="submit" value ="logout">
		    </form>
		 </div>
		   
         </div>
    	 </c:if>
    
    
    
    
	

	
	

</body>
</html>