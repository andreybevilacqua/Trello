<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:url value="/list" var="contextPath" />
<c:url value="/list/create" var="createListContextPath" />

<title>List</title>

</head>

<body>

	<div class="container">
		<h1>
			Your Lists<br />
		</h1>

		<ul>
			<c:forEach var="list" items="${lists }">
				<li>${list.getTitle() }</li>
			</c:forEach>
		</ul>

		<form action="${createListContextPath}" method="POST">
			<button type="submit">Create List</button>
		</form>

	</div>

</body>
</html>