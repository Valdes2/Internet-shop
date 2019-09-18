<%--
  Created by IntelliJ IDEA.
  User: valde
  Date: 18.09.2019
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bucket</title>
</head>
<body>
<h1>Items in bucket:</h1>
<form action="${pageContext.request.contextPath}/getAllItems">
    <input type="submit" value="Return to All items" />
</form>
<table border="2">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Remove</th>
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
                <a href="${pageContext.request.contextPath}/deleteFromBucket?item_id=${item.id}">Remove</a>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="${pageContext.request.contextPath}/completeOrder">
    <input type="submit" value="Complete order" />
</form>
<p>**************************</p>
<form action="${pageContext.request.contextPath}/index">
    <input type="submit" value="Return to main page" />
</form>
</body>
</html>
