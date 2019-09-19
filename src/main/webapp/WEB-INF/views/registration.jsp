<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <style>
        <%@include file="/WEB-INF/styles/w3.css"%>
    </style>
</head>
<body>
    <div class="w3-container w3-teal">
        <h2>Registration</h2>
    </div>
    <div class="w3-container">
        <form class="w3-container" action="${pageContext.request.contextPath}/registration" method="post">
            <label class="w3-text-teal">Name
                <input class="w3-input w3-border w3-light-grey" style="width:30%" type="text" name="name"><br />
            </label>

            <label class="w3-text-teal">Password
                <input class="w3-input w3-border w3-light-grey" style="width:30%" type="password" name="pass"><br />
            </label>
            <button class="w3-btn w3-blue-grey" type="submit">Enter</button>
        </form>
    </div>
    <div class="w3-container">
        <form action="${pageContext.request.contextPath}/index">
            <input class="w3-btn w3-dark-gray" type="submit" value="Return to main page" />
        </form>
    </div>
</body>
</html>