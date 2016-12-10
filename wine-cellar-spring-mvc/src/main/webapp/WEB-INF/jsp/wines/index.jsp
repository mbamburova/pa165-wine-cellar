<%--
  Created by IntelliJ IDEA.
  User: MarekScholtz
  Date: 9.12.2016
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Wines</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"  crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.7/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.7/angular-resource.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.7/angular-route.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Wine Cellar</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/tastingticket">Tasting Ticket</a></li>
                <li><a href="${pageContext.request.contextPath}/completeoffer">Complete Offer</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Admin<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/wines">Wines</a></li>
                        <li><a href="${pageContext.request.contextPath}/winelists">Wine Lists</a></li>
                        <li><a href="${pageContext.request.contextPath}/packings">Packings</a></li>
                        <li><a href="${pageContext.request.contextPath}/prices">Prices</a></li>
                        <li><a href="${pageContext.request.contextPath}/marketingevents">Marketing Events</a></li>
                    </ul>
                </li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<table class="table">

    <tr>
        <th><fmt:message key="wine.name"/></th>
        <th><fmt:message key="wine.vintage"/></th>
        <th><fmt:message key="wine.batch"/></th>
        <th><fmt:message key="wine.predicate"/></th>
        <th><fmt:message key="wine.predicateEquivalent"/></th>
        <th><fmt:message key="wine.description"/></th>
        <th><fmt:message key="wine.notes"/></th>
        <th><fmt:message key="wine.alcoholVolume"/></th>
        <th><fmt:message key="wine.residualSugar"/></th>
        <th><fmt:message key="wine.acidity"/></th>
        <th><fmt:message key="wine.grapeSugarContent"/></th>
        <%--<th><fmt:message key="wine.wineLists"/></th>--%>
        <c:if test="${isAdmin}">
            <th class="text-center"><fmt:message key="edit"/></th>
            <th class="text-center"><fmt:message key="remove"/></th>
        </c:if>
    </tr>

    <c:forEach items="${wines}" var="wine">
        <c:set var="count" value="${count + 1}" scope="page"/>
    <tr>
        <td class="col-xs-1 lead-column">${count}.</td>
        <td class="col-xs-3 lead-column"><c:out value="${wine.name}"/></td>
        <td class="col-xs-3 text-center"><c:out value="${wine.vintage}"/></td>
        <td class="col-xs-3 text-center"><c:out value="${wine.batch}"/></td>
        <td class="col-xs-3 text-center"><c:out value="${wine.predicate}"/></td>
        <td class="col-xs-3 text-center"><c:out value="${wine.predicateEquivalent}"/></td>
        <td class="col-xs-3 text-center"><c:out value="${wine.description}"/></td>
        <td class="col-xs-3 text-center"><c:out value="${wine.notes}"/></td>
        <td class="col-xs-3 text-center"><c:out value="${wine.alcoholVolume}"/></td>
        <td class="col-xs-3 text-center"><c:out value="${wine.residualSugar}"/></td>
        <td class="col-xs-3 text-center"><c:out value="${wine.acidity}"/></td>
        <td class="col-xs-3 text-center"><c:out value="${wine.grapeSugarContent}"/></td>
        <%--<td class="col-xs-3 text-center"><c:out value="${wine.wineLists}"/></td>--%>

        <c:if test="${isAdmin}">
            <form:form method="get" action="${pageContext.request.contextPath}/sports/update/${sport.id}" cssClass="form-horizontal">
                <td class="col-xs-1 text-center">
                    <button class="btn btn-default" type="submit">
                        <span class="sr-only"><fmt:message key="edit"/></span>
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                    </button>
                </td>
            </form:form>

            <form:form method="post" action="${pageContext.request.contextPath}/sports/remove/${sport.id}" cssClass="form-horizontal">
                <td class="col-xs-1 text-center">
                    <button class="btn btn-default" type="submit">
                        <span class="sr-only"><fmt:message key="remove"/></span>
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </button>
                </td>
            </form:form>
        </c:if>
    </tr>
    </c:forEach>
</table>

</body>
</html>
