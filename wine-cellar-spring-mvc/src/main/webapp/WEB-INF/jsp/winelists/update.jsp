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
    <jsp:attribute name="scripts">
        <script>
            $(function () {
                $("#datepicker").datepicker({ format: 'MM/dd/yyyy' }).val();
            });
        </script>
    </jsp:attribute>
    <jsp:attribute name="body">

        <form:form method="post" action="${pageContext.request.contextPath}/winelists/update/${wineListUpdate.id}"
                   modelAttribute="wineListUpdate" cssClass="form-horizontal">

            <div class="col-md-12 com-md-offset-2 form-group ${name_error?'has-error':''}">
                <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
                <div class="col-sm-4">
                    <form:input path="name" cssClass="form-control"/>
                    <form:errors path="name" cssClass="help-block"/>
                </div>
            </div>

            <div class="col-md-12 com-md-offset-2 form-group ${date_error?'has-error':''}">
                <form:label path="date" cssClass="col-sm-2 control-label">Date</form:label>
                <div class="col-sm-4">
                    <form:input path="date" id="datepicker" cssClass="form-control"/>
                    <form:errors path="date" cssClass="help-block"/>
                </div>
            </div>

            <div class="col-md-12 com-md-offset-2 form-group">
                <form:label path="marketingEvent" cssClass="col-sm-2 control-label">MarketingEvent</form:label>
                <div class="col-sm-4">
                    <form:select path="marketingEvent.id" cssClass="form-control">
                         <c:choose>
                            <c:when test="${marketingEvent==null}">
                                     <option><fmt:message key="selectEvent"/></option>
                            </c:when>
                        </c:choose>

                        <c:forEach items="${marketingEvents}" var="marketingEvent">
                            <form:option value="${marketingEvent.id}">${marketingEvent.description}</form:option>
                        </c:forEach>
                    </form:select>
                    <p class="help-block"><form:errors path="marketingEvent" cssClass="error"/></p>
                </div>
            </div>
            <form:hidden path="id"/>
            <button class="btn btn-primary" type="submit">Update WineList</button>

        </form:form>

    </jsp:attribute>
</my:template>
