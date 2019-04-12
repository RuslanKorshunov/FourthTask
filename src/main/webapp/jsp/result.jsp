<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="CustomTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
    <c:import url="date.jsp"/>
    <form action="ControlServlet">
        <ctg:table gems="${ gems }"></ctg:table>
        <input type="submit" value="Go back">
    </form>
</body>
</html>
