<%--
  Created by IntelliJ IDEA.
  User: Tomas
  Date: 12/11/2016
  Time: 6:15 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:template title="Wines">
<jsp:attribute name="body">

    <form:form method="get" action="${pageContext.request.contextPath}/winelists/view/${wineListView.id}"
               modelAttribute="wineListView" cssClass="form-horizontal">

    <table class="table">
        <thead>
            <tr>
                <th class="text-center"><fmt:message key="number"/></th>
                <th class="text-center"><fmt:message key="wine.name"/></th>
                <th class="text-center"><fmt:message key="wine.vintage"/></th>
                <th class="text-center"><fmt:message key="wine.batch"/></th>
                <th class="text-center"><fmt:message key="wine.predicate"/></th>
                <th class="text-center"><fmt:message key="wine.predicateEquivalent"/></th>
                <th class="text-center"><fmt:message key="wine.description"/></th>
                <th class="text-center"><fmt:message key="wine.notes"/></th>
                <th class="text-center"><fmt:message key="wine.alcoholVolume"/></th>
                <th class="text-center"><fmt:message key="wine.residualSugar"/></th>
                <th class="text-center"><fmt:message key="wine.acidity"/></th>
                <th class="text-center"><fmt:message key="wine.grapeSugarContent"/></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${wineListView.wines}" var="wine">
            <c:set var="count" value="${count + 1}" scope="page"/>
                <tr>
                    <td class="col-xs-3 text-center">${count}.</td>
                    <td class="col-xs-3 text-center"><c:out value="${wine.name}"/></td>
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
                </tr>
            </c:forEach>
        </tbody>
    </table>

    </form:form>

</jsp:attribute>
</my:template>