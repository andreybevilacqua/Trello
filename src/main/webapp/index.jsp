<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/dashboard" var="dashboardContextPath" />

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>

<body>
	<h1>
		Welcome to my own Trello!<br />
	</h1>

	<form action="${dashboardContextPath}" method="post">
		<div>
			<button type="submit">Dashboard</button>
		</div>
	</form>
</body>
</html>