<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/view/includes.jsp" %>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<title>Edit Delete Movie</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<jsp:include page="headerAdmin.jsp" />
<body>
<div class="container">
	<div class="col-lg-12 col-md-12">
    	<h1>Edit or Delete Movie</h1>
	</div>
</div>
<div class="container">
	<div class="col-lg-12 col-md-12">
		<form action="searchMovieForEdit" method="post">
			<h2 class="form-heading">Search movie and then edit or delete</h2>
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
			<th>Edit and Delete Movie</th>
			<%-- <c:forEach items="${movieList}" var="movie"> --%>
			<c:forEach items="${movieInformationList}" var="movieInformation"> 
				<tr>
					<form action="chooseMovieToEditOrDelete" method="post">
						<td style="display:none;"><input name="movieId" type="text" autofocus="true" value="${movieInformation.movieId}"/></td>
						<td><a style="${movieInformation.disabled}" target="_blank" href="/user/log?movieId=${movieInformation.movieId}">${movieInformation.movieTitle}</td>
						<td>${movieInformation.note}</td>
						<td>
							<input type="submit" name="action" value="Edit"/>
							<input type="submit" name="action" value="Delete"/>
						</td>
					</form>
				</tr>
			</c:forEach>
		</table>
		<span style="color:green">${submitEditedMovieMsg}</span>
	</div>
</div>
<div style="${editMovieDivStyle}" class="container">
	<div class="col-md-12">
		<form action="submitEditedMovie" method="post">
			<h3 class="form-heading">Edit Movie</h3>
			<div class="form-group ${error != null ? 'has-error' : ''}">
				<span style="color:green">${submitEditedMovieMsg}</span>
				<span style="color:red">${submitEditedMovieErrMsg}</span>
				<table class="col-lg-12 col-md-12">
					<tr><input name="movieId" value="${movieId}" required="required" type="hidden" class="form-control" placeholder="movieId" autofocus="true"/></tr>
					<tr>
						<td><label>Title</label></td>
						<td><input name="title" value="${title}" required="required" type="text" class="col-lg-6 col-md-6 form-control" placeholder="title" autofocus="true"/></td>
					</tr>
					<tr>
						<td><label>Year</label></td>
						<td><input name="year" value="${year}" required="required" type="number" min="1930" class="form-control" placeholder="year" autofocus="true"/></td>
					</tr>
					<tr>
						<td><label>Actors</label></td>
						<td><input name="actors" value="${actors}" required="required" type="text" class="form-control" placeholder="actor(s)" autofocus="true"/></td>
					</tr>
					<tr>
						<td><label>Director</label></td>
						<td><input name="director" value="${director}" required="required" type="text" class="form-control" placeholder="director" autofocus="true"/></td>
					</tr>
					<tr>
						<td><label>Studio</label></td>
						<td><input name="studio" value="${studio}" required="required" type="text" class="form-control" placeholder="studio" autofocus="true"/></td>
					</tr>
					<tr>
						<td><label>Synopsis</label></td>
						<td><input name="synopsis" value="${synopsis}" required="required" type="text" class="form-control" placeholder="synopsis" autofocus="true"/></td>
					</tr>
					<tr>
						<td><label>Country</label></td>
						<td><input name="country" value="${country}" required="required" type="text" class="form-control" placeholder="country" autofocus="true"/></td>
					</tr>
					<tr>
						<td><label>Image</label></td>
						<td><input name="image" value="${image}" required="required" type="text" class="form-control" placeholder="image URL" autofocus="true"/></td>
					</tr>
					<tr>
						<td><label>Movie</label></td>
						<td><input name="movie" value="${movie}" required="required" type="text" class="form-control" placeholder="movie URL" autofocus="true"/></td>
					</tr>
					<tr>
						<td><label>Genre</label></td>
						<td>
							<select name="genre" value="${genre}" required="required" type="text" class="form-control" autofocus="true">
								<option value="" disabled selected>Genre</option>
								<option value="HORROR">HORROR</option>
								<option value="ACTION">ACTION</option>
								<option value="ROMANCE">ROMANCE</option>
								<option value="FICTION">FICTION</option>
								<option value="COMEDY">COMEDY</option>
								<option value="DRAMA">DRAMA</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><label>MPAA Rating</label></td>
						<td>
							<select name="mpaaRating" value="${mpaaRating}" required="required" type="text" class="form-control" autofocus="true">
								<option value="" disabled selected>MPAA Rating</option>
								<option value="G">G</option>
								<option value="PG">PG</option>
								<option value="PG13">PG-13</option>
								<option value="R">R</option>
								<option value="NC17">NC-17</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><label>Availability</label></td>
						<td>
							<select name="availability" value="${availability}" required="required" type="text" class="form-control" autofocus="true">
								<option value="" disabled selected>Movie Availability</option>
								<option value="FREE">FREE</option>
								<option value="SUBSCRIPTION_ONLY">SUBSCRIPTION_ONLY</option>
								<option value="PAY_PER_VIEW_ONLY">PAY_PER_VIEW_ONLY</option>
								<option value="PAID">PAID</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan='2'><button class="btn btn-lg btn-primary btn-block" type="submit">Edit Movie</button></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</div>
</body>
</html>