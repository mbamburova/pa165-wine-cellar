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

<my:template title="Update Winelist">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/winelists/update/${wineListUpdate.id}"
               modelAttribute="wineListUpdate" cssClass="form-horizontal">

        <div class="container">
            <div class="form-group ${name_error?'has-error':''}">
                <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
                <div class="col-sm-10">
                    <form:input path="name" cssClass="form-control"/>
                    <form:errors path="name" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${date_error?'has-error':''}">
                <form:label path="date" cssClass="col-sm-2 control-label">Date</form:label>
                <div class="col-sm-10">
                    <form:input path="date" cssClass="form-control"/>
                    <form:errors path="date" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="wines" cssClass="col-sm-2 control-label">Wine</form:label>
                <div class="col-sm-10">
                <form:select path="wines" cssClass="form-control">
                    <c:forEach items="${wines}" var="wine">
                        <form:option value="${wine.id}">${wine.name}</form:option>
                    </c:forEach>
                </form:select>
                    <p class="help-block"><form:errors path="wines" cssClass="error"/></p>
                </div>
            </div>
            <div class="form-group">
                <form:label path="marketingEvent" cssClass="col-sm-2 control-label">MarketingEvent</form:label>
                <div class="col-sm-10">
                    <form:select path="marketingEvent" cssClass="form-control">
                        <c:forEach items="${marketingEvents}" var="marketingEvent">
                            <form:option value="${marketingEvent.description}">${marketingEvent.description}</form:option>
                        </c:forEach>
                    </form:select>
                    <p class="help-block"><form:errors path="marketingEvent" cssClass="error"/></p>
                </div>
            </div>
        </div>

        <button class="btn btn-primary" type="submit">Update WineList</button>
    </form:form>

</jsp:attribute>
</my:template>
