<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<title>Customer Subscription</title>
</head>
<body>
        <form action="signup" method="post" modelAttribute="usersignup">
			emailAddress: <input type="email" name="emailAddress"/>
			firstName:<input type="text"  name="firstName" />
			lastName:<input type="text"  name="lastName" />
			password:<input type="password"  name="password" />
			<input type="submit" value="Sign Up">
		</form>
</body>
</html>