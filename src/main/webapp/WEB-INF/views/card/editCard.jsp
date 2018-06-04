<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<c:url value="/" var="contextPath" />
<c:url value="/card" var="cardContextPath" />
<c:url value="/card/edit" var="editCardContextPath" />
<c:url value="/list/detail" var="detailListContextPath" />

<tags:pageTemplate pageTitle="Edit Card" bodyClass="bg-light">

    <div class="container">

        <h1>Edit List</h1>

        <form action="${editCardContextPath} " method="POST">

            <div class="form-group">
                <label>Card name:</label>
                <input type="text" name="cardName" class="form-control" value="${card.getTitle()}" required/>
                <input type="hidden" name="cardId" value="${card.getId()}">
                <input type="hidden" name="listAreaId" value="${card.getListArea().getId()}">
                <input type="hidden" name="dashboardId" value="${card.getListArea().getDashboard().getId()}">
            </div>

            <button type="submit" class="btn btn-primary" value="Create">Update</button>
            <br>
            <br>
        </form>

        <form action="${detailListContextPath}" method="POST">
            <button type="submit" class="btn btn-primary" value="Cancel">Cancel</button>
            <input type="hidden" name="listAreaId" value="${card.getListArea().getId()}">
        </form>

    </div>

</tags:pageTemplate>

