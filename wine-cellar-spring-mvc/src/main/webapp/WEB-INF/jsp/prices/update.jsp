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

<own:template title="Update packing with price">
     <jsp:attribute name="scripts">
        <script>
            $(function () {
                $("#datepickerFrom").datepicker({format: 'MM/dd/yyyy'}).val();
            });
        </script>
        <script>
            $(function () {
                $("#datepickerTo").datepicker({format: 'MM/dd/yyyy'}).val();
            });
        </script>
    </jsp:attribute>

    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/prices/update"
                   modelAttribute="pricePacking" cssClass="form-horizontal">
            <div class="container">
                <div class="col-md-12 com-md-offset-2 form-group ${volume_error?'has-error':''}">
                    <form:label path="packingDto.volume" cssClass="col-sm-2 control-label">
                        <fmt:message key="packing.volumeInLiters"/>*
                    </form:label>
                    <div class="col-sm-4">
                        <form:input path="packingDto.volume" cssClass="form-control" required="true"/>
                        <form:errors path="packingDto.volume" cssClass="help-block"/>
                    </div>
                </div>
                <div class="col-md-12 com-md-offset-2 form-group ${price_error?'has-error':''}">
                    <form:label path="priceDto.price" cssClass="col-sm-2 control-label">
                        <fmt:message key="price.price"/>*
                    </form:label>
                    <div class="col-sm-4">
                        <form:input path="priceDto.price" cssClass="form-control" required="true"/>
                        <form:errors path="priceDto.price" cssClass="help-block"/>
                    </div>
                </div>
                <div class="col-md-12 com-md-offset-2 form-group">
                    <form:label path="priceDto.currency" cssClass="col-sm-2 control-label">
                        <fmt:message key="price.currency"/>*
                    </form:label>
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
                    <form:label path="packingDto.validFrom" cssClass="col-sm-2 control-label">
                        <fmt:message key="packing.validFrom"/>*
                    </form:label>
                    <div class="col-sm-4">
                        <form:input path="packingDto.validFrom" id="datepickerFrom" cssClass="form-control" required="true"/>
                        <form:errors path="packingDto.validFrom" cssClass="help-block"/>
                    </div>
                </div>
                <div class="col-md-12 com-md-offset-2 form-group ${validTo_error?'has-error':''}">
                    <form:label path="packingDto.validTo" cssClass="col-sm-2 control-label">
                        <fmt:message key="packing.validTo"/>
                    </form:label>
                    <div class="col-sm-4">
                        <form:input path="packingDto.validTo" id="datepickerTo" cssClass="form-control"/>
                        <form:errors path="packingDto.validTo" cssClass="help-block"/>
                    </div>
                </div
                <div class="col-md-12 com-md-offset-2 form-group ">
                    <form:label path="priceDto.marketingEvent.id" cssClass="col-sm-2 control-label">
                        <fmt:message key="marketingevent"/>
                    </form:label>
                    <div class="col-sm-4">
                    <form:select path="priceDto.marketingEvent.id" cssClass="form-control">
                        <form:option value=""><fmt:message key="nothingSelected"/></form:option>
                        <c:forEach items="${marketingevents}" var="marketingevent">
                            <form:option value="${marketingevent.id}">${marketingevent.description}</form:option>
                        </c:forEach>
                    </form:select>
                        <p class="help-block"><form:errors path="priceDto.marketingEvent.id" cssClass="error"/></p>
                        <br/>
                        <p align="right">
                            <button class="btn btn-primary" type="submit"><fmt:message key="update"/></button>
                                <%--<a class="btn btn-default" href="${pageContext.request.contextPath}/wines/update/${wineUpdate.id}"><fmt:message key="cancel"/></a>--%>
                        </p>
                    </div>
                </div>
            </div>
            <form:hidden path="packingDto.wine.id"/>
            <form:hidden path="packingDto.id"/>
            <form:hidden path="priceDto.id"/>
            <form:hidden path="priceDto.packing.id"/>
        </form:form>
    </jsp:attribute>
</own:template>
