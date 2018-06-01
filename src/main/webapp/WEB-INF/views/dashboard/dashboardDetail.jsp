<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<c:url value="/" var="contextPath"/>
<c:url value="/dashboard" var="dashboardContextPath"/>
<c:url value="/dashboard/delete" var="deleteDashboardContextPath"/>
<c:url value="/dashboard/detail" var="detailDashboardContextPath" />
<c:url value="/list" var="listContextPath"/>
<c:url value="/list/create" var="createListContextPath"/>
<c:url value="/list/edit" var="editListContextPath"/>
<c:url value="/list/delete" var="deleteListContextPath"/>


<tags:pageTemplate pageTitle="Dashboard Detail" bodyClass="bg-light">

    <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
        <h1 class="display-4">${dashboard.getTitle() }</h1>
        <img class="card-img-top" src="${contextPath}resources/images/default-image.png" height=58
             width="88"> <br>
    </div>

    <main role="main" class="container">

        <c:forEach var="listArea" items="${dashboard.getListAreas() }">

            <div class="d-flex align-items-center p-3 my-3 text-white-50 rounded box-shadow">

                <div class="col-md-4">
                    <div class="card mb-4 box-shadow">
                        <div class="card-body">
                            <h4 class="card-text">${listArea.getTitle() }</h4>

                            <div class="my-3 p-3 bg-white rounded box-shadow">
                                <div class="media text-muted pt-3">
                                    <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                                        <strong class="d-block text-gray-dark">@username</strong>
                                        Donec id
                                    </p>
                                </div>

                                <small class="d-block text-right mt-3">
                                    <a href="#">Add Card</a>
                                </small>

                                <br>

                                <form action="${editListContextPath}/${dashboard.getId()}" method="POST">
                                    <button class="btn btn-link btn-sm" type="submit">Edit List</button>
                                    <input type="hidden" name="listAreaId" value="${listArea.getId()}">
                                </form>

                                <form action="${deleteListContextPath}/${dashboard.getId()}" method="POST">
                                    <button type="submit" class="btn btn-link btn-sm" onclick="return confirm('Are you sure you want to Delete?');">Delete List</button>
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
        <form action="${createListContextPath}/${dashboard.getId()}" method="POST">
            <button type="submit" class="btn btn-primary my-2">Create new List</button>
            <input type="hidden" name="dashboardId" value="${dashboard.getId()}">
        </form>
    </div>

</tags:pageTemplate>

