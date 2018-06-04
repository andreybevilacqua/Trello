<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<c:url value="/" var="contextPath"/>
<c:url value="/dashboard/detail" var="detailDashboardContextPath" />
<c:url value="/list" var="listContextPath"/>
<c:url value="/list/create" var="createListContextPath"/>
<c:url value="/list/edit" var="editListContextPath"/>
<c:url value="/list/delete" var="deleteListContextPath"/>
<c:url value="/list/detail" var="detailListContextPath"/>
<c:url value="/card/create" var="createCardContextPath" />
<c:url value="/card/edit" var="editCardContextPath" />
<c:url value="/card/delete" var="deleteCardContextPath" />

<tags:pageTemplate pageTitle="List Detail" bodyClass="bg-light">

    <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
        <h1 class="display-4">${listArea.getTitle() }</h1>
        <img class="card-img-top" src="${contextPath}resources/images/default-image.png" height=58
             width="88"> <br>
    </div>

    <main role="main" class="container">

        <c:forEach var="card" items="${listArea.getCards() }">

            <div class="d-flex align-items-center p-3 my-3 text-white-50 rounded box-shadow">

                <div class="col-md-4">
                    <div class="card mb-4 box-shadow">
                        <div class="card-body">

                            <h4 class="card-text">${card.getTitle() }</h4>

                            <div class="my-3 p-3 bg-white rounded box-shadow">
                                <div class="media text-muted pt-3">

                                    <c:forEach var="comment" items="${card.getComments()}">
                                        <p class="card-text">
                                            <strong class="d-block text-gray-dark">${comment.getComment()}</strong>
                                            Card Comments.
                                        </p>
                                    </c:forEach>

                                </div>

                                <form action="${createCardContextPath}" method="POST">
                                    <button class="btn btn-link btn-sm" type="submit">Add Comment</button>
                                    <input type="hidden" name="listAreaId" id="listAreaId" value="${listArea.getId()}">
                                    <input type="hidden" name="cardId" id="cardId" value="${card.getId()}">
                                </form>

                                <br>

                                <form action="${editCardContextPath}" method="POST">
                                    <button class="btn btn-link btn-sm" type="submit">Edit Card</button>
                                    <input type="hidden" name="cardId" value="${card.getId()}">
                                </form>

                                <form action="${deleteCardContextPath}" method="POST">
                                    <button type="submit" class="btn btn-link btn-sm" onclick="return confirm('Are you sure you want to Delete?');">Delete Card</button>
                                    <input type="hidden" name="listAreaId" value="${listArea.getId()}">
                                </form>

                            </div>

                        </div>
                    </div>
                </div>

            </div>

        </c:forEach>

    </main>

    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <div class="btn-group">
        <br>
        <form action="${createCardContextPath}" method="POST">
            <button type="submit" class="btn btn-primary my-2">Create new Card</button>
            <input type="hidden" name="listAreaId" value="${listArea.getId()}">
        </form>
    </div>

</tags:pageTemplate>

