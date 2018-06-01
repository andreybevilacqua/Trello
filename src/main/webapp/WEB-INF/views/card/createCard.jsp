<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<c:url value="/" var="contextPath" />
<c:url value="/card" var="cardContextPath" />
<c:url value="/card/create" var="createCardContextPath" />
<c:url value="/dashboard/detail" var="detailDashboardContextPath" />


<tags:pageTemplate pageTitle="Create Card" bodyClass="bg-light">

	<div class="container">
	
		<h1>New Card</h1>
	
		<form action="${createCardContextPath}" method="POST">
		
			<div class="form-group">
				<label>Card name:</label>
				<input type="text" name="cardName" class="form-control"/>
				<input type="hidden" name="listAreaId" value="${listArea.getId()}">
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

