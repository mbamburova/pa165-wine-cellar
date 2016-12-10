<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:template>
<jsp:attribute name="body">

    <div class="jumbotron">
        <h1>Welcome to Wine Cellar!</h1>
        <p class="lead">It's high time to get drunk from wine!</p>
        <p><a class="btn btn-lg btn-success" href="winelists/index"
              role="button">Tasting Ticket</a></p>
        <p><a class="btn btn-lg btn-success" href="wines/index"
              role="button">Complete offer</a></p>
    </div>

</jsp:attribute>
</my:template>