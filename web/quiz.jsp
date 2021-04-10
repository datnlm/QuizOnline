<%-- 
    Document   : quiz.jsp
    Created on : Jan 31, 2021, 1:12:35 AM
    Author     : macbook
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Online Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <c:set var="dto" value="${requestScope.QUESTIONS}"></c:set>
            <link rel="stylesheet" href="//fontawesome.io/assets/font-awesome/css/font-awesome.css">
            <form action="QuizController" method="POST">
                <h1>${sessionScope.QUESTION_NAME} - ${QUIZ_TIME} minutes</h1>
                <p style="width: 250px; font-size: 35px;color: black" id="time"></p>
                <div class="container" id="quiz">
                    <div class="row">
                        <div class="col-md-8 col-md-offset-2" id="panel1">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title text-danger">
                                        <i class="fa fa-question-circle fa-3x"></i> ${requestScope.INDEX + 1}. ${dto.question_content}
                                </h3>
                            </div>
                            <div class="panel-body two-col" style="margin-left: 30px;">
                                <div class="row">
                                    <div class="col-3">
                                        <div class="frb frb-danger margin-bottom-none">
                                            <input type="radio" id="radio-button-5" name="rdoAnswer" value="${dto.answer_content1}"  <c:if test="${requestScope.YOUR_ANSWER == dto.answer_content1}">checked="checked"</c:if>>
                                                <label for="radio-button-5">
                                                    <span class="frb-title">${dto.answer_content1}</span>
                                                <span class="frb-description"></span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-3">
                                        <div class="frb frb-danger margin-bottom-none">
                                            <input type="radio" id="radio-button-5" name="rdoAnswer" value="${dto.answer_content2}"  <c:if test="${requestScope.YOUR_ANSWER == dto.answer_content2}">checked="checked"</c:if>>
                                                <label for="radio-button-5">
                                                    <span class="frb-title">${dto.answer_content2}</span>
                                                <span class="frb-description"></span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-3">
                                        <div class="frb frb-danger margin-bottom-none">
                                            <input type="radio" id="radio-button-5" name="rdoAnswer" value="${dto.answer_content3}" <c:if test="${requestScope.YOUR_ANSWER == dto.answer_content3}">checked="checked"</c:if>>
                                                <label for="radio-button-5">
                                                    <span class="frb-title">${dto.answer_content3}</span>
                                                <span class="frb-description"></span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-3">
                                        <div class="frb frb-danger margin-bottom-none">
                                            <input type="radio" id="radio-button-5" name="rdoAnswer" value="${dto.answer_content4}" <c:if test="${requestScope.YOUR_ANSWER == dto.answer_content4}">checked="checked"</c:if>>
                                                <label for="radio-button-5">
                                                    <span class="frb-title">${dto.answer_content4}</span>
                                                <span class="frb-description"></span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-footer">
                                <div class="row">
                                    <div class="col-md-6">
                                        <button type="submit" name= "btnAction" value="Back" class="btn btn-light btn-sm btn-block">
                                            <span class="fa fa-send"></span>back</button>
                                        <h1 style="text-align: center; color: #ff0000;">${requestScope.ERROR}</h1>
                                    </div>
                                    <div class="col-md-6">
                                        <button type="submit" name="btnAction" value="Next" class="btn btn-primary btn-sm btn-block">
                                            next</button>
                                    </div>
                                    <div class="col-md-12">
                                        <button style="margin-top: 10px;" type="submit" name="btnAction" value="Submit" class="btn btn-success btn-sm btn-block" id="buttonClick">
                                            Submit</button>
                                    </div>

                                </div>
                            </div>
                            <input type="hidden" name="txtIndex" value="${requestScope.INDEX}">
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <script>
            // Set the date we're counting down to

            var distance = ${sessionScope.QUIZ_TIME} * 60 * 1000
            // Update the count down every 1 second
            var x = setInterval(function () {
                distance = distance - 1000;
                // Time calculations for days, hours, minutes and seconds
                var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                var seconds = Math.floor((distance % (1000 * 60)) / 1000);

                // Output the result in an element with id="time"
                document.getElementById("time").innerHTML = hours + "h " +
                        minutes + "m " + seconds + "s ";

                // If the count down is over, write some text 
                if (distance < 0) {
                    clearInterval(x);
                    document.getElementById("time").innerHTML = "EXPIRED";
                    document.getElementById("buttonClick").click();
                }
            }, 1000);
        </script>

    </body>
</html>
