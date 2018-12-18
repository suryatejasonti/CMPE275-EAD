<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/view/includes.jsp" %>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<title>Admin</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<jsp:include page="headerAdmin.jsp" />
<body>
<div class="container">
	<div class="col-md-12">
		<form action="addMovie" method="post">
			<h2 class="form-heading">Add Movie</h2>
			<div class="form-group ${error != null ? 'has-error' : ''}">
				<span style="color:green">${addMovieMsg}</span>
				<span style="color:red">${addMovieErrMsg}</span>
				<table class="col-lg-12 col-md-12">
					<tr><input name="title" required="required" type="text" class="form-control" placeholder="title" autofocus="true"/></tr>
					<tr><input name="year" required="required" type="number" min="1930" class="form-control" placeholder="year" autofocus="true"/></tr>
					<tr><input name="actors" required="required" type="text" class="form-control" placeholder="actor(s)" autofocus="true"/></tr>
					<tr><input name="director" required="required" type="text" class="form-control" placeholder="director" autofocus="true"/></tr>
					<tr><input name="studio" required="required" type="text" class="form-control" placeholder="studio" autofocus="true"/></tr>
					<tr><input name="synopsis" required="required" type="text" class="form-control" placeholder="synopsis" autofocus="true"/></tr>
					<tr><input name="country" required="required" type="text" class="form-control" placeholder="country" autofocus="true"/></tr>
					<tr><input name="image" required="required" type="text" class="form-control" placeholder="image URL" autofocus="true"/></tr>
					<tr><input name="movie" required="required" type="text" class="form-control" placeholder="movie URL" autofocus="true"/></tr>
					<tr>
						<select name="genre" required="required" type="text" class="form-control" autofocus="true">
							<option value="" disabled selected>Genre</option>
							<option value="HORROR">HORROR</option>
							<option value="ACTION">ACTION</option>
							<option value="ROMANCE">ROMANCE</option>
							<option value="FICTION">FICTION</option>
							<option value="COMEDY">COMEDY</option>
							<option value="DRAMA">DRAMA</option>
						</select>
					</tr>
					<tr>
						<select name="mpaaRating" required="required" type="text" class="form-control" autofocus="true">
							<option value="" disabled selected>MPAA Rating</option>
							<option value="G">G</option>
							<option value="PG">PG</option>
							<option value="PG13">PG-13</option>
							<option value="R">R</option>
							<option value="NC17">NC-17</option>
						</select>
					</tr>
					<tr>
						<select name="availability" required="required" type="text" class="form-control" autofocus="true">
							<option value="" disabled selected>Movie Availability</option>
							<option value="FREE">FREE</option>
							<option value="SUBSCRIPTION_ONLY">SUBSCRIPTION_ONLY</option>
							<option value="PAY_PER_VIEW_ONLY">PAY_PER_VIEW_ONLY</option>
							<option value="PAID">PAID</option>
						</select>
					</tr>
					<tr>
						<button class="btn btn-lg btn-primary btn-block" type="submit">Add Movie</button>
					</tr>
				</table>
			</div>
		</form>
	</div>
</div>
<div class="container">
	<div class="col-lg-12 col-md-12">
		<form action="searchMovieForAdmin" method="post">
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
						<td><a style="${movieInformation.disabled}" target="_blank" href="/user/log?movieId=${movieInformation.movieId}">${movieInformation.movieTitle}</td>
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
    	<h1><a style="font-size:100%" href="/user/editMovie">Edit or Delete movies</a></h1>
    </div>
</div>
</body>
</html>

