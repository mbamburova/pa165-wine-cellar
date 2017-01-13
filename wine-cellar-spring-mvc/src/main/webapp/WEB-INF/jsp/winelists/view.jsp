<%--
  Created by IntelliJ IDEA.
  User: Tomas
  Date: 12/11/2016
  Time: 6:15 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:template title="Wines in ${wineListDto.name}">
<jsp:attribute name="body">
     <c:choose>
           <c:when test="${empty wineListView}">
               <h5><em>There are no wines</em></h5>
               <br />
           </c:when>
           <c:otherwise>
               <table class="table table-hover">
                   <thead>
                   <tr>
                       <th class="text-left"><fmt:message key="number"/></th>
                       <th class="text-left"><fmt:message key="wine.name"/></th>
                       <th class="text-left"><fmt:message key="wine.vintage"/></th>
                       <th class="text-left"><fmt:message key="wine.predicate"/></th>
                       <th class="text-left"><fmt:message key="wine.alcoholVolume"/></th>
                       <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <th class="text-left"><fmt:message key="removeFromWineList"/></th>
                       </sec:authorize>
                   </tr>
                   </thead>
                   <tbody>
                   <c:forEach items="${wineListView}" var="wine">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                            <tr>
                                <td class="col-xs-2 text-left">${count}.</td>
                                <td class="col-xs-3 text-left"><c:out value="${wine.name}"/></td>
                                <td class="col-xs-2 text-left"><c:out value="${wine.vintage}"/></td>
                                <td class="col-xs-2 text-left"><c:out value="${wine.predicate}"/></td>
                                <td class="col-xs-2 text-left"><c:out value="${wine.alcoholVolume}"/></td>

                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <form:form method="get" action="${pageContext.request.contextPath}/wines/remove/${wineListDto.id}/${wine.id}" cssClass="form-horizontal">
                                    <td class="col-xs-3 text-left">
                                        <button class="btn btn-default" type="submit">
                                            <span class="sr-only"><fmt:message key="remove"/></span>
                                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                        </button>
                                    </td>
                                </form:form>
                                </sec:authorize>
                            </tr>
                        </c:forEach>
                   </tbody>
               </table>
           </c:otherwise>
     </c:choose>
</jsp:attribute>
</my:template>