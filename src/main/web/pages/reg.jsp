<%--
  Created by IntelliJ IDEA.
  User: Artur
  Date: 19.05.2020
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="form" type="servlets.RegistrationForm" scope="request" />
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <form action="reg" method="post" enctype="application/x-www-form-urlencoded">
        <p>
            First Name
        </p>
        <p>
            <input type="text" name="name">
        </p>
        <p>
            Last Name
        </p>
        <p>
            <input type="text" name="lastName">
        </p>
        <p>
            Email
        </p>
        <p>
            <input type="text" name="email">
        </p>
        <p>
            <label>
                Password
                <input type="password" name="password">
            </label>
        </p>
        <p>
            <label>
                Group:
                <select name="role">
                    <c:forEach items="${form.roles}" var="role">
                        <option value="${role.roleName}">${role.roleName}</option>
                    </c:forEach>
                </select>
            </label>
        </p>
        <p>
            <button type="submit">Sign up</button>
        </p>
    </form>
</body>
</html>
