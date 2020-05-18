<%--
  Created by IntelliJ IDEA.
  User: Artur
  Date: 18.05.2020
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <c:choose>
  <c:when test="${empty sessionScope['verifiedEmail']}">
  <a href="http://localhost:8080/test/login"> login<a>
    </c:when>
    <c:otherwise>
    Hello, ${sessionScope['verifiedEmail']}!
    </c:otherwise>
  </c:choose>

  </body>
</html>
