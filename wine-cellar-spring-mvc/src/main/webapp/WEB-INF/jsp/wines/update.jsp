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

<my:template title="Update wine">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/wines/update/${wineUpdate.id}"
               modelAttribute="wineUpdate" cssClass="form-horizontal">

        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${vintage_error?'has-error':''}">
            <form:label path="vintage" cssClass="col-sm-2 control-label">Vintage</form:label>
            <div class="col-sm-10">
                <form:input path="vintage" cssClass="form-control"/>
                <form:errors path="vintage" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${batch_error?'has-error':''}">
            <form:label path="batch" cssClass="col-sm-2 control-label">Batch</form:label>
            <div class="col-sm-10">
                <form:input path="batch" cssClass="form-control"/>
                <form:errors path="batch" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${predicate_error?'has-error':''}">
            <form:label path="predicate" cssClass="col-sm-2 control-label">Predicate</form:label>
            <div class="col-sm-10">
                <form:input path="predicate" cssClass="form-control"/>
                <form:errors path="predicate" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${predicateEquivalent_error?'has-error':''}">
            <form:label path="predicateEquivalent" cssClass="col-sm-2 control-label">PredicateEquivalent</form:label>
            <div class="col-sm-10">
                <form:input path="predicateEquivalent" cssClass="form-control"/>
                <form:errors path="predicateEquivalent" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${description_error?'has-error':''}">
            <form:label path="description" cssClass="col-sm-2 control-label">Description</form:label>
            <div class="col-sm-10">
                <form:input cols="80" rows="10" path="description" cssClass="form-control"/>
                <form:errors path="description" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${notes_error?'has-error':''}">
            <form:label path="notes" cssClass="col-sm-2 control-label">Notes</form:label>
            <div class="col-sm-10">
                <form:textarea cols="80" rows="10" path="notes" cssClass="form-control"/>
                <form:errors path="notes" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${alcoholVolume_error?'has-error':''}">
            <form:label path="alcoholVolume" cssClass="col-sm-2 control-label">AlcoholVolume</form:label>
            <div class="col-sm-10">
                <form:input path="alcoholVolume" cssClass="form-control"/>
                <form:errors path="alcoholVolume" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${residualSugar_error?'has-error':''}">
            <form:label path="residualSugar" cssClass="col-sm-2 control-label">ResidualSugar</form:label>
            <div class="col-sm-10">
                <form:input path="residualSugar" cssClass="form-control"/>
                <form:errors path="residualSugar" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${acidity_error?'has-error':''}">
            <form:label path="acidity" cssClass="col-sm-2 control-label">Acidity</form:label>
            <div class="col-sm-10">
                <form:input path="acidity" cssClass="form-control"/>
                <form:errors path="acidity" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${grapeSugarContent_error?'has-error':''}">
            <form:label path="grapeSugarContent" cssClass="col-sm-2 control-label">GrapeSugarContent</form:label>
            <div class="col-sm-10">
                <form:input path="grapeSugarContent" cssClass="form-control"/>
                <form:errors path="grapeSugarContent" cssClass="help-block"/>
            </div>
        </div>

        <button class="btn btn-primary" type="submit">Update wine</button>
    </form:form>

</jsp:attribute>
</my:template>