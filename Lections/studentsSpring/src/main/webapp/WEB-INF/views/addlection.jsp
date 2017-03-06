<%--
  Created by IntelliJ IDEA.
  User: smoldyrev
  Date: 24.02.17
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>добавляем лекцию</title>
</head>
<body>
<form action="/students/addlection" method="post">
    <label for="id">id:</label>
    <input type="text" name="id" id="id" value="${id}" placeholder="id">

    <label for="name">name:</label>
    <input type="text" name="name" id="name" value="${name}" placeholder="FIO">

    <label for="subject">subject:</label>
    <input type="text" name="subject" id="subject" value="${subject}" placeholder="subject">

    <label for="textLection">textlection:</label>
    <input type="text" name="textLection" id="textLection" value="${textLection}" placeholder="textLection">

    <label for="datetime">time:</label>
    <input type="date" name="datetime" id="datetime" value="${date}" placeholder="datetime">

    <label for="groupid">Group:</label>
    <input type="text" name="groupid" id="groupid" value="${groupid}" placeholder="groupid">

    <input type="submit" value="ok" formmethod="post">
</form>
</body>
</html>
