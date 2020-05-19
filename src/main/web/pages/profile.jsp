<%--
  Created by IntelliJ IDEA.
  User: Artur
  Date: 19.05.2020
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="form" type="servlets.ProfileForm" scope="request"/>
<html>
<head>
    <title>Profile</title>
</head>
<body>
    <P>
        Hello, ${form.name} ${form.lastName}!
    </P>
    <c:choose>
        <c:when test="${not empty form.projects}">
            <p>
                You are in following projects:
            </p>
            <ul>
                <c:forEach items="${form.projects}" var="project">
                    <li>${project.projectName}</li>
                </c:forEach>
            </ul>

        </c:when>
        <c:otherwise>
            <p>
                You currently are not involved in any projects:(
            </p>
        </c:otherwise>
    </c:choose>

    <form method="post" action="profile" enctype="application/x-www-form-urlencoded">
        <button type="submit">Log out</button>
    </form>
</body>
</html>
