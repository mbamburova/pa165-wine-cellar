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

<my:template title="New Packing">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/packings/create"
               modelAttribute="packingCreate" cssClass="form-horizontal">
        <div class="container">
            <div class="form-group ${volume_error?'has-error':''}">
                <form:label path="volume" cssClass="col-sm-2 control-label">Volume</form:label>
                <div class="col-sm-10">
                    <form:input path="volume" cssClass="form-control"/>
                    <form:errors path="volume" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${validFrom_error?'has-error':''}">
                <form:label path="validFrom" cssClass="col-sm-2 control-label">Valid From</form:label>
                <div class="col-sm-10">
                    <form:input path="validFrom" cssClass="form-control"/>
                    <form:errors path="validFrom" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${validTo_error?'has-error':''}">
                <form:label path="validTo" cssClass="col-sm-2 control-label">Valid To</form:label>
                <div class="col-sm-10">
                    <form:input path="validTo" cssClass="form-control"/>
                    <form:errors path="validTo" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="wineId" cssClass="col-sm-2 control-label">Wine</form:label>
                <div class="col-sm-10">
                <form:select path="wineId" cssClass="form-control">
                    <c:forEach items="${wines}" var="wine">
                        <form:option value="${wine.id}">${wine.name}</form:option>
                    </c:forEach>
                </form:select>
                    <p class="help-block"><form:errors path="wineId" cssClass="error"/></p>
                </div>
            </div>
        </div>

        <script type="text/javascript">
            $(function() {
                $('#idDateField').datepicker();
            });
        </script>

        <button class="btn btn-primary" type="submit">Create packing</button>
    </form:form>

</jsp:attribute>
</my:template>