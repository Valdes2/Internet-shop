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
    <style><%@include file="/WEB-INF/styles/w3.css"%></style>
</head>
<body>
    <div class="w3-container w3-teal">
        <table class="w3-table w3-text-light-gray">
            <th class="w3-left-align w3-cell-middle"><h1>Bucket</h1></th>
            <th class="w3-right-align">
                <form class="w3-margin-top" action="${pageContext.request.contextPath}/index">
                    <input class="w3-btn w3-dark-gray" type="submit" value="Main" />
                </form>
            </th>
        </table>
    </div>
    <div class="w3-container">
        <form action="${pageContext.request.contextPath}/getAllItems">
            <input class="w3-btn w3-dark-gray" type="submit" value="To items" />
        </form>
    </div>
    <div class="w3-container">
        <table class="w3-table w3-striped">
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
                        <a class="w3-btn w3-greyscale" href="${pageContext.request.contextPath}/deleteFromBucket?item_id=${item.id}">Remove</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="w3-container">
        <form action="${pageContext.request.contextPath}/completeOrder">
            <input class="w3-btn w3-dark-gray" type="submit" value="Complete order" />
        </form>
    </div>
</body>
</html>
