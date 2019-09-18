<%--
  Created by IntelliJ IDEA.
  User: valde
  Date: 18.09.2019
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>getAllItems</title>
</head>
<body>
<h1>All Items:</h1>
<table border="2">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Add to bucket</th>
    </tr>
    <c:forEach var="item" items="${items}">
        <tr>
            <td>
                <c:out value="${item.id}" />
            </td>
            <td>
                <c:out value="${item.name}" />
            </td>
            <td>
                <c:out value="${item.price}" />
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/addToBucket?item_id=${item.id}">Add to bucket</a>
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
</body>
</html>
