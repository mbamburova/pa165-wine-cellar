<%--
  Created by IntelliJ IDEA.
  User: MarekScholtz
  Date: 10.12.2016
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:template title="New Price">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/prices/create"
               modelAttribute="priceCreate" cssClass="form-horizontal">
        <div class="container">
            <div class="form-group ${name_error?'has-error':''}">
                <form:label path="price" cssClass="col-sm-2 control-label">Price</form:label>
                <div class="col-sm-10">
                    <form:input path="price" cssClass="form-control"/>
                    <form:errors path="price" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="currency" cssClass="col-sm-2 control-label">currency</form:label>
                <div class="col-sm-10">
                <form:select path="currency" cssClass="form-control">
                    <c:forEach items="${currencies}" var="c">
                        <form:option value="${c}">${c}</form:option>
                    </c:forEach>
                </form:select>
                    <form:errors path="currency" cssClass="error"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="packingId" cssClass="col-sm-2 control-label">Packing</form:label>
                <div class="col-sm-10">
                <form:select path="packingId" cssClass="form-control">
                    <c:forEach items="${packings}" var="packing">
                        <form:option value="${packing.id}">${packing.volume}</form:option>
                    </c:forEach>
                </form:select>
                    <p class="help-block"><form:errors path="packingId" cssClass="error"/></p>
                </div>
            </div>
            <div class="form-group">
                <form:label path="marketingEventId" cssClass="col-sm-2 control-label">Marketing Event</form:label>
                <div class="col-sm-10">
                <form:select path="marketingEventId" cssClass="form-control">
                    <c:forEach items="${marketingevents}" var="marketingevent">
                        <form:option value="${marketingevent.id}">${marketingevent.description}</form:option>
                    </c:forEach>
                </form:select>
                    <p class="help-block"><form:errors path="marketingEventId" cssClass="error"/></p>
                </div>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Create prices</button>
    </form:form>

</jsp:attribute>
</my:template>
