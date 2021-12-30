<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>CodePen - Sign up / Login Form</title>
  <link rel="stylesheet" href="./style.css">
</head>
<body>


	<div > 
	<center> 			
			<h1 style="color:white;">PMT Application</h1>
			<table border=1 >
			<tr style="color:blue;">
			 <th>Account Id </th>
			 <th>Account Name</th>
			 <th>Account Url</th>
			 <th>Password</th>
			</tr>
			
			<tr style="color:white;" >
 <td> <c:out value="${user.getAccountId()}" ></c:out> </td>
 <td> <c:out value="${user.getAccountName()}" ></c:out> </td>
 <td> <c:out value="${user.getAccountUrl()}" ></c:out> </td>
 <td> <c:out value="${user.getAccountPassword()}" ></c:out> </td>
</tr>
			
			</table>
		<h2>	<a href="sucess.jsp"> Home Page</h2>
</center>
			

		
	</div>

</body>
</html>