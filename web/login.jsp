<%-- 
    Document   : login
    Created on : Jan 6, 2021, 7:01:41 PM
    Author     : macbook
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="css/style.css">
        <title>Login Page</title>
    </head>
    <body>
    <body
        <c:set var="error" value="${requestScope.ERROR}"></c:set>
            <div class="container">
                <div class="row">
                    <div class="col-md-5 mx-auto">
                        <div id="first">
                            <div class="myform form ">
                                <form action="LoginController" method="POST">
                                    <div class="logo mb-3">
                                        <div class="col-md-12 text-center">
                                            <h1>Login</h1>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label>User ID</label>
                                        <input type="text" name="txtUserID"  class="form-control" placeholder="Enter account" value="${param.txtUserID}">
                                    <font color="red"> ${error.getUserIDError()}</font>

                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input type="password" name="txtPassword" id="password"  class="form-control" placeholder="Enter Password" value="${param.txtPassword}">
                                    <font color="red">  ${error.getPasswordError()}</font>
                                </div>                  
                                <div class="col-md-12 text-center ">
                                    <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm">Login</button>
                                </div>
                            </form>
                            <div class="form-group">
                                <c:url var="SignUp" value="Register">
                                </c:url>
                                <p class="text-center">Don't have account? 
                                    <a href="${SignUp}" id="signup">Sign up here</a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>   
    </body>
</html>
