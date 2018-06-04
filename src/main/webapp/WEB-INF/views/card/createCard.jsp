<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<c:url value="/" var="contextPath" />
<c:url value="/card" var="cardContextPath" />
<c:url value="/card/create" var="createCardContextPath" />
<c:url value="/list/detail" var="detailListContextPath" />

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

		<form action="${detailListContextPath}" method="POST">
			<button type="submit" class="btn btn-primary" value="Cancel">Cancel</button>
            <input type="hidden" name="listAreaId" value="${listArea.getId()}">
		</form>

	</div>

</tags:pageTemplate>

