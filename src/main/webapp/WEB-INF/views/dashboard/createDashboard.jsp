<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<c:url value="/" var="contextPath" />
<c:url value="/dashboard" var="dashboardPath" />
<c:url value="/dashboard/create" var="dashboardCreatePath" />

<tags:pageTemplate pageTitle="Create Dashboard">

	<div class="container">
		
		<h1>New Dashboard</h1>
		
		<form action="${dashboardCreatePath}" method="POST">
			<div class="form-group">
				<label>Dashboard name:</label>
				<input type="text" name="dashboardName" class="form-control"/>
			</div>
			<div class="form-group">
				<label>User Id:</label>
				<input type="number" name="userId" min="1" max="999" class="form-control"/>
			</div>
			
			<button type="submit" class="btn btn-primary" value="Create">Create</button>
			<br>
			<br>
		</form>
		
		<form action="${dashboardPath}" method="POST">
			<button type="submit" class="btn btn-primary" value="Cancel">Cancel</button>
		</form>
	</div>

</tags:pageTemplate>
