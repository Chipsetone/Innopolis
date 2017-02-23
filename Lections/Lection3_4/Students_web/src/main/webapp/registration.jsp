<%--
  Created by IntelliJ IDEA.
  User: Chi
  Date: 23.02.2017
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Registration</h1>
    <form action="/students/registration.jsp" method="post">
        <label for="login">Login: </label>
        <input id="login" type="text" name="Login" value="" placeholder="Login" />
        <label for="password">Login: </label>
        <input id="password" type="text" name="Password" value="" placeholder="Password" />
        <label for="role">Role: </label>
        <input id="role" type="text" name="Role" value="" placeholder="Role" />

        <input type="submit" value="submit"/>
    </form>
</body>
</html>
