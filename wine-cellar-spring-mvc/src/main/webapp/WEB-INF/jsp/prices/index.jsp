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

<my:template title="Prices">
<jsp:attribute name="body">

    <my:a href="/prices/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New Price
    </my:a>

    <table class="table">
        <thead>
        <tr>
            <th><fmt:message key="number"/></th>
            <th class="text-center"><fmt:message key="price.price"/></th>
            <th class="text-center"><fmt:message key="price.currency"/></th>
            <th class="text-center"><fmt:message key="packing"/></th>
            <th class="text-center"><fmt:message key="marketingevent"/></th>
            <th class="text-center"><fmt:message key="edit"/></th>
            <th class="text-center"><fmt:message key="delete"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${prices}" var="price">
        <c:set var="count" value="${count + 1}" scope="page"/>
    <tr>
        <td>${count}.</td>
        <td class="col-xs-3 text-center"><c:out value="${price.price}"/></td>
        <td class="col-xs-3 text-center"><c:out value="${price.currency}"/></td>
        <td class="col-xs-3 text-center"><c:out value="${price.packingId}"/></td>
        <td class="col-xs-3 text-center"><c:out value="${price.marketingEventId}"/></td>

        <form:form method="get" action="${pageContext.request.contextPath}/prices/update/${price.id}" cssClass="form-horizontal">
                <td class="col-xs-1 text-center">
                    <button class="btn btn-default" type="submit">
                        <span class="sr-only"><fmt:message key="edit"/></span>
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                    </button>
                </td>
            </form:form>

        <form:form method="post" action="${pageContext.request.contextPath}/prices/delete/${price.id}" cssClass="form-horizontal">
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