<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/view/includes.jsp" %>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<title>Customer Subscription</title>
</head>
<jsp:include page="header.jsp" />
<body>
	<form action="subscribe" method="post">
		<input type="hidden" name="emailaddress" value="${emailAddress}"/><br>
		Number of Months: <input type="text" name="noOfMonths"/><br>
		Price per month($):<input style="background-color: #E0E0E0;" type="text" readonly="readonly" name="price" value="10"/><br>
		<input type="submit" value="Pay & Subscribe">
	</form>
	<br><br>
	<form action="searchMovie">
	    <input type="hidden" name="emailaddress" value="${emailAddress}"/><br>
		<input style="width:1000px" type"text" name="searchText"></input><br>
		<input type="submit" value="Search Movie">
	</form>
	<h1>If after clicking the link ,you can not watch the movie,it means you need to subscribe to watch the movie!</h1>
	<br>
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
			<table class="w-25 table table-bordered table-striped table-hover col-md-1" border="1">
				<th>Movie Title</th>
				<th>Movie Link</th>
					<%-- <c:forEach items="${movieList}" var="movie"> --%>
					<c:forEach items="${movieInformationList}" var="movieInformation"> 
					<tr>
						<td>${movieInformation.movieTitle}</td>
						<td><a href="${movieInformation.movieLink}">link</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
</body>
</html>

