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
  </head>
  <body>
  <h1>Main</h1>
  <form action="${pageContext.request.contextPath}/testData">
    <input type="submit" value="Create test data" />
  </form>
  <form action="${pageContext.request.contextPath}/registration">
    <input type="submit" value="Registration" />
  </form>
  <form action="${pageContext.request.contextPath}/allUsers">
    <input type="submit" value="All Users" />
  </form>
  <form action="${pageContext.request.contextPath}/getAllItems">
    <input type="submit" value="All items" />
  </form>
  <form action="${pageContext.request.contextPath}/bucket">
    <input type="submit" value="Bucket" />
  </form>
  <form action="${pageContext.request.contextPath}/ordersList">
    <input type="submit" value="Users orders" />
  </form>
  </body>
</html>
