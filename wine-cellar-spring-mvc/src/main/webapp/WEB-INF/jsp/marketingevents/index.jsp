<%--
  Created by IntelliJ IDEA.
  User: MarekScholtz
  Date: 9.12.2016
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:template title="Marketing events">
    <jsp:attribute name="body">
         <my:a href="/marketingevents/new" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
             New event
        </my:a>
        <c:choose>
              <c:when test="${empty marketingevents}">
                  <h5><em>There are no marketing events</em></h5>
                  <br />
               </c:when>
               <c:otherwise>
                   <table class="table table-hover">
                       <thead>
                       <tr>
                           <th class="text-left"><fmt:message key="number"/></th>
                           <th class="text-left"><fmt:message key="marketingevent.description"/></th>
                           <th class="text-left"><fmt:message key="edit"/></th>
                           <th class="text-left"><fmt:message key="delete"/></th>
                       </tr>
                       </thead>
                       <tbody>
                       <c:forEach items="${marketingevents}" var="marketingevent">
                            <c:set var="count" value="${count + 1}" scope="page"/>
                            <tr>
                                <td class="col-xs-3 text-left">${count}.</td>
                                <td class="col-xs-3 text-left"><c:out value="${marketingevent.description}"/></td>

                                <form:form method="get" action="${pageContext.request.contextPath}/marketingevents/update/${marketingevent.id}" cssClass="form-horizontal">
                                    <td class="col-xs-1 text-left">
                                        <button class="btn btn-default" type="submit">
                                            <span class="sr-only"><fmt:message key="edit"/></span>
                                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                        </button>
                                    </td>
                                </form:form>

                                <form:form method="post" action="${pageContext.request.contextPath}/marketingevents/delete/${marketingevent.id}" cssClass="form-horizontal">
                                    <td class="col-xs-1 text-left">
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
    </jsp:attribute>
</my:template>