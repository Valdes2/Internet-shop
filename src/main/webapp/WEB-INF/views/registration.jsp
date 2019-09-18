<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h2>List of users:</h2>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <label>Name:
        <input type="text" name="name"><br />
    </label>

    <label>Password:
        <input type="password" name="pass"><br />
    </label>
    <button type="submit">Enter</button>
</form>
<form action="${pageContext.request.contextPath}/index">
    <input type="submit" value="Return to main page" />
</form>
</body>
</html>