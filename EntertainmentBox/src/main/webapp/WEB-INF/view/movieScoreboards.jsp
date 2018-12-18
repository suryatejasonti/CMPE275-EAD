<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/view/includes.jsp" %>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<title>Movie Scoreboards</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<jsp:include page="header.jsp" />
<body>
<div class="container">
	<div class="col-lg-12 col-md-12">
    	<h1>Movie Scoreboards</h1>
	</div>
</div>
<div class="container">
	<div class="col-md-6">
		<form action="viewHighlyRatedMovieCustomer" method="post">
			<h2 class="form-heading">View highly rated movies</h2>
			<div class="form-group ${error != null ? 'has-error' : ''}">
				<span style="color:green">${highlyRatedMovieMsg}</span>
				<span style="color:red">${highlyRatedMovieErrMsg}</span>
				<table>
					<tr>
						<td>Duration in days</td>
						<td><input name="duration" required="required" type="number" class="form-control" autofocus="true"/></td>
					</tr>
					<tr>
						<td colspan='2'><button class="btn btn-lg btn-primary btn-block" type="submit">View Highly rated movies</button></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</div>
<br>
<div class="container">
	<div class="col-lg-12 col-md-12">
		<table class="table table-bordered table-striped table-hover col-md-1" border="1">
			<th style="display:none;">Movie ID</th>
			<th>Movie Title (Click to watch)</th>
			<th>Note</th>
			<th>Rating</th>
			<%-- <c:forEach items="${movieList}" var="movie"> --%>
			<c:forEach items="${movieInformationList}" var="movieInformation"> 
				<tr>
					<form action="submitRow" method="post">
						<td style="display:none;"><input name="movieId" type="text" autofocus="true" value="${movieInformation.movieId}"/></td>
						<td><a style="${movieInformation.disabled}" target="_blank" href="/user/watchMovie?movieId=${movieInformation.movieId}">${movieInformation.movieTitle}</td>
						<td>${movieInformation.note} <input style="${movieInformation.enable}" name="action" type="submit" value="Subscribe for this movie"/></td>
						<td><input name="stars" value="${movieInformation.stars}" style="background-color: #E0E0E0;" type="text" readonly="readonly"/></td>
					</form>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
</body>
</html>