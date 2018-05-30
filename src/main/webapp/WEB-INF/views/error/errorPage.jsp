<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<c:url value="/" var="contextPath" />
<c:url value="/dashboard/create" var="createDashboardContextPath" />
<c:url value="/dashboard/delete" var="deleteDashboardContextPath" />
<c:url value="/dashboard/detail" var="detailDashboardContextPath" />

<tags:pageTemplate pageTitle="Dashboard">

      <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
      	<h1 class="display-4">Error Page</h1>
      	<p class="lead">An error occured during a process. Please try again.</p>
      	<br>
   	 </div>

      <div class="album py-5 bg-light">
        <div class="container">

          <div class="row">
          
          </div> <!-- div  rows -->
          
          <form action="${createDashboardContextPath}" method="POST">
          	<button type="submit" class="btn btn-primary my-2">Create dashboard</button>
          </form>
          
        </div>

      </div>

</tags:pageTemplate>

