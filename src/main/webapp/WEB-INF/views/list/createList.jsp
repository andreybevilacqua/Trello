<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

<title>Create List</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:url value="/" var="contextPath" />
<c:url value="/list/create" var="listPath" />

<link rel="stylesheet" href="${contextPath}resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${contextPath}resources/css/bootstrap-theme.min.css">

</head>
<body>

	<div class="container">
	
		<h1>New List</h1>
	
		<form action="${listPath} " method="POST">
		
			<div class="form-group">
				<label>List name:</label>
				<input type="text" name="listName" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Dashboard Id:</label>
				<input type="number" name="dashboardId" min="1" max="999" class="form-control"/>
			</div>
			
			<button type="submit" class="btn btn-primary" value="Create">Create</button>
		
		</form>

	</div>
	
</body>
</html>