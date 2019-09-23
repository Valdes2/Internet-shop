<%--
  Created by IntelliJ IDEA.
  User: valde
  Date: 20.09.2019
  Time: 08:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style><%@include file="/WEB-INF/styles/w3.css"%></style>
</head>
<body>
    <div class="w3-container w3-teal">
        <table class="w3-table w3-text-light-gray">
            <th class="w3-left-align w3-cell-middle"><h1>Login page</h1></th>
            <th class="w3-right-align">
                <form class="w3-margin-top" action="${pageContext.request.contextPath}/index">
                    <input class="w3-btn w3-dark-gray" type="submit" value="Main" />
                </form>
            </th>
        </table>
    </div>
    <div class="w3-container w3-text-red">
        <p>${errorMsg}</p>
    </div>
    <div class="w3-container">
        <form class="w3-container" action="${pageContext.request.contextPath}/login" method="post">
            <label class="w3-text-teal">Login
                <input class="w3-input w3-border w3-light-grey" style="width:30%" type="text" name="login"><br />
            </label>

            <label class="w3-text-teal">Password
                <input class="w3-input w3-border w3-light-grey" style="width:30%" type="password" name="pass"><br />
            </label>
            <button class="w3-btn w3-blue-grey" type="submit">Login</button>
        </form>
    </div>
</body>
</html>
