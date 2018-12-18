<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/view/includes.jsp" %>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<title>Customer</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<jsp:include page="header.jsp" />
<body>
<div class="container">
	<div class="col-md-6">
		<form action="startSubscription" method="post">
			<h2 class="form-heading">Start Subscription</h2>
			<div class="form-group ${error != null ? 'has-error' : ''}">
				<span style="color:green">${subscriptionMsg}</span>
				<span style="color:red">${subscriptionErrMsg}</span>
				<table>
					<tr>
						<td>Number of Months</td>
						<td><input name="noOfMonths" type="number" required="required" class="form-control" autofocus="true"/></td>
					</tr>
					<tr>
						<td>Price per month($)</td>
						<td><input name="price" value="10" style="background-color: #E0E0E0;" type="text" readonly="readonly" class="form-control"/></td>
					</tr>
					<tr>
						<td colspan='2'><button class="btn btn-lg btn-primary btn-block" type="submit">Subscribe</button></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</div>
<div class="container">
	<div class="col-lg-12 col-md-12">
		<form action="searchMovie" method="post">
			<h2 class="form-heading">Movie Search and Filter</h2>
			<div class="form-group ${error != null ? 'has-error' : ''}">
				<span style="color:green">${searchMovieMsg}</span>
				<span style="color:red">${searchMovieErrMsg}</span>
				<table class="col-lg-12 col-md-12">
					<tr>
						<td><input name="keywords" type="text" class="form-control" placeholder="Keywords" autofocus="true"/></td>
						<td><input name="year" type="text" class="form-control" placeholder="year" autofocus="true"/></td>
						<td><input name="actors" type="text" class="form-control" placeholder="actor(s)" autofocus="true"/></td>
						<td><input name="director" type="text" class="form-control" placeholder="director" autofocus="true"/></td>
						<td>
							<select name="genre" type="text" class="form-control" size="3" multiple autofocus="true">
								<option value="" disabled selected>Genre</option>
								<option value="HORROR">HORROR</option>
								<option value="ACTION">ACTION</option>
								<option value="ROMANCE">ROMANCE</option>
								<option value="FICTION">FICTION</option>
								<option value="COMEDY">COMEDY</option>
								<option value="DRAMA">DRAMA</option>
							</select>
						</td>
						<td>
							<select name="mpaaRating" type="text" class="form-control" size="3" multiple autofocus="true">
								<option value="" disabled selected>MPAA Rating</option>
								<option value="G">G</option>
								<option value="PG">PG</option>
								<option value="PG13">PG-13</option>
								<option value="R">R</option>
								<option value="NC17">NC-17</option>
							</select>
						</td>
						<td>
							<select name="numberOfStars" type="text" class="form-control" autofocus="true">
								<option value="" disabled selected>Min. Stars</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan='7'><button class="btn btn-lg btn-primary btn-block" type="submit">Search Movie</button></td>
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
			<th>Submit Rating</th>
			<%-- <c:forEach items="${movieList}" var="movie"> --%>
			<c:forEach items="${movieInformationList}" var="movieInformation"> 
				<tr>
					<form action="submitRow" method="post">
						<td style="display:none;"><input name="movieId" type="text" autofocus="true" value="${movieInformation.movieId}"/></td>
						<td><a style="${movieInformation.disabled}" target="_blank" href="/user/watchMovie?movieId=${movieInformation.movieId}">${movieInformation.movieTitle}</td>
						<td>${movieInformation.note} <input style="${movieInformation.enable}" name="action" type="submit" value="Subscribe for this movie"/></td>
						<td>
							<select name="submitStars" type="text" class="" autofocus="true">
								<option value="" disabled selected>Stars</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>
							<input type="submit" name="action" value="Rate"/>
						</td>
					</form>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
<div class="container">
	<div class="col-lg-12 col-md-12">
    	<h1><a style="font-size:100%" href="/user/movieScoreboards">Movie Scoreboards</a></h1>
    </div>
</div>
</body>
</html>

