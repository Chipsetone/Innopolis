<%--
  Created by IntelliJ IDEA.
  User: smoldyrev
  Date: 24.02.17
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Лекции</title>
</head>
<body>
<h1>LECTIONS</h1>
<a href="/students/addlection?id=0">Добавить</a>
<table border="1" width="100%" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>SUBJECT</th>
        <th>TEXT</th>
        <th>GROUP</th>
        <th>OPERATION</th>
    </tr>
<c:forEach items="${lections}" var="lection">

    <tr>
        <td><c:out value="${lection.id}"></c:out></td>
        <td><c:out value="${lection.name}"></c:out></td>
        <td><c:out value="${lection.subject}"></c:out></td>
        <td><c:out value="${lection.textLection}"></c:out></td>
        <td><c:out value="${lection.groupid}"></c:out></td>

        <td><a href="/students/addlection?id=${lection.id}">edit</a>
            /
            <a href="/students/deletelection?id=${lection.id}">del</a></td>
    </tr>
</c:forEach>
</table>
</body>
</html>
