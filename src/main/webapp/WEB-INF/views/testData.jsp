<%--
  Created by IntelliJ IDEA.
  User: valde
  Date: 18.09.2019
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TestData</title>
</head>
<body>
<h1>Create data for testing</h1>
<h3>Create new Item</h3>
<form action="${pageContext.request.contextPath}/testData" method="post">
    <label>Name:
        <input type="text" name="name"><br/>
    </label>

    <label>Price:
        <input type="text" name="price"><br/>
    </label>
    <button type="submit">Create</button>
</form>
<form action="${pageContext.request.contextPath}/index">
    <input type="submit" value="Return to main page" />
</form>
</body>
</html>
