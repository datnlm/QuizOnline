<%-- 
    Document   : history.jsp
    Created on : Feb 7, 2021, 3:16:18 AM
    Author     : macbook
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
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
        <h1>Your History!</h1>

        <c:set var="list" value="${requestScope.LIST}"></c:set>
        <c:set var="listSubject" value="${sessionScope.LIST_SUBJECT}"></c:set>  
            <form action="ViewHistoryController" method="POST">
                Search <select class="form-control ml-3" name="cbxSubject" style= "width: 150px">
                    <option>Subject</option>
                <c:forEach var="dto" items="${listSubject}">
                    <c:if test="${param.cbxSubject == dto.subjectName}">
                        <option selected="selected">${dto.subjectName}</option>
                    </c:if>
                    <c:if test="${param.cbxSubject != dto.subjectName}">
                        <option>${dto.subjectName}</option>

                    </c:if>
                </c:forEach>
            </select>
            <input type="submit" name="btnAction" value="SearchSubject">
        </form>
        <c:if test="${list != null && not empty list}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Subject ID</th>
                        <th>Number or correct</th>
                        <th>Total</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var= "dto" items="${list}" varStatus="counter">
                    <form action="ViewHistoryController" method="POST">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.subjectID}</td>
                            <td>${dto.number_of_correct}</td>
                            <td>${dto.total}</td>
                            <td>${dto.createDate}</td>
                            <td>
                                <input type="submit" name="btnAction" value="ViewDetail">
                                <input type="hidden" name="txtHistoryID" value="${dto.id}">
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${list == null && empty list}">
        <h1>You have not taken any test</h1>
    </c:if>
    <c:url var="user" value="UserController">
    </c:url>
    <a href="${user}">Back to home</a>
</body>
</html>
