<%--
  Created by IntelliJ IDEA.
  User: Руслан
  Date: 11.04.2019
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
    <c:import url="date.jsp"/>
    <form action="ControlServlet" method="post" enctype="multipart/form-data">
        <input type="file" name="file"><!--<input type="text" name="file_name" value="">-->
        <table>
            <tr>
                <td>
                    <input type="submit" name="button" value="DOM">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" name="button" value="SAX">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" name="button" value="StAX">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
