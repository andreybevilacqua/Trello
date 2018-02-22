<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Dashboard</title>
</head>
<body>
	<form action="dashboard" method="POST">
		Dashboard Name: <input type="text" name="dashboardName" />
		User Id: <input type="text" name="userId" />
		<input type="submit" value="Create" />
	</form>
</body>
</html>