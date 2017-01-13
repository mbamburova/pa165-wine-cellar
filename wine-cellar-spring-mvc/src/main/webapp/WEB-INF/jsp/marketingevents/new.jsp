<%--
  Created by IntelliJ IDEA.
  User: MarekScholtz
  Date: 11.12.2016
  Time: 1:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:template title="New marketing event">
<jsp:attribute name="body">
    <form:form method="post" action="${pageContext.request.contextPath}/marketingevents/create"
               modelAttribute="marketingEventCreate" cssClass="form-horizontal">

        <div class="col-md-12 com-md-offset-2 form-group ${description_error?'has-error':''}">
            <form:label path="description" cssClass="col-sm-2 control-label">
                <fmt:message key="marketingevent.description"/>*
            </form:label>
            <div class="col-sm-4">
                <form:input path="description" class="form-control" required="true"/>
                <form:errors path="description" cssClass="help-block"/>
                <br/>
                <p align="right">
                    <button class="btn btn-primary" type="submit">
                        <fmt:message key="create"/>
                    </button>
                    <a class="btn btn-default" href="${pageContext.request.contextPath}/marketingevents/index">
                        <fmt:message key="cancel"/>
                    </a>
                </p>
            </div>
        </div>
        <br/>

    </form:form>
</jsp:attribute>
</my:template>
