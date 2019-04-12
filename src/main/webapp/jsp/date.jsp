<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Date</title>
</head>
<body>
    <jsp:useBean id="now" class="java.util.Date" />
    <fmt:setLocale value="en-EN"/>
    <fmt:formatDate value="${now}"/><br/>
    <fmt:formatDate value="${now}" type="time" timeStyle="short" />
    <hr/>
</body>
</html>
