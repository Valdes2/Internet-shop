<%--
  Created by IntelliJ IDEA.
  User: valde
  Date: 16.09.2019
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Internetshop_v3</title>
    <style><%@include file="/WEB-INF/styles/w3.css"%></style>

  </head>
  <body class="w3-light-grey">
  <div class="w3-container w3-teal">
    <table class="w3-table w3-text-light-gray">
      <th class="w3-left-align w3-cell-middle"><h1>Main</h1></th>
        <th class="w3-right-align">
            <form class="w3-margin-top" action="${pageContext.request.contextPath}/inject">
                <input class="w3-btn w3-dark-gray" type="submit" value="inject" />
            </form>
        </th>
      <th class="w3-right-align">
        <form class="w3-margin-top" action="${pageContext.request.contextPath}/logout">
          <input class="w3-btn w3-dark-gray" type="submit" value="Logout" />
        </form>
      </th>
    </table>
  </div>
    <div class="w3-container w3-centered">
      <div class="w3-bar w3-padding-large w3-padding-24">
        <form action="${pageContext.request.contextPath}/addUser">
          <input class="w3-button w3-block w3-dark-gray" style="width:30%" type="submit" value="Registration" />
        </form>
        <form action="${pageContext.request.contextPath}/login">
          <input class="w3-button w3-block w3-dark-gray" style="width:30%" type="submit" value="Login" />
        </form>
        <form action="${pageContext.request.contextPath}/servlet/testData">
          <input class="w3-button w3-block  w3-dark-gray" style="width:30%" type="submit" value="Create test data" />
        </form>
        <form action="${pageContext.request.contextPath}/servlet/allUsers">
          <input class="w3-button w3-block w3-dark-gray" style="width:30%" type="submit" value="All Users" />
        </form>
        <form action="${pageContext.request.contextPath}/getAllItems">
          <input class="w3-button w3-block w3-dark-gray" style="width:30%" type="submit" value="All items" />
        </form>
        <form action="${pageContext.request.contextPath}/servlet/bucket">
          <input class="w3-button w3-block w3-dark-gray" style="width:30%" type="submit" value="Bucket" />
        </form>
        <form action="${pageContext.request.contextPath}/servlet/ordersList">
          <input class="w3-button w3-block w3-dark-gray" style="width:30%" type="submit" value="Orders" />
        </form>
      </div>
    </div>

  </body>
</html>
