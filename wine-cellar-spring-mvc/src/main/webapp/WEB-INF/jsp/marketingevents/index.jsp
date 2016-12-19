<%--
  Created by IntelliJ IDEA.
  User: MarekScholtz
  Date: 9.12.2016
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:template title="Marketing Events">
    <jsp:attribute name="body">
        <c:choose>
              <c:when test="${empty winelists}">
                   <h4>There are no marketing events.</h4>
                   <br />
              <c:if test="${loggedUser.isAdmin()}">
                    <my:a href="/marketingevents/new" class="btn btn-primary">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                    New event
                    </my:a>
              </c:if>
               </c:when>
               <c:otherwise>
                <c:if test="${loggedUser.isAdmin()}">
                        <my:a href="/marketingevents/new" class="btn btn-primary">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                            New event
                       </my:a>
                </c:if>

                   <table class="table">
                       <thead>
                       <tr>
                           <th class="text-center"><fmt:message key="number"/></th>
                           <th class="text-center"><fmt:message key="marketingevent.description"/></th>
                           <th class="text-center"><fmt:message key="edit"/></th>
                           <th class="text-center"><fmt:message key="delete"/></th>
                       </tr>
                       </thead>
                       <tbody>
                       <c:forEach items="${marketingevents}" var="marketingevent">
                            <c:set var="count" value="${count + 1}" scope="page"/>
                            <tr>
                                <td>${count}.</td>
                                <td class="col-xs-3 text-center"><c:out value="${marketingevent.description}"/></td>

                                <form:form method="get" action="${pageContext.request.contextPath}/marketingevents/update/${marketingevent.id}" cssClass="form-horizontal">
                                    <td class="col-xs-1 text-center">
                                        <button class="btn btn-default" type="submit">
                                            <span class="sr-only"><fmt:message key="edit"/></span>
                                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                        </button>
                                    </td>
                                </form:form>

                                <form:form method="post" action="${pageContext.request.contextPath}/marketingevents/delete/${marketingevent.id}" cssClass="form-horizontal">
                                    <td class="col-xs-1 text-center">
                                        <button class="btn btn-default" type="submit">
                                            <span class="sr-only"><fmt:message key="delete"/></span>
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
        <br/>
    </jsp:attribute>
</my:template>