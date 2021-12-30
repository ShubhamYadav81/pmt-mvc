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

<center>
	<div >  			
			<h1 style="color:white;">All Account Details</h1>
			<table border=1 >
			<tr style="color:blue;">
			 <th>Group Id </th>
			 <th>Group Name</th>
			 <th>Account Name</th>
			 <th>Password</th>
			 <th>Account Url</th>
			</tr>
			
			 <c:forEach items="${groups}" var="group">
			
			
			 <c:if test="${group.getAccount().size() != 0}">
			        <c:forEach items="${group.getAccount()}" var="account">
			            <tr class="table-info" style="color:white;">
			                <td><c:out value="${group.getGroupId()}"></c:out></td>
			                <td><c:out value="${group.getGroupName()}"></c:out></td>
			                <td><c:out value="${account.getAccountName()}"></c:out></td>
			                <td><c:out value="${account.getAccountPassword()}"></c:out></td>
			                <td><c:out value="${account.getAccountUrl()}"></c:out></td>
			            </tr>
			        </c:forEach>
			    </c:if>
			</c:forEach>
			
			</table>
			<a href="sucess.jsp"> Home Page</h2>
</center>
			

		
	</div>


</body>
</html>
