<%--
  Created by IntelliJ IDEA.
  User: Tomas
  Date: 12/11/2016
  Time: 7:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>

<my:template title="Update - ${wineListUpdate.name}">
    <jsp:attribute name="scripts">
        <script>
            $(function () {
                $("#datepicker").datepicker({format: 'MM/dd/yyyy'}).val();
            });
        </script>
    </jsp:attribute>
    <jsp:attribute name="body">

        <form:form method="post" action="${pageContext.request.contextPath}/winelists/update/${wineListUpdate.id}"
                   modelAttribute="wineListUpdate" cssClass="form-horizontal">

            <div class="col-md-12 com-md-offset-2 form-group ${name_error?'has-error':''}">
                <form:label path="name" cssClass="col-sm-2 control-label">
                    <fmt:message key="winelist.name"/>*
                </form:label>
                <div class="col-sm-4">
                    <form:input path="name" cssClass="form-control" required="true"/>
                    <form:errors path="name" cssClass="help-block"/>
                </div>
            </div>

            <div class="col-md-12 com-md-offset-2 form-group ${date_error?'has-error':''}">
                <form:label path="date" cssClass="col-sm-2 control-label">
                    <fmt:message key="winelist.date"/>*
                </form:label>
                <div class="col-sm-4">
                    <form:input path="date" id="datepicker" cssClass="form-control" required="true"/>
                    <form:errors path="date" cssClass="help-block"/>
                </div>
            </div>

            <div class="col-md-12 com-md-offset-2 form-group">
                <form:label path="marketingEvent" cssClass="col-sm-2 control-label">
                    <fmt:message key="marketingevent"/>
                </form:label>
                <div class="col-sm-4">
                    <form:select path="marketingEvent.id" cssClass="form-control">
                        <form:option value=""><fmt:message key="nothingSelected"/></form:option>
                            <c:forEach items="${marketingEvents}" var="marketingevent">
                                <form:option value="${marketingevent.id}">${marketingevent.description}</form:option>
                            </c:forEach>
                    </form:select>
                    <p class="help-block"><form:errors path="marketingEvent" cssClass="error"/></p>
                    <br/>
                    <p align="right">
                        <button class="btn btn-primary" type="submit"><fmt:message key="update"/></button>
                        <a class="btn btn-default" href="${pageContext.request.contextPath}/winelists/index">
                            <fmt:message key="cancel"/>
                        </a>
                    </p>
                </div>
            </div>
            <form:hidden path="id"/>
        </form:form>
    </jsp:attribute>
</my:template>
