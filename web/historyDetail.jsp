<%-- 
    Document   : historyDetail
    Created on : Feb 7, 2021, 3:53:37 AM
    Author     : macbook
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Detail Page</title>
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
        <h1>Your detail!</h1>
        <c:set var="list" value="${requestScope.LIST_DETAIL}"></c:set>
        <c:if test="${list != null && not empty list}">
            <table border="1">
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Question Content</th>
                        <th>You answer</th>
                        <th>Mark</th>
                    </tr>
                </thead>
                <tbody>   
                    <c:forEach var="dto" items="${list}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.question_content}</td>
                            <td>${dto.answer_content}</td>
                            <td>${dto.mark}</td>
                        </tr> 
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:url var="user" value="ViewHistoryController">
        </c:url>
        <a href="${user}">Back to history</a>
    </body>
</html>
