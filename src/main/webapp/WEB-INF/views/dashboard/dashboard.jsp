<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:url value="/dashboard" var="contextPath" />
<c:url value="/dashboard/create" var="createDashboardContextPath" />

<title>Dashboard</title>

</head>

<body>

	<div class="container">
		<h1>
			Your Dashboards<br />
		</h1>
		
		<ul>
			<c:forEach var="dashboard" items="${dashboards }">
				<li>${dashboard.getTitle() } </li>
			</c:forEach>
		</ul>
		
		<form action="${createDashboardContextPath}" method="POST">
			<button type="submit">Create Dashboard</button>
		</form>
		
	</div>

</body>
</html>