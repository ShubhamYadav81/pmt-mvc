<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>CodePen - Sign up / Login Form</title>
  <link rel="stylesheet" href="./style.css">

</head>

<body>
	<div class="main">  	
		
		<input type="checkbox" id="chk" aria-hidden="true">
			
		
			<div class="signup">
				<form>
					<label for="chk" aria-hidden="true">Sign up</label>
					<input type="text" name="txt" placeholder="User name" required="">
					<input type="email" name="email" placeholder="Email" required="">
					<input type="password" name="pswd" placeholder="Password" required="">
					
					<button>Sign up</button>
				</form>
			</div>
			<div class="login">
				
					<form name="login"  action="/" method="post">
						<label for="chk" aria-hidden="true">Login</label>
						<input type="text" name="userName" placeholder="UserName" required="">
						<input type="password" name="password" placeholder="Password" required="">
						<button>Login</button>
						
					</form>
			</div>
			

		
	</div>
</body>
</html>
<!-- partial -->
  
</body>
</html>
