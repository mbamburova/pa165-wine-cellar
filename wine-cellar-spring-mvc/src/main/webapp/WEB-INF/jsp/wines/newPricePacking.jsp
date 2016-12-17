<%--
  Created by IntelliJ IDEA.
  User: Silvia
  Date: 16/12/2016
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:template title="Create packing with price">
    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/wines/createPricePacking"
                   modelAttribute="pricePacking" cssClass="form-horizontal">

            <div class="col-md-12 com-md-offset-2 form-group ${name_error?'has-error':''}">
                <form:label path="packingDto.volume" cssClass="col-sm-2 control-label"><fmt:message key="packing.volume"/></form:label>
                <div class="col-sm-4">
                    <form:input path="packingDto.volume" cssClass="form-control" required="true"/>
                    <form:errors path="packingDto.volume" cssClass="help-block"/>
                </div>
            </div>
            <div class="col-md-12 com-md-offset-2 form-group ${batch_error?'has-error':''}">
                <form:label path="priceDto.price" cssClass="col-sm-2 control-label"><fmt:message key="price.price"/></form:label>
                <div class="col-sm-4">
                    <form:input path="priceDto.price" cssClass="form-control" required="true"/>
                    <form:errors path="priceDto.price" cssClass="help-block"/>
                </div>
            </div>
            <div class="col-md-12 com-md-offset-2 form-group ${predicate_error?'has-error':''}">
                <form:label path="packingDto.validFrom" cssClass="col-sm-2 control-label"><fmt:message key="packing.validFrom"/></form:label>
                <div class="col-sm-4">
                    <form:input path="packingDto.validFrom" cssClass="form-control" required="true"/>
                    <form:errors path="packingDto.validFrom" cssClass="help-block"/>
                </div>
            </div>
            <div class="col-md-12 com-md-offset-2 form-group ${predicateEquivalent_error?'has-error':''}">
                <form:label path="packingDto.validTo" cssClass="col-sm-2 control-label"><fmt:message key="packing.validTo"/></form:label>
                <div class="col-sm-4">
                    <form:input path="packingDto.validTo" cssClass="form-control"/>
                    <form:errors path="packingDto.validTo" cssClass="help-block"/>
                </div>
            </div>

            <button class="btn btn-primary" type="submit">Create</button>
            <br/>
            <br/>
        </form:form>
    </jsp:attribute>
</my:template>