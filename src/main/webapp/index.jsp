<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:url value="/Trello" var="contextPath" />

<title>Create Dashboard</title>
</head>

<body>
	<h1>
		Bem vindo ao meu Trello!<br />
	</h1>

	<form action="/Trello/dashboard" method="POST" class="">
		<div class="">
			<button type="submit">Dashboard</button>
		</div>
	</form>
</body>
</html>