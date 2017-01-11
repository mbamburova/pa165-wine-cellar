<%--
  Created by IntelliJ IDEA.
  User: MarekScholtz
  Date: 9.12.2016
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:template>
    <jsp:attribute name="body">

        <div class="jumbotron">
            <h1><fmt:message key="Wines"/></h1>
            <fmt:message key="nameHolder" var="wineHolder"/>
            <c:if test="${user.isAdmin()}">
                <p align="right">
                    <a class="btn btn-lg btn-success btn-jumbotron" href="${pageContext.request.contextPath}/wines/new" role="button">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                        <fmt:message key="create_new"/>
                    </a>
                </p>
            </c:if>
        </div>

        <table class="table table-hover">
            <thead>
                <tr>
                    <th class="text-left"><fmt:message key="number"/></th>
                    <th class="text-left"><fmt:message key="wine.name"/></th>
                    <th class="text-left"><fmt:message key="wine.vintage"/></th>
                    <th class="text-left"><fmt:message key="wine.predicate"/></th>
                    <th class="text-left"><fmt:message key="detail"/></th>
                    <c:if test="${loggedUser.isAdmin()}">
                        <th class="text-left"><fmt:message key="edit"/></th>
                        <th class="text-left"><fmt:message key="delete"/></th>
                        <th class="text-left"><fmt:message key="addToWineList"/></th>
                    </c:if>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${wines}" var="wine">
                <c:set var="count" value="${count + 1}" scope="page"/>
                <tr>
                    <td class="col-xs-3 text-left">${count}.</td>
                    <td class="col-xs-3 text-left"><c:out value="${wine.name}"/></td>
                    <td class="col-xs-3 text-left"><c:out value="${wine.vintage}"/></td>
                    <td class="col-xs-3 text-left"><c:out value="${wine.predicate}"/></td>

                    <form:form method="get" action="${pageContext.request.contextPath}/wines/detail/${wine.id}" cssClass="form-horizontal">
                        <td class="col-xs-1 text-center">
                            <button class="btn btn-default" type="submit">
                                <span class="sr-only"><fmt:message key="detail"/></span>
                                <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                            </button>
                        </td>
                    </form:form>

                    <c:if test="${loggedUser.isAdmin()}">
                        <form:form method="get" action="${pageContext.request.contextPath}/wines/update/${wine.id}" cssClass="form-horizontal">
                            <td class="col-xs-1 text-center">
                                <button class="btn btn-default" type="submit">
                                    <span class="sr-only"><fmt:message key="edit"/></span>
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                </button>
                            </td>
                        </form:form>

                        <form:form method="post" action="${pageContext.request.contextPath}/wines/delete/${wine.id}" cssClass="form-horizontal">
                            <td class="col-xs-1 text-center">
                                <button class="btn btn-default" type="submit">
                                    <span class="sr-only"><fmt:message key="remove"/></span>
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                </button>
                            </td>
                        </form:form>

                        <form:form method="post" action="${pageContext.request.contextPath}/wines/add/${wine.id}" cssClass="form-horizontal">
                            <td class="col-xs-1 text-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default .dropdown-toggle" data-toggle="dropdown">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> <span class="caret"></span>
                                    </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <c:forEach items="${wineLists}" var="winelist" >
                                                <li><a href="${pageContext.request.contextPath}/wines/add/${wine.id}" class="button btn-default"><c:out value="${winelist.name}" /></a></li>
                                                <%--<c:set var="listId" value="${winelist.id}"/>--%>
                                            </c:forEach>
                                        </ul>
                                </div>
                            </td>
                        </form:form>
                    </c:if>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</my:template>
