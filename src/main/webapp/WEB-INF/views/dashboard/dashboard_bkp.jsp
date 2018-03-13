<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

<title>Dashboard</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:url value="/" var="contextPath" />
<c:url value="/dashboard/create" var="createDashboardContextPath" />

<link rel="stylesheet" href="${contextPath}resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${contextPath}resources/css/bootstrap-theme.min.css">

</head>
<!-- https://getbootstrap.com/docs/4.0/examples/album/ -->
<body>

	<div class="container">
		<h1>
			Your Dashboards<br />
		</h1>
		
		<ul class="list-group">
			<c:forEach var="dashboard" items="${dashboards }">
				<a href="#" class="list-group-item list-group-item-action">${dashboard.getTitle() } </a>
			</c:forEach>
		</ul>
		
		<form action="${createDashboardContextPath}" method="POST">
			<button type="submit" class="btn btn-primary">Create Dashboard</button>
		</form>
		
	</div>

</body>
</html>