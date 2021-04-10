<%-- 
    Document   : manager.jsp
    Created on : Jan 27, 2021, 8:56:00 AM
    Author     : macbook
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Manager Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    </head>
    <body>
        <c:set var="list" value="${requestScope.LIST_QUESTIONS}"></c:set>
        <c:set var="listStatus" value="${sessionScope.LIST_STATUS}"></c:set>  
        <c:set var="listSubject" value="${sessionScope.LIST_SUBJECT}"></c:set>  
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand">Quiz Online</a>
                    </div>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a><span class="glyphicon glyphicon-user"></span> ${sessionScope.LOGIN_USER.roleID}</a></li>
                                <c:url var="logout" value="LogoutController">
                                </c:url>
                    <li><a href="${logout}"><span class="fa fa-sign-out"></span> Logout</a></li>
                </ul>
            </div>
        </nav>
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">Quiz Online Manager</h1>
            </div>
        </section>


        <h1 class="text-center">   
            <font color="red">${requestScope.ERROR}</font>
        </h1>
        <div class="container mb-4">
            <div class="row">
                <div class="col-12">
                    <div class="table-responsive">
                        <table>
                            <th> Add new </th>
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">Subject</th>
                                        <th scope="col">Question</th>
                                        <th scope="col">Answer content</th>
                                        <th scope="col" class="text-right">Mark</th>
                                        <th></th>
                                        <th scope="col" class="text-left"></th>
                                        <th> </th>
                                    </tr>
                                </thead>
                                <tbody>
                                <form action="CreateController" method="POST">
                                    <tr>
                                        <c:set var="error" value="${requestScope.ERROR_QUESTION}"></c:set>
                                            <td>
                                                <select class="form-control ml-3" name="cbxSubjectAdd" style= "width: 120px">
                                                    <option>Subject</option>
                                                <c:forEach var="subject" items="${listSubject}">
                                                    <c:if test="${param.cbxSubjectAdd == subject}">
                                                        <option selected="selected">${subject}</option>
                                                    </c:if>
                                                    <c:if test="${param.cbxSubjectAdd != subject}">
                                                        <option>${subject}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                            <p class="text-center">   
                                                <font color="red">${error.getSubjectIDError()}</font>
                                            </p>
                                        </td>
                                        <td>
                                            <input type="text" name="txtQuestionContentAdd" value="${param.txtQuestionContentAdd}">
                                            <p class="text-left">   
                                                <font color="red">${error.getQuestion_contentError()}</font>
                                            </p>
                                        </td>
                                        <td>

                                            <input type="radio" name="rdoAdd" value="answer1" checked="checked"> <input style="margin-top: 5px;" class="form-control" type="text" name="txtAnswerContent1Add" value="${param.txtAnswerContent1Add}">
                                            <p class="text-center">   
                                                <font color="red">${error.getAnswer_content1Error()}</font>
                                            </p>
                                            <input type="radio" name="rdoAdd" value="answer2"> <input style="margin-top: 5px;" class="form-control" type="text" name="txtAnswerContent2Add" value="${param.txtAnswerContent2Add}">
                                            <p class="text-center">   
                                                <font color="red">${error.getAnswer_content2Error()}</font>
                                            </p>
                                            <input type="radio" name="rdoAdd" value="answer3"> <input style="margin-top: 5px;" class="form-control" type="text" name="txtAnswerContent3Add" value="${param.txtAnswerContent3Add}">
                                            <p class="text-center">   
                                                <font color="red">${error.getAnswer_content3Error()}</font>
                                            </p>
                                            <input type="radio" name="rdoAdd" value="answer4"> <input style="margin-top: 5px;" class="form-control" type="text" name="txtAnswerContent4Add" value="${param.txtAnswerContent4Add}">
                                            <p class="text-center">   
                                                <font color="red">${error.getAnswer_content4Error()}</font>
                                            </p>
                                        </td>
                                        <td class="text-right" style="width: 50px;">
                                            <input style="width: 50px;"class="text-left" type="number" name="txtMarkAdd" value="${param.txtMarkAdd}">
                                            <p class="text-center">   
                                                <font color="red">${error.getMarkError()}</font>
                                            </p>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td class="text-right">
                                            <button class="btn btn-sm btn-success" type="submit">
                                                <i class="fa fa-plus" aria-hidden="true"></i> 
                                            </button>
                                        </td>
                                        <td></td>
                                </form>
                                </tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                </tbody>
                            </table>
                            </tbody>
                        </table>


                        <h1 class="text-center">   
                            <font color="red">${requestScope.NOT_FOUND}</font>
                        </h1>


                        <form action="ManagerController" method="POST">
                            <input style="width: 500px;"type="text" name="txtQuestionName" value="${param.txtQuestionName}" placeholder="Search name">
                            <input type="submit" name="txtSearch" value="SearchQuestionName"><br> 
                        </form>

                        <form action="ManagerController" method="POST">
                            <select class="form-control ml-3" name="cbxSubjectSearch" style= "width: 120px">
                                <option>Subject</option>
                                <c:forEach var="subject" items="${listSubject}">
                                    <c:if test="${param.cbxSubjectSearch == subject}">
                                        <option selected="selected">${subject}</option>
                                    </c:if>
                                    <c:if test="${param.cbxSubjectSearch != subject}">
                                        <option>${subject}</option>

                                    </c:if>
                                </c:forEach>
                            </select>
                            <input type="submit" name="txtSearch" value="SearchSubject">
                        </form>

                        <form action="ManagerController" method="POST">
                            <select class="form-control ml-3" name="cbxStatusSearch" style= "width: 120px">
                                <option>Status</option>
                                <c:forEach var="status" items="${listStatus}">
                                    <c:if test="${param.cbxStatusSearch == status}">
                                        <option selected="selected">${status}</option>
                                    </c:if>
                                    <c:if test="${param.cbxStatusSearch != status}">
                                        <option>${status}</option>
                                    </c:if>
                                </c:forEach>
                            </select> 

                            <input type="submit" name="txtSearch" value="SearchStatus"> <br>
                        </form>
                        <c:if test="${list != null && not empty list}">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">Subject</th>
                                        <th scope="col">Question</th>
                                        <th scope="col">Answer content</th>
                                        <th scope="col" class="text-right">Mark</th>
                                        <th scope="col">Create Date</th>
                                        <th scope="col" class="text-left">Status</th>
                                        <th> </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="dto" items="${list}">
                                    <form action="UpdateController" method="POST">
                                        <tr>
                                            <c:set var="error_update" value="${requestScope.ERROR_QUESTION_UPDATE}"></c:set>

                                                <td>
                                                    <select class="form-control ml-3" name="cbxSubject" style= "width: 120px">
                                                    <c:forEach var="subject" items="${listSubject}">
                                                        <c:if test="${dto.subjectID == subject}">
                                                            <option selected="selected">${subject}</option>
                                                        </c:if>
                                                        <c:if test="${dto.subjectID != subject}">
                                                            <option>${subject}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td> 
                                                <input class="form-control" type="text" name="txtQuestionContent" value="${dto.question_content}">
                                                <c:if test="${dto.questionID == questionID}">
                                                    <p class="text-center">   
                                                        <font color="red">${error_update.getQuestion_contentError()}</font>
                                                    </p>
                                                </c:if>
                                            </td>
                                            <td>
                                                <input type="radio" name="rdoUpdate" value="${dto.answer_content1}"
                                                       <c:if test="${dto.answer_correct == dto.answer_content1}">checked="checked"</c:if>>
                                                <input style="margin-top: 5px;" class="form-control" type="text" name="txtAnswerContent1" value="${dto.answer_content1}">
                                                <c:if test="${dto.questionID == questionID}">
                                                    <p class="text-center">   
                                                        <font color="red">${error_update.getAnswer_content1Error()}</font>
                                                    </p>
                                                </c:if>
                                                <input type="radio" name="rdoUpdate" value="${dto.answer_content2}"<c:if test="${dto.answer_correct == dto.answer_content2}">checked="checked"</c:if>><input style="margin-top: 5px;" class="form-control" type="text" name="txtAnswerContent2" value="${dto.answer_content2}">
                                                <c:if test="${dto.questionID == questionID}">
                                                    <p class="text-center">   
                                                        <font color="red">${error_update.getAnswer_content2Error()}</font>
                                                    </p>
                                                </c:if>
                                                <input type="radio" name="rdoUpdate" value="${dto.answer_content3}"<c:if test="${dto.answer_correct == dto.answer_content3}">checked="checked"</c:if>><input style="margin-top: 5px;" class="form-control" type="text" name="txtAnswerContent3" value="${dto.answer_content3}">
                                                <c:if test="${dto.questionID == questionID}">
                                                    <p class="text-center">   
                                                        <font color="red">${error_update.getAnswer_content3Error()}</font>
                                                    </p>
                                                </c:if>
                                                <input type="radio" name="rdoUpdate" value="${dto.answer_content4}"<c:if test="${dto.answer_correct == dto.answer_content4}">checked="checked"</c:if>><input style="margin-top: 5px;" class="form-control" type="text" name="txtAnswerContent4" value="${dto.answer_content4}">
                                                <c:if test="${dto.questionID == questionID}">
                                                    <p class="text-center">   
                                                        <font color="red">${error_update.getAnswer_content4Error()}</font>
                                                    </p>
                                                </c:if>
                                            </td>
                                            <td class="text-right" style="width: 50px;">
                                                <input style="width: 50px;"class="text-left" type="number" name="txtMark" value="${dto.mark}">
                                                <c:if test="${dto.questionID == questionID}">
                                                    <p class="text-center">   
                                                        <font color="red">${error_update.getMarkError()}</font>
                                                    </p>
                                                </c:if>
                                            </td>
                                            <td>
                                                ${dto.createDate}
                                            </td>
                                            <td>
                                                <select class="form-control ml-3" name="cbxStatus" style= "width: 120px">
                                                    <c:forEach var="status" items="${listStatus}">
                                                        <c:if test="${dto.status == status}">
                                                            <option selected="selected">${status}</option>
                                                        </c:if>
                                                        <c:if test="${dto.status != status}">
                                                            <option>${status}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td class="text-right">
                                                <button class="btn btn-sm btn-success" type="submit">
                                                    <i class="fa fa-wrench" aria-hidden="true"></i> 
                                                </button>
                                                <input type="hidden" name="txtQuestionID" value="${dto.questionID}">
                                            </td>
                                    </form>
                                    <td class="text-right">
                                        <c:url var="delete" value="DeleteController">
                                            <c:param name="txtQuestionID" value="${dto.questionID}"></c:param>
                                        </c:url>
                                        <a href="${delete}">
                                            <button class="btn btn-sm btn-danger">
                                                <i class="fa fa-trash"></i> 
                                            </button>
                                        </a>
                                    </td>
                                    </tr>
                                </c:forEach>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                </tbody>
                            </table>
                            <div class="col-12">
                                <nav aria-label="...">
                                    <ul class="pagination">
                                        <c:forEach var="number" begin="1" end="${requestScope.NUMBER_PAGE}"> 
                                            <li class="page-item">
                                                <a class="page-link" href="ManagerController?cbxSubjectSearch=${param.cbxSubjectSearch}&cbxStatusSearch=${param.cbxStatusSearch}&txtQuestionName=${param.txtQuestionName}&txtSearch=${param.txtSearch}&txtIndex=${number}">${number}</a>
                                            </li>
                                        </c:forEach>                                  
                                    </ul>
                                </nav>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>


