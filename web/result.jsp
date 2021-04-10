<%-- 
    Document   : result.jsp
    Created on : Feb 7, 2021, 12:57:09 AM
    Author     : macbook
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand">Quiz Online</a>
                </div>
                <ul class="nav navbar-nav navbar-right">
                    <li><a><span class="glyphicon glyphicon-user"></span> ${sessionScope.LOGIN_USER.userID}</a></li>
                                <c:url var="logout" value="LogoutController">
                                </c:url>
                    <li><a href="${logout}"><span class="fa fa-sign-out"></span> Logout</a></li>
                </ul>
            </div>
        </nav>
        <h1>Your result</h1>
        <h2>Subject ID: ${requestScope.SUBJECT_ID}</h2>
        <h2>Number of correct: ${requestScope.NUMBER_CORRECT}</h2>
        <c:if test="${requestScope.MARK <= 0}"><h2>Your score: 0</h2></c:if>
        <c:if test="${requestScope.MARK > 0}"><h2>Your score: ${requestScope.MARK}</h2></c:if>
        <c:url var="user" value="UserController">
        </c:url>
        <a href="${user}">Back to home</a>
    </body>
</html>
