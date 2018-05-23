<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<c:url value="/" var="contextPath" />
<c:url value="/list" var="listContextPath" />
<c:url value="/list/create" var="createListContextPath" />
<c:url value="/dashboard/detail" var="detailDashboardContextPath" />


<tags:pageTemplate pageTitle="Create List" bodyClass="bg-light">

	<div class="container">
	
		<h1>New List</h1>
	
		<form action="${createListContextPath}/${dashboardId} " method="POST">
		
			<div class="form-group">
				<label>List name:</label>
				<input type="text" name="listName" class="form-control"/>
			</div>
			
			<button type="submit" class="btn btn-primary" value="Create">Create</button>
			<br>
			<br>
		</form>

		<form action="${detailDashboardContextPath}/${dashboardId}" method="POST">
			<button type="submit" class="btn btn-primary" value="Cancel">Cancel</button>
		</form>

	</div>

</tags:pageTemplate>

