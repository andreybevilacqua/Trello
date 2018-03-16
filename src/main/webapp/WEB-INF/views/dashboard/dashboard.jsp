<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<c:url value="/" var="contextPath" />
<c:url value="/dashboard/create" var="createDashboardContextPath" />
<c:url value="/dashboard/delete" var="deleteDashboardContextPath" />
<c:url value="/list" var="listContextPath" />

<tags:pageTemplate pageTitle="Dashboard">

      <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
      	<h1 class="display-4">Dashboards</h1>
      	<p class="lead">All your dashboards with a count of total lists per dashboard</p>
      	<br>
   	 </div>

      <div class="album py-5 bg-light">
        <div class="container">

          <div class="row">
          
          <c:forEach var="dashboard" items="${dashboards }">
            <div class="col-md-4">
              <div class="card mb-4 box-shadow">
                <img class="card-img-top" src="${contextPath}resources/images/default-image.png" height=60 width="130">
                <div class="card-body">
                  <p class="card-text">${dashboard.getTitle() }</p>
                  <small class="text-muted">Lists: ${dashboard.getNumberOfLists() }</small>
                  <div class="d-flex justify-content-between align-items-center">

                    <div class="btn-group">                      	
                      	<a href="${listContextPath}"
                      	   class="view" >View</a>&nbsp;&nbsp;&nbsp;&nbsp;
                      
                      	<a href="${deleteDashboardContextPath}/${dashboard.getId()}"
                      	   class="delete"
                      	   onclick="return confirm('Are you sure you want to Delete?');">Delete</a>
                    </div>
                    
                  </div>
                  <br>
                  <br>
                </div>
              </div>
            </div>
           </c:forEach>
          
          </div> <!-- div  rows -->
          
          <form action="${createDashboardContextPath}" method="POST">
          	<button type="submit" class="btn btn-primary my-2">Create dashboard</button>
          </form>
          
        </div>
      </div>

</tags:pageTemplate>

