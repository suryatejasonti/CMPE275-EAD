<%-- <!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<title>Customer Subscription</title>
</head>
<body>
        <form action="login" method="post" modelAttribute="userlogin">
			emailAddress: <input type="email" name="emailAddress"/>
			password:<input type="password"  name="password" />
			<input type="submit" value="Login">
		</form>
</body>
</html> --%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	    <meta name="description" content="">
	    <meta name="author" content="">
	
	    <title>Log in with your account</title>
	
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	</head>
	
	<body>
	
		<div class="container">
		<div class="col-md-6">
		    <form method="POST" action="login" class="form-signin">
		        <h2 class="form-heading">Log in</h2>
		
		        <div class="form-group ${error != null ? 'has-error' : ''}">
		            <span>${msg}</span>
		            <input name="username" type="text" class="form-control" placeholder="Email address"
		                   autofocus="true"/>
		            <input name="password" type="password" class="form-control" placeholder="Password"/>
		            <span>${errorMsg}</span>
		
		            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
		        </div>
		
		    </form>
		</div>
		</div>
		<!-- /container -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>