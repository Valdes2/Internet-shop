<%--
  Created by IntelliJ IDEA.
  User: valde
  Date: 18.09.2019
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OrdersList</title>
</head>
<h1>User orders:</h1>
<table border="2">
    <tr>
        <th>ID</th>
        <th>User ID</th>
        <th>Ordered items</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.id}" />
            </td>
            <td>
                <c:out value="${order.userId}" />
            </td>
            <td>
                <c:out value="${order.orderedItems}" />
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/deleteOrder?order_id=${order.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<p>**************************</p>
<form action="${pageContext.request.contextPath}/bucket">
    <input type="submit" value="Go to bucket" />
</form>
<form action="${pageContext.request.contextPath}/index">
    <input type="submit" value="Return to main page" />
</form>
<body>

</body>
</html>
