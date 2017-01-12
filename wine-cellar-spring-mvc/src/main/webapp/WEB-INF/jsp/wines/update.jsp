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

<my:template title="Update wine">
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-sm-6">
                <form:form method="post" action="${pageContext.request.contextPath}/wines/update/${wineUpdate.id}"
                           modelAttribute="wineUpdate" cssClass="form-horizontal">

                     <div class="col-md-12 com-md-offset-2 form-group ${name_error?'has-error':''}">
                         <form:label path="name" cssClass="col-sm-4 control-label">Name</form:label>
                         <div class="col-sm-8">
                             <form:input path="name" cssClass="form-control" required="true" />
                             <form:errors path="name" cssClass="help-block"/>
                         </div>
                     </div>
                     <div class="col-md-12 com-md-offset-2 form-group ${vintage_error?'has-error':''}">
                         <form:label path="vintage" cssClass="col-sm-4 control-label">Vintage</form:label>
                         <div class="col-sm-8">
                             <form:select class="form-control" path="vintage" >
                                 <c:forEach items="${vintageValues}" var="year" >
                                    <option><c:out value="${year}" /></option>
                                 </c:forEach>
                             </form:select>
                        <form:errors path="vintage" cssClass="help-block"/>
                         </div>
                     </div>
                     <div class="col-md-12 com-md-offset-2 form-group ${batch_error?'has-error':''}">
                        <form:label path="batch" cssClass="col-sm-4 control-label">Batch</form:label>
                        <div class="col-sm-8">
                            <form:input path="batch" cssClass="form-control" required="true" />
                            <form:errors path="batch" cssClass="help-block"/>
                        </div>
                     </div>
                     <div class="col-md-12 com-md-offset-2 form-group ${predicate_error?'has-error':''}">
                        <form:label path="predicate" cssClass="col-sm-4 control-label">Predicate</form:label>
                        <div class="col-sm-8">
                            <form:input path="predicate" cssClass="form-control"/>
                            <form:errors path="predicate" cssClass="help-block"/>
                        </div>
                     </div>
                        <div class="col-md-12 com-md-offset-2 form-group ${predicateEquivalent_error?'has-error':''}">
                            <form:label path="predicateEquivalent" cssClass="col-sm-4 control-label">PredicateEquivalent</form:label>
                            <div class="col-sm-8">
                                <form:input path="predicateEquivalent" cssClass="form-control"/>
                                <form:errors path="predicateEquivalent" cssClass="help-block"/>
                            </div>
                     </div>
                     <div class="col-md-12 com-md-offset-2 form-group ${description_error?'has-error':''}">
                            <form:label path="description" cssClass="col-sm-4 control-label">Description</form:label>
                            <div class="col-sm-8">
                                <form:textarea cols="80" rows="4" path="description" cssClass="form-control"/>
                                <form:errors path="description" cssClass="help-block"/>
                            </div>
                     </div>
                     <div class="col-md-12 com-md-offset-2 form-group ${notes_error?'has-error':''}">
                            <form:label path="notes" cssClass="col-sm-4 control-label">Notes</form:label>
                            <div class="col-sm-8">
                                <form:input cols="80" path="notes" cssClass="form-control"/>
                                <form:errors path="notes" cssClass="help-block"/>
                            </div>
                     </div>
                     <div class="col-md-12 com-md-offset-2 form-group ${alcoholVolume_error?'has-error':''}">
                            <form:label path="alcoholVolume" cssClass="col-sm-4 control-label">AlcoholVolume</form:label>
                            <div class="col-sm-8">
                                <form:input path="alcoholVolume" cssClass="form-control"/>
                                <form:errors path="alcoholVolume" cssClass="help-block"/>
                            </div>
                     </div>
                     <div class="col-md-12 com-md-offset-2 form-group ${residualSugar_error?'has-error':''}">
                            <form:label path="residualSugar" cssClass="col-sm-4 control-label">ResidualSugar</form:label>
                            <div class="col-sm-8">
                                <form:input path="residualSugar" cssClass="form-control"/>
                                <form:errors path="residualSugar" cssClass="help-block"/>
                            </div>
                     </div>
                     <div class="col-md-12 com-md-offset-2 form-group ${acidity_error?'has-error':''}">
                            <form:label path="acidity" cssClass="col-sm-4 control-label">Acidity</form:label>
                            <div class="col-sm-8">
                                <form:input path="acidity" cssClass="form-control"/>
                                <form:errors path="acidity" cssClass="help-block"/>
                            </div>
                     </div>
                     <div class="col-md-12 com-md-offset-2 form-group ${grapeSugarContent_error?'has-error':''}">
                            <form:label path="grapeSugarContent" cssClass="col-sm-4 control-label">GrapeSugarContent</form:label>
                            <div class="col-sm-8">
                                <form:input path="grapeSugarContent" cssClass="form-control"/>
                                <form:errors path="grapeSugarContent" cssClass="help-block"/>
                            </div>
                     </div>
                     <form:hidden path="id"/>
                    <div>
                        <button class="btn btn-primary" type="submit">Update wine</button>
                    </div>
                     <br/>
                     <br/>
                </form:form>
            </div>

            <div class="col-sm-6">
                <h3><fmt:message key="priceList"/>
                    <div>
                        <br/>
                        <a class="btn btn-primary btn-success btn-jumbotron" href="${pageContext.request.contextPath}/prices/new/${wineUpdate.id}" role="button">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                            <fmt:message key="createPricePacking"/>
                        </a>
                    </div>
                </h3>
                <br/>
                <br/>
                <c:choose>
                    <c:when test="${empty pricePackings}">
                        <h5><em>The price for this wine hasn't been assinged yet</em></h5>
                        <br />
                    </c:when>
                    <c:otherwise>
                        <table class="table table-hover col-md-3">
                            <thead>
                            <tr>
                                <th><fmt:message key="price.price"/></th>
                                <th><fmt:message key="packing.volume"/></th>
                                <th><fmt:message key="packing.validFrom"/></th>
                                <th><fmt:message key="packing.validTo"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pricePackings}" var="item">
                            <tr>
                                <td><c:out value="${item.priceDto.price}"/> ${item.priceDto.currency}</td>
                                <td><c:out value="${item.packingDto.volume}"/></td>
                                <td><javatime:format value="${item.packingDto.validFrom}" pattern="dd.MM.yyyy"/></td>
                                <td><javatime:format value="${item.packingDto.validTo}" pattern="dd.MM.yyyy"/></td>
                                <form:form method="get" action="${pageContext.request.contextPath}/prices/update" cssClass="form-horizontal">
                                    <input type="hidden" name="priceId" value="${item.priceDto.id}">
                                    <input type="hidden" name="packingId" value="${item.packingDto.id}">
                                    <td class="col-xs-1 text-center">
                                        <button class="btn btn-default" type="submit">
                                            <span class="sr-only"><fmt:message key="edit"/></span>
                                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                        </button>
                                    </td>
                                </form:form>

                                <form:form method="post" action="${pageContext.request.contextPath}/prices/delete" cssClass="form-horizontal">
                                    <input type="hidden" name="priceId" value="${item.priceDto.id}">
                                    <input type="hidden" name="packingId" value="${item.packingDto.id}">
                                    <td class="col-xs-1 text-center">
                                        <button class="btn btn-default" type="submit">
                                            <span class="sr-only"><fmt:message key="remove"/></span>
                                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                        </button>
                                    </td>
                                </form:form>
                            </tr>
                         </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </jsp:attribute>
</my:template>