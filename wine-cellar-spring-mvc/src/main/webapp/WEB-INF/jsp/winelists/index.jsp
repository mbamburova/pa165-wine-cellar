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
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>

<my:template title="Winelists">
    <jsp:attribute name="body">
        <c:choose>
               <c:when test="${empty winelists}">
                   <h4>There are no winelists.</h4>
                   <br />
                   <c:if test="${loggedUser.isAdmin()}">
                       <my:a href="/winelists/new" class="btn btn-primary">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                            New wineList
                        </my:a>
                   </c:if>
               </c:when>
               <c:otherwise>
                <c:if test="${loggedUser.isAdmin()}">
                   <my:a href="/winelists/new" class="btn btn-primary">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                        New wineList
                   </my:a>
               </c:if>
                   <br />
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="text-center"><fmt:message key="number"/></th>
                            <th class="text-center"><fmt:message key="winelist.name"/></th>
                            <th class="text-center"><fmt:message key="winelist.date"/></th>
                            <th class="text-center"><fmt:message key="winelist.marketingevent"/></th>
                                <c:if test="${loggedUser.isAdmin()}">
                            <th class="text-center"><fmt:message key="edit"/></th>
                            <th class="text-center"><fmt:message key="remove"/></th>
                                </c:if>
                            <th class="text-center"><fmt:message key="wineList.viewWines"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${winelists}" var="winelist">
                            <c:set var="count" value="${count + 1}" scope="page"/>
                            <tr>
                                <td class="col-xs-3 text-center">${count}.</td>
                                <td class="col-xs-3 text-center"><c:out value="${winelist.name}"/></td>
                                <td><javatime:format value="${winelist.date}" pattern="dd.MM.yyyy"/></td>
                                <td class="col-xs-3 text-center"><c:out value="${winelist.marketingEvent.description}"/></td>

                                    <c:if test="${loggedUser.isAdmin()}">
                                <form:form method="get" action="${pageContext.request.contextPath}/winelists/update/${winelist.id}" cssClass="form-horizontal">
                                        <td class="col-xs-1 text-center">
                                            <button class="btn btn-default" type="submit">
                                                <span class="sr-only"><fmt:message key="edit"/></span>
                                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                            </button>
                                        </td>
                                    </form:form>

                                <form:form method="post" action="${pageContext.request.contextPath}/winelists/delete/${winelist.id}" cssClass="form-horizontal">
                                        <td class="col-xs-1 text-center">
                                            <button class="btn btn-default" type="submit">
                                                <span class="sr-only"><fmt:message key="remove"/></span>
                                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                            </button>
                                        </td>
                                    </form:form>
                                    </c:if>
                                <form:form method="get" action="${pageContext.request.contextPath}/winelists/view/${winelist.id}" cssClass="form-horizontal">
                                    <td class="col-xs-1 text-center">
                                        <button class="btn btn-default" type="submit">
                                            <span class="sr-only"><fmt:message key="wineList.viewWines"/></span>
                                            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                                        </button>
                                    </td>
                                </form:form>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
               </c:otherwise>
        </c:choose>
     <br/>
    </jsp:attribute>
</my:template>
