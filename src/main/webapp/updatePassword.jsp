<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>CodePen - Sign up / Login Form</title>
  <link rel="stylesheet" href="./style.css">
<link rel="stylesheet" type="text/css" href="slide navbar style.css">
<link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
</head>
<body>

<div class="main">  	
		
		
			
		
			<div class="signup">
					<label for="chk" aria-hidden="true">Update Password</label>

				<form name="updatePassword" action="UpdatePasswordByUserName">
					
					<input type="text" name="user" placeholder="Account Name" required="">
					<input type="password" name="password" placeholder="New Password" required="">
					<button>Updated Password</button>
				</form>
			</div>
			

		
	</div>

</body>
</html>
