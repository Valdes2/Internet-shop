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
    <style><%@include file="/WEB-INF/styles/w3.css"%></style>
</head>
<body>
    <div class="w3-container w3-teal">
        <table class="w3-table w3-text-light-gray">
            <th class="w3-left-align w3-cell-middle"><h1>Create new Item</h1></th>
            <th class="w3-right-align">
                <form class="w3-margin-top" action="${pageContext.request.contextPath}/index">
                    <input class="w3-btn w3-dark-gray" type="submit" value="Main" />
                </form>
            </th>
        </table>
    </div>
    <div class="w3-container">
        <h5 class="w3-text-teal">Add Item</h5>
        <form class="w3-container" action="${pageContext.request.contextPath}/servlet/testData" method="post">
            <label class="w3-text-teal">Name
                <input class="w3-input w3-border w3-light-grey" style="width:30%" type="text" name="name"><br/>
            </label>

            <label class="w3-text-teal">Price
                <input class="w3-input w3-border w3-light-grey" style="width:30%" type="text" name="price"><br/>
            </label>
            <button class="w3-btn w3-blue-grey" type="submit">Create</button>
        </form>
    </div>
</body>
</html>
