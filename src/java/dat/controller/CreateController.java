/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.controller;

import dat.dao.QuestionsDAO;
import dat.dto.QuestionDTO;
import dat.dto.QuestionErrorDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author macbook
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {
    
    private static final String ERROR = "manager.jsp";
    private static final String SUCCESS = "ManagerController";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        boolean flag = true;
        try {
            String question_content = request.getParameter("txtQuestionContentAdd");
            String answer_correct = "";
            String answer_content1 = request.getParameter("txtAnswerContent1Add");
            String answer_content2 = request.getParameter("txtAnswerContent2Add");
            String answer_content3 = request.getParameter("txtAnswerContent3Add");
            String answer_content4 = request.getParameter("txtAnswerContent4Add");
            String tmpMark = request.getParameter("txtMarkAdd");
            float mark = -1;
            String subjectID = request.getParameter("cbxSubjectAdd");
            QuestionErrorDTO error = new QuestionErrorDTO();
            if (subjectID.equals("Subject")) {
                flag = false;
                error.setSubjectIDError("Please choice subject");
            }
            if (question_content.trim().isEmpty()) {
                flag = false;
                error.setQuestion_contentError("Question can not null");
            }
            if (answer_content1.trim().isEmpty()) {
                flag = false;
                error.setAnswer_content1Error("Answer can not null");
            }
            if (answer_content2.trim().isEmpty()) {
                flag = false;
                error.setAnswer_content2Error("Answer can not null");
            }
            if (answer_content3.trim().isEmpty()) {
                flag = false;
                error.setAnswer_content3Error("Answer can not null");
            }
            if (answer_content4.trim().isEmpty()) {
                flag = false;
                error.setAnswer_content4Error("Answer can not null");
            }
            if (tmpMark.trim().isEmpty()) {
                flag = false;
                error.setMarkError("Mark can not null");
            } else {
                mark = Float.parseFloat(tmpMark);
                if (mark <= 0) {
                    flag = false;
                    error.setMarkError("Mark > 0");
                }
            }
            if (answer_content1.equals(answer_content2)
                    || answer_content1.equals(answer_content3)
                    || answer_content1.equals(answer_content4)
                    || answer_content2.equals(answer_content3)
                    || answer_content2.equals(answer_content4)
                    || answer_content3.equals(answer_content4)) {
                request.setAttribute("ERROR", "The answer cannot be the same");
                flag = false;
            }
            if (flag) {
                String rdoAdd = request.getParameter("rdoAdd");
                if (rdoAdd.equals("answer1")) {
                    answer_correct = answer_content1;
                }
                if (rdoAdd.equals("answer2")) {
                    answer_correct = answer_content2;
                }
                if (rdoAdd.equals("answer3")) {
                    answer_correct = answer_content3;
                }
                if (rdoAdd.equals("answer4")) {
                    answer_correct = answer_content4;
                }
                QuestionsDAO dao = new QuestionsDAO();
                QuestionDTO dto = new QuestionDTO(question_content, answer_correct, answer_content1, answer_content2, answer_content3, answer_content4, mark, subjectID);
                int result = dao.addQuestion(dto);
                if (result != -1) {
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Add new has failed");
                }
            } else {
                request.setAttribute("ERROR_QUESTION", error);
                flag = false;
            }
            
        } catch (Exception e) {
            log("CreateController: " + e.getMessage());
        } finally {
            if (flag) {
                response.sendRedirect(url);
            } else {
                request.getRequestDispatcher(url).forward(request, response);
            }
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
