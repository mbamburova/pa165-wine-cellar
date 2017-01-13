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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:template title="Tasting tickets">
    <jsp:attribute name="body">
       <sec:authorize access="hasRole('ROLE_ADMIN')">
           <my:a href="/winelists/new" class="btn btn-primary">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    New tasting ticket
            </my:a>
       </sec:authorize>
       <c:choose>
           <c:when test="${empty winelists}">
               <h5><em>There are no tasting tickets.</em></h5>
               <br />
           </c:when>
           <c:otherwise>
               <table class="table table-hover">
                   <thead>
                   <tr>
                       <th class="text-left"><fmt:message key="number"/></th>
                       <th class="text-left"><fmt:message key="winelist.name"/></th>
                       <th class="text-left"><fmt:message key="winelist.date"/></th>
                       <sec:authorize access="hasRole('ROLE_ADMIN')">
                       <th class="text-left"><fmt:message key="winelist.marketingevent"/></th>
                       <th class="text-left"><fmt:message key="edit"/></th>
                       <th class="text-left"><fmt:message key="remove"/></th>
                       </sec:authorize>
                       <th class="text-center"><fmt:message key="wineList.viewWines"/></th>
                   </tr>
                   </thead>
                   <tbody>
                   <c:forEach items="${winelists}" var="winelist">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <tr>
                        <td class="col-xs-3 text-left">${count}.</td>
                        <td class="col-xs-3 text-left"><c:out value="${winelist.name}"/></td>
                        <td class="col-xs-3 text-left"><javatime:format value="${winelist.date}" pattern="dd.MM.yyyy"/></td>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td class="col-xs-3 text-left"><c:out value="${winelist.marketingEvent.description}"/></td>
                        <form:form method="get" action="${pageContext.request.contextPath}/winelists/update/${winelist.id}" cssClass="form-horizontal">
                                <td class="col-xs-1 text-left">
                                    <button class="btn btn-default" type="submit">
                                        <span class="sr-only"><fmt:message key="edit"/></span>
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                    </button>
                                </td>
                        </form:form>

                        <form:form method="get" action="${pageContext.request.contextPath}/winelists/delete/${winelist.id}" cssClass="form-horizontal">
                                <td class="col-xs-1 text-center">
                                    <button class="btn btn-default" type="submit">
                                        <span class="sr-only"><fmt:message key="remove"/></span>
                                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                    </button>
                                </td>
                        </form:form>
                        </sec:authorize>
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
    </jsp:attribute>
</my:template>
