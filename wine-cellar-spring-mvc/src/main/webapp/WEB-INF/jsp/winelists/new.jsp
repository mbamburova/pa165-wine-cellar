<%--
  Created by IntelliJ IDEA.
  User: Tomas
  Date: 12/11/2016
  Time: 1:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time"  %>

<my:template title="Create new tasting tickes">
<jsp:attribute name="scripts">
    <script>
        $(function () {
            $("#datepicker").datepicker({ format: 'MM/dd/yyyy' }).val();
        });
    </script>
</jsp:attribute>

<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/winelists/create"
               modelAttribute="wineListCreate" cssClass="form-horizontal">

        <div class="col-md-12 com-md-offset-2 form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-4">
                <form:input path="name" class="form-control" required="true"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>

        <div class="col-md-12 com-md-offset-2 form-group ${date_error?'has-error':''}">
            <form:label path="date" cssClass="col-sm-2 control-label">Date</form:label>
            <div class="col-sm-4">
                <form:input path="date" id="datepicker" class="form-control" required="true"/>
                <form:errors path="date" cssClass="help-block"/>
            </div>
        </div>

        <div class="col-md-12 com-md-offset-2 form-group ">
            <form:label path="marketingEventId" cssClass="col-sm-2 control-label"><fmt:message key="marketingevent"/></form:label>
            <div class="col-sm-4">
                <form:select path="marketingEventId" cssClass="form-control" items="${marketingEvents}"
                             itemLabel="description" itemValue="id" />
                <p class="help-block"><form:errors path="marketingEventId" cssClass="error"/></p>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Create WineList</button>
    </form:form>

</jsp:attribute>
</my:template>
