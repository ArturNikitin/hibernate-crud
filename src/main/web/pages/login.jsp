<%--
  Created by IntelliJ IDEA.
  User: Artur
  Date: 18.05.2020
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
    <p>Hello User</p>
    <form method="post" action="login" enctype="application/x-www-form-urlencoded">
        <p>
            Email
        </p>
        <p>
            <input type="text" name ="email">
        </p>
        <p>
            Password
        </p>
            <input type="password" name="password">
        <p>
            <button type = "submit">Login</button>
        </p>
    </form>
</body>
</html>
