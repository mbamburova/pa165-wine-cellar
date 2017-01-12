<%--
  Created by IntelliJ IDEA.
  User: Tomas Gordian
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
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>

<my:template title="Create packing with price">
    <jsp:attribute name="scripts">
        <script>
            $(function () {
                $("#datepickerFrom").datepicker({ format: 'MM/dd/yyyy' }).val();
            });
        </script>
        <script>
            $(function () {
                $("#datepickerTo").datepicker({ format: 'MM/dd/yyyy' }).val();
            });
        </script>
    </jsp:attribute>

    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/prices/new"
                   modelAttribute="pricePacking" cssClass="form-horizontal">
            <div class="container">
                <div class="col-md-12 com-md-offset-2 form-group ${volume_error?'has-error':''}">
                    <form:label path="packingDto.volume" cssClass="col-sm-2 control-label"><fmt:message key="packing.volume"/> in liters</form:label>
                    <div class="col-sm-4">
                        <form:input path="packingDto.volume" cssClass="form-control" required="true"/>
                        <form:errors path="packingDto.volume" cssClass="help-block"/>
                    </div>
                </div>
                <div class="col-md-12 com-md-offset-2 form-group ${price_error?'has-error':''}">
                    <form:label path="priceDto.price" cssClass="col-sm-2 control-label"><fmt:message key="price.price"/></form:label>
                    <div class="col-sm-4">
                        <form:input path="priceDto.price" cssClass="form-control" required="true"/>
                        <form:errors path="priceDto.price" cssClass="help-block"/>
                    </div>
                </div>
                <div class="col-md-12 com-md-offset-2 form-group">
                    <form:label path="priceDto.currency" cssClass="col-sm-2 control-label"><fmt:message key="price.currency"/></form:label>
                    <div class="col-sm-4">
                    <form:select path="priceDto.currency" cssClass="form-control">
                        <c:forEach items="${currencies}" var="c">
                            <form:option value="${c}">${c}</form:option>
                        </c:forEach>
                    </form:select>
                        <form:errors path="priceDto.currency" cssClass="error"/>
                    </div>
                </div>
                <div class="col-md-12 com-md-offset-2 form-group ${validFrom_error?'has-error':''}">
                    <form:label path="packingDto.validFrom" cssClass="col-sm-2 control-label">Valid From</form:label>
                    <div class="col-sm-4">
                        <form:input path="packingDto.validFrom" id="datepickerFrom" class="form-control" required="true"/>
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
                <div class="col-md-12 com-md-offset-2 form-group ">
                    <form:label path="priceDto.marketingEventId" cssClass="col-sm-2 control-label"><fmt:message key="marketingevent"/></form:label>
                    <div class="col-sm-4">
                    <form:select path="priceDto.marketingEventId" cssClass="form-control">
                        <c:forEach items="${marketingevents}" var="marketingevent">
                            <form:option value="${marketingevent.id}">${marketingevent.description}</form:option>
                        </c:forEach>
                    </form:select>
                    <p class="help-block"><form:errors path="priceDto.marketingEventId" cssClass="error"/></p>
                    </div>
                </div>
            </div>
            <form:hidden path="packingDto.wineId"/>
            <button class="btn btn-primary" type="submit"><fmt:message key="save"/></button>
        </form:form>
    </jsp:attribute>
</my:template>
