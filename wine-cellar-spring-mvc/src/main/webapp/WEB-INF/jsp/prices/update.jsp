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
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>

<own:template title="Update Price">
     <jsp:attribute name="scripts">
        <script>
            $(function () {
                $("#datepickerFrom").datepicker({ dateFormat: 'MM/dd/yyyy' }).val();
            });
        </script>
        <script>
            $(function () {
                $("#datepickerTo").datepicker({ dateFormat: 'MM/dd/yyyy' }).val();
            });
        </script>
    </jsp:attribute>

    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/prices/update/${priceUpdate.id}"
                       modelAttribute="priceUpdate" cssClass="form-horizontal">
            <div class="container">
                <div class="form-group ${price_error?'has-error':''}">
                    <form:label path="price" cssClass="col-sm-2 control-label">Price</form:label>
                    <div class="col-sm-10">
                        <form:input path="price" cssClass="form-control"/>
                        <form:errors path="price" cssClass="help-block"/>
                    </div>
                </div>

                <div class="col-md-12 com-md-offset-2 form-group ${validFrom_error?'has-error':''}">
                    <form:label path="packingDto.validFrom" cssClass="col-sm-2 control-label">Valid From</form:label>
                    <div class="col-sm-4">
                        <form:input path="packingDto.validFrom" id="datepickerFrom" cssClass="form-control"/>
                        <form:errors path="packingDto.validFrom" cssClass="help-block"/>
                    </div>
                </div>
                <div class="col-md-12 com-md-offset-2 form-group ${validTo_error?'has-error':''}">
                    <form:label path="packingDto.validTo" cssClass="col-sm-2 control-label">Valid To</form:label>
                    <div class="col-sm-4">
                        <form:input path="packingDto.validTo" id="datepickerTo" cssClass="form-control"/>
                        <form:errors path="packingDto.validTo" cssClass="help-block"/>
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
                                <form:option value="${packing.id}">${packing.volume}&nbspfrom&nbsp${packing.validFrom}&nbspto&nbsp${packing.validTo}</form:option>
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
            <button class="btn btn-primary updateBtn center-block allow-vertical-space" type="submit"><fmt:message key="update"/></button>
        </form:form>
    </jsp:attribute>
</own:template>
