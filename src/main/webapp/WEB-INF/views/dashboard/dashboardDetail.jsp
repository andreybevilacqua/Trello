<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<c:url value="/" var="contextPath" />
<c:url value="/dashboard/create" var="createDashboardContextPath" />
<c:url value="/dashboard/delete" var="deleteDashboardContextPath" />
<c:url value="/list" var="listContextPath" />

<tags:pageTemplate pageTitle="Dashboard Detail">

      <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
      	<h1 class="display-4">${dashboard.getTitle() }</h1>
      	<br>
   	 </div>

	<!--  https://getbootstrap.com/docs/4.0/examples/offcanvas/# -->

</tags:pageTemplate>

