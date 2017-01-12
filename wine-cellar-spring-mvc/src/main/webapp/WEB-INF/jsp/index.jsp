<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:template>
<jsp:attribute name="body">

    <div class="jumbotron">
        <h1>Welcome to Wine Cellar!</h1>
        <p class="lead">It's high time to get drunk from wine!</p>
    </div>

    <div>
    <c:choose>
         <c:when test="${not empty pageContext.request.remoteUser}">
             <sec:authorize access="hasRole('ROLE_ADMIN')">
                 <h2>You are logged in as an Administrator!</h2>
             </sec:authorize>
              <sec:authorize access="hasRole('ROLE_USER')">
                 <h2>Thanks for coming back!</h2>
             </sec:authorize>
        </c:when>
        <c:otherwise>
            <form:form method="POST" action="j_spring_security_check" cssClass="form-horizontal">
                <c:if test="${not empty param.error}">
                    <div class="alert alert-danger" role="alert">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        Login or password is invalid
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
                        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required="true">
                    </div>
                </div>

                <div class="col-sm-offset-3 col-sm-6">
                    <button class="btn btn-primary" type="submit"><fmt:message key="login"/></button>
                </div>
            </form:form>
        </c:otherwise>
    </c:choose>
    </div>

    <hr/>
    <div class="col-md-12 com-md-offset-2">
        <h3>Admin</h3>
        <p>Login: admin@wines.com</p>
        <p>Password: admin123</p>
    <br/>
        <h3>User</h3>
        <p>Login: user@wines.com</p>
        <p>Password: user123</p>
    </div>

 </jsp:attribute>
</my:template>

