<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:url value="/list/create" var="contextPath" />

<title>Create List</title>
</head>
<body>
	<form action="${contextPath} " method="POST">
		List Name: <input type="text" name="listName" />
		Dashboard Id: <input type="number" name="userId" min="1" max="999" />
		<input type="submit" value="Create" />
	</form>

</body>
</html>