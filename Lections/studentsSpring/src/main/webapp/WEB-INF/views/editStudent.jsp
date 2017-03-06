<%--
  Created by IntelliJ IDEA.
  User: smoldyrev
  Date: 23.02.17
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EditStudentForm</title>
</head>
<body>

<form action="/students/edit" method="post">
    <label for="id">id:</label>
    <input type="text" name="id" id="id" value="${id}" placeholder="id">

    <label for="name">FIO:</label>
    <input type="text" name="name" id="name" value="${name}" placeholder="FIO">

    <label for="birthdate">dd.mm.yyyy:</label>
    <input type="date" name="birthdate" id="birthdate" value="${birthdate}" placeholder="Input">

    <label for="group">Group:</label>
    <input type="text" name="group" id="group" value="${group}" placeholder="group">

    <label for="sex">Sex:</label>
    <input type="text" name="sex" id="sex" value="${sex}" placeholder="sex">

    <input type="submit" value="ok" formmethod="post">
</form>
</body>
</html>
