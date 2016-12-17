<%--
  Created by IntelliJ IDEA.
  User: MarekScholtz
  Date: 9.12.2016
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:template>
<jsp:attribute name="body">

    <div class="jumbotron">
        <h1><fmt:message key="Wines"/></h1>
        <%--<p class="lead"><fmt:message key="songs.subheading"/></p>--%>
        <%--<p><fmt:message key="songs.search_intro_text"/></p>--%>

        <form:form method="GET"
                   action="${pageContext.request.contextPath}/wines/index"
                   acceptCharset=""
                   cssClass="form-inline">

                <fmt:message key="nameHolder" var="wineHolder"/>
                <input name="name" value="${wine.name}" class="form-control" autocomplete="off" placeholder="${wineHolder}"/>

                <select name="vintage" class="form-control">
                    <option value="" display ${empty wine.vintage ? ' selected' : ''}><fmt:message key="vintageHolder"/></option>
                    <c:forEach items="${wines}" var="wine">
                        <option value="${wine.vintage}">${wine.vintage}</option>
                    </c:forEach>
                </select>

            <button class="btn btn-primary search-btn" type="submit"><i class="glyphicon glyphicon-search"></i>&nbsp;<fmt:message key="search"/></button>
            </form:form>

            <p align="right">
                <a class="btn btn-lg btn-success btn-jumbotron" href="${pageContext.request.contextPath}/wines/new" role="button">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    <fmt:message key="create_new"/>
                </a>
            </p>
    </div>

    <table class="table table-hover">
        <thead>
            <tr>
                <th class="text-center"><fmt:message key="number"/></th>
                <th class="text-center"><fmt:message key="wine.name"/></th>
                <th class="text-center"><fmt:message key="wine.vintage"/></th>
                <th class="text-center"><fmt:message key="wine.predicate"/></th>
                <th class="text-center"><fmt:message key="wine.alcoholVolume"/></th>
                <th class="text-center"><fmt:message key="detail"/></th>
                <th class="text-center"><fmt:message key="edit"/></th>
                <th class="text-center"><fmt:message key="delete"/></th>
                <th class="text-center"><fmt:message key="addToWineList"/></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${wines}" var="wine">
            <c:set var="count" value="${count + 1}" scope="page"/>
            <tr>
                <td class="col-xs-3 text-center">${count}.</td>
                <td class="col-xs-3 text-center"><c:out value="${wine.name}"/></td>
                <td class="col-xs-3 text-center"><c:out value="${wine.vintage}"/></td>
                <td class="col-xs-3 text-center"><c:out value="${wine.predicate}"/></td>
                <td class="col-xs-3 text-center"><c:out value="${wine.alcoholVolume}"/></td>

                <form:form method="get" action="${pageContext.request.contextPath}/wines/detail/${wine.id}" cssClass="form-horizontal">
                    <td class="col-xs-1 text-center">
                        <button class="btn btn-default" type="submit">
                            <span class="sr-only"><fmt:message key="detail"/></span>
                            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                        </button>
                    </td>
                </form:form>

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

                <form:form method="get" action="${pageContext.request.contextPath}/wines/add/${wine.id}" cssClass="form-horizontal">
                <td class="col-xs-1 text-center">
                    <div class="btn-group">
                        <button type="button" class="btn btn-default .dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <c:forEach items="${wineLists}" var="winelist" >
                                <li> <form:option value="${winelist.id}">${winelist.description}</form:option></li>
                            </c:forEach>
                        </ul>
                    </div>
                </td>
                </form:form>
            </tr>
            </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:template>
