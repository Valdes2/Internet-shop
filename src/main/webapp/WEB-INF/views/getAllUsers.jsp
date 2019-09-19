<jsp:useBean id="users" scope="request" type="java.util.List<mate.academy.internetshop.model.User>"/>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>getAllUsers</title>
</head>
<body>
<h1>All users:</h1>
<form action="${pageContext.request.contextPath}/registration">
    <input type="submit" value="Add new user" />
</form>
<table border="2">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Password</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                <c:out value="${user.id}" />
            </td>
            <td>
                <c:out value="${user.name}" />
            </td>
            <td>
                <c:out value="${user.password}" />
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/deleteUser?user_id=${user.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<p>**************************</p>
<form action="${pageContext.request.contextPath}/index?">
    <input type="submit" value="Return to main page" />
</form>
<div>
    <button onclick="location.href=/index">Back to main</button>
</div>
</body>
</html>