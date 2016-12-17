<%--
  Created by IntelliJ IDEA.
  User: Silvia
  Date: 16/12/2016
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<my:template>
   <jsp:attribute name="body">
       <div class="jumbotron">
           <h3><c:out value="${wine.name}"/></h3>
           <h5><c:out value="${wine.vintage}"/></h5>
       </div>

       <div class="row">
          <div class="row">
              <table class="table col-md-3">
              <tbody>
              <tr>
                  <td><b><fmt:message key="wine.predicate"/>: </b></td>
                  <td><c:out value="${wine.predicate}" /></td>
              </tr>
              <tr>
                  <td><b><fmt:message key="wine.predicateEquivalent"/>: </b></td>
                  <td><c:out value="${wine.predicateEquivalent}" /></td>
              </tr>
              <tr>
                  <td><b><fmt:message key="wine.batch"/>: </b></td>
                  <td><c:out value="${wine.batch}" /></td>
              </tr>
              <tr>
                  <td><b><fmt:message key="wine.alcoholVolume"/>: </b></td>
                  <td><c:out value="${wine.alcoholVolume}" /></td>
              </tr>
              <tr>
                  <td><b><fmt:message key="wine.acidity"/>: </b></td>
                  <td><c:out value="${wine.acidity}" /></td>
              </tr>
              <tr>
                  <td><b><fmt:message key="wine.residualSugar"/>: </b></td>
                  <td><c:out value="${wine.residualSugar}" /></td>
              </tr>
              <tr>
                  <td><b><fmt:message key="wine.grapeSugarContent"/>: </b></td>
                  <td><c:out value="${wine.grapeSugarContent}" /></td>
              </tr>
              <tr>
                  <td><b><fmt:message key="wine.description"/>: </b></td>
                  <td><c:out value="${wine.description}" /></td>
              </tr>
              <tr>
                  <td><b><fmt:message key="wine.notes"/>: </b></td>
                  <td><c:out value="${wine.notes}" /></td>
              </tr>
              </tbody>
              </table>
          </div>

          <div>
              <table class="col-md-3">
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
                        <td><c:out value="${item.packingDto.validFrom}"/></td>
                        <td><c:out value="${item.packingDto.validTo}"/></td>
                    </tr>
                </c:forEach>
              </tbody>
              </table>
          </div>
       </div>
   </jsp:attribute>
</my:template>
