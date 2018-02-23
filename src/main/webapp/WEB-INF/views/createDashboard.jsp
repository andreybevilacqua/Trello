<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:url value="/Trello/dashboard" var="contextPath" />

<title>Create Dashboard</title>
</head>
<body>
	<form action="${contextPath} " method="POST">
		Dashboard Name: <input type="text" name="dashboardName" />
		User Id: <input type="text" name="userId" />
		<input type="submit" value="Create" />
	</form>
</body>
</html>