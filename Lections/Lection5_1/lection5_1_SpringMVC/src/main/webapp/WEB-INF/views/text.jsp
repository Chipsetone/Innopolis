<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Text</title>
</head>
<body>
    <h1>${myText}</h1>
    <h2>${car.model}</h2>

    <a ref="/text/showfullcar">show full car</a>
    <form action="/text" method="post">
        <input name="param1" type="text"/>
        <%--<input name="car" type="hidden"--%>
        <input type="submit" value="submit"/>
    </form>

    <spring:form method="post"  modelAttribute="car" action="/text/showfullcar">
        <spring:button>Next Page</spring:button>
    </spring:form>
</body>
</html>
