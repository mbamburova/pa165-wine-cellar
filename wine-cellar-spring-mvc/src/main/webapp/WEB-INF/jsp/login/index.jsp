<%--
  Created by IntelliJ IDEA.
  User: Silvia
  Date: 18/12/2016
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<my:template>
<jsp:attribute name="body">

    <form:form method="POST" action="j_spring_security_check" cssClass="form-horizontal">
        <c:if test="${not empty param.error}">
            <div class="alert alert-danger" role="alert">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            </div>
        </c:if>

        <div class="col-md-12 com-md-offset-2 form-group">
            <label for="inputEmail" class="col-sm-2 control-label sr-only"><fmt:message key="mail"/></label>
            <div class="col-sm-4">
                <input type="email" name="user" id="inputEmail" class="form-control" placeholder="Email address" required="true" autofocus="true">
            </div>
        </div>

        <div class="col-md-12 com-md-offset-2 form-group">
            <label for="inputPassword" class="col-sm-2 control-label sr-only"><fmt:message key="password"/></label>
            <div class="col-sm-4">
                <input type="password" name="pass" id="inputPassword" class="form-control" placeholder="Password" required="true">
            </div>
        </div>

        <div class="col-sm-offset-3 col-sm-6">
            <button class="btn btn-primary" type="submit"><fmt:message key="login"/></button>
        </div>
</form:form>

</jsp:attribute>
</my:template>
