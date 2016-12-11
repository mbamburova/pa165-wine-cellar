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

<my:template title="Packings">
<jsp:attribute name="body">

    <my:a href="/packings/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New packing
    </my:a>

    <table class="table">
        <thead>
        <tr>
            <th><fmt:message key="number"/></th>
            <th><fmt:message key="packing.volume"/></th>
            <th><fmt:message key="packing.validFrom"/></th>
            <th><fmt:message key="packing.validTo"/></th>
            <th><fmt:message key="packing.wineId"/></th>
            <th class="text-center"><fmt:message key="edit"/></th>
            <th class="text-center"><fmt:message key="delete"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${packings}" var="packing">
        <c:set var="count" value="${count + 1}" scope="page"/>
    <tr>
        <td class="col-xs-3 lead-column">${count}.</td>
        <td class="col-xs-3 text-center"><c:out value="${packing.volume}"/></td>
        <td class="col-xs-3 text-center"><c:out value="${packing.validFrom}"/></td>
        <td class="col-xs-3 text-center"><c:out value="${packing.validTo}"/></td>
        <td class="col-xs-3 text-center"><c:out value="${packing.wine.name}"/></td>

        <form:form method="get" action="${pageContext.request.contextPath}/packings/update/${packing.id}" cssClass="form-horizontal">
                <td class="col-xs-1 text-center">
                    <button class="btn btn-default" type="submit">
                        <span class="sr-only"><fmt:message key="edit"/></span>
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                    </button>
                </td>
            </form:form>

        <form:form method="post" action="${pageContext.request.contextPath}/packings/delete/${packing.id}" cssClass="form-horizontal">
                <td class="col-xs-1 text-center">
                    <button class="btn btn-default" type="submit">
                        <span class="sr-only"><fmt:message key="delete"/></span>
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </button>
                </td>
            </form:form>
    </tr>
    </c:forEach>
        </tbody>
    </table>


</jsp:attribute>
</my:template>

</body>
</html>
