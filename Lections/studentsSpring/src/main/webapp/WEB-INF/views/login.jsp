<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div>
    <a href="/students/registration">Регистрация</a>
    <form action="/students/login" method="post">
        <label for="login">Login:</label>
        <input type="text" name="login" id="login" value="" placeholder="логин">
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" value="" placeholder="пароль">

        <input type="submit" value="Submit" formmethod="post">
    </form>
</div>

</body>
</html>
