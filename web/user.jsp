<%-- 
    Document   : user.jsp
    Created on : Feb 1, 2021, 10:01:08 PM
    Author     : macbook
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
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
        <c:if test="${requestScope.ERROR == 'taking_quiz'}">
            <script>
                alert("You are taking a quiz");
            </script>
        </c:if>
        <c:set var="list" value="${sessionScope.LIST_SUBJECT}"></c:set>
            <h1 style="text-align: center;"> Take a quiz </h1>
            <div style="margin-left: 30%;">
            <c:if test="${list != null || not empty list}">
                <c:forEach var="dto" items="${list}">
                    <form action="MainQuizController" method="POST">
                        <button type="submit" class="btn btn-primary btn-lg btn-block" style="width: 50%; margin-bottom: 10px;">${dto.subjectName} - Time: ${dto.time}'</button>
                        <input type="hidden" name="txtSubjectID" value="${dto.subjectID}">
                        <input type="hidden" name="txtTime" value="${dto.time}">
                        <input type="hidden" name="txtQuantity" value="${dto.quantity}">
                    </form>

                </c:forEach> 
            </c:if>
            <form action="ViewHistoryController" method="POST">
                <button type="submit" class="btn btn-light btn-lg btn-block" style="width: 50%; margin-bottom: 10px;">View history</button>
            </form>
        </div>


    </body>
</html>
