<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>first app</title>
</head>
<body>
    <h1>First app - index</h1>
    <div>
        <a href="/list">Open list</a>
    </div>
    <div>
        <form action="/" method="post">
            <label for="input">Label:</label>
            <input type="text" name="input" id="input" value="" placeholder="Input">
            <input type="submit" value="submit">
        </form>
    </div>
</body>
</html>