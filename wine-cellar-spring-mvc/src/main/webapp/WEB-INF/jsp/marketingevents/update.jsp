<%--
  Created by IntelliJ IDEA.
  User: MarekScholtz
  Date: 11.12.2016
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:template title="Update Marketing Event">
    <jsp:attribute name="body">

        <form:form method="post" action="${pageContext.request.contextPath}/marketingevents/update/${marketingEventUpdate.id}"
                   modelAttribute="marketingEventUpdate" cssClass="form-horizontal">

            <div class="form-group ${description_error?'has-error':''}">
                <form:label path="description" cssClass="col-sm-2 control-label"><fmt:message key="marketingevent.description"/></form:label>
                <div class="col-sm-10">
                    <form:input path="description" cssClass="form-control"/>
                    <form:errors path="description" cssClass="help-block"/>
                </div>
            </div>

            <button class="btn btn-primary updateBtn center-block allow-vertical-space" type="submit"><fmt:message key="update"/></button>
        </form:form>
    </jsp:attribute>
</own:template>
