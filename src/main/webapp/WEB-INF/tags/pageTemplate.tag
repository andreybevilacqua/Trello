<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="pageTitle" required="true"%>
<%@ attribute name="bodyClass" required="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

	<c:url value="/" var="contextPath" />
	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
	
	<title>${pageTitle }</title>
	
	<link rel="stylesheet" href="${contextPath}resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="${contextPath}resources/css/bootstrap-theme.min.css">
	
</head>

<body class="${bodyClass }">

	<%@ include file="/WEB-INF/views/header.jsp" %>
	
	<jsp:doBody />

</body>
</html>