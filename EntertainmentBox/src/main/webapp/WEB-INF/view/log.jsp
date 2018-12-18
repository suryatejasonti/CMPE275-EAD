<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/view/includes.jsp" %>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<title>Edit Delete Movie</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<jsp:include page="header.jsp" />
<body>
<div class="container">
    <div class="col-md-12">
       	<h1><a href="${movieLink}">Watch movie</a></h1>
       	<h1><a href="/user/customer">Go Back</a></h1>
    </div>
</div>
<div class="container">
	<div class="col-md-12">
			<h3 class="form-heading">Movie information</h3>
			<div class="form-group ${error != null ? 'has-error' : ''}">
				<span style="color:green">${submitEditedMovieMsg}</span>
				<span style="color:red">${submitEditedMovieErrMsg}</span>
				<table class="col-lg-12 col-md-12">
					<tr>
						<td><label>Title</label></td>
						<td><input name="title" value="${title}" readonly type="text" class="col-lg-6 col-md-6 form-control" placeholder="title" autofocus="true"/></td>
					</tr>
					<tr>
						<td><label>Year</label></td>
						<td><input name="year" value="${year}" readonly type="number" min="1930" class="form-control" placeholder="year" autofocus="true"/></td>
					</tr>
					<tr>
						<td><label>Actors</label></td>
						<td><input name="actors" value="${actors}" readonly type="text" class="form-control" placeholder="actor(s)" autofocus="true"/></td>
					</tr>
					<tr>
						<td><label>Director</label></td>
						<td><input name="director" value="${director}" readonly type="text" class="form-control" placeholder="director" autofocus="true"/></td>
					</tr>
					<tr>
						<td><label>Studio</label></td>
						<td><input name="studio" value="${studio}" readonly type="text" class="form-control" placeholder="studio" autofocus="true"/></td>
					</tr>
					<tr>
						<td><label>Synopsis</label></td>
						<td><input name="synopsis" value="${synopsis}" readonly type="text" class="form-control" placeholder="synopsis" autofocus="true"/></td>
					</tr>
					<tr>
						<td><label>Country</label></td>
						<td><input name="country" value="${country}" readonly type="text" class="form-control" placeholder="country" autofocus="true"/></td>
					</tr>
				</table>
			</div>
	</div>
</div>
</body>
</html>