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

<my:template title="Wines">
<jsp:attribute name="body">

    <table class="table table-hover">
        <thead>
            <tr>
                <th class="text-center"><fmt:message key="number"/></th>
                <th class="text-center"><fmt:message key="wine.name"/></th>
                <th class="text-center"><fmt:message key="wine.vintage"/></th>
                <th class="text-center"><fmt:message key="wine.predicate"/></th>
                <th class="text-center"><fmt:message key="wine.alcoholVolume"/></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${wineListView}" var="wine">
            <c:set var="count" value="${count + 1}" scope="page"/>
                <tr>
                    <td class="col-xs-3 text-center">${count}.</td>
                    <td class="col-xs-3 text-center"><c:out value="${wine.name}"/></td>
                    <td class="col-xs-3 text-center"><c:out value="${wine.vintage}"/></td>
                    <td class="col-xs-3 text-center"><c:out value="${wine.predicate}"/></td>
                    <td class="col-xs-3 text-center"><c:out value="${wine.alcoholVolume}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <%--</form:form>--%>

</jsp:attribute>
</my:template>