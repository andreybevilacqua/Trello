<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

  <head>
  
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Album example for Bootstrap</title>

	<c:url value="/" var="contextPath" />
	<link rel="stylesheet" href="${contextPath}resources/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="${contextPath}resources/css/bootstrap-theme.min.css">

  </head>

  <body>

      <section class="jumbotron text-center">
        <div class="container">
          <h1 class="jumbotron-heading">Album example</h1>
          <p class="lead text-muted">Something short and leading about the collection below—its contents, the creator, etc. Make it short and sweet, but not too short so folks don't simply skip over it entirely.</p>
          <p>
            <a href="#" class="btn btn-primary my-2">Main call to action</a>
            <a href="#" class="btn btn-secondary my-2">Secondary action</a>
          </p>
        </div>
      </section>

      <div class="album py-5 bg-light">
        <div class="container">

          <div class="row">
          
          <c:forEach var="dashboard" items="${dashboards }">
            <div class="col-md-4">
              <div class="card mb-4 box-shadow">
                <div class="card-body">
                  <p class="card-text">${dashboard.getTitle() }</p>
                  <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">
                      <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                    </div>
                    <small class="text-muted">9 mins</small>
                  </div>
                  <br>
                  <br>
                </div>
              </div>
            </div>
           </c:forEach>
          
          </div> <!-- div  rows -->
          
        </div>
      </div>

  </body>
</html>
